package cipm.consistency.initialisers.tests.dummy.packages;

import cipm.consistency.initialisers.IInitialiser;

public class ObjCFirstInitStepStrat extends AbstractDummyAdaptationStrategy {
	public ObjCFirstInitStepStrat() {
		super();
	}

	public ObjCFirstInitStepStrat(boolean initSuccessfully) {
		super(initSuccessfully);
	}

	@Override
	public boolean apply(IInitialiser init, Object obj) {
		if (obj instanceof DummyObjC) {
			var castedO = (DummyObjC) obj;

			if (this.doesInitialiseSuccessfully()) {
				castedO.initStepOne();
			}

			return castedO.isInitialisationStepOneDone();
		}

		return true;
	}

	@Override
	public ObjCFirstInitStepStrat newStrategy() {
		return new ObjCFirstInitStepStrat();
	}
}
