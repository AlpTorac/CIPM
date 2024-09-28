package cipm.consistency.initialisers.emftext.expressions;

import org.emftext.language.java.expressions.Expression;

import cipm.consistency.initialisers.emftext.arrays.IArrayInitializationValueInitialiser;

public interface IExpressionInitialiser extends IArrayInitializationValueInitialiser, ILambdaBodyInitialiser {
	@Override
	public Expression instantiate();
}
