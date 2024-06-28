package cipm.consistency.fitests.similarity.java.initialiser.modules;

import org.emftext.language.java.modifiers.ModuleRequiresModifier;
import org.emftext.language.java.modules.ModuleReference;
import org.emftext.language.java.modules.RequiresModuleDirective;

public interface IRequiresModuleDirectiveInitialiser extends IModuleDirectiveInitialiser {
	public default void setModifier(RequiresModuleDirective rmd, ModuleRequiresModifier mrm) {
		if (mrm != null) {
			rmd.setModifier(mrm);
			assert rmd.getModifier().equals(mrm);
		}
	}
	
	public default void setRequiredModule(RequiresModuleDirective rmd, ModuleReference mref) {
		if (mref != null) {
			rmd.setRequiredModule(mref);
			assert rmd.getRequiredModule().equals(mref);
		}
	}
}
