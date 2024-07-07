package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.ExpressionStatement;

import cipm.consistency.fitests.similarity.java.initialiser.ModificationMethod;

import org.emftext.language.java.statements.ExpressionStatement;

import org.emftext.language.java.statements.ExpressionStatement;

public interface IExpressionStatementInitialiser extends IStatementInitialiser {
    @Override
    public ExpressionStatement instantiate();
    @ModificationMethod
	public default boolean setExpression(ExpressionStatement es, Expression expr) {
		if (expr != null) {
			es.setExpression(expr);
			return es.getExpression().equals(expr);
		}
		return true;
	}
}
