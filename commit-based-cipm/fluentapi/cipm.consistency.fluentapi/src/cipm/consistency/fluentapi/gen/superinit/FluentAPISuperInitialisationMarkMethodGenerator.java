package cipm.consistency.fluentapi.gen.superinit;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EcorePackage;

import cipm.consistency.fluentapi.gen.FluentAPIDocumentationUtil;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.IFluentAPIMethodGenerator;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;
import cipm.consistency.fluentapi.gen.methods.mark.FluentAPIMarkExtension;
import cipm.consistency.fluentapi.gen.rootapi.FluentAPIRootAPIConstants;

public class FluentAPISuperInitialisationMarkMethodGenerator implements IFluentAPIMethodGenerator {
	private static final String unmarkMethodSummary = "Removes the given markKey's marking, does not modify the (formerly) marked object.";
	private static final String unmarkMethodDocumentation = FluentAPIDocumentationUtil
			.appendSummaryToStart(unmarkMethodSummary) + "Removes any associations between the given "
			+ FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationMarkKeyParameterName()
			+ " and its corresponding EObject obj. Doing so unmarks obj, meaning that "
			+ FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationMarkKeyParameterName()
			+ " can no longer be used to retrieve obj. Does nothing, if this API instance did not mark obj with markKey.";
	private static final String unmarkCurrentMethodBody = FluentAPIMethodsUtil.joinLOC(
			FluentAPIMarkExtension.class.getName() + ".unmark(this.get"
					+ FluentAPISuperInitialisationConstants
							.getCapitalisedFluentAPISuperInitialisationRootAPIReferenceName()
					+ "(), " + FluentAPIRootAPIConstants.getFluentAPIRootAPIMarkKeyParameterName() + ", this.get"
					+ FluentAPISuperInitialisationConstants
							.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName()
					+ "())",
			"return this");

	private static final String markMethodSummary = "Marks the object currently under construction with markKey, does not modify the object.";
	private static final String markMethodDocumentation = FluentAPIDocumentationUtil
			.appendSummaryToStart(markMethodSummary)
			+ "Associates this.get"
			+ FluentAPISuperInitialisationConstants
					.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName()
			+ "() with " + FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationMarkKeyParameterName()
			+ ". Doing so marks this.get"
			+ FluentAPISuperInitialisationConstants
					.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName()
			+ "(), meaning that using "
			+ FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationMarkKeyParameterName()
			+ " in mark-related operations will result in retrieving this.get" + FluentAPISuperInitialisationConstants
					.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName()
			+ "().";
	private static final String markMethodBodyTemplate = FluentAPIMethodsUtil
			// %s: Mark key parameter name
			.joinLOC(
					FluentAPIMarkExtension.class.getName() + ".mark(this.get"
							+ FluentAPISuperInitialisationConstants
									.getCapitalisedFluentAPISuperInitialisationRootAPIReferenceName()
							+ "(), %s, this.get"
							+ FluentAPISuperInitialisationConstants
									.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName()
							+ "())",
					"return this");

//	private static final String getMarkedMethodSummary = "Returns the object marked by this API instance with the given markKey.";
//	private static final String getMarkedMethodDocumentation = FluentAPIDocumentationUtil
//			.appendSummaryToStart(getMarkedMethodSummary) + "Returns the EObject obj associated with "
//			+ FluentAPIRootAPIConstants.getFluentAPIRootAPIMarkKeyParameterName() + ". Note that "
//			+ FluentAPIRootAPIConstants.getFluentAPIRootAPIMarkKeyParameterName()
//			+ " has to be the exact Object instance that was used to mark obj, in order for this method to successfully retrieve obj. Using another Object instance that is content-wise equal to "
//			+ FluentAPIRootAPIConstants.getFluentAPIRootAPIMarkKeyParameterName()
//			+ " will not work, as only the memory address of "
//			+ FluentAPIRootAPIConstants.getFluentAPIRootAPIMarkKeyParameterName() + " is relevant.";
//	private static final String getMarkedMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
//			// %s: Mark key parameter name
//			"return " + FluentAPIMarkExtension.class.getName() + ".getMarked(this.get"
//					+ FluentAPISuperInitialisationConstants
//							.getCapitalisedFluentAPISuperInitialisationRootAPIReferenceName()
//					+ "(), %s)");

	public List<EOperation> generateAllMarkMethods(EClass initEClass) {
		return List.of(generateUnmarkMethod(initEClass), generateMarkMethod(initEClass)
//				generateGetMarkedMethod()
		);
	}

	public EOperation generateUnmarkMethod(EClass initEClass) {
		var param = getMarkKeyParam();
		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationUnmarkMethodName(), initEClass);
		FluentAPIGenerationUtil.addBody(op, unmarkCurrentMethodBody);
		FluentAPIGenerationUtil.addDocumentation(op, unmarkMethodDocumentation);
		FluentAPIGenerationUtil.addEParameters(op, param);
		return op;
	}

	public EOperation generateMarkMethod(EClass initEClass) {
		var param = getMarkKeyParam();
		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationMarkMethodName(), initEClass);
		FluentAPIGenerationUtil.addBody(op, String.format(markMethodBodyTemplate, param.getName()));
		FluentAPIGenerationUtil.addDocumentation(op, markMethodDocumentation);
		FluentAPIGenerationUtil.addEParameters(op, param);
		return op;
	}

//	public EOperation generateGetMarkedMethod() {
//		var param = getMarkKeyParam();
//		var op = FluentAPIGenerationUtil.generateEOperation(
//				FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationGetMarkedMethodName(),
//				EcorePackage.Literals.EOBJECT);
//		FluentAPIGenerationUtil.addBody(op, String.format(getMarkedMethodBodyTemplate, param.getName()));
//		FluentAPIGenerationUtil.addDocumentation(op, getMarkedMethodDocumentation);
//		FluentAPIGenerationUtil.addEParameters(op, param);
//		return op;
//	}

	private EParameter getMarkKeyParam() {
		var param = FluentAPIGenerationUtil.generateSingleValuedEParameter(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIMarkKeyParameterName(),
				EcorePackage.Literals.EJAVA_OBJECT);
		FluentAPIGenerationUtil.addDocumentation(param,
				FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsMarkKeyDocumentation());
		return param;
	}

	@Override
	public Map<String, String> getMethodNamesToDescriptions() {
		return Map.of(FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationMarkMethodName(),
				markMethodSummary,
				FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationUnmarkMethodName(),
				unmarkMethodSummary
//				FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationGetMarkedMethodName(),
//				getMarkedMethodSummary
		);
	}
}
