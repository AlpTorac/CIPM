package cipm.consistency.fitests.similarity.eobject.initialiser.java.annotations;

import org.emftext.language.java.annotations.AnnotationParameter;

import cipm.consistency.fitests.similarity.eobject.initialiser.java.commons.ICommentableInitialiser;

public interface IAnnotationParameterInitialiser extends ICommentableInitialiser {
	@Override
	public AnnotationParameter instantiate();

}
