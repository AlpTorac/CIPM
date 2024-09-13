package cipm.consistency.fitests.similarity.java.eobject.initialiser.modules;

import org.emftext.language.java.modules.ModulesFactory;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

import org.emftext.language.java.modules.ExportsModuleDirective;

public class ExportsModuleDirectiveInitialiser extends AbstractInitialiserBase
		implements IExportsModuleDirectiveInitialiser {
	@Override
	public IExportsModuleDirectiveInitialiser newInitialiser() {
		return new ExportsModuleDirectiveInitialiser();
	}

	@Override
	public ExportsModuleDirective instantiate() {
		return ModulesFactory.eINSTANCE.createExportsModuleDirective();
	}
}
