package cipm.consistency.initialisers.tests.dummy;

import cipm.consistency.initialisers.IInitialiser;
import cipm.consistency.initialisers.IInitialiserAdapterStrategy;

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
	public IInitialiserAdapterStrategy newStrategy() {
		return new ObjCSecondInitStepStrat();
	}
}