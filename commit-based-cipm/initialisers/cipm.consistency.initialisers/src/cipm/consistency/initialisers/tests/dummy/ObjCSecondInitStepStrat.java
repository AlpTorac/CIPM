package cipm.consistency.initialisers.tests.dummy;

import cipm.consistency.initialisers.IInitialiser;
import cipm.consistency.initialisers.IInitialiserAdapterStrategy;

public class ObjCSecondInitStepStrat implements IInitialiserAdapterStrategy {
	private boolean initSuccessfully;
	
	public ObjCSecondInitStepStrat() {
	}
	
	public ObjCSecondInitStepStrat(boolean initSuccessfully) {
		this.initSuccessfully = initSuccessfully;
	}
	
	@Override
	public boolean apply(IInitialiser init, Object obj) {
		if (obj instanceof DummyObjC) {
			var castedO = (DummyObjC) obj;
			
			if (this.initSuccessfully) {
				castedO.initStepTwo();
			}
		}
		
		return true;
	}
	
	@Override
	public IInitialiserAdapterStrategy newStrategy() {
		return new ObjCSecondInitStepStrat();
	}
}