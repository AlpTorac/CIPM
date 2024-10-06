package cipm.consistency.initialisers.tests.dummy.packages;

import cipm.consistency.initialisers.AbstractInitialiserBase;
import cipm.consistency.initialisers.IInitialiserAdapterStrategy;

public class DummyInitialiserC extends AbstractInitialiserBase {

	public DummyInitialiserC() {
		super();
	}

	public DummyInitialiserC(IInitialiserAdapterStrategy[] adaptingInits) {
		super(adaptingInits);
	}

	@Override
	public DummyInitialiserC newInitialiser() {
		return new DummyInitialiserC();
	}

	@Override
	public DummyObjC instantiate() {
		return new DummyObjC();
	}
}
