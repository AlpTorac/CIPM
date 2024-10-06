package cipm.consistency.initialisers.tests.dummy.types.flathierarchy;

import cipm.consistency.initialisers.tests.dummy.types.AbstractDummyTriviallyInitialisingInitialiser;

public class DummyObjTwoInitialiser extends AbstractDummyTriviallyInitialisingInitialiser
		implements IDummyObjTwoInitialiser {

	public DummyObjTwoInitialiser() {
		super();
	}

	public DummyObjTwoInitialiser(boolean isInitialiseSuccessful) {
		super(isInitialiseSuccessful);
	}

	@Override
	public DummyObjTwoInitialiser newInitialiser() {
		return new DummyObjTwoInitialiser();
	}

	@Override
	public DummyObjTwo instantiate() {
		return new DummyObjTwo();
	}

	public boolean modificationMethodInClass(DummyObjTwo obj, Object param) {
		return true;
	}
}
