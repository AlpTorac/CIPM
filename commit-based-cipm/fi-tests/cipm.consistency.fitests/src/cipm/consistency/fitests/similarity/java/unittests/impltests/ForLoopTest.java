package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.ForLoop;
import org.emftext.language.java.statements.StatementsPackage;
import org.emftext.language.java.statements.ForLoopInitializer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.statements.ForLoopInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesExpressions;

public class ForLoopTest extends EObjectSimilarityTest implements UsesExpressions {
	protected ForLoop initElement(ForLoopInitializer fli, Expression[] exprs) {
		var flInit = new ForLoopInitialiser();
		var fl = flInit.instantiate();
		Assertions.assertTrue(flInit.setInit(fl, fli));
		Assertions.assertTrue(flInit.addUpdates(fl, exprs));
		return fl;
	}
	
	@Test
	public void testInit() {
		this.setResourceFileTestIdentifier("testInit");
		
		var objOne = this.initElement(this.createExprList(), null);
		var objTwo = this.initElement(this.createExprList(this.createMinimalFalseEE()), null);
		
		this.testSimilarity(objOne, objTwo, StatementsPackage.Literals.FOR_LOOP__INIT);
	}
	
	@Test
	public void testUpdate() {
		this.setResourceFileTestIdentifier("testUpdate");
		
		var objOne = this.initElement(null, new Expression[] {this.createMinimalFalseEE()});
		var objTwo = this.initElement(null, new Expression[] {this.createMinimalTrueNEE()});
		
		this.testSimilarity(objOne, objTwo, StatementsPackage.Literals.FOR_LOOP__UPDATES);
	}
}
