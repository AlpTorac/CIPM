package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.Conditional;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IConditionalInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesExpressions;

public class ConditionalTest extends EObjectSimilarityTest implements UsesExpressions {
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
		
		var objOne = this.initElement(init, this.createMinimalTrueEE());
		var objTwo = this.initElement(init, this.createMinimalTrueNEE());
		
		this.testX(objOne, objTwo, false);
	}
}