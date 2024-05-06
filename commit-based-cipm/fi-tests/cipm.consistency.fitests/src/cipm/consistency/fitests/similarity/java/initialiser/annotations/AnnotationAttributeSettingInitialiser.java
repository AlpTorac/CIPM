package cipm.consistency.fitests.similarity.java.initialiser.annotations;

import org.emftext.language.java.annotations.AnnotationAttributeSetting;
import org.emftext.language.java.annotations.AnnotationsFactory;

public class AnnotationAttributeSettingInitialiser implements IAnnotationAttributeSettingInitialiser {
	@Override
	public IAnnotationAttributeSettingInitialiser newInitialiser() {
		return new AnnotationAttributeSettingInitialiser();
	}

	@Override
	public AnnotationAttributeSetting instantiate() {
		return AnnotationsFactory.eINSTANCE.createAnnotationAttributeSetting();
	}
}