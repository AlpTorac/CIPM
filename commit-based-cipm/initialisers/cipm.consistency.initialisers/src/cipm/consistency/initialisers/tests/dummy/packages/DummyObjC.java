package cipm.consistency.initialisers.tests.dummy.packages;

public class DummyObjC {
	private boolean initialisationStepOneDone = false;
	private boolean initialisationStepTwoDone = false;

	public void initStepOne() {
		this.initialisationStepOneDone = true;
	}

	public void initStepTwo() {
		this.initialisationStepTwoDone = true;
	}

	public boolean isInitialisationStepOneDone() {
		return initialisationStepOneDone;
	}

	public boolean isInitialisationStepTwoDone() {
		return initialisationStepTwoDone;
	}
}
