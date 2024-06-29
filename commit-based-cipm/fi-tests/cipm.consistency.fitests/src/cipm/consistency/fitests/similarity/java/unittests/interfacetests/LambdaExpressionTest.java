package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import org.emftext.language.java.expressions.ExpressionsPackage;
import org.emftext.language.java.expressions.LambdaBody;
import org.emftext.language.java.expressions.LambdaExpression;
import org.emftext.language.java.expressions.LambdaParameters;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.expressions.ILambdaExpressionInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesLambdaParameters;
import cipm.consistency.fitests.similarity.java.unittests.UsesStatements;

public class LambdaExpressionTest extends EObjectSimilarityTest
	implements UsesStatements, UsesLambdaParameters {
	protected LambdaExpression initElement(ILambdaExpressionInitialiser init,
			LambdaBody body, LambdaParameters param) {
		LambdaExpression result = init.instantiate();
		Assertions.assertTrue(init.minimalInitialisation(result));
		Assertions.assertTrue(init.setBody(result, body));
		Assertions.assertTrue(init.setParameters(result, param));
		return result;
	}
	
	@ParameterizedTest
	@ArgumentsSource(LambdaExpressionTestParams.class)
	public void testBody(ILambdaExpressionInitialiser init) {
		this.setResourceFileTestIdentifier("testBody");
		
		var objOne = this.initElement(init, this.createMinimalBlockWithNullReturn(), null);
		var objTwo = this.initElement(init, this.createMinimalBlockWithTrivialAssert(), null);
		
		this.testX(objOne, objTwo, ExpressionsPackage.Literals.LAMBDA_EXPRESSION__BODY);
	}
	
	@ParameterizedTest
	@ArgumentsSource(LambdaExpressionTestParams.class)
	public void testParameters(ILambdaExpressionInitialiser init) {
		this.setResourceFileTestIdentifier("testParameters");
		
		var objOne = this.initElement(init, null, this.createMinimalETLP("p1", "c1"));
		var objTwo = this.initElement(init, null, this.createMinimalETLP("p2", "c2"));
		
		this.testX(objOne, objTwo, ExpressionsPackage.Literals.LAMBDA_EXPRESSION__PARAMETERS);
	}
}
