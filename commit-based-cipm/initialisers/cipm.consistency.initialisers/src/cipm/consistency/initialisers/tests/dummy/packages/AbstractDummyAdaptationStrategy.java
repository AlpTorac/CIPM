package cipm.consistency.initialisers.tests.dummy.packages;

import cipm.consistency.initialisers.IInitialiserAdapterStrategy;

public abstract class AbstractDummyAdaptationStrategy implements IInitialiserAdapterStrategy {
	private boolean initSuccessfully;

	public AbstractDummyAdaptationStrategy() {
		this(true);
	}

	public AbstractDummyAdaptationStrategy(boolean initSuccessfully) {
		this.initSuccessfully = initSuccessfully;
	}

	public boolean doesInitialiseSuccessfully() {
		return this.initSuccessfully;
	}
}
