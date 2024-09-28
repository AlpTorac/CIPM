package cipm.consistency.initialisers.eobject.java.annotations;

import org.emftext.language.java.annotations.AnnotationValue;

import cipm.consistency.initialisers.eobject.java.commons.ICommentableInitialiser;

public interface IAnnotationValueInitialiser extends ICommentableInitialiser {
	@Override
	public AnnotationValue instantiate();

}
