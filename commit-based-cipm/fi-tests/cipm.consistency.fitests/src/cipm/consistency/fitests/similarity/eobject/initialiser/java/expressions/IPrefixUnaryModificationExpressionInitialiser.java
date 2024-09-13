package cipm.consistency.fitests.similarity.eobject.initialiser.java.expressions;

import org.emftext.language.java.expressions.PrefixUnaryModificationExpression;

public interface IPrefixUnaryModificationExpressionInitialiser extends IUnaryModificationExpressionInitialiser {
	@Override
	public PrefixUnaryModificationExpression instantiate();

}