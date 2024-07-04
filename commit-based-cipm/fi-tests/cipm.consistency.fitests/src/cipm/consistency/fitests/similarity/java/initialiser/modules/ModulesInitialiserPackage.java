package cipm.consistency.fitests.similarity.java.initialiser.modules;

import java.util.Collection;

import cipm.consistency.fitests.similarity.java.initialiser.EObjectInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiserPackage;

public class ModulesInitialiserPackage implements IInitialiserPackage {
	@Override
	public Collection<EObjectInitialiser> getInitialisers() {
		return this.initCol(new EObjectInitialiser[] {
				new ExportsModuleDirectiveInitialiser(),
				new ModuleReferenceInitialiser(),
				new OpensModuleDirectiveInitialiser(),
				new ProvidesModuleDirectiveInitialiser(),
				new RequiresModuleDirectiveInitialiser(),
				new UsesModuleDirectiveInitialiser(),
		});
	}
}
