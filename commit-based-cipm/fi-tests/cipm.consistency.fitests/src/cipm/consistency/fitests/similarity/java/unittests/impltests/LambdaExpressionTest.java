package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.expressions.ExpressionsPackage;
import org.emftext.language.java.expressions.LambdaBody;
import org.emftext.language.java.expressions.LambdaExpression;
import org.emftext.language.java.expressions.LambdaParameters;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.expressions.LambdaExpressionInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesLambdaParameters;
import cipm.consistency.fitests.similarity.java.unittests.UsesStatements;

public class LambdaExpressionTest extends EObjectSimilarityTest implements UsesStatements, UsesLambdaParameters {
	protected LambdaExpression initElement(LambdaBody body, LambdaParameters param) {
		var init = new LambdaExpressionInitialiser();
		LambdaExpression result = init.instantiate();
		Assertions.assertTrue(init.setBody(result, body));
		Assertions.assertTrue(init.setParameters(result, param));
		return result;
	}

	@Test
	public void testBody() {
		this.setResourceFileTestIdentifier("testBody");

		var objOne = this.initElement(this.createMinimalBlockWithNullReturn(), null);
		var objTwo = this.initElement(this.createMinimalBlockWithTrivialAssert(), null);

		this.testSimilarity(objOne, objTwo, ExpressionsPackage.Literals.LAMBDA_EXPRESSION__BODY);
	}

	@Test
	public void testBodyNullCheck() {
		this.setResourceFileTestIdentifier("testBodyNullCheck");

		var objOne = this.initElement(this.createMinimalBlockWithNullReturn(), null);
		var objTwo = new LambdaExpressionInitialiser().instantiate();

		this.testSimilarity(objOne, objTwo, ExpressionsPackage.Literals.LAMBDA_EXPRESSION__BODY);
	}

	@Test
	public void testParameters() {
		this.setResourceFileTestIdentifier("testParameters");

		var objOne = this.initElement(null, this.createMinimalETLP("p1", "c1"));
		var objTwo = this.initElement(null, this.createMinimalETLP("p2", "c2"));

		this.testSimilarity(objOne, objTwo, ExpressionsPackage.Literals.LAMBDA_EXPRESSION__PARAMETERS);
	}

	@Test
	public void testParametersNullCheck() {
		this.setResourceFileTestIdentifier("testParametersNullCheck");

		var objOne = this.initElement(null, this.createMinimalETLP("p1", "c1"));
		var objTwo = new LambdaExpressionInitialiser().instantiate();

		this.testSimilarity(objOne, objTwo, ExpressionsPackage.Literals.LAMBDA_EXPRESSION__PARAMETERS);
	}
}
