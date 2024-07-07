package cipm.consistency.fitests.similarity.java.initialiser.classifiers;

import java.util.Collection;

import cipm.consistency.fitests.similarity.java.initialiser.EObjectInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiserPackage;

public class ClassifierInitialiserPackage implements IInitialiserPackage {
	@Override
	public Collection<EObjectInitialiser> getInitialiserInstances() {
		return this.initCol(new EObjectInitialiser[] {
				new AnnotationInitialiser(),
				new AnonymousClassInitialiser(),
				new ClassInitialiser(),
				new EnumerationInitialiser(),
				new InterfaceInitialiser()
		});
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<Class<? extends EObjectInitialiser>> getInitialiserClasses() {
		return this.initCol(new Class[] {
				IAnnotationInitialiser.class,
				IAnonymousClassInitialiser.class,
				IClassifierInitialiser.class,
				IClassInitialiser.class,
				IConcreteClassifierInitialiser.class,
				IEnumerationInitialiser.class,
				IImplementorInitialiser.class,
				IInterfaceInitialiser.class
		});
	}
}
