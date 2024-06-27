package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.expressions.CastExpression;
import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.expressions.ExpressionsPackage;
import org.emftext.language.java.expressions.MultiplicativeExpressionChild;
import org.emftext.language.java.types.TypeReference;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.expressions.CastExpressionInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesExpressions;
import cipm.consistency.fitests.similarity.java.unittests.UsesTypeReferences;

/**
 * 
 * General child and child are the same. Therefore testChild is left out.
 * 
 * @author atora
 */
public class CastExpressionTest extends EObjectSimilarityTest implements UsesExpressions, UsesTypeReferences {
	protected CastExpression initElement(TypeReference[] trefs, MultiplicativeExpressionChild child,
			Expression gChild) {
		var ceInit = new CastExpressionInitialiser();
		var ce = ceInit.instantiate();
		ceInit.minimalInitialisation(ce);
		ceInit.addAdditionalBounds(ce, trefs);
		ceInit.setChild(ce, child);
		ceInit.setGeneralChild(ce, gChild);
		
		this.getLogger().info("Child and general child are the same: " + (ce.getChild() == ce.getGeneralChild()));
		
		return ce;
	}
	
	@Test
	public void testAdditionalBould() {
		this.setResourceFileTestIdentifier("testAdditionalBould");
		
		this.testX(
				this.initElement(new TypeReference[] {this.createMinimalClsRef("cls1")}, null, null),
				this.initElement(new TypeReference[] {this.createMinimalClsRef("cls2")}, null, null),
				ExpressionsPackage.Literals.CAST_EXPRESSION__ADDITIONAL_BOUNDS);
	}
	
	@Test
	public void testGeneralChild() {
		this.setResourceFileTestIdentifier("testGeneralChild");
		
		this.testX(
				this.initElement(null, null, this.createInteger(1)),
				this.initElement(null, null, this.createInteger(2)),
				ExpressionsPackage.Literals.CAST_EXPRESSION__GENERAL_CHILD);
	}
}