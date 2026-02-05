package cipm.consistency.fluentapi.gen.superinit;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;

import cipm.consistency.fluentapi.gen.FluentAPIDocumentationUtil;
import cipm.consistency.fluentapi.gen.FluentAPIGeneralParameterGenerator;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.IFluentAPIMethodGenerator;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;
import cipm.consistency.fluentapi.gen.methods.mark.FluentAPIMarkExtension;

public class FluentAPISuperInitialisationMarkMethodGenerator implements IFluentAPIMethodGenerator {
	private static final String unmarkMethodSummary = "Removes the given "
			+ FluentAPIGeneralParameterGenerator.getFluentAPIMarkKeyParameterName()
			+ "'s marking, does not modify the (formerly) marked object.";
	private static final String unmarkMethodDocumentation = FluentAPIDocumentationUtil
			.appendSummaryToStart(unmarkMethodSummary) + "Removes any associations between the given "
			+ FluentAPIGeneralParameterGenerator.getFluentAPIMarkKeyParameterName()
			+ " and its corresponding EObject obj. Doing so unmarks obj, meaning that "
			+ FluentAPIGeneralParameterGenerator.getFluentAPIMarkKeyParameterName()
			+ " can no longer be used to retrieve obj. Does nothing, if this API instance did not mark obj with "
			+ FluentAPIGeneralParameterGenerator.getFluentAPIMarkKeyParameterName() + ".";
	private static final String unmarkCurrentMethodBody = FluentAPIMethodsUtil.joinLOC(
			FluentAPIMarkExtension.class.getName() + ".unmark("
					+ FluentAPIGeneralParameterGenerator.getFluentAPIMarkKeyParameterName() + ", this.get"
					+ FluentAPISuperInitialisationConstants
							.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName()
					+ "())",
			"return this");

	private static final String markMethodSummary = "Marks the object currently under construction with "
			+ FluentAPIGeneralParameterGenerator.getFluentAPIMarkKeyParameterName() + ", does not modify the object.";
	private static final String markMethodDocumentation = FluentAPIDocumentationUtil
			.appendSummaryToStart(markMethodSummary)
			+ "Associates this.get"
			+ FluentAPISuperInitialisationConstants
					.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName()
			+ "() with " + FluentAPIGeneralParameterGenerator.getFluentAPIMarkKeyParameterName()
			+ ". Doing so marks this.get"
			+ FluentAPISuperInitialisationConstants
					.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName()
			+ "(), meaning that using " + FluentAPIGeneralParameterGenerator.getFluentAPIMarkKeyParameterName()
			+ " in mark-related operations will result in retrieving this.get" + FluentAPISuperInitialisationConstants
					.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName()
			+ "().";
	private static final String markMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			FluentAPIMarkExtension.class.getName() + ".mark("
					+ FluentAPIGeneralParameterGenerator.getFluentAPIMarkKeyParameterName() + ", this.get"
					+ FluentAPISuperInitialisationConstants
							.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName()
					+ "())",
			"return this");

	public List<EOperation> generateAllMarkMethods(EClass initEClass) {
		return List.of(generateUnmarkMethod(initEClass), generateMarkMethod(initEClass));
	}

	private EOperation generateUnmarkMethod(EClass initEClass) {
		var param = FluentAPIGeneralParameterGenerator.getMarkKeyParam();
		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationUnmarkMethodName(), initEClass);
		FluentAPIGenerationUtil.addBody(op, unmarkCurrentMethodBody);
		FluentAPIGenerationUtil.addDocumentation(op, unmarkMethodDocumentation);
		FluentAPIGenerationUtil.addEParameters(op, param);
		return op;
	}

	private EOperation generateMarkMethod(EClass initEClass) {
		var param = FluentAPIGeneralParameterGenerator.getMarkKeyParam();
		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationMarkMethodName(), initEClass);
		FluentAPIGenerationUtil.addBody(op, markMethodBodyTemplate);
		FluentAPIGenerationUtil.addDocumentation(op, markMethodDocumentation);
		FluentAPIGenerationUtil.addEParameters(op, param);
		return op;
	}

	@Override
	public Map<String, String> getMethodNamesToDescriptions() {
		return Map.of(FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationMarkMethodName(),
				markMethodSummary,
				FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationUnmarkMethodName(),
				unmarkMethodSummary);
	}
}
