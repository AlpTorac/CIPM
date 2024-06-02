package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.expressions.AssignmentExpressionChild;
import org.emftext.language.java.expressions.ConditionalExpression;
import org.emftext.language.java.expressions.ConditionalExpressionChild;
import org.emftext.language.java.expressions.Expression;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.expressions.ConditionalExpressionInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesExpressions;

public class ConditionalExpressionTest extends EObjectSimilarityTest implements UsesExpressions {
	protected ConditionalExpression initElement(ConditionalExpressionChild child,
			AssignmentExpressionChild exprChild,
			Expression exprIf,
			Expression geeExpr) {
		var ceInit = new ConditionalExpressionInitialiser();
		var ce = ceInit.instantiate();
		ceInit.minimalInitialisation(ce);
		ceInit.setChild(ce, child);
		ceInit.setExpressionChild(ce, exprChild);
		ceInit.setExpressionIf(ce, exprIf);
		ceInit.setGeneralExpressionElse(ce, geeExpr);
		return ce;
	}
	
	@Test
	public void testChild() {
		this.setResourceFileTestIdentifier("testChild");
		
		this.compareX(
				this.initElement(this.createInteger(1), null, null, null),
				this.initElement(this.createInteger(2), null, null, null),
				false);
	}
	
	@Test
	public void testExpressionChild() {
		this.setResourceFileTestIdentifier("testExpressionChild");
		
		this.compareX(
				this.initElement(null, this.createInteger(1), null, null),
				this.initElement(null, this.createInteger(2), null, null),
				false);
	}
	
	@Test
	public void testExpressionIf() {
		this.setResourceFileTestIdentifier("testExpressionIf");
		
		this.compareX(
				this.initElement(null, null, this.createInteger(1), null),
				this.initElement(null, null, this.createInteger(2), null),
				false);
	}
	
	@Test
	public void testGeneralExpressionElse() {
		this.setResourceFileTestIdentifier("testGeneralExpressionElse");
		
		this.compareX(
				this.initElement(null, null, null, this.createInteger(1)),
				this.initElement(null, null, null, this.createInteger(2)),
				false);
	}
}
