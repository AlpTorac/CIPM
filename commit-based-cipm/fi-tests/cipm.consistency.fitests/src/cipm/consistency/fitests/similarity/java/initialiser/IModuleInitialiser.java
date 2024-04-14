package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.containers.Module;
import org.emftext.language.java.containers.Package;
import org.emftext.language.java.modifiers.Open;
import org.emftext.language.java.modules.ModuleDirective;

public interface IModuleInitialiser extends IJavaRootInitialiser {
	@Override
	public Module getCurrentObject();
	
	public default void initialiseOpen(Open open) {
		var cObj = this.getCurrentObject();
		
		if (open != null) {
			cObj.setOpen(open);
			assert cObj.getOpen().equals(open);
		}
	}
	
	public default void addTarget(ModuleDirective md) {
		var cObj = this.getCurrentObject();
		
		if (md != null) {
			cObj.getTarget().add(md);
			assert cObj.getTarget().contains(md);
		}
	}
	
	public default void addPackage(Package pac) {
		var cObj = this.getCurrentObject();
		
		if (pac != null) {
			cObj.getPackages().add(pac);
			assert cObj.getPackages().contains(pac);
		}
	}
	
	@Override
	public default Module build() {
		return (Module) IJavaRootInitialiser.super.build();
	}
}
