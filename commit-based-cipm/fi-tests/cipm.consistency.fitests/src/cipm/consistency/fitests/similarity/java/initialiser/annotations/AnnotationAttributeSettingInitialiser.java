package cipm.consistency.fitests.similarity.java.initialiser.annotations;

import org.emftext.language.java.annotations.AnnotationAttributeSetting;
import org.emftext.language.java.annotations.AnnotationsFactory;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

public class AnnotationAttributeSettingInitialiser extends AbstractInitialiserBase
		implements IAnnotationAttributeSettingInitialiser {
	@Override
	public IAnnotationAttributeSettingInitialiser newInitialiser() {
		return new AnnotationAttributeSettingInitialiser();
	}

	@Override
	public AnnotationAttributeSetting instantiate() {
		return AnnotationsFactory.eINSTANCE.createAnnotationAttributeSetting();
	}
}
