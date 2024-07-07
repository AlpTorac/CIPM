package cipm.consistency.fitests.similarity.java.initialiser.containers;

import java.util.Collection;

import cipm.consistency.fitests.similarity.java.initialiser.EObjectInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiserPackage;
import cipm.consistency.fitests.similarity.java.initialiser.classifiers.IClassInitialiser;

public class ContainersInitialiserPackage implements IInitialiserPackage {
	@Override
	public Collection<EObjectInitialiser> getInitialiserInstances() {
		return this.initCol(new EObjectInitialiser[] {
			new CompilationUnitInitialiser(),
			new EmptyModelInitialiser(),
			new ModuleInitialiser(),
			new PackageInitialiser()
		});
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<Class<? extends EObjectInitialiser>> getInitialiserClasses() {
		return this.initCol(new Class[] {
			ICompilationUnitInitialiser.class,
			IEmptyModelInitialiser.class,
			IJavaRootInitialiser.class,
			IModuleInitialiser.class,
			IPackageInitialiser.class
		});
	}
}
