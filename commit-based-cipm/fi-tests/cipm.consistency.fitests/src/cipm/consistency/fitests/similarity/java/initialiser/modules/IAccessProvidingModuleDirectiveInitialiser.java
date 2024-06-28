package cipm.consistency.fitests.similarity.java.initialiser.modules;

import org.emftext.language.java.containers.Package;
import org.emftext.language.java.modules.AccessProvidingModuleDirective;
import org.emftext.language.java.modules.ModuleReference;

import cipm.consistency.fitests.similarity.java.initialiser.commons.INamespaceAwareElementInitialiser;

public interface IAccessProvidingModuleDirectiveInitialiser extends
	IModuleDirectiveInitialiser,
	INamespaceAwareElementInitialiser {
	
	public default void setAccessablePackage(AccessProvidingModuleDirective apmd, Package pac) {
		if (pac != null) {
			apmd.setAccessablePackage(pac);
			assert apmd.getAccessablePackage().equals(pac);
		}
	}
	
	public default void addModule(AccessProvidingModuleDirective apmd, ModuleReference modref) {
		if (modref != null) {
			apmd.getModules().add(modref);
			assert apmd.getModules().contains(modref);
		}
	}
	
	public default void addModules(AccessProvidingModuleDirective apmd, ModuleReference[] modrefs) {
		this.addXs(apmd, modrefs, this::addModule);
	}
}
