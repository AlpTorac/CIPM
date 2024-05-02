package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.statements.ExpressionStatement;
import org.emftext.language.java.statements.StatementsFactory;

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