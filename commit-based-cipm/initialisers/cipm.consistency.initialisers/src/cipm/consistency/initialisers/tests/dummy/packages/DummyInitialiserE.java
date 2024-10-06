package cipm.consistency.initialisers.tests.dummy.packages;

import cipm.consistency.initialisers.IInitialiser;

public class DummyInitialiserE implements IInitialiser {

	@Override
	public IInitialiser newInitialiser() {
		return new DummyInitialiserE();
	}

	@Override
	public DummyObjE instantiate() {
		return new DummyObjE();
	}

	@Override
	public boolean initialise(Object obj) {
		return false;
	}
}
