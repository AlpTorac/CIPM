package cipm.consistency.fitests.similarity.java.initialiser.modules;

import org.emftext.language.java.containers.Module;
import org.emftext.language.java.modules.ModuleReference;

import cipm.consistency.fitests.similarity.java.initialiser.commons.INamespaceAwareElementInitialiser;

public interface IModuleReferenceInitialiser extends
	INamespaceAwareElementInitialiser {
	
	public default void setTarget(ModuleReference mref, Module mod) {
		if (mod != null) {
			mref.setTarget(mod);
			assert mref.getTarget().equals(mod);
		}
	}
}
