package cipm.consistency.initialisers.tests.dummy.types.flathierarchy;

import cipm.consistency.initialisers.tests.dummy.types.AbstractDummyTriviallyInitialisingInitialiser;

public class DummyObjOneInitialiser extends AbstractDummyTriviallyInitialisingInitialiser
		implements IDummyObjOneInitialiser {

	public DummyObjOneInitialiser() {
		super();
	}

	public DummyObjOneInitialiser(boolean isInitialiseSuccessful) {
		super(isInitialiseSuccessful);
	}

	@Override
	public DummyObjOneInitialiser newInitialiser() {
		return new DummyObjOneInitialiser();
	}

	@Override
	public DummyObjOne instantiate() {
		return new DummyObjOne();
	}
}
