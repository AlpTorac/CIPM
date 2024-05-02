package cipm.consistency.fitests.similarity.java.initialiser.modules;

import org.emftext.language.java.modules.ModulesFactory;
import org.emftext.language.java.modules.ProvidesModuleDirective;

public class ProvidesModuleDirectiveInitialiser implements IProvidesModuleDirectiveInitialiser {
	@Override
	public IProvidesModuleDirectiveInitialiser newInitialiser() {
		return new ProvidesModuleDirectiveInitialiser();
	}

	@Override
	public ProvidesModuleDirective instantiate() {
		return ModulesFactory.eINSTANCE.createProvidesModuleDirective();
	}
}