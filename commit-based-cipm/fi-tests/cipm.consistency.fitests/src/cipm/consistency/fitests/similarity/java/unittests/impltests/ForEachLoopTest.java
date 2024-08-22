package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.parameters.OrdinaryParameter;
import org.emftext.language.java.statements.ForEachLoop;
import org.emftext.language.java.statements.StatementsPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.statements.ForEachLoopInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesExpressions;
import cipm.consistency.fitests.similarity.java.unittests.UsesParameters;

public class ForEachLoopTest extends EObjectSimilarityTest implements UsesExpressions, UsesParameters {
	protected ForEachLoop initElement(Expression col, OrdinaryParameter next) {
		var felInit = new ForEachLoopInitialiser();
		var fel = felInit.instantiate();
		Assertions.assertTrue(felInit.setCollection(fel, col));
		Assertions.assertTrue(felInit.setNext(fel, next));
		return fel;
	}

	@Test
	public void testCollection() {
		this.setResourceFileTestIdentifier("testCollection");

		var objOne = this.initElement(this.createMinimalFalseEE(), null);
		var objTwo = this.initElement(this.createMinimalTrueNEE(), null);

		this.testSimilarity(objOne, objTwo, StatementsPackage.Literals.FOR_EACH_LOOP__COLLECTION);
	}
	
	@Test
	public void testCollectionNull() {
		this.setResourceFileTestIdentifier("testCollectionNull");
		
		var objOne = this.initElement(this.createMinimalFalseEE(), null);
		var objTwo = new ForEachLoopInitialiser().instantiate();
		
		this.testSimilarity(objOne, objTwo, StatementsPackage.Literals.FOR_EACH_LOOP__COLLECTION);
	}

	@Test
	public void testNext() {
		this.setResourceFileTestIdentifier("testNext");

		var objOne = this.initElement(null, this.createMinimalOrdParamWithClsTarget("param1", "cls1"));
		var objTwo = this.initElement(null, this.createMinimalOrdParamWithClsTarget("param2", "cls2"));

		this.testSimilarity(objOne, objTwo, StatementsPackage.Literals.FOR_EACH_LOOP__NEXT);
	}
	
	@Test
	public void testNextNull() {
		this.setResourceFileTestIdentifier("testNextNull");
		
		var objOne = this.initElement(null, this.createMinimalOrdParamWithClsTarget("param1", "cls1"));
		var objTwo = new ForEachLoopInitialiser().instantiate();
		
		this.testSimilarity(objOne, objTwo, StatementsPackage.Literals.FOR_EACH_LOOP__NEXT);
	}
}
