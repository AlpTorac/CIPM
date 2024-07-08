package cipm.consistency.fitests.similarity.java.initialiser.modules;

import org.emftext.language.java.modules.ModulesFactory;
import org.emftext.language.java.modules.RequiresModuleDirective;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

public class RequiresModuleDirectiveInitialiser extends AbstractInitialiserBase implements IRequiresModuleDirectiveInitialiser {
	@Override
	public IRequiresModuleDirectiveInitialiser newInitialiser() {
		return new RequiresModuleDirectiveInitialiser();
	}

	@Override
	public RequiresModuleDirective instantiate() {
		return ModulesFactory.eINSTANCE.createRequiresModuleDirective();
	}
}