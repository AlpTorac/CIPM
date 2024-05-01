package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.modules.ModuleReference;
import org.emftext.language.java.modules.ModulesFactory;

import cipm.consistency.fitests.similarity.java.initialiser.IModuleReferenceInitialiser;

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