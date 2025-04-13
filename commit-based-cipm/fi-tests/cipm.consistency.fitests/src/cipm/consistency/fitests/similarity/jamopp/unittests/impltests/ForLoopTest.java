package cipm.consistency.fitests.similarity.jamopp.unittests.impltests;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.ForLoop;
import org.emftext.language.java.statements.StatementsPackage;
import org.emftext.language.java.statements.ForLoopInitializer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesExpressions;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesLocalVariables;
import cipm.consistency.initialisers.jamopp.statements.ForLoopInitialiser;

public class ForLoopTest extends AbstractJaMoPPSimilarityTest implements UsesExpressions, UsesLocalVariables {
	private ForLoopInitializer flInit1;
	private ForLoopInitializer flInit2;
	private Expression updateExpr1;
	private Expression updateExpr2;

	protected ForLoop initElement(ForLoopInitializer flInit, Expression[] updateExprs) {
		var init = new ForLoopInitialiser();
		var fl = init.instantiate();
		Assertions.assertTrue(init.setInit(fl, flInit));
		Assertions.assertTrue(init.addUpdates(fl, updateExprs));
		return fl;
	}

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		flInit1 = this.createExprList();
		flInit2 = this.createMinimalLV("lv");
		Assertions.assertFalse(this.isSimilar(flInit1, flInit2));

		updateExpr1 = this.createMinimalFalseEE();
		updateExpr2 = this.createMinimalTrueNEE();
		Assertions.assertFalse(this.isSimilar(updateExpr1, updateExpr2));
	}

	@Test
	public void testInit() {
		var objOne = this.initElement(this.cloneEObjWithContainers(flInit2), null);
		var objTwo = this.initElement(this.cloneEObjWithContainers(flInit1), null);

		this.testSimilarity(objOne, objTwo, StatementsPackage.Literals.FOR_LOOP__INIT);
	}

	@Test
	public void testInitNullCheck() {
		this.testSimilarityNullCheck(this.initElement(this.cloneEObjWithContainers(flInit2), null),
				new ForLoopInitialiser(), false, StatementsPackage.Literals.FOR_LOOP__INIT);
	}

	@Test
	public void testUpdate() {
		var objOne = this.initElement(null, new Expression[] { this.cloneEObjWithContainers(updateExpr1) });
		var objTwo = this.initElement(null, new Expression[] { this.cloneEObjWithContainers(updateExpr2) });

		this.testSimilarity(objOne, objTwo, StatementsPackage.Literals.FOR_LOOP__UPDATES);
	}

	@Test
	public void testUpdateSize() {
		var objOne = this.initElement(null, new Expression[] { this.cloneEObjWithContainers(updateExpr1),
				this.cloneEObjWithContainers(updateExpr2) });
		var objTwo = this.initElement(null, new Expression[] { this.cloneEObjWithContainers(updateExpr1) });

		this.testSimilarity(objOne, objTwo, StatementsPackage.Literals.FOR_LOOP__UPDATES);
	}

	@Test
	public void testUpdatePosition() {
		var objOne = this.initElement(null, new Expression[] { this.cloneEObjWithContainers(updateExpr1),
				this.cloneEObjWithContainers(updateExpr2) });
		var objTwo = this.initElement(null, new Expression[] { this.cloneEObjWithContainers(updateExpr2),
				this.cloneEObjWithContainers(updateExpr1) });

		this.testSimilarity(objOne, objTwo, StatementsPackage.Literals.FOR_LOOP__UPDATES);
	}

	@Test
	public void testUpdateDuplication() {
		var objOne = this.initElement(null, new Expression[] { this.cloneEObjWithContainers(updateExpr1),
				this.cloneEObjWithContainers(updateExpr1) });
		var objTwo = this.initElement(null, new Expression[] { this.cloneEObjWithContainers(updateExpr1) });

		this.testSimilarity(objOne, objTwo, StatementsPackage.Literals.FOR_LOOP__UPDATES);
	}

	@Test
	public void testUpdateNullCheck() {
		this.testSimilarityNullCheck(
				this.initElement(null, new Expression[] { this.cloneEObjWithContainers(updateExpr1) }),
				new ForLoopInitialiser(), false, StatementsPackage.Literals.FOR_LOOP__UPDATES);
	}
}
