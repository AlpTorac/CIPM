package cipm.consistency.fitests.similarity.eobject.initialiser.java.modules;

import org.emftext.language.java.modules.ModulesFactory;
import org.emftext.language.java.modules.OpensModuleDirective;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

public class OpensModuleDirectiveInitialiser extends AbstractInitialiserBase
		implements IOpensModuleDirectiveInitialiser {
	@Override
	public IOpensModuleDirectiveInitialiser newInitialiser() {
		return new OpensModuleDirectiveInitialiser();
	}

	@Override
	public OpensModuleDirective instantiate() {
		return ModulesFactory.eINSTANCE.createOpensModuleDirective();
	}
}
