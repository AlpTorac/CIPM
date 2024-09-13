package cipm.consistency.fitests.similarity.java.eobject.initialiser.modifiers;

import org.emftext.language.java.modifiers.ModuleRequiresModifier;

import cipm.consistency.fitests.similarity.java.eobject.initialiser.commons.ICommentableInitialiser;

public interface IModuleRequiresModifierInitialiser extends ICommentableInitialiser {
	@Override
	public ModuleRequiresModifier instantiate();

}
