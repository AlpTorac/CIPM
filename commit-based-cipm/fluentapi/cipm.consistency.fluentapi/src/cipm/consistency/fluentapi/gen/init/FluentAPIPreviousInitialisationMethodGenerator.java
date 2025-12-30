package cipm.consistency.fluentapi.gen.init;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;
import cipm.consistency.fluentapi.gen.methods.FluentEObjectAPIMethods;

public class FluentAPIPreviousInitialisationMethodGenerator {
	private static final String previousInitMethodName = "getPreviousInit";
	private static final String previousInitMethodDocumentation = "Returns the initialisation instance of the same type, which was created by this.toAPI() before this one. Can be used to quickly swap between initialisation instances that were created consecutively.";
	private static final String previousInitMethodBodyTemplate = FluentAPIMethodsUtil
			// %s: Initialisation class
			// %s: Initialised EObject class
			.joinLOC("return (%s) " + FluentEObjectAPIMethods.class.getName()
					+ ".getPreviousInit(this, this.toAPI(), %s.class)");

	public EOperation getPreviousInitialisationMethodFor(EClass initEClass, EClass eobjEClass) {
		return FluentAPIGenerationUtil.generateEOperationWithBodyAndDocumentation(previousInitMethodName, initEClass,
				String.format(previousInitMethodBodyTemplate,
						FluentAPIGenerationUtil.getFullyQualifiedEClassName(initEClass),
						eobjEClass.getInstanceClass().getName()),
				previousInitMethodDocumentation);
	}
}
