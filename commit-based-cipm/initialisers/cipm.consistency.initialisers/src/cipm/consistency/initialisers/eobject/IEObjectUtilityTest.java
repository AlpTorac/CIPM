package cipm.consistency.initialisers.eobject;

import cipm.consistency.initialisers.IInitialiserUtilityTest;

public interface IEObjectUtilityTest extends IInitialiserUtilityTest {
	/**
	 * {@link InitialiserNameHelper#getInitialiserInterfaceName(Class)}
	 */
	public default String getInitialiserInterfaceName(Class<?> cls) {
		return InitialiserNameHelper.getInitialiserInterfaceName(cls);
	}

	/**
	 * {@link InitialiserNameHelper#getConcreteInitialiserName(Class)}
	 */
	public default String getConcreteInitialiserName(Class<?> ifc) {
		return InitialiserNameHelper.getConcreteInitialiserName(ifc);
	}
}
