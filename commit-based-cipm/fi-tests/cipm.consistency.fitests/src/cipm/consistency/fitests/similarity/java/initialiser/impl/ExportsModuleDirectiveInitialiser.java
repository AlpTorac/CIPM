package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.modules.ModulesFactory;
import org.emftext.language.java.modules.ExportsModuleDirective;

import cipm.consistency.fitests.similarity.java.initialiser.IExportsModuleDirectiveInitialiser;

public class ExportsModuleDirectiveInitialiser implements IExportsModuleDirectiveInitialiser {
	@Override
	public IExportsModuleDirectiveInitialiser newInitialiser() {
		return new ExportsModuleDirectiveInitialiser();
	}

	@Override
	public ExportsModuleDirective instantiate() {
		return ModulesFactory.eINSTANCE.createExportsModuleDirective();
	}
}
