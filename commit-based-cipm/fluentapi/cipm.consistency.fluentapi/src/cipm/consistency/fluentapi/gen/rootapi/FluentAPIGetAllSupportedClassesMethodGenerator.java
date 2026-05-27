package cipm.consistency.fluentapi.gen.rootapi;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EcorePackage;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.FluentAPIRootAPIConstants;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;
import cipm.consistency.fluentapi.gen.methods.FluentEObjectAPIMethods;

public class FluentAPIGetAllSupportedClassesMethodGenerator {
	// TODO Add documentation

	private static final String getAllSupportedClassesMethodTemplate = FluentAPIMethodsUtil
			.joinLOC("return " + FluentEObjectAPIMethods.class.getName() + ".getAllSupportedClasses(this)");

	public EOperation generateGetAllSupportedClassesMethodGenerator(EClass rootAPIEClass) {
		// TODO Use generic parameter
		return FluentAPIGenerationUtil.generateEOperationWithBody(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIGetAllSupportedClassesMethodName(),
				EcorePackage.Literals.EE_LIST, getAllSupportedClassesMethodTemplate);
	}
}
