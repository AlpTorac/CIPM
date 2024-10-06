package cipm.consistency.initialisers.tests.dummy.types.flathierarchy;

import cipm.consistency.initialisers.IInitialiser;
import cipm.consistency.initialisers.tests.dummy.types.AbstractDummyTriviallyInitialisingInitialiser;

public class DummyObjThreeInitialiser extends AbstractDummyTriviallyInitialisingInitialiser
		implements IDummyObjThreeInitialiser {

	public DummyObjThreeInitialiser() {
		super();
	}

	public DummyObjThreeInitialiser(boolean isInitialiseSuccessful) {
		super(isInitialiseSuccessful);
	}

	@Override
	public IInitialiser newInitialiser() {
		return new DummyObjThreeInitialiser();
	}

	@Override
	public DummyObjThree instantiate() {
		return new DummyObjThree();
	}
}
