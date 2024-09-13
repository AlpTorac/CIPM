package cipm.consistency.fitests.similarity.java.eobject.initialiser.modifiers;

import org.emftext.language.java.modifiers.AnnotationInstanceOrModifier;

import cipm.consistency.fitests.similarity.java.eobject.initialiser.commons.ICommentableInitialiser;

public interface IAnnotationInstanceOrModifierInitialiser extends ICommentableInitialiser {
	@Override
	public AnnotationInstanceOrModifier instantiate();

}
