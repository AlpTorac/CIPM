package cipm.consistency.initialisers.tests.dummy.packages;

import cipm.consistency.initialisers.IInitialiser;
import cipm.consistency.initialisers.IInitialiserAdapterStrategy;

public class ObjAInitStrat extends AbstractDummyAdaptationStrategy {
	public ObjAInitStrat() {
		super();
	}

	public ObjAInitStrat(boolean initSuccessfully) {
		super(initSuccessfully);
	}

	@Override
	public boolean apply(IInitialiser init, Object obj) {
		if (obj instanceof DummyObjA) {
			var castedO = (DummyObjA) obj;

			if (this.doesInitialiseSuccessfully()) {
				castedO.initStep();
			}

			return castedO.isInitialisationStepDone();
		}

		return true;
	}

	@Override
	public IInitialiserAdapterStrategy newStrategy() {
		return new ObjAInitStrat();
	}
}
