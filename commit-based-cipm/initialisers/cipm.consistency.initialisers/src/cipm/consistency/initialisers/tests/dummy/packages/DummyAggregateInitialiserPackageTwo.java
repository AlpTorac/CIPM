package cipm.consistency.initialisers.tests.dummy.packages;

import java.util.Collection;

import cipm.consistency.initialisers.IInitialiserPackage;

public class DummyAggregateInitialiserPackageTwo implements IInitialiserPackage {
	@Override
	public Collection<IInitialiserPackage> getSubPackages() {
		return this
				.initCol(new IInitialiserPackage[] { new DummyInitialiserPackageCD() });
	}
}
