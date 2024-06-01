package cipm.consistency.fitests.similarity.java.unittests;

import org.emftext.language.java.modules.ModuleReference;

import cipm.consistency.fitests.similarity.java.initialiser.modules.ModuleReferenceInitialiser;

public interface UsesModuleReferences extends UsesModules {
	public default ModuleReference createMinimalModuleReference(String modName) {
		var mrInit = new ModuleReferenceInitialiser();
		var mr = mrInit.instantiate();
		mrInit.minimalInitialisation(mr);
		mrInit.setTarget(mr, this.createMinimalModule(modName));
		return mr;
	}
}
