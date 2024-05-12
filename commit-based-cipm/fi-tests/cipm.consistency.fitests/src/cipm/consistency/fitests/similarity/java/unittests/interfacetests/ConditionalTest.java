package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import java.math.BigInteger;

import org.emftext.language.java.expressions.ConditionalExpression;
import org.emftext.language.java.expressions.EqualityExpression;
import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.Conditional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.expressions.ConditionalExpressionInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.expressions.EqualityExpressionInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.expressions.IConditionalExpressionInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.expressions.IEqualityExpressionInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.operators.EqualInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.operators.NotEqualInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.params.LiteralFactory;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IConditionalInitialiser;

public class ConditionalTest extends EObjectSimilarityTest {
	// 1 == 2
	private EqualityExpression eq1;
	
	// 1 =/= 2
	private EqualityExpression eq2;
	
	@BeforeEach
	@Override
	public void setUp() {
		IEqualityExpressionInitialiser eqInit = new EqualityExpressionInitialiser();
		
		eq1 = eqInit.instantiate();
		eqInit.minimalInitialisation(eq1);
		eqInit.addEqualityOperator(eq1, new EqualInitialiser().instantiate());
		eqInit.addChild(eq1, new LiteralFactory().createDecIntegerLiteral(BigInteger.valueOf(1)));
		eqInit.addChild(eq1, new LiteralFactory().createDecIntegerLiteral(BigInteger.valueOf(2)));
		
		eq2 = eqInit.instantiate();
		eqInit.minimalInitialisation(eq2);
		eqInit.addEqualityOperator(eq2, new NotEqualInitialiser().instantiate());
		eqInit.addChild(eq2, new LiteralFactory().createDecIntegerLiteral(BigInteger.valueOf(1)));
		eqInit.addChild(eq2, new LiteralFactory().createDecIntegerLiteral(BigInteger.valueOf(2)));
		
		super.setUp();
	}
	
	protected Conditional initElement(IConditionalInitialiser init, Expression expr) {
		Conditional result = init.instantiate();
		init.minimalInitialisation(result);
		init.setCondition(result, expr);
		return result;
	}
	
	@ParameterizedTest
	@ArgumentsSource(ConditionalTestParams.class)
	public void testConditional(IConditionalInitialiser init) {
		this.setResourceFileTestIdentifier("testConditional");
		
		var objOne = this.initElement(init, eq1);
		var objTwo = this.initElement(init, eq2);
		
		this.testX(objOne, objTwo, false);
	}
}
