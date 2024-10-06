package cipm.consistency.initialisers.tests.dummy.types.verticalhierarchy;

import cipm.consistency.initialisers.IInitialiser;

public class DummyNonTerminalObjInitialiser implements IDummyNonTerminalObjInitialiser {
	@Override
	public IInitialiser newInitialiser() {
		return new DummyNonTerminalObjInitialiser();
	}

	@Override
	public DummyNonTerminalObj instantiate() {
		return new DummyNonTerminalObj();
	}
	
	@Override
	public boolean initialise(Object obj) {
		return true;
	}
}