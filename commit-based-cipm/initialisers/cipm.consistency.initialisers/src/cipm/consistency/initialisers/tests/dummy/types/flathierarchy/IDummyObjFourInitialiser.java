package cipm.consistency.initialisers.tests.dummy.types.flathierarchy;

import cipm.consistency.initialisers.IInitialiser;

public interface IDummyObjFourInitialiser extends IInitialiser {
	@Override
	public DummyObjFour instantiate();

	public default boolean someUtilityMethodInInterface(Object someParam) {
		return true;
	}

	public default boolean someUtilityMethodInInterfaceWithoutParam() {
		return true;
	}
}
