package cipm.consistency.fitests.similarity.eobject.initialiser.java.classifiers;

import org.emftext.language.java.classifiers.ClassifiersFactory;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

import org.emftext.language.java.classifiers.AnonymousClass;

public class AnonymousClassInitialiser extends AbstractInitialiserBase implements IAnonymousClassInitialiser {
	@Override
	public IAnonymousClassInitialiser newInitialiser() {
		return new AnonymousClassInitialiser();
	}

	@Override
	public AnonymousClass instantiate() {
		return ClassifiersFactory.eINSTANCE.createAnonymousClass();
	}
}