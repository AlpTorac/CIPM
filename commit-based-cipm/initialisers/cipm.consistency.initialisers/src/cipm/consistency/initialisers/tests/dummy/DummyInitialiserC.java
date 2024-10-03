package cipm.consistency.initialisers.tests.dummy;

import cipm.consistency.initialisers.AbstractInitialiserBase;
import cipm.consistency.initialisers.IInitialiser;

public class DummyInitialiserC extends AbstractInitialiserBase {

	@Override
	public IInitialiser newInitialiser() {
		return new DummyInitialiserC();
	}

	@Override
	public DummyObjC instantiate() {
		return new DummyObjC();
	}
}
