package cipm.consistency.initialisers.eobject.java.expressions;

import org.emftext.language.java.expressions.EqualityExpressionChild;

public interface IEqualityExpressionChildInitialiser extends IAndExpressionChildInitialiser {
	@Override
	public EqualityExpressionChild instantiate();

}