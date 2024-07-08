package cipm.consistency.fitests.similarity.java.initialiser.containers;

import org.emftext.language.java.containers.ContainersFactory;
import org.emftext.language.java.containers.Module;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

public class ModuleInitialiser extends AbstractInitialiserBase implements IModuleInitialiser {
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
