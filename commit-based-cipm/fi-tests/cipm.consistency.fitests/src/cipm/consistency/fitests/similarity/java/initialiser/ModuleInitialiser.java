package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.containers.ContainersFactory;
import org.emftext.language.java.containers.Module;

public class ModuleInitialiser extends AbstractInitialiser implements IModuleInitialiser {
	private boolean setDefaultName = true;
	
	@Override
	public Module getCurrentObject() {
		return (Module) super.getCurrentObject();
	}
	
	@Override
	public boolean isSetDefaultName() {
		return this.setDefaultName;
	}
	
	@Override
	public void instantiate() {
		var fac = ContainersFactory.eINSTANCE;
		var mod = fac.createModule();
		this.setCurrentObject(mod);
	}
	
	@Override
	public void shouldSetDefaultName(boolean setDefaultName) {
		this.setDefaultName = setDefaultName;
	}
}
