package cipm.consistency.fitests.similarity.eobject.initialiser.java.expressions;

import org.emftext.language.java.expressions.Expression;

import cipm.consistency.fitests.similarity.eobject.initialiser.java.arrays.IArrayInitializationValueInitialiser;

public interface IExpressionInitialiser extends IArrayInitializationValueInitialiser, ILambdaBodyInitialiser {
	@Override
	public Expression instantiate();
}
