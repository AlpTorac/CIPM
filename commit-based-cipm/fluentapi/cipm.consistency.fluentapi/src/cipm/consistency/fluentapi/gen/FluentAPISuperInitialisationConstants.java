package cipm.consistency.fluentapi.gen;

import org.apache.commons.lang.StringUtils;

public final class FluentAPISuperInitialisationConstants {
	/*
	 * EClass
	 */
	/**
	 * Named this way to make sure that the generated EClasses do not have the same
	 * name
	 */
	private static final String fluentAPISuperInitialisationClassName = "FluentAPISuperInitialisation";

	/*
	 * EReferences
	 */
	private static final String fluentAPISuperInitialisationRootAPIReferenceName = "rootAPI";
	private static final String fluentAPISuperInitialisationCurrentElementReferenceName = "currentElement";

	/*
	 * EOperations
	 */

	/*
	 * toAPI
	 */
	private static final String fluentAPISuperInitialisationToAPIMethodName = "toAPI";

	/*
	 * drop
	 */
	private static final String fluentAPISuperInitialisationDropMethodName = "drop";

	/*
	 * getInitialisedEClass
	 */
	private static final String fluentAPISuperInitialisationGetInitialisedEClassMethodName = "getInitialisedEClass";

	/*
	 * newElement
	 */
	private static final String fluentAPISuperInitialisationNewElementMethodName = "newElement";

	/*
	 * mark
	 */
	private static final String fluentAPISuperInitialisationMarkMethodName = "markCurrent";

	/*
	 * unmark
	 */
	private static final String fluentAPISuperInitialisationUnmarkMethodName = FluentAPIRootAPIConstants
			.getFluentAPIRootAPIUnmarkMethodName();

	/*
	 * getMarked
	 */
	private static final String fluentAPISuperInitialisationGetMarkedMethodName = FluentAPIRootAPIConstants
			.getFluentAPIRootAPIGetMarkedMethodName();

	/*
	 * reset
	 */
	private static final String fluentAPISuperInitialisationResetMethodNameTemplate = "reset";

	/*
	 * getPreviousInit
	 */
	private static final String fluentAPISuperInitialisationPreviousInitMethodName = "getPreviousInit";

	/*
	 * getNextInit
	 */
	private static final String fluentAPISuperInitialisationNextInitMethodName = "getNextInit";

	/*
	 * EParameter
	 */
	private static final String fluentAPISuperInitialisationMarkKeyParameterName = FluentAPIRootAPIConstants
			.getFluentAPIRootAPIMarkKeyParameterName();

	public static String getFluentAPISuperInitialisationNextInitMethodName() {
		return fluentAPISuperInitialisationNextInitMethodName;
	}

	public static String getFluentAPISuperInitialisationPreviousInitMethodName() {
		return fluentAPISuperInitialisationPreviousInitMethodName;
	}

	public static String getFluentAPISuperInitialisationResetMethodNameTemplate() {
		return fluentAPISuperInitialisationResetMethodNameTemplate;
	}

	public static String getFluentAPISuperInitialisationUnmarkMethodName() {
		return fluentAPISuperInitialisationUnmarkMethodName;
	}

	public static String getFluentAPISuperInitialisationMarkKeyParameterName() {
		return fluentAPISuperInitialisationMarkKeyParameterName;
	}

	public static String getFluentAPISuperInitialisationGetMarkedMethodName() {
		return fluentAPISuperInitialisationGetMarkedMethodName;
	}

	public static String getFluentAPISuperInitialisationMarkMethodName() {
		return fluentAPISuperInitialisationMarkMethodName;
	}

	public static String getFluentAPISuperInitialisationNewElementMethodName() {
		return fluentAPISuperInitialisationNewElementMethodName;
	}

	public static String getFluentAPISuperInitialisationGetInitialisedEClassMethodName() {
		return fluentAPISuperInitialisationGetInitialisedEClassMethodName;
	}

	public static String getFluentAPISuperInitialisationDropMethodName() {
		return fluentAPISuperInitialisationDropMethodName;
	}

	public static String getFluentAPISuperInitialisationToAPIMethodName() {
		return fluentAPISuperInitialisationToAPIMethodName;
	}

	public static String getFluentAPISuperInitialisationRootAPIReferenceName() {
		return fluentAPISuperInitialisationRootAPIReferenceName;
	}

	public static String getCapitalisedFluentAPISuperInitialisationRootAPIReferenceName() {
		return StringUtils.capitalize(fluentAPISuperInitialisationRootAPIReferenceName);
	}

	public static String getFluentAPISuperInitialisationCurrentElementReferenceName() {
		return fluentAPISuperInitialisationCurrentElementReferenceName;
	}

	public static String getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName() {
		return StringUtils.capitalize(fluentAPISuperInitialisationCurrentElementReferenceName);
	}

	public static String getFluentAPISuperInitialisationClassName() {
		return fluentAPISuperInitialisationClassName;
	}
}
