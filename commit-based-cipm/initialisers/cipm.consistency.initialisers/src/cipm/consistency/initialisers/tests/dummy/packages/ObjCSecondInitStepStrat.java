package cipm.consistency.initialisers.tests.dummy.packages;

import cipm.consistency.initialisers.IInitialiser;

public class ObjCSecondInitStepStrat extends AbstractDummyAdaptationStrategy {
	public ObjCSecondInitStepStrat() {
		super();
	}

	public ObjCSecondInitStepStrat(boolean initSuccessfully) {
		super(initSuccessfully);
	}

	@Override
	public boolean apply(IInitialiser init, Object obj) {
		if (obj instanceof DummyObjC) {
			var castedO = (DummyObjC) obj;

			if (this.doesInitialiseSuccessfully()) {
				castedO.initStepTwo();
			}

			return castedO.isInitialisationStepTwoDone();
		}

		return true;
	}

	@Override
	public ObjCSecondInitStepStrat newStrategy() {
		return new ObjCSecondInitStepStrat();
	}
}