package cipm.consistency.initialisers.tests.dummy.packages;

import cipm.consistency.initialisers.IInitialiser;

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
	public ObjAInitStrat newStrategy() {
		return new ObjAInitStrat();
	}
}
