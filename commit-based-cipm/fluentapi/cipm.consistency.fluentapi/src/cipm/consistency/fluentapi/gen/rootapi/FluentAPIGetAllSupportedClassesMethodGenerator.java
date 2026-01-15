package cipm.consistency.fluentapi.gen.rootapi;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EcorePackage;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;
import cipm.consistency.fluentapi.gen.methods.FluentEObjectAPIMethods;

public class FluentAPIGetAllSupportedClassesMethodGenerator {
	// TODO Add documentation

	private static final String methodName = "getAllSupportedClasses";

	private static final String methodBodyTemplate = FluentAPIMethodsUtil
			.joinLOC("return " + FluentEObjectAPIMethods.class.getName() + ".getAllSupportedClasses(this)");

	public EOperation generateGetAllSupportedClassesMethodGenerator(EClass rootAPIEClass) {
		return FluentAPIGenerationUtil.generateEOperationWithBody(methodName, EcorePackage.Literals.EE_LIST,
				methodBodyTemplate);
	}
}
