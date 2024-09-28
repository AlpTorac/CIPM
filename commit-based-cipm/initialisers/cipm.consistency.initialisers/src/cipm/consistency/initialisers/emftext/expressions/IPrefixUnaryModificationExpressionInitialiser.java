package cipm.consistency.initialisers.emftext.expressions;

import org.emftext.language.java.expressions.PrefixUnaryModificationExpression;

public interface IPrefixUnaryModificationExpressionInitialiser extends IUnaryModificationExpressionInitialiser {
	@Override
	public PrefixUnaryModificationExpression instantiate();

}