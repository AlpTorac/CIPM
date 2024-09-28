package cipm.consistency.initialisers.emftext.modifiers;

import org.emftext.language.java.modifiers.ModuleRequiresModifier;

import cipm.consistency.initialisers.emftext.commons.ICommentableInitialiser;

public interface IModuleRequiresModifierInitialiser extends ICommentableInitialiser {
	@Override
	public ModuleRequiresModifier instantiate();

}
