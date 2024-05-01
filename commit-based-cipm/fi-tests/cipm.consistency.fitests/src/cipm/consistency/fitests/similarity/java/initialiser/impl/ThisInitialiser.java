package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.literals.LiteralsFactory;
import org.emftext.language.java.literals.This;

import cipm.consistency.fitests.similarity.java.initialiser.IThisInitialiser;

public class ThisInitialiser implements IThisInitialiser {
	@Override
	public IThisInitialiser newInitialiser() {
		return new ThisInitialiser();
	}

	@Override
	public This instantiate() {
		return LiteralsFactory.eINSTANCE.createThis();
	}
}