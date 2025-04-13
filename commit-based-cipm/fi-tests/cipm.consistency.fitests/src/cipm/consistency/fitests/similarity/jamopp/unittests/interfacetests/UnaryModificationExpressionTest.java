package cipm.consistency.fitests.similarity.jamopp.unittests.interfacetests;

import java.util.stream.Stream;

import org.emftext.language.java.expressions.ExpressionsPackage;
import org.emftext.language.java.expressions.UnaryModificationExpression;
import org.emftext.language.java.expressions.UnaryModificationExpressionChild;
import org.emftext.language.java.operators.UnaryModificationOperator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesExpressions;
import cipm.consistency.initialisers.jamopp.expressions.IUnaryModificationExpressionInitialiser;

public class UnaryModificationExpressionTest extends AbstractJaMoPPSimilarityTest implements UsesExpressions {
	private UnaryModificationExpressionChild child1;
	private UnaryModificationExpressionChild child2;
	private UnaryModificationOperator op1;
	private UnaryModificationOperator op2;

	private static Stream<Arguments> provideArguments() {
		return AbstractJaMoPPSimilarityTest
				.getAllInitialiserArgumentsFor(IUnaryModificationExpressionInitialiser.class);
	}

	protected UnaryModificationExpression initElement(IUnaryModificationExpressionInitialiser init,
			UnaryModificationExpressionChild child, UnaryModificationOperator op) {
		UnaryModificationExpression result = init.instantiate();
		Assertions.assertTrue(init.initialise(result));
		Assertions.assertTrue(init.setChild(result, child));
		Assertions.assertTrue(init.setOperator(result, op));
		return result;
	}

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		child1 = this.createDecimalIntegerLiteral(1);
		child2 = this.createDecimalIntegerLiteral(2);
		Assertions.assertFalse(this.isSimilar(child1, child2));

		op1 = this.createPlusPlusOperator();
		op2 = this.createMinusMinusOperator();
		Assertions.assertFalse(this.isSimilar(op1, op2));
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testChild(IUnaryModificationExpressionInitialiser init, String displayName) {
		this.testSimilarity(this.initElement(init, this.cloneEObjWithContainers(child1), null),
				this.initElement(init, this.cloneEObjWithContainers(child2), null),
				ExpressionsPackage.Literals.UNARY_MODIFICATION_EXPRESSION__CHILD);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testChildNullCheck(IUnaryModificationExpressionInitialiser init, String displayName) {
		this.testSimilarityNullCheck(this.initElement(init, this.cloneEObjWithContainers(child1), null), init, true,
				ExpressionsPackage.Literals.UNARY_MODIFICATION_EXPRESSION__CHILD);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testOperator(IUnaryModificationExpressionInitialiser init, String displayName) {
		this.testSimilarity(this.initElement(init, null, this.cloneEObjWithContainers(op1)),
				this.initElement(init, null, this.cloneEObjWithContainers(op2)),
				ExpressionsPackage.Literals.UNARY_MODIFICATION_EXPRESSION__OPERATOR);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testOperatorNullCheck(IUnaryModificationExpressionInitialiser init, String displayName) {
		this.testSimilarityNullCheck(this.initElement(init, null, this.cloneEObjWithContainers(op1)), init, true,
				ExpressionsPackage.Literals.UNARY_MODIFICATION_EXPRESSION__OPERATOR);
	}
}
