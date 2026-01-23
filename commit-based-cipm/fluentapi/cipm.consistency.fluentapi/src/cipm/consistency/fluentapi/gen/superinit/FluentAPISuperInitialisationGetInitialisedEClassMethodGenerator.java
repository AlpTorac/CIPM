package cipm.consistency.fluentapi.gen.superinit;

import java.util.Map;

import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EcorePackage;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.IFluentAPIMethodGenerator;
import cipm.consistency.fluentapi.gen.init.FluentAPIInitialisationConstants;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;

public class FluentAPISuperInitialisationGetInitialisedEClassMethodGenerator implements IFluentAPIMethodGenerator {
	private static final String getInitialisedEClassSummary = "Returns the targeted EClass.";
	private static final String getInitialisedEClassDocumentation = FluentAPIGenerationUtil
			.appendSummaryToStart(getInitialisedEClassSummary) + "Returns the EClass, which this "
			+ FluentAPIInitialisationConstants.getFluentAPIInitialisationClassNameSuffix() + " instance targets.";

	private static final String getInitialisedEClassMethodBody = FluentAPIMethodsUtil.joinLOC(
			"return this." + FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationNewElementMethodName()
					+ "().eClass()");

	public EOperation generateGetInitialisedEClassMethod() {
		return FluentAPIGenerationUtil.generateEOperationWithBodyAndDocumentation(
				FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationGetInitialisedEClassMethodName(),
				EcorePackage.Literals.ECLASS, getInitialisedEClassMethodBody, getInitialisedEClassDocumentation);
	}

	@Override
	public Map<String, String> getMethodNamesToDescriptions() {
		return Map.of(
				FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationGetInitialisedEClassMethodName(),
				getInitialisedEClassSummary);
	}
}
