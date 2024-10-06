package cipm.consistency.initialisers.tests.dummy.types.verticalhierarchy;

public interface IDummyNonTerminalObjInitialiser extends IDummyTopLevelObjInitialiser {
	@Override
	public DummyNonTerminalObj instantiate();

	public default boolean someNonTerminalObjModificationMethod(DummyNonTerminalObj obj) {
		return true;
	}
}
