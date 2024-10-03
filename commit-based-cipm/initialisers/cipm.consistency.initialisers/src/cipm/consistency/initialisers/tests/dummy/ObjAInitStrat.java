package cipm.consistency.initialisers.tests.dummy;

import cipm.consistency.initialisers.IInitialiser;
import cipm.consistency.initialisers.IInitialiserAdapterStrategy;

public class ObjAInitStrat implements IInitialiserAdapterStrategy {
	@Override
	public boolean apply(IInitialiser init, Object obj) {
		if (obj instanceof DummyObjA) {
			var castedO = (DummyObjA) obj;
			castedO.initStep();
		}

		return true;
	}

	@Override
	public IInitialiserAdapterStrategy newStrategy() {
		return new ObjAInitStrat();
	}
}
