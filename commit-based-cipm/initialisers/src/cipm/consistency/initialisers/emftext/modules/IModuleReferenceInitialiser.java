package cipm.consistency.initialisers.emftext.modules;

import org.emftext.language.java.containers.Module;
import org.emftext.language.java.modules.ModuleReference;

import cipm.consistency.initialisers.emftext.commons.INamespaceAwareElementInitialiser;

public interface IModuleReferenceInitialiser extends INamespaceAwareElementInitialiser {
	@Override
	public ModuleReference instantiate();

	public default boolean setTarget(ModuleReference mref, Module target) {
		if (target != null) {
			mref.setTarget(target);
			return mref.getTarget().equals(target);
		}
		return true;
	}
}
