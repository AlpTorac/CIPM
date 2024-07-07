package cipm.consistency.fitests.similarity.java.initialiser.modules;

import java.util.Collection;

import cipm.consistency.fitests.similarity.java.initialiser.EObjectInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiserPackage;

public class ModulesInitialiserPackage implements IInitialiserPackage {
	@Override
	public Collection<EObjectInitialiser> getInitialiserInstances() {
		return this.initCol(new EObjectInitialiser[] {
				new ExportsModuleDirectiveInitialiser(),
				new ModuleReferenceInitialiser(),
				new OpensModuleDirectiveInitialiser(),
				new ProvidesModuleDirectiveInitialiser(),
				new RequiresModuleDirectiveInitialiser(),
				new UsesModuleDirectiveInitialiser(),
		});
	}
	
	@Override
	public Collection<Class<? extends EObjectInitialiser>> getInitialiserClasses() {
		return this.initCol(new Class[] {
				IAccessProvidingModuleDirectiveInitialiser.class,
				IExportsModuleDirectiveInitialiser.class,
				IModuleDirectiveInitialiser.class,
				IModuleReferenceInitialiser.class,
				IOpensModuleDirectiveInitialiser.class,
				IProvidesModuleDirectiveInitialiser.class,
				IRequiresModuleDirectiveInitialiser.class,
				IUsesModuleDirectiveInitialiser.class,
		});
	}
}
