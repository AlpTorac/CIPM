package cipm.consistency.fluentapi.gen.rootapi.templates;

import cipm.consistency.fluentapi.gen.FluentAPIAbstractTemplate;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;
import cipm.consistency.fluentapi.gen.rootapi.FluentAPIRootAPIConstants;

public class FluentAPIRootAPINewMethodTemplate extends FluentAPIAbstractTemplate {
	private static final String fluentAPIRootAPINewXMethodName = "newX";
	private static final String fluentAPIRootAPINewMethodNameTemplate = "new%s";

	private static final String newXMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			// %s: Initialisation super type class name
			"return (%s)" + "this." + FluentAPIRootAPIConstants.getFluentAPIRootAPIGetInitialisationForMethodName()
					+ "(" + FluentAPIRootAPIConstants.getFluentAPIRootAPINewMethodEClassParameterName()
					+ ".getInstanceClass())");

	private static final String newXWithClassParamMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			// %s: Initialisation super type class name
			"return (%s)" + "this." + FluentAPIRootAPIConstants.getFluentAPIRootAPIGetInitialisationForMethodName()
					+ "(" + FluentAPIRootAPIConstants.getFluentAPIRootAPINewMethodClassParameterName() + ")");

	private static final String newXWithModifiableFeatsMethodBodyTemplate = FluentAPIMethodsUtil
			.joinLOC("return (%s) this." + FluentAPIRootAPIConstants.getFluentAPIRootAPIGetInitialisationForMethodName()
					+ "(%s.class)");

	private static final String newXWithOnlyOneModifiableSingleValuedFeatsMethodBodyTemplate = FluentAPIMethodsUtil
			.joinLOC("return (%s) ((%s) this."
					+ FluentAPIRootAPIConstants.getFluentAPIRootAPIGetInitialisationForMethodName()
					+ "(%s.class)).with%s("
					+ FluentAPIRootAPIConstants.getFluentAPIRootAPINewMethodFeatureValueParameterName()
					+ ").createNow()");

	private static final String newXWithOnlyOneModifiableManyValuedFeatsMethodBodyTemplate_singleValue = FluentAPIMethodsUtil
			.joinLOC("return (%s) ((%s) this."
					+ FluentAPIRootAPIConstants.getFluentAPIRootAPIGetInitialisationForMethodName()
					+ "(%s.class)).withAdded%s("
					+ FluentAPIRootAPIConstants.getFluentAPIRootAPINewMethodFeatureValueParameterName()
					+ ").createNow()");

	private static final String newXWithoutModifiableFeatsMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			"return (%s) ((%s) this." + FluentAPIRootAPIConstants.getFluentAPIRootAPIGetInitialisationForMethodName()
					+ "(%s.class)).createNow()");

	public static String renderTopLevelNewMethodName() {
		return fluentAPIRootAPINewXMethodName;
	}

	public static String renderNewMethodName(String elemToInitSimpleName) {
		return String.format(fluentAPIRootAPINewMethodNameTemplate, capitaliseFirstLetter(elemToInitSimpleName));
	}

	public static String renderNewMethodBody(String fullyQualifiedInitName) {
		return String.format(newXMethodBodyTemplate, fullyQualifiedInitName);
	}

	public static String renderNewMethodWithClassParamBody(String fullyQualifiedInitName) {
		return String.format(newXWithClassParamMethodBodyTemplate, fullyQualifiedInitName);
	}

	public static String renderNewMethodBody_ForModifiableFeats(String fullyQualifiedInitName,
			String fullyQualifiedElemToInitName) {
		return String.format(newXWithModifiableFeatsMethodBodyTemplate, fullyQualifiedInitName,
				fullyQualifiedElemToInitName);
	}

	public static String renderNewMethodBody_ForOnlyOneModifiableSingleValuedFeat(String fullyQualifiedInitName,
			String fullyQualifiedElemToInitName, String featureName) {
		return String.format(newXWithOnlyOneModifiableSingleValuedFeatsMethodBodyTemplate, fullyQualifiedElemToInitName,
				fullyQualifiedInitName, fullyQualifiedElemToInitName, capitaliseFirstLetter(featureName));
	}

	public static String renderNewMethodBody_ForOnlyOneModifiableManyValuedFeat(String fullyQualifiedInitName,
			String fullyQualifiedElemToInitName, String featureName) {
		return String.format(newXWithOnlyOneModifiableManyValuedFeatsMethodBodyTemplate_singleValue,
				fullyQualifiedElemToInitName, fullyQualifiedInitName, fullyQualifiedElemToInitName,
				capitaliseFirstLetter(featureName));
	}

	public static String renderNewMethodBody_ForWithoutModifiableFeats(String fullyQualifiedInitName,
			String fullyQualifiedElemToInitName) {
		return String.format(newXWithoutModifiableFeatsMethodBodyTemplate, fullyQualifiedElemToInitName,
				fullyQualifiedInitName, fullyQualifiedElemToInitName);
	}
}
