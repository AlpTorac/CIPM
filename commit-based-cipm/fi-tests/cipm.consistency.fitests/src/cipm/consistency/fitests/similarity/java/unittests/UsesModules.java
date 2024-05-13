package cipm.consistency.fitests.similarity.java.unittests;

import org.emftext.language.java.containers.Module;
import org.emftext.language.java.modules.ModuleReference;

import cipm.consistency.fitests.similarity.java.initialiser.containers.ModuleInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.modules.ModuleReferenceInitialiser;

public interface UsesModules {
	public default Module createMinimalModule(String name) {
		var modInit = new ModuleInitialiser();
		Module result = modInit.instantiate();
		modInit.minimalInitialisation(result);
		modInit.initialiseName(result, name);
		return result;
	}
	
	public default ModuleReference createMinimalMR(String modName) {
		var init = new ModuleReferenceInitialiser();
		ModuleReference result = init.instantiate();
		init.minimalInitialisation(result);
		init.setTarget(result, this.createMinimalModule(modName));
		return result;
	}
}
