package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.modules.ModulesFactory;
import org.emftext.language.java.modules.UsesModuleDirective;

import cipm.consistency.fitests.similarity.java.initialiser.IUsesModuleDirectiveInitialiser;

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