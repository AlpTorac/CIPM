package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.statements.ExpressionStatement;
import org.emftext.language.java.statements.StatementsFactory;

import cipm.consistency.fitests.similarity.java.initialiser.IExpressionStatementInitialiser;

public class ExpressionStatementInitialiser implements IExpressionStatementInitialiser {
	@Override
	public IExpressionStatementInitialiser newInitialiser() {
		return new ExpressionStatementInitialiser();
	}

	@Override
	public ExpressionStatement instantiate() {
		return StatementsFactory.eINSTANCE.createExpressionStatement();
	}
}