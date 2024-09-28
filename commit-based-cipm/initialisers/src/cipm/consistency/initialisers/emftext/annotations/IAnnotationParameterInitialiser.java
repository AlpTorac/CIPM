package cipm.consistency.initialisers.emftext.annotations;

import org.emftext.language.java.annotations.AnnotationParameter;

import cipm.consistency.initialisers.emftext.commons.ICommentableInitialiser;

public interface IAnnotationParameterInitialiser extends ICommentableInitialiser {
	@Override
	public AnnotationParameter instantiate();

}
