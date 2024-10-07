package cipm.consistency.initialisers.eobject;

/**
 * A utility class that contains information about the naming convention used
 * within the sub-packages of this package. <br>
 * <br>
 * This class is intended to be used from tests, which ensure that all necessary
 * initialisers are implemented and are accessible.
 * 
 * @author Alp Torac Genc
 */
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
		return cls.getSimpleName() + getInitialiserSuffix();
	}

	/**
	 * @return The name of the initialiser interface corresponding to cls.
	 */
	public static String getInitialiserInterfaceName(Class<?> cls) {
		return getInitialiserInterfacePrefix() + cls.getSimpleName() + getInitialiserSuffix();
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
