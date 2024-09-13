package cipm.consistency.fitests.similarity.eobject.initialiser.java.modifiers;

import org.emftext.language.java.modifiers.AnnotationInstanceOrModifier;

import cipm.consistency.fitests.similarity.eobject.initialiser.java.commons.ICommentableInitialiser;

public interface IAnnotationInstanceOrModifierInitialiser extends ICommentableInitialiser {
	@Override
	public AnnotationInstanceOrModifier instantiate();

}
