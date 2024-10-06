package cipm.consistency.initialisers.tests.dummy.types.verticalhierarchy;

public interface IDummyTerminalObjInitialiser extends IDummyNonTerminalObjInitialiser {
	@Override
	public DummyTerminalObj instantiate();

	public default boolean someTerminalObjModificationMethod(DummyTerminalObj obj) {
		return true;
	}
}
