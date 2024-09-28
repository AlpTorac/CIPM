package cipm.consistency.initialisers.emftext.modules;

import org.emftext.language.java.modifiers.ModuleRequiresModifier;
import org.emftext.language.java.modules.ModuleReference;
import org.emftext.language.java.modules.RequiresModuleDirective;

public interface IRequiresModuleDirectiveInitialiser extends IModuleDirectiveInitialiser {
	@Override
	public RequiresModuleDirective instantiate();

	public default boolean setModifier(RequiresModuleDirective rmd, ModuleRequiresModifier modif) {
		if (modif != null) {
			rmd.setModifier(modif);
			return rmd.getModifier().equals(modif);
		}
		return true;
	}

	public default boolean setRequiredModule(RequiresModuleDirective rmd, ModuleReference reqMod) {
		if (reqMod != null) {
			rmd.setRequiredModule(reqMod);
			return rmd.getRequiredModule().equals(reqMod);
		}
		return true;
	}
}
