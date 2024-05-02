package cipm.consistency.fitests.similarity.java.initialiser.annotations;

import org.emftext.language.java.annotations.AnnotationsFactory;
import org.emftext.language.java.annotations.SingleAnnotationParameter;

public class SingleAnnotationParameterInitialiser implements ISingleAnnotationParameterInitialiser {
	@Override
	public ISingleAnnotationParameterInitialiser newInitialiser() {
		return new SingleAnnotationParameterInitialiser();
	}

	@Override
	public SingleAnnotationParameter instantiate() {
		return AnnotationsFactory.eINSTANCE.createSingleAnnotationParameter();
	}
}