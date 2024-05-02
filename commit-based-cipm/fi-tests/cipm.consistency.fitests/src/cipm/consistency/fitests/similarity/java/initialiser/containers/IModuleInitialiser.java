package cipm.consistency.fitests.similarity.java.initialiser.containers;

import org.emftext.language.java.commons.NamespaceAwareElement;
import org.emftext.language.java.containers.Module;
import org.emftext.language.java.containers.Package;
import org.emftext.language.java.modifiers.Modifiable;
import org.emftext.language.java.modifiers.Open;
import org.emftext.language.java.modules.ModuleDirective;

import cipm.consistency.fitests.similarity.java.initialiser.IJavaRootInitialiser;

public interface IModuleInitialiser extends IJavaRootInitialiser {
	public default void initialiseOpen(Module mod, Open open) {
		if (open != null) {
			mod.setOpen(open);
			assert mod.getOpen().equals(open);
		}
	}
	
	public default void addTarget(Module mod, ModuleDirective md) {
		if (md != null) {
			mod.getTarget().add(md);
			assert mod.getTarget().contains(md);
		}
	}
	
	public default void addPackage(Module mod, Package pac) {
		if (pac != null) {
			mod.getPackages().add(pac);
			assert mod.getPackages().contains(pac);
		}
	}
}
