package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import org.emftext.language.java.expressions.ExpressionsPackage;
import org.emftext.language.java.expressions.UnaryModificationExpression;
import org.emftext.language.java.expressions.UnaryModificationExpressionChild;
import org.emftext.language.java.operators.UnaryModificationOperator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.expressions.IUnaryModificationExpressionInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesExpressions;

public class UnaryModificationExpressionTest extends EObjectSimilarityTest implements UsesExpressions {
	protected UnaryModificationExpression initElement(IUnaryModificationExpressionInitialiser initialiser,
			UnaryModificationExpressionChild child, UnaryModificationOperator op) {
		UnaryModificationExpression result = initialiser.instantiate();
		Assertions.assertTrue(initialiser.initialise(result));
		Assertions.assertTrue(initialiser.setChild(result, child));
		Assertions.assertTrue(initialiser.setOperator(result, op));
		return result;
	}
	
	@ParameterizedTest
	@ArgumentsSource(UnaryModificationExpressionTestParams.class)
	public void testChild(IUnaryModificationExpressionInitialiser initialiser) {
		this.setResourceFileTestIdentifier("testChild");
		
		this.testX(
				this.initElement(initialiser, this.createInteger(1), null),
				this.initElement(initialiser, this.createInteger(2), null),
				ExpressionsPackage.Literals.UNARY_MODIFICATION_EXPRESSION__CHILD);
	}
	
	@ParameterizedTest
	@ArgumentsSource(UnaryModificationExpressionTestParams.class)
	public void testOperator(IUnaryModificationExpressionInitialiser initialiser) {
		this.setResourceFileTestIdentifier("testOperator");
		
		this.testX(
				this.initElement(initialiser, null, this.createPlusPlusOperator()),
				this.initElement(initialiser, null, this.createMinusMinusOperator()),
				ExpressionsPackage.Literals.UNARY_MODIFICATION_EXPRESSION__OPERATOR);
	}
}
