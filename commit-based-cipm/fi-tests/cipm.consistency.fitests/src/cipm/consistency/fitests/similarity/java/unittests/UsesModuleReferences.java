package cipm.consistency.fitests.similarity.java.unittests;

import org.emftext.language.java.modules.ModuleReference;

import cipm.consistency.fitests.similarity.java.initialiser.modules.ModuleReferenceInitialiser;

public interface UsesModuleReferences extends UsesModules {
	public default ModuleReference createMinimalMR(String modName) {
		var mrInit = new ModuleReferenceInitialiser();
		var mr = mrInit.instantiate();
		mrInit.setTarget(mr, this.createMinimalModule(modName));
		return mr;
	}
	
	public default ModuleReference createMinimalMR(String modName, String[] modRefNss) {
		var mr = this.createMinimalMR(modName);
		var mrInit = new ModuleReferenceInitialiser();
		mrInit.addNamespaces(mr, modRefNss);
		return mr;
	}
}
