package cipm.consistency.fitests.similarity.java.initialiser.modules;

import org.emftext.language.java.containers.Package;
import org.emftext.language.java.modules.AccessProvidingModuleDirective;
import org.emftext.language.java.modules.ModuleReference;

import cipm.consistency.fitests.similarity.java.initialiser.commons.INamespaceAwareElementInitialiser;

public interface IAccessProvidingModuleDirectiveInitialiser extends
	IModuleDirectiveInitialiser,
	INamespaceAwareElementInitialiser {
	@Override
	public AccessProvidingModuleDirective instantiate();
	
	public default boolean setAccessablePackage(AccessProvidingModuleDirective apmd, Package pac) {
		if (pac != null) {
			apmd.setAccessablePackage(pac);
			return apmd.getAccessablePackage().equals(pac);
		}
		return true;
	}
	
	public default boolean addModule(AccessProvidingModuleDirective apmd, ModuleReference modref) {
		if (modref != null) {
			apmd.getModules().add(modref);
			return apmd.getModules().contains(modref);
		}
		return true;
	}
	
	public default boolean addModules(AccessProvidingModuleDirective apmd, ModuleReference[] modrefs) {
		return this.addXs(apmd, modrefs, this::addModule);
	}
}
