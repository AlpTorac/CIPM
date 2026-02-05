package cipm.consistency.fluentapi.gen.superinit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EcorePackage;

import cipm.consistency.fluentapi.gen.FluentAPIDocumentationUtil;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.IFluentAPIMethodGenerator;
import cipm.consistency.fluentapi.gen.init.FluentAPIInitialisationConstants;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;
import cipm.consistency.fluentapi.gen.rootapi.FluentAPIRootAPIConstants;

public class FluentAPISuperInitialisationCreateNowMethodGenerator implements IFluentAPIMethodGenerator {

	private static final String createNowMethodSummary = "Finalises and returns the object under construction.";
	private static final String createNowMethodDocumentation = FluentAPIDocumentationUtil
			.appendSummaryToStart(createNowMethodSummary)
			+ "Finalises the construction of this.get"
			+ FluentAPISuperInitialisationConstants
					.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName()
			+ " and returns it. Drops this "
			+ FluentAPIInitialisationConstants.getFluentAPIInitialisationClassNameSuffix() + " instance from this."
			+ FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationToAPIMethodName()
			+ "(), meaning that this " + FluentAPIInitialisationConstants.getFluentAPIInitialisationClassNameSuffix()
			+ " instance will no longer be accessible from this."
			+ FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationToAPIMethodName() + "().";

	private static final String createNowMethodWithClassParameterAdditionalDocumentation = "EMF-based metamodels consider interfaces, which allow diamond structures in the type hierarchy of their implementors. To spare type casting in model construction, this method can be given a class parameter, to which the returned value will be cast.";
	private static final String createNowMethodWithClassParameterDocumentation = createNowMethodDocumentation
			+ FluentAPIDocumentationUtil.getDocParagraphSeparator()
			+ createNowMethodWithClassParameterAdditionalDocumentation;

	private static final String createNowMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			"this." + FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationToAPIMethodName() + "()."
					+ FluentAPIRootAPIConstants.getFluentAPIRootAPIDropInitialisationMethodName() + "(this)",
			// %s: Element class
			"return (%s) this.get" + FluentAPISuperInitialisationConstants
					.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName() + "()");

	public List<EOperation> generateAllCreateNowMethods(EClass elemToInit) {
		var ops = new ArrayList<EOperation>();

		ops.add(generateCreateNowMethod(elemToInit));
		ops.add(generateGenericCreateNowMethod(elemToInit));

		return ops;
	}

	private EOperation generateCreateNowMethod(EClass elemToInit) {
		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationCreateNowMethodName(), elemToInit);
		FluentAPIGenerationUtil.addBody(op,
				String.format(createNowMethodBodyTemplate, elemToInit.getInstanceClass().getName()));
		FluentAPIGenerationUtil.addDocumentation(op, createNowMethodDocumentation);
		return op;
	}

	private EOperation generateGenericCreateNowMethod(EClass elemToInit) {
		// TODO Clean up

		// Goal: <T> T createNowMethodTypeParamName(Class<T> createNowMethodParamName)

		var typeParam = FluentAPIGenerationUtil.generateETypeParameter(FluentAPISuperInitialisationConstants
				.getFluentAPISuperInitialisationCreateNowMethodTypeParameterName());

		// Make sure to create 2 generic types, one for the method parameter (Class<T>)
		// and one for the return type of the method (T)

		var genericParamTypeForJavaClass = FluentAPIGenerationUtil.generateEGenericTypeWithTypeParameter(typeParam);

		var genericClassType = FluentAPIGenerationUtil
				.generateEGenericTypeWithClassifier(EcorePackage.Literals.EJAVA_CLASS);
		FluentAPIGenerationUtil.addTypeArgument(genericClassType, genericParamTypeForJavaClass);

		var genericParamTypeForOp = FluentAPIGenerationUtil.generateEGenericTypeWithTypeParameter(typeParam);

		var param = FluentAPIGenerationUtil.generateSingleValuedEParameter(
				FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationCreateNowMethodParameterName(),
				genericClassType);

		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationCreateNowMethodName(),
				genericParamTypeForOp);
		FluentAPIGenerationUtil.addBody(op, String.format(createNowMethodBodyTemplate, typeParam.getName()));
		FluentAPIGenerationUtil.addTypeParameters(op, typeParam);
		FluentAPIGenerationUtil.addEParameters(op, param);
		FluentAPIGenerationUtil.addDocumentation(op, createNowMethodWithClassParameterDocumentation);

		return op;
	}

	@Override
	public Map<String, String> getMethodNamesToDescriptions() {
		return Map.of(FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationCreateNowMethodName(),
				createNowMethodSummary);
	}
}
