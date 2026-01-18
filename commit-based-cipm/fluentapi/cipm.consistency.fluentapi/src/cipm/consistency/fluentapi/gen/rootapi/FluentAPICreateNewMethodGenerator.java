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
public class FluentAPICreateNewMethodGenerator {
	// TODO Add documentation

	private static final String createNewXMethodNameTemplate = "createNew%s";

	private static final String createNewXMethodBodyTemplate = FluentAPIMethodsUtil
			.joinLOC("return (%s) this.getInitialisationForX(%s.class).createNow()");

	private static final String createNewXWithClassParamMethodName = "createNewX";
	private static final String createNewXWithClassParamTypeParamName = "T";
	private static final String createNewXWithClassParamParamName = "eObjCls";

	private static final String createNewXWithClassParamMethodBodyTemplate = FluentAPIMethodsUtil
			.joinLOC("return (%s) this.getInitialisationForX(%s).createNow()");

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
				String.format(createNewXMethodNameTemplate, eObjEClass.getInstanceClass().getSimpleName()), eObjEClass,
				String.format(createNewXMethodBodyTemplate, eObjEClass.getInstanceClass().getName(),
						eObjEClass.getInstanceClass().getName()));
	}

	private EOperation generateGenericCreateNewMethod() {
		// TODO Clean up

		// Goal: <T> T createNewX(Class<T> createNewXWithClassParamParamName)

		var typeParam = EcoreFactory.eINSTANCE.createETypeParameter();
		typeParam.setName(createNewXWithClassParamTypeParamName);

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
		param.setName(createNewXWithClassParamParamName);
		param.setEGenericType(genericClassType);
		param.setLowerBound(1);
		param.setUpperBound(1);

		var op = FluentAPIGenerationUtil.generateEOperationWithBody(createNewXWithClassParamMethodName, null,
				String.format(createNewXWithClassParamMethodBodyTemplate, typeParam.getName(), param.getName()));
		op.setEGenericType(genericParamTypeForOp);
		op.getETypeParameters().add(typeParam);
		op.getEParameters().add(param);

		return op;
	}
}
