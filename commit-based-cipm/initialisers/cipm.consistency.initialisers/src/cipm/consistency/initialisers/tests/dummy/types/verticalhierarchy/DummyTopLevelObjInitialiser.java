package cipm.consistency.initialisers.tests.dummy.types.verticalhierarchy;

import cipm.consistency.initialisers.IInitialiser;

public class DummyTopLevelObjInitialiser implements IDummyTopLevelObjInitialiser {
	@Override
	public IInitialiser newInitialiser() {
		return new DummyTopLevelObjInitialiser();
	}

	@Override
	public DummyTopLevelObj instantiate() {
		return new DummyTopLevelObj();
	}
	
	@Override
	public boolean initialise(Object obj) {
		return true;
	}
}
