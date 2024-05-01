package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.containers.Package;
import org.emftext.language.java.modules.AccessProvidingModuleDirective;

public interface IAccessProvidingModuleDirectiveInitialiser extends
	IModuleDirectiveInitialiser,
	INamespaceAwareElementInitialiser {
	
	public default void setAccessablePackage(AccessProvidingModuleDirective apmd, Package pac) {
		if (pac != null) {
			apmd.setAccessablePackage(pac);
			assert apmd.getAccessablePackage().equals(pac);
		}
	}
}
