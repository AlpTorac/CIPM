package cipm.consistency.initialisers.tests.dummy.packages;

import cipm.consistency.initialisers.IInitialiser;

public class DummyInitialiserB implements IInitialiser {

	@Override
	public IInitialiser newInitialiser() {
		return new DummyInitialiserB();
	}

	@Override
	public DummyObjB instantiate() {
		return new DummyObjB();
	}

	@Override
	public boolean initialise(Object obj) {
		return true;
	}
}
