package cipm.consistency.fitests.similarity.jamopp.unittests.impltests;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.NormalSwitchCase;
import org.emftext.language.java.statements.StatementsPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesExpressions;
import cipm.consistency.initialisers.jamopp.statements.NormalSwitchCaseInitialiser;

public class NormalSwitchCaseTest extends AbstractJaMoPPSimilarityTest implements UsesExpressions {
	private Expression ac1;
	private Expression ac2;

	protected NormalSwitchCase initElement(Expression[] additionalConds) {
		var nscInit = new NormalSwitchCaseInitialiser();
		var nsc = nscInit.instantiate();
		Assertions.assertTrue(nscInit.addAdditionalConditions(nsc, additionalConds));
		return nsc;
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

		this.testSimilarity(objOne, objTwo, StatementsPackage.Literals.NORMAL_SWITCH_CASE__ADDITIONAL_CONDITIONS);
	}

	@Test
	public void testAdditionalConditionSize() {
		var objOne = this
				.initElement(new Expression[] { this.cloneEObjWithContainers(ac1), this.cloneEObjWithContainers(ac2) });
		var objTwo = this.initElement(new Expression[] { this.cloneEObjWithContainers(ac1) });

		this.testSimilarity(objOne, objTwo, StatementsPackage.Literals.NORMAL_SWITCH_CASE__ADDITIONAL_CONDITIONS);
	}

	@Test
	public void testAdditionalConditionPosition() {
		var objOne = this
				.initElement(new Expression[] { this.cloneEObjWithContainers(ac1), this.cloneEObjWithContainers(ac2) });
		var objTwo = this
				.initElement(new Expression[] { this.cloneEObjWithContainers(ac2), this.cloneEObjWithContainers(ac1) });

		this.testSimilarity(objOne, objTwo, StatementsPackage.Literals.NORMAL_SWITCH_CASE__ADDITIONAL_CONDITIONS);
	}

	@Test
	public void testAdditionalConditionDuplication() {
		var objOne = this
				.initElement(new Expression[] { this.cloneEObjWithContainers(ac1), this.cloneEObjWithContainers(ac1) });
		var objTwo = this.initElement(new Expression[] { this.cloneEObjWithContainers(ac1) });

		this.testSimilarity(objOne, objTwo, StatementsPackage.Literals.NORMAL_SWITCH_CASE__ADDITIONAL_CONDITIONS);
	}

	@Test
	public void testAdditionalConditionNullCheck() {
		this.testSimilarityNullCheck(this.initElement(new Expression[] { this.cloneEObjWithContainers(ac1) }),
				new NormalSwitchCaseInitialiser(), false,
				StatementsPackage.Literals.NORMAL_SWITCH_CASE__ADDITIONAL_CONDITIONS);
	}
}
