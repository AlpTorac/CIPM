package cipm.consistency.fitests.similarity.java.initialiser.modules;

import org.emftext.language.java.modules.ModulesFactory;
import org.emftext.language.java.modules.ExportsModuleDirective;

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
