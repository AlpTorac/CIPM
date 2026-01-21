package cipm.consistency.fluentapi.gen;

public final class FluentAPISuperInitialisationConstants {
	/**
	 * Named this way to make sure that the generated EClasses do not have the same
	 * name
	 */
	private static final String fluentAPISuperInitialisationClassName = "FluentAPISuperInitialisation";
	private static final String fluentAPISuperInitialisationRootAPIReferenceName = "rootAPI";
	private static final String fluentAPISuperInitialisationCurrentElementReferenceName = "currentElement";

	public static String getFluentAPISuperInitialisationRootAPIReferenceName() {
		return fluentAPISuperInitialisationRootAPIReferenceName;
	}

	public static String getFluentAPISuperInitialisationCurrentElementReferenceName() {
		return fluentAPISuperInitialisationCurrentElementReferenceName;
	}

	public static String getFluentAPISuperInitialisationClassName() {
		return fluentAPISuperInitialisationClassName;
	}
}
