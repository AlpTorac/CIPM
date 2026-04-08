package cipm.consistency.fluentapi.gen.rootapi;

import java.util.Map;

import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EcorePackage;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.IFluentAPIMethodGenerator;
import cipm.consistency.fluentapi.gen.ModelConstants;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;
import cipm.consistency.fluentapi.gen.methods.FluentEObjectAPIMethods;

public class FluentAPIRootAPIGetAllSupportedClassesMethodGenerator implements IFluentAPIMethodGenerator {
	// TODO Add documentation

	// TODO Do not use ModelConstants here, since it cannot control the name of the
	// method
	private static final String getAllSupportedClassesMethodBodyTemplate = FluentAPIMethodsUtil
			.joinLOC("return " + FluentEObjectAPIMethods.class.getName()
					+ ModelConstants.FluentAPI.GetAllSupportedEClasses.NAME.call("this"));

	public EOperation generateGetAllSupportedClassesMethodGenerator() {
		var javaClassType = FluentAPIGenerationUtil
				.generateEGenericTypeWithClassifier(EcorePackage.Literals.EJAVA_CLASS);

		var eObjLowerBound = FluentAPIGenerationUtil.generateEGenericTypeWithBounds(null,
				FluentAPIGenerationUtil.generateEGenericTypeWithClassifier(EcorePackage.Literals.EOBJECT));
		FluentAPIGenerationUtil.addTypeArgument(javaClassType, eObjLowerBound);

		var eListType = FluentAPIGenerationUtil.generateEGenericTypeWithClassifier(EcorePackage.Literals.EE_LIST);
		FluentAPIGenerationUtil.addTypeArgument(eListType, javaClassType);

		var op = FluentAPIGenerationUtil.generateEOperation(ModelConstants.FluentAPI.GetAllSupportedEClasses.NAME.get(),
				eListType);

		FluentAPIGenerationUtil.addBody(op, getAllSupportedClassesMethodBodyTemplate);
		FluentAPIGenerationUtil.addDocumentation(op, ModelConstants.FluentAPI.GetAllSupportedEClasses.SUMMARY.get());

		return op;
	}

	@Override
	public Map<String, String> getMethodNamesToDescriptions() {
		return Map.of(ModelConstants.FluentAPI.GetAllSupportedEClasses.NAME.get(),
				ModelConstants.FluentAPI.GetAllSupportedEClasses.SUMMARY.get());
	}
}
