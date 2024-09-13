package cipm.consistency.fitests.similarity.eobject.initialiser.java.modules;

import org.emftext.language.java.modules.ModuleReference;
import org.emftext.language.java.modules.ModulesFactory;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

public class ModuleReferenceInitialiser extends AbstractInitialiserBase implements IModuleReferenceInitialiser {
	@Override
	public IModuleReferenceInitialiser newInitialiser() {
		return new ModuleReferenceInitialiser();
	}

	@Override
	public ModuleReference instantiate() {
		return ModulesFactory.eINSTANCE.createModuleReference();
	}
}