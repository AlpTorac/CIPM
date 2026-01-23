package cipm.consistency.fluentapi.gen.superinit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EcoreFactory;
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

	private static final String createNowMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			"this." + FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationToAPIMethodName() + "()."
					+ FluentAPIRootAPIConstants.getFluentAPIRootAPIDropInitialisationMethodName() + "(this)",
			//
			"return (%s) this.get" + FluentAPISuperInitialisationConstants
					.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName() + "()");

	public List<EOperation> generateAllCreateNowMethods(EClass elemToInit) {
		var ops = new ArrayList<EOperation>();

		ops.add(generateCreateNowMethod(elemToInit));
		ops.add(generateGenericCreateNowMethod(elemToInit));

		return ops;
	}

	private EOperation generateCreateNowMethod(EClass elemToInit) {
		return FluentAPIGenerationUtil.generateEOperationWithBodyAndDocumentation(
				FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationCreateNowMethodName(), elemToInit,
				String.format(createNowMethodBodyTemplate, elemToInit.getInstanceClass().getName()),
				createNowMethodDocumentation);
	}

	private EOperation generateGenericCreateNowMethod(EClass elemToInit) {
		// TODO Clean up

		// Goal: <T> T createNowMethodTypeParamName(Class<T> createNowMethodParamName)

		var typeParam = EcoreFactory.eINSTANCE.createETypeParameter();
		typeParam.setName(FluentAPISuperInitialisationConstants
				.getFluentAPISuperInitialisationCreateNowMethodTypeParameterName());

		// Make sure to create 2 generic types, one for the method parameter (Class<T>)
		// and one for the return type of the method (T)

		var genericParamTypeForJavaClass = EcoreFactory.eINSTANCE.createEGenericType();
		genericParamTypeForJavaClass.setETypeParameter(typeParam);

		var genericClassType = EcoreFactory.eINSTANCE.createEGenericType();
		genericClassType.setEClassifier(EcorePackage.Literals.EJAVA_CLASS);
		genericClassType.getETypeArguments().add(genericParamTypeForJavaClass);

		var genericParamTypeForOp = EcoreFactory.eINSTANCE.createEGenericType();
		genericParamTypeForOp.setETypeParameter(typeParam);

		var param = EcoreFactory.eINSTANCE.createEParameter();
		param.setName(
				FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationCreateNowMethodParameterName());
		param.setEGenericType(genericClassType);
		param.setLowerBound(1);
		param.setUpperBound(1);

		var op = FluentAPIGenerationUtil.generateEOperationWithBody(
				FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationCreateNowMethodName(), null,
				String.format(createNowMethodBodyTemplate, typeParam.getName()));
		op.setEGenericType(genericParamTypeForOp);
		op.getETypeParameters().add(typeParam);
		op.getEParameters().add(param);

		return op;
	}

	@Override
	public Map<String, String> getMethodNamesToDescriptions() {
		return Map.of(FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationCreateNowMethodName(),
				createNowMethodSummary);
	}
}
