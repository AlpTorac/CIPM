package cipm.consistency.fitests.similarity.java.eobject.unittests.interfacetests;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.Conditional;
import org.emftext.language.java.statements.StatementsPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.eobject.initialiser.statements.IConditionalInitialiser;
import cipm.consistency.fitests.similarity.java.eobject.unittests.UsesExpressions;

public class ConditionalTest extends EObjectSimilarityTest implements UsesExpressions {
	protected Conditional initElement(IConditionalInitialiser init, Expression cond) {
		Conditional result = init.instantiate();
		Assertions.assertTrue(init.setCondition(result, cond));
		return result;
	}

	@ParameterizedTest
	@ArgumentsSource(ConditionalTestParams.class)
	public void testCondition(IConditionalInitialiser init) {
		var objOne = this.initElement(init, this.createMinimalTrueEE());
		var objTwo = this.initElement(init, this.createMinimalTrueNEE());

		this.testSimilarity(objOne, objTwo, StatementsPackage.Literals.CONDITIONAL__CONDITION);
	}

	@ParameterizedTest
	@ArgumentsSource(ConditionalTestParams.class)
	public void testConditionNullCheck(IConditionalInitialiser init) {
		this.testSimilarityNullCheck(this.initElement(init, this.createMinimalTrueEE()), init, false,
				StatementsPackage.Literals.CONDITIONAL__CONDITION);
	}
}
