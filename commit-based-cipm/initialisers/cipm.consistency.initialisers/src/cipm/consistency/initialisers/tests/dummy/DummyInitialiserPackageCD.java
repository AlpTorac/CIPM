package cipm.consistency.initialisers.tests.dummy;

import java.util.Collection;

import cipm.consistency.initialisers.IInitialiser;
import cipm.consistency.initialisers.IInitialiserPackage;

public class DummyInitialiserPackageCD implements IInitialiserPackage {
	@Override
	public Collection<IInitialiser> getInitialiserInstances() {
		return this.initCol(new IInitialiser[] {new DummyInitialiserC(), new DummyInitialiserD()});
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<Class<? extends IInitialiser>> getInitialiserInterfaceTypes() {
		return this.initCol(new Class[] { DummyInitialiserC.class, DummyInitialiserD.class });
	}
}
