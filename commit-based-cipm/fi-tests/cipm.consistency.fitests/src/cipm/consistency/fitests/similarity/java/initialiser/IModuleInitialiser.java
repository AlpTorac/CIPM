package cipm.consistency.fitests.similarity.java.initialiser;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.commons.NamespaceAwareElement;
import org.emftext.language.java.containers.Module;
import org.emftext.language.java.containers.Package;
import org.emftext.language.java.modifiers.Open;
import org.emftext.language.java.modules.ModuleDirective;

public interface IModuleInitialiser extends IJavaRootInitialiser {
	@Override
	public Module instantiate();
	
	@Override
	public default Module clone(EObject obj) {
		return (Module) IJavaRootInitialiser.super.clone(obj);
	}
	
	/**
	 * Modules' namespaces are NOT used while comparing them.
	 */
	@Override
	public default void initialiseNamespace(NamespaceAwareElement nae, String namespace) {}
	
	/**
	 * Modules' namespaces are NOT used while comparing them.
	 */
	@Override
	public default void initialiseNamespaces(NamespaceAwareElement nae, String[] namespaces) {}
	
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
