package cipm.consistency.fluentapi.gen.init;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;
import cipm.consistency.fluentapi.gen.methods.FluentEObjectAPIMethods;

public class FluentAPINextInitialisationMethodGenerator {
	private static final String nextInitMethodName = "getNextInit";
	private static final String nextInitMethodDocumentation = "Returns the initialisation instance of the same type, which was created by this.toAPI() after this one. Can be used to quickly swap between initialisation instances that were created consecutively.";

	private static final String nextInitMethodBodyTemplate = FluentAPIMethodsUtil
			// %s: Initialisation class
			// %s: Initialised EObject class
			.joinLOC("return (%s) " + FluentEObjectAPIMethods.class.getName()
					+ ".getNextInit(this, this.toAPI(), %s.class)");

	public EOperation getNextInitialisationMethodFor(EClass initEClass, EClass eobjEClass) {
		return FluentAPIGenerationUtil.generateEOperationWithBodyAndDocumentation(nextInitMethodName, initEClass,
				String.format(nextInitMethodBodyTemplate,
						FluentAPIGenerationUtil.getFullyQualifiedEClassName(initEClass),
						eobjEClass.getInstanceClass().getName()),
				nextInitMethodDocumentation);
	}
}
