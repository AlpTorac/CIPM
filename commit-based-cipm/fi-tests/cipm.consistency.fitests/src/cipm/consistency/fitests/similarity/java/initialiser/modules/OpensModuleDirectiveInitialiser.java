package cipm.consistency.fitests.similarity.java.initialiser.modules;

import org.emftext.language.java.modules.ModulesFactory;
import org.emftext.language.java.modules.OpensModuleDirective;

public class OpensModuleDirectiveInitialiser implements IOpensModuleDirectiveInitialiser {
	@Override
	public IOpensModuleDirectiveInitialiser newInitialiser() {
		return new OpensModuleDirectiveInitialiser();
	}

	@Override
	public OpensModuleDirective instantiate() {
		return ModulesFactory.eINSTANCE.createOpensModuleDirective();
	}
}
