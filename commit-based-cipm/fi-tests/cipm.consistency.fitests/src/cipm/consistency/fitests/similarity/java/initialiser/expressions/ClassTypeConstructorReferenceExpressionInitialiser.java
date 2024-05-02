package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.ClassTypeConstructorReferenceExpression;
import org.emftext.language.java.expressions.ExpressionsFactory;

public class ClassTypeConstructorReferenceExpressionInitialiser implements IClassTypeConstructorReferenceExpressionInitialiser {
	@Override
	public IClassTypeConstructorReferenceExpressionInitialiser newInitialiser() {
		return new ClassTypeConstructorReferenceExpressionInitialiser();
	}

	@Override
	public ClassTypeConstructorReferenceExpression instantiate() {
		return ExpressionsFactory.eINSTANCE.createClassTypeConstructorReferenceExpression();
	}
}