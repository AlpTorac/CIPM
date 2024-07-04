package cipm.consistency.fitests.similarity.java.initialiser.containers;

import java.util.Collection;

import cipm.consistency.fitests.similarity.java.initialiser.EObjectInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiserPackage;

public class ContainersInitialiserPackage implements IInitialiserPackage {
	@Override
	public Collection<EObjectInitialiser> getInitialisers() {
		return this.initCol(new EObjectInitialiser[] {
			new CompilationUnitInitialiser(),
			new EmptyModelInitialiser(),
			new ModuleInitialiser(),
			new PackageInitialiser()
		});
	}
}
