package cipm.consistency.fitests.similarity.java.initialiser.modules;

import org.emftext.language.java.modules.ModuleReference;
import org.emftext.language.java.modules.ModulesFactory;

public class ModuleReferenceInitialiser implements IModuleReferenceInitialiser {
	@Override
	public IModuleReferenceInitialiser newInitialiser() {
		return new ModuleReferenceInitialiser();
	}

	@Override
	public ModuleReference instantiate() {
		return ModulesFactory.eINSTANCE.createModuleReference();
	}
}