package cipm.consistency.initialisers.eobject.java.expressions;

import org.emftext.language.java.expressions.ArrayConstructorReferenceExpression;
import org.emftext.language.java.expressions.ExpressionsFactory;

import cipm.consistency.initialisers.AbstractInitialiserBase;

public class ArrayConstructorReferenceExpressionInitialiser extends AbstractInitialiserBase
		implements IArrayConstructorReferenceExpressionInitialiser {
	@Override
	public IArrayConstructorReferenceExpressionInitialiser newInitialiser() {
		return new ArrayConstructorReferenceExpressionInitialiser();
	}

	@Override
	public ArrayConstructorReferenceExpression instantiate() {
		return ExpressionsFactory.eINSTANCE.createArrayConstructorReferenceExpression();
	}
}