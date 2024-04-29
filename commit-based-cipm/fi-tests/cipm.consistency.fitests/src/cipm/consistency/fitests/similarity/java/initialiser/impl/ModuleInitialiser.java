package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.containers.ContainersFactory;
import org.emftext.language.java.containers.Module;

import cipm.consistency.fitests.similarity.java.initialiser.EObjectInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IModuleInitialiser;

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
