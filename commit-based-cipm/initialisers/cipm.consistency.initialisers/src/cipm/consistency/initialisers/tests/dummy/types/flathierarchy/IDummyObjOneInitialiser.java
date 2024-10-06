package cipm.consistency.initialisers.tests.dummy.types.flathierarchy;

import cipm.consistency.initialisers.IInitialiser;

public interface IDummyObjOneInitialiser extends IInitialiser {
	@Override
	public DummyObjOne instantiate();

	public default boolean modificationMethodInInterface(DummyObjOne obj, Object param1, Object param2) {
		return true;
	}
}
