package cipm.consistency.fitests.similarity.eobject.java.unittests.impltests;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.NormalSwitchRule;
import org.emftext.language.java.statements.StatementsPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.eobject.java.AbstractEObjectJavaSimilarityTest;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.statements.NormalSwitchRuleInitialiser;
import cipm.consistency.fitests.similarity.eobject.java.unittests.UsesExpressions;

public class NormalSwitchRuleTest extends AbstractEObjectJavaSimilarityTest implements UsesExpressions {
	protected NormalSwitchRule initElement(Expression[] additionalConds) {
		var nsrInit = new NormalSwitchRuleInitialiser();
		var nsr = nsrInit.instantiate();
		Assertions.assertTrue(nsrInit.addAdditionalConditions(nsr, additionalConds));
		return nsr;
	}

	@Test
	public void testAdditionalCondition() {
		var objOne = this.initElement(new Expression[] { this.createMinimalFalseEE() });
		var objTwo = this.initElement(new Expression[] { this.createMinimalTrueNEE() });

		this.testSimilarity(objOne, objTwo, StatementsPackage.Literals.NORMAL_SWITCH_RULE__ADDITIONAL_CONDITIONS);
	}

	@Test
	public void testAdditionalConditionSize() {
		var objOne = this.initElement(new Expression[] { this.createMinimalFalseEE(), this.createMinimalFalseEE() });
		var objTwo = this.initElement(new Expression[] { this.createMinimalFalseEE() });

		this.testSimilarity(objOne, objTwo, StatementsPackage.Literals.NORMAL_SWITCH_RULE__ADDITIONAL_CONDITIONS);
	}

	@Test
	public void testAdditionalConditionNullCheck() {
		this.testSimilarityNullCheck(this.initElement(new Expression[] { this.createMinimalFalseEE() }),
				new NormalSwitchRuleInitialiser(), false,
				StatementsPackage.Literals.NORMAL_SWITCH_RULE__ADDITIONAL_CONDITIONS);
	}
}
