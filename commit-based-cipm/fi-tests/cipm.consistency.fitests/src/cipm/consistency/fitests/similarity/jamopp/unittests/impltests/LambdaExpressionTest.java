package cipm.consistency.fitests.similarity.jamopp.unittests.impltests;

import org.emftext.language.java.expressions.ExpressionsPackage;
import org.emftext.language.java.expressions.LambdaBody;
import org.emftext.language.java.expressions.LambdaExpression;
import org.emftext.language.java.expressions.LambdaParameters;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesLambdaParameters;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesStatements;
import cipm.consistency.initialisers.jamopp.expressions.LambdaExpressionInitialiser;

public class LambdaExpressionTest extends AbstractJaMoPPSimilarityTest implements UsesStatements, UsesLambdaParameters {
	private LambdaBody body1;
	private LambdaBody body2;
	private LambdaParameters param1;
	private LambdaParameters param2;

	protected LambdaExpression initElement(LambdaBody body, LambdaParameters param) {
		var init = new LambdaExpressionInitialiser();
		LambdaExpression result = init.instantiate();
		Assertions.assertTrue(init.setBody(result, body));
		Assertions.assertTrue(init.setParameters(result, param));
		return result;
	}

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		body1 = this.createMinimalBlock();
		body2 = this.createDecimalIntegerLiteral(1);
		Assertions.assertFalse(this.isSimilar(body1, body2));

		param1 = this.createMinimalETLP("p1", "c1");
		param2 = this.createMinimalITLP("p2", "c2");
		Assertions.assertFalse(this.isSimilar(param1, param2));
	}

	@Test
	public void testBody() {
		var objOne = this.initElement(this.cloneEObjWithContainers(body1), null);
		var objTwo = this.initElement(this.cloneEObjWithContainers(body2), null);

		this.testSimilarity(objOne, objTwo, ExpressionsPackage.Literals.LAMBDA_EXPRESSION__BODY);
	}

	@Test
	public void testBodyNullCheck() {
		this.testSimilarityNullCheck(this.initElement(this.cloneEObjWithContainers(body1), null),
				new LambdaExpressionInitialiser(), false, ExpressionsPackage.Literals.LAMBDA_EXPRESSION__BODY);
	}

	@Test
	public void testParameters() {
		var objOne = this.initElement(null, this.cloneEObjWithContainers(param1));
		var objTwo = this.initElement(null, this.cloneEObjWithContainers(param2));

		this.testSimilarity(objOne, objTwo, ExpressionsPackage.Literals.LAMBDA_EXPRESSION__PARAMETERS);
	}

	@Test
	public void testParametersNullCheck() {
		this.testSimilarityNullCheck(this.initElement(null, this.cloneEObjWithContainers(param1)),
				new LambdaExpressionInitialiser(), false, ExpressionsPackage.Literals.LAMBDA_EXPRESSION__PARAMETERS);
	}
}
