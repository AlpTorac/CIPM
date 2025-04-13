package cipm.consistency.fitests.similarity.jamopp.unittests.impltests;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.parameters.OrdinaryParameter;
import org.emftext.language.java.statements.ForEachLoop;
import org.emftext.language.java.statements.StatementsPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesExpressions;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesParameters;
import cipm.consistency.initialisers.jamopp.statements.ForEachLoopInitialiser;

public class ForEachLoopTest extends AbstractJaMoPPSimilarityTest implements UsesExpressions, UsesParameters {
	private Expression col1;
	private Expression col2;
	private OrdinaryParameter next1;
	private OrdinaryParameter next2;

	protected ForEachLoop initElement(Expression col, OrdinaryParameter next) {
		var felInit = new ForEachLoopInitialiser();
		var fel = felInit.instantiate();
		Assertions.assertTrue(felInit.setCollection(fel, col));
		Assertions.assertTrue(felInit.setNext(fel, next));
		return fel;
	}

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		col1 = this.createMinimalFalseEE();
		col2 = this.createMinimalTrueNEE();
		Assertions.assertFalse(this.isSimilar(col1, col2));

		next1 = this.createMinimalOrdParamWithClsTarget("param1", "cls1");
		next2 = this.createMinimalOrdParamWithClsTarget("param2", "cls2");
		Assertions.assertFalse(this.isSimilar(next1, next2));
	}

	@Test
	public void testCollection() {
		var objOne = this.initElement(this.cloneEObjWithContainers(col1), null);
		var objTwo = this.initElement(this.cloneEObjWithContainers(col2), null);

		this.testSimilarity(objOne, objTwo, StatementsPackage.Literals.FOR_EACH_LOOP__COLLECTION);
	}

	@Test
	public void testCollectionNullCheck() {
		this.testSimilarityNullCheck(this.initElement(this.cloneEObjWithContainers(col1), null),
				new ForEachLoopInitialiser(), false, StatementsPackage.Literals.FOR_EACH_LOOP__COLLECTION);
	}

	@Test
	public void testNext() {
		var objOne = this.initElement(null, this.cloneEObjWithContainers(next1));
		var objTwo = this.initElement(null, this.cloneEObjWithContainers(next2));

		this.testSimilarity(objOne, objTwo, StatementsPackage.Literals.FOR_EACH_LOOP__NEXT);
	}

	@Test
	public void testNextNullCheck() {
		this.testSimilarityNullCheck(this.initElement(null, this.cloneEObjWithContainers(next1)),
				new ForEachLoopInitialiser(), false, StatementsPackage.Literals.FOR_EACH_LOOP__NEXT);
	}
}
