package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.expressions.ArrayConstructorReferenceExpression;
import org.emftext.language.java.expressions.ExpressionsFactory;

import cipm.consistency.fitests.similarity.java.initialiser.IArrayConstructorReferenceExpressionInitialiser;

public class ArrayConstructorReferenceExpressionInitialiser implements IArrayConstructorReferenceExpressionInitialiser {
	@Override
	public IArrayConstructorReferenceExpressionInitialiser newInitialiser() {
		return new ArrayConstructorReferenceExpressionInitialiser();
	}

	@Override
	public ArrayConstructorReferenceExpression instantiate() {
		return ExpressionsFactory.eINSTANCE.createArrayConstructorReferenceExpression();
	}
}