package cipm.consistency.fitests.similarity.jamopp.unittests.impltests;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.NormalSwitchRule;
import org.emftext.language.java.statements.StatementsPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesExpressions;
import cipm.consistency.initialisers.jamopp.statements.NormalSwitchRuleInitialiser;

public class NormalSwitchRuleTest extends AbstractJaMoPPSimilarityTest implements UsesExpressions {
	private Expression ac1;
	private Expression ac2;

	protected NormalSwitchRule initElement(Expression[] additionalConds) {
		var nsrInit = new NormalSwitchRuleInitialiser();
		var nsr = nsrInit.instantiate();
		Assertions.assertTrue(nsrInit.addAdditionalConditions(nsr, additionalConds));
		return nsr;
	}

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		ac1 = this.createMinimalFalseEE();
		ac2 = this.createMinimalTrueNEE();
		Assertions.assertFalse(this.isSimilar(ac1, ac2));
	}

	@Test
	public void testAdditionalCondition() {
		var objOne = this.initElement(new Expression[] { this.cloneEObjWithContainers(ac1) });
		var objTwo = this.initElement(new Expression[] { this.cloneEObjWithContainers(ac2) });

		this.testSimilarity(objOne, objTwo, StatementsPackage.Literals.NORMAL_SWITCH_RULE__ADDITIONAL_CONDITIONS);
	}

	@Test
	public void testAdditionalConditionSize() {
		var objOne = this
				.initElement(new Expression[] { this.cloneEObjWithContainers(ac1), this.cloneEObjWithContainers(ac2) });
		var objTwo = this.initElement(new Expression[] { this.cloneEObjWithContainers(ac1) });

		this.testSimilarity(objOne, objTwo, StatementsPackage.Literals.NORMAL_SWITCH_RULE__ADDITIONAL_CONDITIONS);
	}

	@Test
	public void testAdditionalConditionPosition() {
		var objOne = this
				.initElement(new Expression[] { this.cloneEObjWithContainers(ac1), this.cloneEObjWithContainers(ac2) });
		var objTwo = this
				.initElement(new Expression[] { this.cloneEObjWithContainers(ac2), this.cloneEObjWithContainers(ac1) });

		this.testSimilarity(objOne, objTwo, StatementsPackage.Literals.NORMAL_SWITCH_RULE__ADDITIONAL_CONDITIONS);
	}

	@Test
	public void testAdditionalConditionDuplication() {
		var objOne = this
				.initElement(new Expression[] { this.cloneEObjWithContainers(ac1), this.cloneEObjWithContainers(ac1) });
		var objTwo = this.initElement(new Expression[] { this.cloneEObjWithContainers(ac1) });

		this.testSimilarity(objOne, objTwo, StatementsPackage.Literals.NORMAL_SWITCH_RULE__ADDITIONAL_CONDITIONS);
	}

	@Test
	public void testAdditionalConditionNullCheck() {
		this.testSimilarityNullCheck(this.initElement(new Expression[] { this.cloneEObjWithContainers(ac1) }),
				new NormalSwitchRuleInitialiser(), false,
				StatementsPackage.Literals.NORMAL_SWITCH_RULE__ADDITIONAL_CONDITIONS);
	}
}
