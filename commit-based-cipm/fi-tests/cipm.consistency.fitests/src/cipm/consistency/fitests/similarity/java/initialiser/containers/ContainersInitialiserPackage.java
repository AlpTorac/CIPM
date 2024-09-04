package cipm.consistency.fitests.similarity.java.initialiser.containers;

import java.util.Collection;

import cipm.consistency.fitests.similarity.java.initialiser.IInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiserPackage;

public class ContainersInitialiserPackage implements IInitialiserPackage {
	@Override
	public Collection<IInitialiser> getInitialiserInstances() {
		return this.initCol(new IInitialiser[] { new CompilationUnitInitialiser(), new EmptyModelInitialiser(),
				new ModuleInitialiser(), new PackageInitialiser() });
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Class<? extends IInitialiser>> getInitialiserInterfaceTypes() {
		return this.initCol(new Class[] { ICompilationUnitInitialiser.class, IEmptyModelInitialiser.class,
				IJavaRootInitialiser.class, IModuleInitialiser.class, IPackageInitialiser.class });
	}
}
