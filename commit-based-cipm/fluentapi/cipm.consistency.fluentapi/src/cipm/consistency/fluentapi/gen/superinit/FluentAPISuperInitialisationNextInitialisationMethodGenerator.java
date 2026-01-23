package cipm.consistency.fluentapi.gen.superinit;

import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.IFluentAPIMethodGenerator;
import cipm.consistency.fluentapi.gen.init.FluentAPIInitialisationConstants;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;
import cipm.consistency.fluentapi.gen.methods.FluentEObjectAPIMethods;

public class FluentAPISuperInitialisationNextInitialisationMethodGenerator implements IFluentAPIMethodGenerator {
	private static final String nextInitMethodSummary = "Returns the Initialisation instance of the same type that this API instance created after this one.";
	private static final String nextInitMethodDocumentation = FluentAPIGenerationUtil
			.appendSummaryToStart(nextInitMethodSummary) + "Returns the "
			+ FluentAPIInitialisationConstants.getFluentAPIInitialisationClassNameSuffix()
			+ " instance of the same type, which was created by this."
			+ FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationToAPIMethodName()
			+ "() after this one. Can be used to quickly swap between "
			+ FluentAPIInitialisationConstants.getFluentAPIInitialisationClassNameSuffix()
			+ " instances that were created consecutively.";

	private static final String nextInitMethodBodyTemplate = FluentAPIMethodsUtil
			// %s: Initialisation class
			// %s: Initialised EObject class
			.joinLOC("return (%s) " + FluentEObjectAPIMethods.class.getName() + "."
					+ FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationNextInitMethodName()
					+ "(this, this."
					+ FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationToAPIMethodName()
					+ "(), %s.class)");

	public EOperation getNextInitialisationMethodFor(EClass initEClass, EClass eobjEClass) {
		return FluentAPIGenerationUtil.generateEOperationWithBodyAndDocumentation(
				FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationNextInitMethodName(), initEClass,
				String.format(nextInitMethodBodyTemplate,
						FluentAPIGenerationUtil.getFullyQualifiedEClassName(initEClass),
						eobjEClass.getInstanceClass().getName()),
				nextInitMethodDocumentation);
	}

	@Override
	public Map<String, String> getMethodNamesToDescriptions() {
		return Map.of(FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationNextInitMethodName(),
				nextInitMethodSummary);
	}
}
