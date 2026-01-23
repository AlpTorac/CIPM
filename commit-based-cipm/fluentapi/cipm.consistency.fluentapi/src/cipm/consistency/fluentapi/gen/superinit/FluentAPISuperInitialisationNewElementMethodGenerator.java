package cipm.consistency.fluentapi.gen.superinit;

import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.IFluentAPIMethodGenerator;
import cipm.consistency.fluentapi.gen.init.FluentAPIInitialisationConstants;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;

public class FluentAPISuperInitialisationNewElementMethodGenerator implements IFluentAPIMethodGenerator {
	private static final String newElementOperationDocumentation = FluentAPIGenerationUtil.appendSummaryToStart(
			FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationNewElementMethodSummary())
			+ "Creates a minimal EObject instance, without modifying any of its features, and sets it as the current element (i.e. return value of this.get"
			+ FluentAPISuperInitialisationConstants
					.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName()
			+ "()) in concrete " + FluentAPIInitialisationConstants.getFluentAPIInitialisationClassNameSuffix()
			+ " classes. Does nothing in this class.";

	private static final String newElementMethodBody = FluentAPIMethodsUtil.joinLOC("return this");

	public EOperation generateNewElementMethod(EClass initSuperEClass) {
		return FluentAPIGenerationUtil.generateEOperationWithBodyAndDocumentation(
				FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationNewElementMethodName(),
				initSuperEClass, newElementMethodBody, newElementOperationDocumentation);
	}

	@Override
	public Map<String, String> getMethodNamesToDescriptions() {
		return Map.of(FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationNewElementMethodName(),
				FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationNewElementMethodSummary());
	}
}
