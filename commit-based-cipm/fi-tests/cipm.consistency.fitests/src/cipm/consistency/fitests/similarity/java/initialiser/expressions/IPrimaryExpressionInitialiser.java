package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.PrimaryExpression;

public interface IPrimaryExpressionInitialiser extends IMethodReferenceExpressionChildInitialiser {
    @Override
    public PrimaryExpression instantiate();
	
}