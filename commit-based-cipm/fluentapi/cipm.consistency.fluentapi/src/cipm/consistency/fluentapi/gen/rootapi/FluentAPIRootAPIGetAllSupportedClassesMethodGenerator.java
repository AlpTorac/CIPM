package cipm.consistency.fluentapi.gen.rootapi;

import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EcorePackage;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.ModelConstants;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;
import cipm.consistency.fluentapi.gen.methods.FluentEObjectAPIMethods;

public class FluentAPIRootAPIGetAllSupportedClassesMethodGenerator {
	// TODO Add documentation

	private static final String getAllSupportedClassesMethodBodyTemplate = FluentAPIMethodsUtil
			.joinLOC("return " + FluentEObjectAPIMethods.class.getName()
					+ ModelConstants.RootAPI.GetAllSupportedEClasses.NAME.call("this"));

	public EOperation generateGetAllSupportedClassesMethodGenerator() {
		var javaClassType = FluentAPIGenerationUtil
				.generateEGenericTypeWithClassifier(EcorePackage.Literals.EJAVA_CLASS);

		var eObjLowerBound = FluentAPIGenerationUtil.generateEGenericTypeWithBounds(null,
				FluentAPIGenerationUtil.generateEGenericTypeWithClassifier(EcorePackage.Literals.EOBJECT));
		FluentAPIGenerationUtil.addTypeArgument(javaClassType, eObjLowerBound);

		var eListType = FluentAPIGenerationUtil.generateEGenericTypeWithClassifier(EcorePackage.Literals.EE_LIST);
		FluentAPIGenerationUtil.addTypeArgument(eListType, javaClassType);

		var op = FluentAPIGenerationUtil.generateEOperation(ModelConstants.RootAPI.GetAllSupportedEClasses.NAME.get(),
				eListType);

		FluentAPIGenerationUtil.addBody(op, getAllSupportedClassesMethodBodyTemplate);

		return op;
	}
}
