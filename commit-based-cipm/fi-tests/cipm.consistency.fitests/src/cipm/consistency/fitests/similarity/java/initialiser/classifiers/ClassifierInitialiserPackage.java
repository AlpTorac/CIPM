package cipm.consistency.fitests.similarity.java.initialiser.classifiers;

import java.util.Collection;

import cipm.consistency.fitests.similarity.java.initialiser.EObjectInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiserPackage;

public class ClassifierInitialiserPackage implements IInitialiserPackage {
	@Override
	public Collection<EObjectInitialiser> getInitialisers() {
		return this.initCol(new EObjectInitialiser[] {
				new AnnotationInitialiser(),
				new AnonymousClassInitialiser(),
				new ClassInitialiser(),
				new EnumerationInitialiser(),
				new InterfaceInitialiser()
		});
	}
}
