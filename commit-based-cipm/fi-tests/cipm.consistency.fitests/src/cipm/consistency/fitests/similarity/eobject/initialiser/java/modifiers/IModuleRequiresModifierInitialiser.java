package cipm.consistency.fitests.similarity.eobject.initialiser.java.modifiers;

import org.emftext.language.java.modifiers.ModuleRequiresModifier;

import cipm.consistency.fitests.similarity.eobject.initialiser.java.commons.ICommentableInitialiser;

public interface IModuleRequiresModifierInitialiser extends ICommentableInitialiser {
	@Override
	public ModuleRequiresModifier instantiate();

}
