package cipm.consistency.initialisers.eobject.java.expressions;

import org.emftext.language.java.expressions.Expression;

import cipm.consistency.initialisers.eobject.java.arrays.IArrayInitializationValueInitialiser;

public interface IExpressionInitialiser extends IArrayInitializationValueInitialiser, ILambdaBodyInitialiser {
	@Override
	public Expression instantiate();
}
