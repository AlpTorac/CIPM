package cipm.consistency.fitests.similarity.java.eobject.initialiser.modules;

import org.emftext.language.java.modules.ModulesFactory;
import org.emftext.language.java.modules.UsesModuleDirective;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

public class UsesModuleDirectiveInitialiser extends AbstractInitialiserBase implements IUsesModuleDirectiveInitialiser {
	@Override
	public IUsesModuleDirectiveInitialiser newInitialiser() {
		return new UsesModuleDirectiveInitialiser();
	}

	@Override
	public UsesModuleDirective instantiate() {
		return ModulesFactory.eINSTANCE.createUsesModuleDirective();
	}
}