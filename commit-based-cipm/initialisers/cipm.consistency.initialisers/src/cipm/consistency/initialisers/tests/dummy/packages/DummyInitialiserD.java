package cipm.consistency.initialisers.tests.dummy.packages;

import cipm.consistency.initialisers.IInitialiser;

public class DummyInitialiserD implements IInitialiser {

	@Override
	public IInitialiser newInitialiser() {
		return new DummyInitialiserD();
	}

	@Override
	public DummyObjD instantiate() {
		return new DummyObjD();
	}

	@Override
	public boolean initialise(Object obj) {
		return true;
	}
}
