package cipm.consistency.fitests.similarity.java.initialiser.modules;

import org.emftext.language.java.modifiers.ModuleRequiresModifier;
import org.emftext.language.java.modules.ModuleReference;
import org.emftext.language.java.modules.RequiresModuleDirective;

public interface IRequiresModuleDirectiveInitialiser extends IModuleDirectiveInitialiser {
	@Override
	public RequiresModuleDirective instantiate();

	public default boolean setModifier(RequiresModuleDirective rmd, ModuleRequiresModifier mrm) {
		if (mrm != null) {
			rmd.setModifier(mrm);
			return rmd.getModifier().equals(mrm);
		}
		return true;
	}

	public default boolean setRequiredModule(RequiresModuleDirective rmd, ModuleReference mref) {
		if (mref != null) {
			rmd.setRequiredModule(mref);
			return rmd.getRequiredModule().equals(mref);
		}
		return true;
	}
}
