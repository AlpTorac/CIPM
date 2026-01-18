package cipm.consistency.fluentapi.gen.init;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;

public class FluentAPICreateNowMethodGenerator {
	private static final String createNowMethodTypeParamName = "T";
	private static final String createNowMethodParamName = "returnTypeCls";

	private static final String createNowMethodName = "createNow";
	private static final String createNowMethodDocumentation = "Finalises the initialisation and returns this.getCurrentElement(). Drops this initialisation instance from this.toAPI(), meaning that this initialisation instance will no longer be accessible from this.toAPI().";

	private static final String createNowMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			"this.toAPI().dropInitialisation(this)",
			//
			"return (%s) this.getCurrentElement()");

	public List<EOperation> generateAllCreateNowMethods(EClass elemToInit) {
		var ops = new ArrayList<EOperation>();

		ops.add(generateCreateNowMethod(elemToInit));
		ops.add(generateGenericCreateNowMethod(elemToInit));

		return ops;
	}

	private EOperation generateCreateNowMethod(EClass elemToInit) {
		return FluentAPIGenerationUtil.generateEOperationWithBodyAndDocumentation(createNowMethodName, elemToInit,
				String.format(createNowMethodBodyTemplate, elemToInit.getInstanceClass().getName()),
				createNowMethodDocumentation);
	}

	private EOperation generateGenericCreateNowMethod(EClass elemToInit) {
		// TODO Clean up

		// Goal: <T> T createNowMethodTypeParamName(Class<T> createNowMethodParamName)

		var typeParam = EcoreFactory.eINSTANCE.createETypeParameter();
		typeParam.setName(createNowMethodTypeParamName);

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
		param.setName(createNowMethodParamName);
		param.setEGenericType(genericClassType);
		param.setLowerBound(1);
		param.setUpperBound(1);

		var op = FluentAPIGenerationUtil.generateEOperationWithBody(createNowMethodName, null,
				String.format(createNowMethodBodyTemplate, typeParam.getName()));
		op.setEGenericType(genericParamTypeForOp);
		op.getETypeParameters().add(typeParam);
		op.getEParameters().add(param);

		return op;
	}

	public static String getCreateNowMethodName() {
		return createNowMethodName;
	}
}
