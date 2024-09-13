package cipm.consistency.fitests.similarity.java.eobject.initialiser.annotations;

import org.emftext.language.java.annotations.AnnotationValue;

import cipm.consistency.fitests.similarity.java.eobject.initialiser.commons.ICommentableInitialiser;

public interface IAnnotationValueInitialiser extends ICommentableInitialiser {
	@Override
	public AnnotationValue instantiate();

}
