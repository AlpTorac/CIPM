package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.expressions.ExpressionsFactory;
import org.emftext.language.java.expressions.RelationExpression;

import cipm.consistency.fitests.similarity.java.initialiser.IRelationExpressionInitialiser;

public class RelationExpressionInitialiser implements IRelationExpressionInitialiser {
	@Override
	public IRelationExpressionInitialiser newInitialiser() {
		return new RelationExpressionInitialiser();
	}

	@Override
	public RelationExpression instantiate() {
		return ExpressionsFactory.eINSTANCE.createRelationExpression();
	}
}