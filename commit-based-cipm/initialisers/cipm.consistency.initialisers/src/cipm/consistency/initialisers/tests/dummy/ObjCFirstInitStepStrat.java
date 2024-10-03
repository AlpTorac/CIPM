package cipm.consistency.initialisers.tests.dummy;

import cipm.consistency.initialisers.IInitialiser;
import cipm.consistency.initialisers.IInitialiserAdapterStrategy;

public class ObjCFirstInitStepStrat implements IInitialiserAdapterStrategy {
	private boolean initSuccessfully;

	public ObjCFirstInitStepStrat() {
	}

	public ObjCFirstInitStepStrat(boolean initSuccessfully) {
		this.initSuccessfully = initSuccessfully;
	}

	@Override
	public boolean apply(IInitialiser init, Object obj) {
		if (obj instanceof DummyObjC) {
			var castedO = (DummyObjC) obj;

			if (this.initSuccessfully) {
				castedO.initStepOne();
			}
		}

		return true;
	}

	@Override
	public IInitialiserAdapterStrategy newStrategy() {
		return new ObjCFirstInitStepStrat();
	}
}
