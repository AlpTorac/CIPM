package cipm.consistency.fitests.similarity.eobject.initialiser.java.classifiers;

import java.util.Collection;

import cipm.consistency.fitests.similarity.initialiser.IInitialiser;
import cipm.consistency.fitests.similarity.initialiser.IInitialiserPackage;

public class ClassifierInitialiserPackage implements IInitialiserPackage {
	@Override
	public Collection<IInitialiser> getInitialiserInstances() {
		return this.initCol(new IInitialiser[] { new AnnotationInitialiser(), new AnonymousClassInitialiser(),
				new ClassInitialiser(), new EnumerationInitialiser(), new InterfaceInitialiser() });
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Class<? extends IInitialiser>> getInitialiserInterfaceTypes() {
		return this.initCol(new Class[] { IAnnotationInitialiser.class, IAnonymousClassInitialiser.class,
				IClassifierInitialiser.class, IClassInitialiser.class, IConcreteClassifierInitialiser.class,
				IEnumerationInitialiser.class, IImplementorInitialiser.class, IInterfaceInitialiser.class });
	}
}
