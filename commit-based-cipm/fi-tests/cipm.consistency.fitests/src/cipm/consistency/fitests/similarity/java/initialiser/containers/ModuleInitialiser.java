package cipm.consistency.fitests.similarity.java.initialiser.containers;

import org.emftext.language.java.containers.ContainersFactory;
import org.emftext.language.java.containers.Module;

public class ModuleInitialiser implements IModuleInitialiser {
	@Override
	public Module instantiate() {
		var fac = ContainersFactory.eINSTANCE;
		return fac.createModule();
	}

	@Override
	public IModuleInitialiser newInitialiser() {
		return new ModuleInitialiser();
	}
}
