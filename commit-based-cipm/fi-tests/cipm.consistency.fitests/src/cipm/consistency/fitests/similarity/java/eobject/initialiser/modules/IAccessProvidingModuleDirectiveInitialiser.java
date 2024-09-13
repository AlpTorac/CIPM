package cipm.consistency.fitests.similarity.java.eobject.initialiser.modules;

import org.emftext.language.java.containers.Package;
import org.emftext.language.java.modules.AccessProvidingModuleDirective;
import org.emftext.language.java.modules.ModuleReference;

import cipm.consistency.fitests.similarity.java.eobject.initialiser.commons.INamespaceAwareElementInitialiser;

public interface IAccessProvidingModuleDirectiveInitialiser
		extends IModuleDirectiveInitialiser, INamespaceAwareElementInitialiser {
	@Override
	public AccessProvidingModuleDirective instantiate();

	public default boolean setAccessablePackage(AccessProvidingModuleDirective apmd, Package accessablePac) {
		if (accessablePac != null) {
			apmd.setAccessablePackage(accessablePac);
			return apmd.getAccessablePackage().equals(accessablePac);
		}
		return true;
	}

	public default boolean addModule(AccessProvidingModuleDirective apmd, ModuleReference mod) {
		if (mod != null) {
			apmd.getModules().add(mod);
			return apmd.getModules().contains(mod);
		}
		return true;
	}

	public default boolean addModules(AccessProvidingModuleDirective apmd, ModuleReference[] mods) {
		return this.doMultipleModifications(apmd, mods, this::addModule);
	}
}
