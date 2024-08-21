package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.ExpressionsFactory;
import org.emftext.language.java.expressions.RelationExpression;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

public class RelationExpressionInitialiser extends AbstractInitialiserBase implements IRelationExpressionInitialiser {
	@Override
	public IRelationExpressionInitialiser newInitialiser() {
		return new RelationExpressionInitialiser();
	}

	@Override
	public RelationExpression instantiate() {
		return ExpressionsFactory.eINSTANCE.createRelationExpression();
	}
}