package cipm.consistency.initialisers.eobject;

public final class InitialiserNameHelper {
	/**
	 * The prefix used in initialiser interfaces.
	 */
	private static final String initialiserInterfacePrefix = "I";
	/**
	 * The suffix used in initialisers.
	 */
	private static final String initialiserSuffix = "Initialiser";
	
	/**
	 * @return The name of the concrete initialiser corresponding to cls.
	 */
	public static String getConcreteInitialiserName(Class<?> cls) {
		return cls.getSimpleName() + initialiserSuffix;
	}

	/**
	 * @return The name of the initialiser interface corresponding to cls.
	 */
	public static String getInitialiserInterfaceName(Class<?> cls) {
		return initialiserInterfacePrefix + cls.getSimpleName() + initialiserSuffix;
	}

	/**
	 * The prefix used in initialiser interfaces.
	 */
	public static String getInitialiserInterfacePrefix() {
		return initialiserInterfacePrefix;
	}

	/**
	 * The suffix used in initialisers.
	 */
	public static String getInitialiserSuffix() {
		return initialiserSuffix;
	}
}
