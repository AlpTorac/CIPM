package cipm.consistency.fitests.similarity.java.eobject.unittests.interfacetests;

import org.emftext.language.java.expressions.ExpressionsPackage;
import org.emftext.language.java.expressions.UnaryModificationExpression;
import org.emftext.language.java.expressions.UnaryModificationExpressionChild;
import org.emftext.language.java.operators.UnaryModificationOperator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.eobject.initialiser.expressions.IUnaryModificationExpressionInitialiser;
import cipm.consistency.fitests.similarity.java.eobject.unittests.UsesExpressions;

public class UnaryModificationExpressionTest extends EObjectSimilarityTest implements UsesExpressions {
	protected UnaryModificationExpression initElement(IUnaryModificationExpressionInitialiser init,
			UnaryModificationExpressionChild child, UnaryModificationOperator op) {
		UnaryModificationExpression result = init.instantiate();
		Assertions.assertTrue(init.setChild(result, child));
		Assertions.assertTrue(init.setOperator(result, op));
		return result;
	}

	@ParameterizedTest
	@ArgumentsSource(UnaryModificationExpressionTestParams.class)
	public void testChild(IUnaryModificationExpressionInitialiser init) {
		this.testSimilarity(this.initElement(init, this.createDecimalIntegerLiteral(1), null),
				this.initElement(init, this.createDecimalIntegerLiteral(2), null),
				ExpressionsPackage.Literals.UNARY_MODIFICATION_EXPRESSION__CHILD);
	}

	@ParameterizedTest
	@ArgumentsSource(UnaryModificationExpressionTestParams.class)
	public void testChildNullCheck(IUnaryModificationExpressionInitialiser init) {
		this.testSimilarityNullCheck(this.initElement(init, this.createDecimalIntegerLiteral(1), null), init, false,
				ExpressionsPackage.Literals.UNARY_MODIFICATION_EXPRESSION__CHILD);
	}

	@ParameterizedTest
	@ArgumentsSource(UnaryModificationExpressionTestParams.class)
	public void testOperator(IUnaryModificationExpressionInitialiser init) {
		this.testSimilarity(this.initElement(init, null, this.createPlusPlusOperator()),
				this.initElement(init, null, this.createMinusMinusOperator()),
				ExpressionsPackage.Literals.UNARY_MODIFICATION_EXPRESSION__OPERATOR);
	}

	@ParameterizedTest
	@ArgumentsSource(UnaryModificationExpressionTestParams.class)
	public void testOperatorNullCheck(IUnaryModificationExpressionInitialiser init) {
		this.testSimilarityNullCheck(this.initElement(init, null, this.createPlusPlusOperator()), init, false,
				ExpressionsPackage.Literals.UNARY_MODIFICATION_EXPRESSION__OPERATOR);
	}
}
