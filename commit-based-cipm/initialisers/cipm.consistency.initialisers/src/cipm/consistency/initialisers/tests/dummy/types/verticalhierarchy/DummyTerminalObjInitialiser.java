package cipm.consistency.initialisers.tests.dummy.types.verticalhierarchy;

import cipm.consistency.initialisers.IInitialiser;

public class DummyTerminalObjInitialiser implements IDummyTerminalObjInitialiser {
	@Override
	public IInitialiser newInitialiser() {
		return new DummyTerminalObjInitialiser();
	}

	@Override
	public DummyTerminalObj instantiate() {
		return new DummyTerminalObj();
	}
	
	@Override
	public boolean initialise(Object obj) {
		return true;
	}
}