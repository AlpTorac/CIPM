package cipm.consistency.fluentapi.gen.rootapi.templates;

import cipm.consistency.fluentapi.gen.FluentAPIAbstractTemplate;
import cipm.consistency.fluentapi.gen.FluentAPIConstants;
import cipm.consistency.fluentapi.gen.FluentAPIGeneralParameterGenerator;
import cipm.consistency.fluentapi.gen.methods.FluentAPIInitialisationStorage;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;
import cipm.consistency.fluentapi.gen.methods.FluentEObjectAPIMethods;
import cipm.consistency.fluentapi.gen.rootapi.FluentAPIRootAPIConstants;
import cipm.consistency.fluentapi.gen.superinit.FluentAPISuperInitialisationConstants;

public class FluentAPIRootAPIContinueMethodTemplate extends FluentAPIAbstractTemplate {
	// TODO Try to simplify marked method bodies

	private static final String fluentAPIRootAPIContinueMethodNameTemplate = "continue%s";
	private static final String fluentAPIRootAPIContinueMarkedMethodNameTemplate = "continueMarked%s";

	private static final String continueMethodBodyTemplate =
			// %s: Full Initialisation class name
			// %s: Initialised element class (statically, i.e. either via method parameter
			// or via .class)
			FluentAPIMethodsUtil
					.joinLOC("return (%s) " + FluentEObjectAPIMethods.class.getName() + ".continueElement(%s)");

	private static final String continueMarkedMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			"var markedElem = this." + FluentAPIRootAPIConstants.getFluentAPIRootAPIGetMarkedMethodName() + "("
					+ FluentAPIGeneralParameterGenerator.getFluentAPIMarkKeyParameterName() + ")",
			// %s: Init class name
			"return markedElem == null ? null : (%s) " + FluentAPIInitialisationStorage.class.getName()
					+ ".getOngoingInits().stream().filter((i) -> (("
					+ FluentAPIRootAPIConstants.getFluentAPIRootPackageName() + "."
					+ FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationClassName() + ") i).get"
					+ FluentAPISuperInitialisationConstants
							.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName()
					+ "() == markedElem).findFirst().get()");

	public static String renderContinueMethodBody(String fullyQualifiedInitClsName,
			String fullyQualifiedElemToInitCls) {
		return String.format(continueMethodBodyTemplate, fullyQualifiedInitClsName,
				getSerialisedClassObject(fullyQualifiedElemToInitCls));
	}

	public static String renderContinueMarkedMethodBody(String fullyQualifiedInitClsName) {
		return String.format(continueMarkedMethodBodyTemplate, fullyQualifiedInitClsName);
	}

	public static String renderContinueMethodNameForECls(String fullyQualifiedElemToInitCls) {
		return String.format(fluentAPIRootAPIContinueMethodNameTemplate,
				capitaliseFirstLetter(fullyQualifiedElemToInitCls));
	}

	public static String renderContinueMarkedMethodNameForECls(String fullyQualifiedElemToInitCls) {
		return String.format(fluentAPIRootAPIContinueMarkedMethodNameTemplate,
				capitaliseFirstLetter(fullyQualifiedElemToInitCls));
	}

	public static String renderTopLevelContinueMethodName() {
		return String.format(fluentAPIRootAPIContinueMethodNameTemplate, FluentAPIConstants.getTemplatePlaceholder());
	}

	public static String renderTopLevelContinueMarkedMethodName() {
		return String.format(fluentAPIRootAPIContinueMarkedMethodNameTemplate,
				FluentAPIConstants.getTemplatePlaceholder());
	}
}
