package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.annotations.AnnotationParameter;

// ToDo: Figure out a way to make parameters

public interface IAnnotationParameterInitialiser extends ICommentableInitialiser {
	@Override
	public AnnotationParameter instantiate();
	
	@Override
	public default AnnotationParameter minimalInstantiation() {
		return (AnnotationParameter) ICommentableInitialiser.super.minimalInstantiation();
	}
	
	
}
