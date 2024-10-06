package cipm.consistency.initialisers.tests.dummy.packages;

import java.util.Collection;

import cipm.consistency.initialisers.IInitialiser;
import cipm.consistency.initialisers.IInitialiserPackage;

public class DummyAggregateInitialiserPackageOne implements IInitialiserPackage {
	@Override
	public Collection<IInitialiserPackage> getSubPackages() {
		return this
				.initCol(new IInitialiserPackage[] { new DummyInitialiserPackageA(), new DummyInitialiserPackageB() });
	}

	@Override
	public Collection<IInitialiser> getInitialiserInstances() {
		return this.initCol(new IInitialiser[] {new DummyInitialiserE()});
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<Class<? extends IInitialiser>> getInitialiserInterfaceTypes() {
		return this.initCol(new Class[] { DummyInitialiserE.class });
	}
}
