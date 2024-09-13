package cipm.consistency.fitests.similarity.eobject.java.unittests.impltests;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.NormalSwitchCase;
import org.emftext.language.java.statements.StatementsPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.eobject.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.statements.NormalSwitchCaseInitialiser;
import cipm.consistency.fitests.similarity.eobject.java.unittests.UsesExpressions;

public class NormalSwitchCaseTest extends EObjectSimilarityTest implements UsesExpressions {
	protected NormalSwitchCase initElement(Expression[] additionalConds) {
		var nscInit = new NormalSwitchCaseInitialiser();
		var nsc = nscInit.instantiate();
		Assertions.assertTrue(nscInit.addAdditionalConditions(nsc, additionalConds));
		return nsc;
	}

	@Test
	public void testAdditionalCondition() {
		var objOne = this.initElement(new Expression[] { this.createMinimalFalseEE() });
		var objTwo = this.initElement(new Expression[] { this.createMinimalTrueNEE() });

		this.testSimilarity(objOne, objTwo, StatementsPackage.Literals.NORMAL_SWITCH_CASE__ADDITIONAL_CONDITIONS);
	}

	@Test
	public void testAdditionalConditionSize() {
		var objOne = this.initElement(new Expression[] { this.createMinimalFalseEE(), this.createMinimalFalseEE() });
		var objTwo = this.initElement(new Expression[] { this.createMinimalFalseEE() });

		this.testSimilarity(objOne, objTwo, StatementsPackage.Literals.NORMAL_SWITCH_CASE__ADDITIONAL_CONDITIONS);
	}

	@Test
	public void testAdditionalConditionNullCheck() {
		this.testSimilarityNullCheck(this.initElement(new Expression[] { this.createMinimalFalseEE() }),
				new NormalSwitchCaseInitialiser(), false,
				StatementsPackage.Literals.NORMAL_SWITCH_CASE__ADDITIONAL_CONDITIONS);
	}
}
