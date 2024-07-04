package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.Expression;

import cipm.consistency.fitests.similarity.java.initialiser.arrays.IArrayInitializationValueInitialiser;

public interface IExpressionInitialiser extends IArrayInitializationValueInitialiser,
	ILambdaBodyInitialiser {
	@Override
	public Expression instantiate();
}
