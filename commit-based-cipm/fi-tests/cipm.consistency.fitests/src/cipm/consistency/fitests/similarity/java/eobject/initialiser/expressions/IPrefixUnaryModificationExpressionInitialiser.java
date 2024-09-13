package cipm.consistency.fitests.similarity.java.eobject.initialiser.expressions;

import org.emftext.language.java.expressions.PrefixUnaryModificationExpression;

public interface IPrefixUnaryModificationExpressionInitialiser extends IUnaryModificationExpressionInitialiser {
	@Override
	public PrefixUnaryModificationExpression instantiate();

}