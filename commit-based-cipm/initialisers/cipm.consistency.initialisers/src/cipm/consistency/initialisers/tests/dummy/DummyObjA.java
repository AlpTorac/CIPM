package cipm.consistency.initialisers.tests.dummy;

public class DummyObjA {
	private boolean initialisationStepDone = false;

	public void initStep() {
		this.initialisationStepDone = true;
	}

	public boolean isInitialisationStepDone() {
		return initialisationStepDone;
	}
}
