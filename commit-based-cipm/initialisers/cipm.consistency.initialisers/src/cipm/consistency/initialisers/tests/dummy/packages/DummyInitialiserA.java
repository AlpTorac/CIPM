package cipm.consistency.initialisers.tests.dummy.packages;

import cipm.consistency.initialisers.AbstractInitialiserBase;

public class DummyInitialiserA extends AbstractInitialiserBase {

	@Override
	public DummyInitialiserA newInitialiser() {
		return new DummyInitialiserA();
	}

	@Override
	public DummyObjA instantiate() {
		return new DummyObjA();
	}
}
