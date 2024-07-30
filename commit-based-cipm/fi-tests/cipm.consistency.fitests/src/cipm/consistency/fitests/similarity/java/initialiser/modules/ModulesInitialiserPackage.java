package cipm.consistency.fitests.similarity.java.initialiser.modules;

import java.util.Collection;

import cipm.consistency.fitests.similarity.java.initialiser.EObjectInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiserPackage;

public class ModulesInitialiserPackage implements IInitialiserPackage {
	@Override
	public Collection<IInitialiser> getInitialiserInstances() {
		return this.initCol(
				new EObjectInitialiser[] { new ExportsModuleDirectiveInitialiser(), new ModuleReferenceInitialiser(),
						new OpensModuleDirectiveInitialiser(), new ProvidesModuleDirectiveInitialiser(),
						new RequiresModuleDirectiveInitialiser(), new UsesModuleDirectiveInitialiser(), });
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Class<? extends IInitialiser>> getInitialiserClasses() {
		return this.initCol(new Class[] { IAccessProvidingModuleDirectiveInitialiser.class,
				IExportsModuleDirectiveInitialiser.class, IModuleDirectiveInitialiser.class,
				IModuleReferenceInitialiser.class, IOpensModuleDirectiveInitialiser.class,
				IProvidesModuleDirectiveInitialiser.class, IRequiresModuleDirectiveInitialiser.class,
				IUsesModuleDirectiveInitialiser.class, });
	}
}
