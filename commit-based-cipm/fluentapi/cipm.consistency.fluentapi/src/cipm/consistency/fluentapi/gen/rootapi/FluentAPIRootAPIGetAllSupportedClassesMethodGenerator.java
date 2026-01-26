package cipm.consistency.fluentapi.gen.rootapi;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EcorePackage;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;
import cipm.consistency.fluentapi.gen.methods.FluentEObjectAPIMethods;

public class FluentAPIRootAPIGetAllSupportedClassesMethodGenerator {
	// TODO Add documentation

	private static final String getAllSupportedClassesMethodBodyTemplate = FluentAPIMethodsUtil
			.joinLOC("return " + FluentEObjectAPIMethods.class.getName() + ".getAllSupportedClasses(this)");

	public EOperation generateGetAllSupportedClassesMethodGenerator(EClass rootAPIEClass) {
		var javaClassType = FluentAPIGenerationUtil
				.generateEGenericTypeWithClassifier(EcorePackage.Literals.EJAVA_CLASS);

		var eObjLowerBound = FluentAPIGenerationUtil.generateEGenericTypeWithBounds(null,
				FluentAPIGenerationUtil.generateEGenericTypeWithClassifier(EcorePackage.Literals.EOBJECT));
		FluentAPIGenerationUtil.addTypeArgument(javaClassType, eObjLowerBound);

		var eListType = FluentAPIGenerationUtil.generateEGenericTypeWithClassifier(EcorePackage.Literals.EE_LIST);
		FluentAPIGenerationUtil.addTypeArgument(eListType, javaClassType);

		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIGetAllSupportedClassesMethodName(), eListType);

		FluentAPIGenerationUtil.addBody(op, getAllSupportedClassesMethodBodyTemplate);

		return op;
	}
}
