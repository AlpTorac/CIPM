package cipm.consistency.fluentapi.gen.rootapi;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EcorePackage;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.FluentAPITargetMetamodelPackageProvider;
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

	public List<EOperation> generateAllCreateNewMethods(
			FluentAPITargetMetamodelPackageProvider targetMetamodelPackageProvider) {
		var eObjEClss = targetMetamodelPackageProvider.getAllTargetMetamodelConcreteEClasses();
		var ops = new ArrayList<EOperation>();

		ops.add(generateGenericCreateNewMethod());

		for (var eObjEClass : eObjEClss) {
			ops.add(generateCreateNewMethod(eObjEClass));
		}

		return ops;
	}

	private EOperation generateCreateNewMethod(EClass eObjEClass) {
		var op = FluentAPIGenerationUtil.generateEOperation(
				String.format(FluentAPIRootAPIConstants.getFluentAPIRootAPICreateNewXMethodNameTemplate(),
						eObjEClass.getInstanceClass().getSimpleName()),
				eObjEClass);
		FluentAPIGenerationUtil.addBody(op, String.format(createNewXMethodBodyTemplate,
				eObjEClass.getInstanceClass().getName(), eObjEClass.getInstanceClass().getName()));
		return op;
	}

	private EOperation generateGenericCreateNewMethod() {
		// Goal: <T> T createNewX(Class<T> createNewXWithClassParamParamName)

		var typeParam = FluentAPIGenerationUtil.generateETypeParameter(
				FluentAPIRootAPIConstants.getFluentAPIRootAPICreateNewXWithClassParameterMethodTypeParameterName());

		// Make sure to create 2 generic types, one for the method parameter (Class<T>)
		// and one for the return type of the method (T)

		// "<T>" in "<T> T createNewX..."
		var methodTypeParam = FluentAPIGenerationUtil.generateEGenericTypeWithTypeParameter(typeParam);

		// "T" in "Class<T> ..."
		var methodParamTypeArgument = FluentAPIGenerationUtil.generateEGenericTypeWithTypeParameter(typeParam);

		// "Class<T>" in "Class<T> createNewXWithClassParamParamName"
		var methodParamType = FluentAPIGenerationUtil
				.generateEGenericTypeWithClassifier(EcorePackage.Literals.EJAVA_CLASS);
		FluentAPIGenerationUtil.addTypeArgument(methodParamType, methodParamTypeArgument);

		// "createNewXWithClassParamParamName" in "Class<T>
		// createNewXWithClassParamParamName"
		var methodParam = FluentAPIGenerationUtil.generateSingleValuedEParameter(
				FluentAPIRootAPIConstants.getFluentAPIRootAPICreateNewXWithClassParameterMethodParameterName());
		methodParam.setEGenericType(methodParamType);

		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIRootAPIConstants.getFluentAPIRootAPICreateNewXWithClassParameterMethodName());
		FluentAPIGenerationUtil.addBody(op, createNewXWithClassParamMethodBody);
		op.setEGenericType(methodTypeParam);
		op.getETypeParameters().add(typeParam);
		op.getEParameters().add(methodParam);
		return op;
	}
}
