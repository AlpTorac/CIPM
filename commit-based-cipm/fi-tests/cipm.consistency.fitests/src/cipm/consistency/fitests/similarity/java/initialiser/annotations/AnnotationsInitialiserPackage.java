package cipm.consistency.fitests.similarity.java.initialiser.annotations;

import java.util.Collection;

import cipm.consistency.fitests.similarity.java.initialiser.EObjectInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiserPackage;

public class AnnotationsInitialiserPackage implements IInitialiserPackage {
	@Override
	public Collection<EObjectInitialiser> getInitialiserInstances() {
		return this.initCol(new EObjectInitialiser[] {
				new AnnotationAttributeSettingInitialiser(),
				new AnnotationInstanceInitialiser(),
				new AnnotationParameterListInitialiser(),
				new SingleAnnotationParameterInitialiser()
		});
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<Class<? extends EObjectInitialiser>> getInitialiserClasses() {
		return this.initCol(new Class[] {
			IAnnotableInitialiser.class,
			IAnnotationAttributeSettingInitialiser.class,
			IAnnotationInstanceInitialiser.class,
			IAnnotationParameterInitialiser.class,
			IAnnotationParameterListInitialiser.class,
			IAnnotationValueInitialiser.class,
			ISingleAnnotationParameterInitialiser.class
		});
	}
}
