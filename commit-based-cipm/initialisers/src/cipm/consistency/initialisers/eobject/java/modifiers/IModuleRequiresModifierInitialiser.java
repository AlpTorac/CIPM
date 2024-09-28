package cipm.consistency.initialisers.eobject.java.modifiers;

import org.emftext.language.java.modifiers.ModuleRequiresModifier;

import cipm.consistency.initialisers.eobject.java.commons.ICommentableInitialiser;

public interface IModuleRequiresModifierInitialiser extends ICommentableInitialiser {
	@Override
	public ModuleRequiresModifier instantiate();

}
