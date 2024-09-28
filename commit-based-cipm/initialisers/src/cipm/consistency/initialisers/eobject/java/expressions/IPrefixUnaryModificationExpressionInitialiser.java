package cipm.consistency.initialisers.eobject.java.expressions;

import org.emftext.language.java.expressions.PrefixUnaryModificationExpression;

public interface IPrefixUnaryModificationExpressionInitialiser extends IUnaryModificationExpressionInitialiser {
	@Override
	public PrefixUnaryModificationExpression instantiate();

}