package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.containers.ContainersFactory;
import org.emftext.language.java.containers.Module;

import cipm.consistency.fitests.similarity.java.initialiser.IModuleInitialiser;

public class ModuleInitialiser implements IModuleInitialiser, IInitialiser<Module> {
	@Override
	public Module instantiate() {
		var fac = ContainersFactory.eINSTANCE;
		return fac.createModule();
	}
}
