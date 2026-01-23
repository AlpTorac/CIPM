package cipm.consistency.fluentapi.gen.rootapi;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;

/**
 * Separated from FluentAPIRootAPINewMethodGenerator, since the "createNewX"
 * methods cannot share their name with "newX" methods, due to Java limitations.
 * 
 * TODO Add commentary
 */
public class FluentAPIRootAPICreateNewMethodGenerator {
	// TODO Add documentation

	private static final String createNewXMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC("return (%s) this."
			+ FluentAPIRootAPIConstants.getFluentAPIRootAPIGetInitialisationForMethodName() + "(%s.class).createNow()");

	private static final String createNewXWithClassParamMethodBody = FluentAPIMethodsUtil.joinLOC("return ("
			+ FluentAPIRootAPIConstants.getFluentAPIRootAPICreateNewXWithClassParameterMethodTypeParameterName()
			+ ") this." + FluentAPIRootAPIConstants.getFluentAPIRootAPIGetInitialisationForMethodName() + "("
			+ FluentAPIRootAPIConstants.getFluentAPIRootAPICreateNewXWithClassParameterMethodParameterName()
			+ ").createNow()");

	public List<EOperation> generateAllCreateNewMethods(List<EClass> eObjEClss) {
		var ops = new ArrayList<EOperation>();

		ops.add(generateGenericCreateNewMethod());

		for (var eObjEClass : eObjEClss) {
			ops.add(generateCreateNewMethod(eObjEClass));
		}

		return ops;
	}

	private EOperation generateCreateNewMethod(EClass eObjEClass) {
		return FluentAPIGenerationUtil.generateEOperationWithBody(
				String.format(FluentAPIRootAPIConstants.getFluentAPIRootAPICreateNewXMethodNameTemplate(),
						eObjEClass.getInstanceClass().getSimpleName()),
				eObjEClass, String.format(createNewXMethodBodyTemplate, eObjEClass.getInstanceClass().getName(),
						eObjEClass.getInstanceClass().getName()));
	}

	private EOperation generateGenericCreateNewMethod() {
		// TODO Clean up

		// Goal: <T> T createNewX(Class<T> createNewXWithClassParamParamName)

		var typeParam = EcoreFactory.eINSTANCE.createETypeParameter();
		typeParam.setName(
				FluentAPIRootAPIConstants.getFluentAPIRootAPICreateNewXWithClassParameterMethodTypeParameterName());

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
		param.setName(FluentAPIRootAPIConstants.getFluentAPIRootAPICreateNewXWithClassParameterMethodParameterName());
		param.setEGenericType(genericClassType);
		param.setLowerBound(1);
		param.setUpperBound(1);

		var op = FluentAPIGenerationUtil.generateEOperationWithBody(
				FluentAPIRootAPIConstants.getFluentAPIRootAPICreateNewXWithClassParameterMethodName(), null,
				createNewXWithClassParamMethodBody);
		op.setEGenericType(genericParamTypeForOp);
		op.getETypeParameters().add(typeParam);
		op.getEParameters().add(param);

		return op;
	}
}
