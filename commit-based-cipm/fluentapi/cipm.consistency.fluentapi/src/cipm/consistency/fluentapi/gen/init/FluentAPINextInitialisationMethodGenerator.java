package cipm.consistency.fluentapi.gen.init;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;

import cipm.consistency.fluentapi.gen.FluentAPIConstants;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;
import cipm.consistency.fluentapi.gen.methods.FluentEObjectAPIMethods;

public class FluentAPINextInitialisationMethodGenerator {
	private static final String nextInitMethodName = "getNextInit";
	private static final String nextInitMethodBodyTemplate = FluentAPIMethodsUtil
			// %s: Initialisation class
			// %s: Initialised EObject class
			.joinLOC("return (%s) " + FluentEObjectAPIMethods.class.getName()
					+ ".getNextInit(this, this.toAPI(), %s.class)");

	public EOperation getNextInitialisationMethodFor(EClass initEClass, EClass eobjEClass) {
		return FluentAPIGenerationUtil.generateEOperationWithBody(nextInitMethodName,
				FluentAPIConstants.getGenModelURL(), initEClass,
				String.format(nextInitMethodBodyTemplate,
						FluentAPIGenerationUtil.getFullyQualifiedEClassName(initEClass),
						eobjEClass.getInstanceClass().getName()));
	}
}
