package cipm.consistency.fitests.similarity.java.initialiser.modules;

import org.emftext.language.java.modules.ModulesFactory;
import org.emftext.language.java.modules.UsesModuleDirective;

public class UsesModuleDirectiveInitialiser implements IUsesModuleDirectiveInitialiser {
	@Override
	public IUsesModuleDirectiveInitialiser newInitialiser() {
		return new UsesModuleDirectiveInitialiser();
	}

	@Override
	public UsesModuleDirective instantiate() {
		return ModulesFactory.eINSTANCE.createUsesModuleDirective();
	}
}