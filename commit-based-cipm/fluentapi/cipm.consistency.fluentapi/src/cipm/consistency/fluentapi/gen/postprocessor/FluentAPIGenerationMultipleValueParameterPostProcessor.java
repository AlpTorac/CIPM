package cipm.consistency.fluentapi.gen.postprocessor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.util.EcoreUtil;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationContext;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;

public class FluentAPIGenerationMultipleValueParameterPostProcessor implements FluentAPIGenerationPostProcessor {
	private static final Pattern methodNamePatternToOverload = Pattern.compile("with(?=Removed|Added).*");

	/**
	 * The method body template for method overloads with collections / lists /
	 * arrays of feature values as parameters.
	 */
	private static final String multipleValueMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			"for (var e : %s) this.%s(e)",
			// %s: Feat vals parameter name
			// %s: Singular method name
			"return this");

	private EParameter getArrayVersion(FluentAPIGenerationContext context, EParameter oldParam) {
		var param = FluentAPIGenerationUtil.generateArrayValuedEParameter(context, oldParam.getName(),
				oldParam.getEType());
		FluentAPIGenerationUtil.useDocumentationOf(param, oldParam);
		return param;
	}

	private EParameter getColVersion(FluentAPIGenerationContext context, EParameter oldParam) {
		var param = FluentAPIGenerationUtil.generateSingleValuedEParameter(oldParam.getName(),
				FluentAPIGenerationUtil.generateCollectionTypeParameter(context, oldParam.getEType()));
		FluentAPIGenerationUtil.useDocumentationOf(param, oldParam);
		return param;
	}

	private EOperation createOverloadingMultipleValueMethodFor(FluentAPIGenerationContext context,
			EOperation opToOverload, EParameter newParam) {
		var copier = new EcoreUtil.Copier();
		var overloadingOp = (EOperation) copier.copy(opToOverload);
		copier.copyReferences();

		// Adjust parameter
		overloadingOp.getEParameters().clear();
		overloadingOp.getEParameters().add(newParam);

		// Adjust method body
		FluentAPIGenerationUtil.addBody(overloadingOp,
				String.format(multipleValueMethodBodyTemplate, newParam.getName(), overloadingOp.getName()));
		FluentAPIGenerationUtil.useDocumentationOf(overloadingOp, opToOverload);

		return overloadingOp;
	}

	private boolean hasArrayOverload(EOperation op) {
		return op.getEContainingClass().getEOperations().stream()
				.anyMatch((opTwo) -> opTwo.getEParameters().size() == 1 && op.getEParameters().size() == 1
						&& op != opTwo && op.getName().equals(opTwo.getName()) && op.getEType().equals(opTwo.getEType())
						&& opTwo.getEParameters().get(0).getEGenericType().getEClassifier().getInstanceClass().isArray()
						&& op.getEParameters().get(0).getEType().getInstanceClass().equals(opTwo.getEParameters().get(0)
								.getEGenericType().getEClassifier().getInstanceClass().getComponentType()));
	}

	private boolean hasColOverload(EOperation op) {
		return op.getEContainingClass().getEOperations().stream()
				.anyMatch((opTwo) -> opTwo.getEParameters().size() == 1 && op.getEParameters().size() == 1
						&& op != opTwo && op.getName().equals(opTwo.getName()) && op.getEType().equals(opTwo.getEType())
						&& opTwo.getEParameters().get(0).getEGenericType().getEClassifier().getInstanceClass()
								.equals(Collection.class));
	}

	private List<EOperation> createOverloadingMethodsFor(FluentAPIGenerationContext context, EOperation opToOverload) {
		var ops = new ArrayList<EOperation>();
		if (!hasArrayOverload(opToOverload)) {
			ops.add(createOverloadingMultipleValueMethodFor(context, opToOverload,
					getArrayVersion(context, opToOverload.getEParameters().get(0))));
		}
		if (!hasColOverload(opToOverload)) {
			ops.add(createOverloadingMultipleValueMethodFor(context, opToOverload,
					getColVersion(context, opToOverload.getEParameters().get(0))));
		}
		return ops;
	}

	@Override
	public void apply(FluentAPIGenerationContext context) {
		var allEClss = new ArrayList<EClass>();
//		allEClss.add(context.getFluentAPIECls());
//		allEClss.add(context.getInitSuperECls());
		allEClss.addAll(context.getAllInitEClss());

		for (var eCls : allEClss) {
			// Only consider EOperations, which have a single parameter and whose return
			// type is their containing EClass
			var opsToOverload = eCls.getEOperations().stream()
					.filter((op) -> methodNamePatternToOverload.matcher(op.getName()).matches()
							&& op.getEParameters().size() == 1 && op.getEType().equals(op.getEContainingClass()))
					.collect(Collectors.toList());
			for (var op : opsToOverload) {
				op.getEContainingClass().getEOperations().addAll(createOverloadingMethodsFor(context, op));
			}
		}
	}
}
