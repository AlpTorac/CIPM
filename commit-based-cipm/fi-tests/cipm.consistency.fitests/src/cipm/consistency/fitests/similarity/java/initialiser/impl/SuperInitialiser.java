package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.literals.LiteralsFactory;
import org.emftext.language.java.literals.Super;

import cipm.consistency.fitests.similarity.java.initialiser.ISuperInitialiser;

public class SuperInitialiser implements ISuperInitialiser {
	@Override
	public ISuperInitialiser newInitialiser() {
		return new SuperInitialiser();
	}

	@Override
	public Super instantiate() {
		return LiteralsFactory.eINSTANCE.createSuper();
	}
}