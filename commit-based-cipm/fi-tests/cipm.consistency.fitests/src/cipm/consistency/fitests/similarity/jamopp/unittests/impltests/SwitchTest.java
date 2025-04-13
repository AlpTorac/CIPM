package cipm.consistency.fitests.similarity.jamopp.unittests.impltests;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.StatementsPackage;
import org.emftext.language.java.statements.Switch;
import org.emftext.language.java.statements.SwitchCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesSwitchCases;
import cipm.consistency.initialisers.jamopp.statements.SwitchInitialiser;

public class SwitchTest extends AbstractJaMoPPSimilarityTest implements UsesSwitchCases {
	private SwitchCase case1;
	private SwitchCase case2;
	private Expression var1;
	private Expression var2;

	protected Switch initElement(SwitchCase[] cases, Expression var) {
		var swInit = new SwitchInitialiser();
		var sw = swInit.instantiate();
		Assertions.assertTrue(swInit.addCases(sw, cases));
		Assertions.assertTrue(swInit.setVariable(sw, var));
		return sw;
	}

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		case1 = this.createMinimalNSC();
		case2 = this.createMinimalDSC();
		Assertions.assertFalse(this.isSimilar(case1, case2));

		var1 = this.createMinimalSR("str1");
		var2 = this.createMinimalSR("str2");
		Assertions.assertFalse(this.isSimilar(var1, var2));
	}

	@Test
	public void testCase() {
		var objOne = this.initElement(new SwitchCase[] { this.cloneEObjWithContainers(case1) }, null);
		var objTwo = this.initElement(new SwitchCase[] { this.cloneEObjWithContainers(case2) }, null);

		this.testSimilarity(objOne, objTwo, StatementsPackage.Literals.SWITCH__CASES);
	}

	@Test
	public void testCaseSize() {
		var objOne = this.initElement(
				new SwitchCase[] { this.cloneEObjWithContainers(case1), this.cloneEObjWithContainers(case2) }, null);
		var objTwo = this.initElement(new SwitchCase[] { this.cloneEObjWithContainers(case1) }, null);

		this.testSimilarity(objOne, objTwo, StatementsPackage.Literals.SWITCH__CASES);
	}

	@Test
	public void testCasePosition() {
		var objOne = this.initElement(
				new SwitchCase[] { this.cloneEObjWithContainers(case1), this.cloneEObjWithContainers(case2) }, null);
		var objTwo = this.initElement(
				new SwitchCase[] { this.cloneEObjWithContainers(case2), this.cloneEObjWithContainers(case1) }, null);

		this.testSimilarity(objOne, objTwo, StatementsPackage.Literals.SWITCH__CASES);
	}

	@Test
	public void testCaseDuplication() {
		var objOne = this.initElement(
				new SwitchCase[] { this.cloneEObjWithContainers(case1), this.cloneEObjWithContainers(case1) }, null);
		var objTwo = this.initElement(new SwitchCase[] { this.cloneEObjWithContainers(case1) }, null);

		this.testSimilarity(objOne, objTwo, StatementsPackage.Literals.SWITCH__CASES);
	}

	@Test
	public void testCaseNullCheck() {
		this.testSimilarityNullCheck(this.initElement(new SwitchCase[] { this.cloneEObjWithContainers(case1) }, null),
				new SwitchInitialiser(), false, StatementsPackage.Literals.SWITCH__CASES);
	}

	@Test
	public void testVariable() {
		var objOne = this.initElement(null, this.cloneEObjWithContainers(var1));
		var objTwo = this.initElement(null, this.cloneEObjWithContainers(var2));

		this.testSimilarity(objOne, objTwo, StatementsPackage.Literals.SWITCH__VARIABLE);
	}

	@Test
	public void testVariableNullCheck() {
		this.testSimilarityNullCheck(this.initElement(null, this.cloneEObjWithContainers(var1)),
				new SwitchInitialiser(), false, StatementsPackage.Literals.SWITCH__VARIABLE);
	}
}
