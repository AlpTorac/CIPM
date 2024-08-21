package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.statements.ExpressionStatement;
import org.emftext.language.java.statements.StatementsFactory;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

public class ExpressionStatementInitialiser extends AbstractInitialiserBase implements IExpressionStatementInitialiser {
	@Override
	public IExpressionStatementInitialiser newInitialiser() {
		return new ExpressionStatementInitialiser();
	}

	@Override
	public ExpressionStatement instantiate() {
		return StatementsFactory.eINSTANCE.createExpressionStatement();
	}
}