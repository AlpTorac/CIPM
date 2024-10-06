package cipm.consistency.initialisers.tests.dummy.types.verticalhierarchy;

public class DummyAlternateInitialiser implements IDummyAlternateInitialiser {
	@Override
	public DummyAlternateInitialiser newInitialiser() {
		return new DummyAlternateInitialiser();
	}

	@Override
	public DummyNonTerminalObj instantiate() {
		return new DummyNonTerminalObj();
	}

	@Override
	public boolean initialise(Object obj) {
		return true;
	}
}