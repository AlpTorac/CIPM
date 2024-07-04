package cipm.consistency.fitests.similarity.java.initialiser.classifiers;

import java.util.Collection;

import cipm.consistency.fitests.similarity.java.initialiser.IInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiserPackage;

public class ClassifierInitialiserPackage implements IInitialiserPackage {
	@Override
	public Collection<IInitialiser> getInitialisers() {
		return this.initCol(new IInitialiser[] {
				new AnnotationInitialiser(),
				new AnonymousClassInitialiser(),
				new ClassInitialiser(),
				new EnumerationInitialiser(),
				new InterfaceInitialiser()
		});
	}
}
