package cipm.consistency.fitests.similarity.java.initialiser.modifiers;

import org.emftext.language.java.modifiers.ModuleRequiresModifier;

import cipm.consistency.fitests.similarity.java.initialiser.commons.ICommentableInitialiser;

public interface IModuleRequiresModifierInitialiser extends ICommentableInitialiser {
	@Override
	public ModuleRequiresModifier instantiate();

}
