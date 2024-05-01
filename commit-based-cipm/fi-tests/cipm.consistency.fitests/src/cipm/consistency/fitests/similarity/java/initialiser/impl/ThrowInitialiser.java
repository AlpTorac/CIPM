package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.statements.StatementsFactory;
import org.emftext.language.java.statements.Throw;

import cipm.consistency.fitests.similarity.java.initialiser.IThrowInitialiser;

public class ThrowInitialiser implements IThrowInitialiser {
	@Override
	public IThrowInitialiser newInitialiser() {
		return new ThrowInitialiser();
	}

	@Override
	public Throw instantiate() {
		return StatementsFactory.eINSTANCE.createThrow();
	}
}