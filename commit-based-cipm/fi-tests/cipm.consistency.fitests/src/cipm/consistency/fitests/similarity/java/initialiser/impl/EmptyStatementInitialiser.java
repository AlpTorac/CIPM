package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.statements.EmptyStatement;
import org.emftext.language.java.statements.StatementsFactory;

import cipm.consistency.fitests.similarity.java.initialiser.IEmptyStatementInitialiser;

public class EmptyStatementInitialiser implements IEmptyStatementInitialiser {
	@Override
	public IEmptyStatementInitialiser newInitialiser() {
		return new EmptyStatementInitialiser();
	}

	@Override
	public EmptyStatement instantiate() {
		return StatementsFactory.eINSTANCE.createEmptyStatement();
	}
}