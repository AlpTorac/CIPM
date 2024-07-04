package cipm.consistency.fitests.similarity.java.initialiser.annotations;

import org.emftext.language.java.annotations.AnnotationParameter;

import cipm.consistency.fitests.similarity.java.initialiser.commons.ICommentableInitialiser;

public interface IAnnotationParameterInitialiser extends ICommentableInitialiser {
    @Override
    public AnnotationParameter instantiate();

}
