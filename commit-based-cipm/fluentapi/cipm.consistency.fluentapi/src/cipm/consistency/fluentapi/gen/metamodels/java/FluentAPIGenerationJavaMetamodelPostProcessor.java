package cipm.consistency.fluentapi.gen.metamodels.java;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.emftext.language.java.classifiers.ClassifiersPackage;
import org.emftext.language.java.types.TypesPackage;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationContext;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;
import cipm.consistency.fluentapi.gen.postprocessor.FluentAPIGenerationPostProcessor;

public class FluentAPIGenerationJavaMetamodelPostProcessor implements FluentAPIGenerationPostProcessor {
	private static final Pattern methodNamePatternToOverload = Pattern.compile("with(?!Removed).*");

	private static final String typeReferenceParameterOverrideTemplate = "this.toAPI().newClassifierReference().withTarget(%s).createNow()";
	private static final String typeReferenceParameterOverrideParameterDocumentation = "The classifier instance, which will be referenced";

	private List<EOperation> createOverloadingMethodsFor(EOperation opToOverload) {
		var copier = new EcoreUtil.Copier();
		var overloadingOp = (EOperation) copier.copy(opToOverload);
		copier.copyReferences();

		// Adjust parameters

		var paramExprs = new ArrayList<String>();

		for (int i = 0; i < overloadingOp.getEParameters().size(); i++) {
			var currentParam = overloadingOp.getEParameters().get(i);
			if (currentParam.getEType().equals(TypesPackage.Literals.TYPE_REFERENCE)) {
				paramExprs.add(String.format(typeReferenceParameterOverrideTemplate, currentParam.getName()));
				currentParam.setEType(ClassifiersPackage.Literals.CLASSIFIER);

				// Add parameter documentation to clarify intent
				FluentAPIGenerationUtil.addDocumentation(currentParam,
						typeReferenceParameterOverrideParameterDocumentation);
			} else {
				paramExprs.add(currentParam.getName());
			}
		}

		// Adjust method body
		var serialisedParameters = String.join(",", paramExprs.toArray(String[]::new));
		FluentAPIGenerationUtil.addBody(overloadingOp, FluentAPIMethodsUtil
				.joinLOC("return this." + overloadingOp.getName() + "(" + serialisedParameters + ")"));

		return List.of(overloadingOp);
	}

	@Override
	public void apply(FluentAPIGenerationContext context) {
		var initEClss = context.getAllInitEClss();

		for (var initECls : initEClss) {
			var opsToOverload = initECls.getEOperations().stream()
					.filter((op) -> methodNamePatternToOverload.matcher(op.getName()).matches())
					.collect(Collectors.toList());
			for (var op : opsToOverload) {
				// Skip parameters of type EList, since overloading them results in type erasure
				// related issues
				if (op.getEParameters().stream().anyMatch((p) -> p.getEType() != null && !p.isMany()
						&& p.getEType().equals(TypesPackage.Literals.TYPE_REFERENCE))) {
					initECls.getEOperations().addAll(createOverloadingMethodsFor(op));
				}
			}
		}
	}
}
