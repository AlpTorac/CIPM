package cipm.consistency.initialisers.emftext.annotations;

import org.emftext.language.java.annotations.AnnotationValue;

import cipm.consistency.initialisers.emftext.commons.ICommentableInitialiser;

public interface IAnnotationValueInitialiser extends ICommentableInitialiser {
	@Override
	public AnnotationValue instantiate();

}
