package cipm.consistency.fluentapi.gen.init;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.FluentAPIInitialisationConstants;
import cipm.consistency.fluentapi.gen.FluentAPIRootAPIConstants;
import cipm.consistency.fluentapi.gen.FluentAPISuperInitialisationConstants;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;

public class FluentAPISuperInitialisationCreateNowMethodGenerator {
	private static final String createNowMethodDocumentation = "Finalises the construction of this.get"
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
				FluentAPIInitialisationConstants.getFluentapiinitialisationcreatenowmethodname(), elemToInit,
				String.format(createNowMethodBodyTemplate, elemToInit.getInstanceClass().getName()),
				createNowMethodDocumentation);
	}

	private EOperation generateGenericCreateNowMethod(EClass elemToInit) {
		// TODO Clean up

		// Goal: <T> T createNowMethodTypeParamName(Class<T> createNowMethodParamName)

		var typeParam = EcoreFactory.eINSTANCE.createETypeParameter();
		typeParam.setName(FluentAPIInitialisationConstants.getFluentapiinitialisationcreatenowmethodtypeparamname());

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
		param.setName(FluentAPIInitialisationConstants.getFluentapiinitialisationcreatenowmethodparamname());
		param.setEGenericType(genericClassType);
		param.setLowerBound(1);
		param.setUpperBound(1);

		var op = FluentAPIGenerationUtil.generateEOperationWithBody(
				FluentAPIInitialisationConstants.getFluentapiinitialisationcreatenowmethodname(), null,
				String.format(createNowMethodBodyTemplate, typeParam.getName()));
		op.setEGenericType(genericParamTypeForOp);
		op.getETypeParameters().add(typeParam);
		op.getEParameters().add(param);

		return op;
	}
}
