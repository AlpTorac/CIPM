package cipm.consistency.fluentapi.gen.superinit;

import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;

import cipm.consistency.fluentapi.gen.FluentAPIDocumentationUtil;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.IFluentAPIMethodGenerator;
import cipm.consistency.fluentapi.gen.init.FluentAPIInitialisationConstants;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;
import cipm.consistency.fluentapi.gen.methods.FluentEObjectAPIMethods;

public class FluentAPISuperInitialisationPreviousInitialisationMethodGenerator implements IFluentAPIMethodGenerator {
	private static final String previousInitMethodSummary = "Returns the Initialisation instance of the same type that this API instance created before this one.";
	private static final String previousInitMethodDocumentation = FluentAPIDocumentationUtil
			.appendSummaryToStart(previousInitMethodSummary) + "Returns the "
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

	public EOperation getPreviousInitialisationMethodFor(EClass initEClass, EClass eobjEClass) {
		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationPreviousInitMethodName(),
				initEClass);
		FluentAPIGenerationUtil.addBody(op,
				String.format(previousInitMethodBodyTemplate,
						FluentAPIGenerationUtil.getFullyQualifiedEClassName(initEClass),
						eobjEClass.getInstanceClass().getName()));
		FluentAPIGenerationUtil.addDocumentation(op, previousInitMethodDocumentation);
		return op;
	}

	@Override
	public Map<String, String> getMethodNamesToDescriptions() {
		return Map.of(FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationPreviousInitMethodName(),
				previousInitMethodSummary);
	}
}
