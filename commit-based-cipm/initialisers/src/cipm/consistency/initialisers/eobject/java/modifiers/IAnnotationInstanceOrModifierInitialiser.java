package cipm.consistency.initialisers.eobject.java.modifiers;

import org.emftext.language.java.modifiers.AnnotationInstanceOrModifier;

import cipm.consistency.initialisers.eobject.java.commons.ICommentableInitialiser;

public interface IAnnotationInstanceOrModifierInitialiser extends ICommentableInitialiser {
	@Override
	public AnnotationInstanceOrModifier instantiate();

}
