package cipm.consistency.fitests.similarity.java.initialiser.annotations;

import java.util.Collection;

import cipm.consistency.fitests.similarity.java.initialiser.IInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiserPackage;

public class AnnotationsInitialiserPackage implements IInitialiserPackage {
	@Override
	public Collection<IInitialiser> getInitialisers() {
		return this.initCol(new IInitialiser[] {
				new AnnotationAttributeSettingInitialiser(),
				new AnnotationInstanceInitialiser(),
				new AnnotationParameterListInitialiser(),
				new SingleAnnotationParameterInitialiser()
		});
	}
}
