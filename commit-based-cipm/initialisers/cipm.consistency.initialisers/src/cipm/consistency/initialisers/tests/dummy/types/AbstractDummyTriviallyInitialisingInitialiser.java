package cipm.consistency.initialisers.tests.dummy.types;

import cipm.consistency.initialisers.IInitialiser;

public abstract class AbstractDummyTriviallyInitialisingInitialiser implements IInitialiser {

	private boolean isInitialiseSuccessful;

	public AbstractDummyTriviallyInitialisingInitialiser() {
		this(true);
	}

	public AbstractDummyTriviallyInitialisingInitialiser(boolean isInitialiseSuccessful) {
		this.isInitialiseSuccessful = isInitialiseSuccessful;
	}

	@Override
	public boolean initialise(Object obj) {
		return this.isInitialiseSuccessful;
	}
}
