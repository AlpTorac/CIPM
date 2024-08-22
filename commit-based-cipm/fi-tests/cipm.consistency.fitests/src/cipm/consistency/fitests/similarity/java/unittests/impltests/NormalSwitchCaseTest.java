package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.NormalSwitchCase;
import org.emftext.language.java.statements.StatementsPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.statements.NormalSwitchCaseInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesExpressions;

public class NormalSwitchCaseTest extends EObjectSimilarityTest implements UsesExpressions {
	protected NormalSwitchCase initElement(Expression[] additionalConds) {
		var nscInit = new NormalSwitchCaseInitialiser();
		var nsc = nscInit.instantiate();
		Assertions.assertTrue(nscInit.addAdditionalConditions(nsc, additionalConds));
		return nsc;
	}

	@Test
	public void testAdditionalCondition() {
		this.setResourceFileTestIdentifier("testAdditionalCondition");

		var objOne = this.initElement(new Expression[] { this.createMinimalFalseEE() });
		var objTwo = this.initElement(new Expression[] { this.createMinimalTrueNEE() });

		this.testSimilarity(objOne, objTwo, StatementsPackage.Literals.NORMAL_SWITCH_CASE__ADDITIONAL_CONDITIONS);
	}
	
	@Test
	public void testAdditionalConditionNull() {
		this.setResourceFileTestIdentifier("testAdditionalConditionNull");
		
		var objOne = this.initElement(new Expression[] { this.createMinimalFalseEE() });
		var objTwo = new NormalSwitchCaseInitialiser().instantiate();
		
		this.testSimilarity(objOne, objTwo, StatementsPackage.Literals.NORMAL_SWITCH_CASE__ADDITIONAL_CONDITIONS);
	}
}
