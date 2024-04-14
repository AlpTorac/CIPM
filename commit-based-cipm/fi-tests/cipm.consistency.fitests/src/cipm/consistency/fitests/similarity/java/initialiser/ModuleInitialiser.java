package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.containers.ContainersFactory;
import org.emftext.language.java.containers.Module;

public class ModuleInitialiser implements IModuleInitialiser {
	private boolean setDefaultName = true;
	
	@Override
	public boolean isSetDefaultName() {
		return this.setDefaultName;
	}
	
	@Override
	public Module instantiate() {
		var fac = ContainersFactory.eINSTANCE;
		return fac.createModule();
	}
	
	@Override
	public void shouldSetDefaultName(boolean setDefaultName) {
		this.setDefaultName = setDefaultName;
	}
}
