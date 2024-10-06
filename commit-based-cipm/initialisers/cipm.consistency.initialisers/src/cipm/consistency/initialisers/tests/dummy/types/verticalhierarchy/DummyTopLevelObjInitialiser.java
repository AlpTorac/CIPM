package cipm.consistency.initialisers.tests.dummy.types.verticalhierarchy;

public class DummyTopLevelObjInitialiser implements IDummyTopLevelObjInitialiser {
	@Override
	public DummyTopLevelObjInitialiser newInitialiser() {
		return new DummyTopLevelObjInitialiser();
	}

	@Override
	public DummyTopLevelObj instantiate() {
		return new DummyTopLevelObj();
	}

	@Override
	public boolean initialise(Object obj) {
		return true;
	}
}
