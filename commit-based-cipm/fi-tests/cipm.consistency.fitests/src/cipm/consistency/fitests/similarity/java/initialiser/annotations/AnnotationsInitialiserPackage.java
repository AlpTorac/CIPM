package cipm.consistency.fitests.similarity.java.initialiser.annotations;

import java.util.Collection;

import cipm.consistency.fitests.similarity.java.initialiser.EObjectInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiserPackage;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

public class AnnotationsInitialiserPackage implements IInitialiserPackage {
	@Override
	public Collection<IInitialiser> getInitialiserInstances() {
		return this.initCol(new EObjectInitialiser[] {
				new AnnotationAttributeSettingInitialiser(),
				new AnnotationInstanceInitialiser(),
				new AnnotationParameterListInitialiser(),
				new SingleAnnotationParameterInitialiser()
		});
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<Class<? extends IInitialiser>> getInitialiserClasses() {
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
