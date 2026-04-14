package cipm.consistency.fluentapi.gen.init;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.FluentAPIInitialisationConstants;
import cipm.consistency.fluentapi.gen.FluentAPISuperInitialisationConstants;
import cipm.consistency.fluentapi.gen.FluentAPITargetMetamodelPackageProvider;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;
import cipm.consistency.fluentapi.gen.methods.FluentEObjectAPIMethods;

public class FluentAPISuperInitialisationPreviousInitialisationMethodGenerator {
	private static final String previousInitMethodDocumentation = "Returns the "
			+ FluentAPIInitialisationConstants.getFluentAPIInitialisationClassNameSuffix()
			+ " instance of the same type, which was created by this."
			+ FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationToAPIMethodName()
			+ "() before this one. Can be used to quickly swap between "
			+ FluentAPIInitialisationConstants.getFluentAPIInitialisationClassNameSuffix()
			+ " instances that were created consecutively.";
	private static final String previousInitMethodBodyTemplate = FluentAPIMethodsUtil
			// %s: Initialisation class
			// %s: Initialised EObject class
			.joinLOC(
					"return (%s) " + FluentEObjectAPIMethods.class.getName() + "."
							+ FluentAPISuperInitialisationConstants
									.getFluentAPISuperInitialisationPreviousInitMethodName()
							+ "(this, this."
							+ FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationToAPIMethodName()
							+ "(), %s.class)");

	public EOperation getPreviousInitialisationMethodFor(EClass initEClass, EClass eobjEClass, FluentAPITargetMetamodelPackageProvider provider) {
		return FluentAPIGenerationUtil.generateEOperationWithBodyAndDocumentation(
				FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationPreviousInitMethodName(),
				initEClass,
				String.format(previousInitMethodBodyTemplate,
						FluentAPIGenerationUtil.getFullyQualifiedEClassName(initEClass),
						provider.getFullyQualifiedClassNameFor(eobjEClass)),
				previousInitMethodDocumentation);
	}
}
