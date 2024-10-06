package cipm.consistency.initialisers.tests.dummy.types.verticalhierarchy;

import cipm.consistency.initialisers.IInitialiser;

public interface IDummyTopLevelObjInitialiser extends IInitialiser {
	@Override
	public DummyTopLevelObj instantiate();

	public default boolean someTopLevelObjModificationMethod(DummyTopLevelObj obj) {
		return true;
	}
}
