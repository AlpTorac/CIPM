package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.classifiers.ClassifiersFactory;
import org.emftext.language.java.classifiers.AnonymousClass;

import cipm.consistency.fitests.similarity.java.initialiser.IAnonymousClassInitialiser;

public class AnonymousClassInitialiser implements IAnonymousClassInitialiser {
	@Override
	public IAnonymousClassInitialiser newInitialiser() {
		return new AnonymousClassInitialiser();
	}

	@Override
	public AnonymousClass instantiate() {
		return ClassifiersFactory.eINSTANCE.createAnonymousClass();
	}
}