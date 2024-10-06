package cipm.consistency.initialisers.tests.dummy.types.flathierarchy;

import cipm.consistency.initialisers.tests.dummy.types.AbstractDummyTriviallyInitialisingInitialiser;

public class DummyObjFourInitialiser extends AbstractDummyTriviallyInitialisingInitialiser
		implements IDummyObjFourInitialiser {

	public DummyObjFourInitialiser() {
		super();
	}

	public DummyObjFourInitialiser(boolean isInitialiseSuccessful) {
		super(isInitialiseSuccessful);
	}

	@Override
	public DummyObjFourInitialiser newInitialiser() {
		return new DummyObjFourInitialiser();
	}

	@Override
	public DummyObjFour instantiate() {
		return new DummyObjFour();
	}

	public boolean someUtilityMethodInClass(Object param) {
		return true;
	}

	public boolean someUtilityMethodInClassWithoutParam() {
		return true;
	}
}
