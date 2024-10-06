package cipm.consistency.initialisers.tests;

import java.util.ArrayList;
import java.util.Collection;

import cipm.consistency.initialisers.IInitialiser;
import cipm.consistency.initialisers.IInitialiserBase;
import cipm.consistency.initialisers.tests.dummy.packages.DummyTopLevelInitialiserPackage;

public abstract class AbstractInitialiserTest {
	// TODO Remove this class and add its methods to InitialiserBaseTests
	public Collection<IInitialiser> getAllInitialisers() {
		return new DummyTopLevelInitialiserPackage().getAllInitialiserInstances();
	}

	public Collection<IInitialiserBase> getAllAdaptableInitialisers() {
		var allInits = this.getAllInitialisers();
		var result = new ArrayList<IInitialiserBase>();

		allInits.forEach((i) -> {
			if (i instanceof IInitialiserBase)
				result.add((IInitialiserBase) i);
		});

		return result;
	}
}
