package cipm.consistency.initialisers.emftext.modifiers;

import org.emftext.language.java.modifiers.AnnotationInstanceOrModifier;

import cipm.consistency.initialisers.emftext.commons.ICommentableInitialiser;

public interface IAnnotationInstanceOrModifierInitialiser extends ICommentableInitialiser {
	@Override
	public AnnotationInstanceOrModifier instantiate();

}
