package cipm.consistency.fitests.similarity.java.initialiser.modules;

import org.emftext.language.java.modules.ModulesFactory;
import org.emftext.language.java.modules.ProvidesModuleDirective;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

public class ProvidesModuleDirectiveInitialiser extends AbstractInitialiserBase implements IProvidesModuleDirectiveInitialiser {
	@Override
	public IProvidesModuleDirectiveInitialiser newInitialiser() {
		return new ProvidesModuleDirectiveInitialiser();
	}

	@Override
	public ProvidesModuleDirective instantiate() {
		return ModulesFactory.eINSTANCE.createProvidesModuleDirective();
	}
}