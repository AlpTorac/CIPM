package cipm.consistency.fitests.similarity.java.unittests;

import org.emftext.language.java.containers.Module;

import cipm.consistency.fitests.similarity.java.initialiser.containers.ModuleInitialiser;

public interface UsesModules {
	public default Module createMinimalModule(String name) {
		var modInit = new ModuleInitialiser();
		Module result = modInit.instantiate();
		modInit.minimalInitialisation(result);
		modInit.initialiseName(result, name);
		return result;
	}
}
