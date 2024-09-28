package cipm.consistency.initialisers.eobject.java.expressions;

import org.emftext.language.java.expressions.SuffixUnaryModificationExpression;

public interface ISuffixUnaryModificationExpressionInitialiser extends IUnaryModificationExpressionInitialiser {
	@Override
	public SuffixUnaryModificationExpression instantiate();

}