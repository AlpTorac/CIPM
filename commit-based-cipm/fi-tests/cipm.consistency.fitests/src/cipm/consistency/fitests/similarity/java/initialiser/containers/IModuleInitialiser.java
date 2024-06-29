package cipm.consistency.fitests.similarity.java.initialiser.containers;

import org.emftext.language.java.containers.Module;
import org.emftext.language.java.containers.Package;
import org.emftext.language.java.modifiers.Open;
import org.emftext.language.java.modules.ModuleDirective;

public interface IModuleInitialiser extends IJavaRootInitialiser {
	public default boolean setOpen(Module mod, Open open) {
		if (open != null) {
			mod.setOpen(open);
			return mod.getOpen().equals(open);
		}
		return false;
	}
	
	public default boolean addTarget(Module mod, ModuleDirective md) {
		if (md != null) {
			mod.getTarget().add(md);
			return mod.getTarget().contains(md);
		}
		return false;
	}
	
	public default boolean addTargets(Module mod, ModuleDirective[] mds) {
		return this.addXs(mod, mds, this::addTarget);
	}
	
	public default boolean addPackage(Module mod, Package pac) {
		if (pac != null) {
			mod.getPackages().add(pac);
			return mod.getPackages().contains(pac);
		}
		return false;
	}
	
	public default boolean addPackages(Module mod, Package[] pacs) {
		return this.addXs(mod, pacs, this::addPackage);
	}
}
