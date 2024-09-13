package cipm.consistency.fitests.similarity.eobject.initialiser.java.annotations;

import org.emftext.language.java.annotations.AnnotationsFactory;
import org.emftext.language.java.annotations.SingleAnnotationParameter;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

public class SingleAnnotationParameterInitialiser extends AbstractInitialiserBase
		implements ISingleAnnotationParameterInitialiser {
	@Override
	public ISingleAnnotationParameterInitialiser newInitialiser() {
		return new SingleAnnotationParameterInitialiser();
	}

	@Override
	public SingleAnnotationParameter instantiate() {
		return AnnotationsFactory.eINSTANCE.createSingleAnnotationParameter();
	}
}