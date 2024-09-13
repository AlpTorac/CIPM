package cipm.consistency.fitests.similarity.eobject.initialiser.java.expressions;

import org.emftext.language.java.expressions.ClassTypeConstructorReferenceExpression;
import org.emftext.language.java.expressions.ExpressionsFactory;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

public class ClassTypeConstructorReferenceExpressionInitialiser extends AbstractInitialiserBase
		implements IClassTypeConstructorReferenceExpressionInitialiser {
	@Override
	public IClassTypeConstructorReferenceExpressionInitialiser newInitialiser() {
		return new ClassTypeConstructorReferenceExpressionInitialiser();
	}

	@Override
	public ClassTypeConstructorReferenceExpression instantiate() {
		return ExpressionsFactory.eINSTANCE.createClassTypeConstructorReferenceExpression();
	}
}