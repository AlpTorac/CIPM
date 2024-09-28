package cipm.consistency.initialisers.eobject.java.annotations;

import org.emftext.language.java.annotations.AnnotationParameter;

import cipm.consistency.initialisers.eobject.java.commons.ICommentableInitialiser;

public interface IAnnotationParameterInitialiser extends ICommentableInitialiser {
	@Override
	public AnnotationParameter instantiate();

}
