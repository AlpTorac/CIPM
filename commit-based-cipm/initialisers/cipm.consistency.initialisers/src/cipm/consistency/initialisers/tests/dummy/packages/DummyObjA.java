package cipm.consistency.initialisers.tests.dummy.packages;

public class DummyObjA {
	private boolean initialisationStepDone = false;

	public void initStep() {
		this.initialisationStepDone = true;
	}

	public boolean isInitialisationStepDone() {
		return initialisationStepDone;
	}
}
