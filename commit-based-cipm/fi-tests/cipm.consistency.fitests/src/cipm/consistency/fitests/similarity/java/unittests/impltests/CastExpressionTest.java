package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.expressions.CastExpression;
import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.expressions.MultiplicativeExpressionChild;
import org.emftext.language.java.types.TypeReference;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.expressions.CastExpressionInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesExpressions;
import cipm.consistency.fitests.similarity.java.unittests.UsesTypeReferences;

public class CastExpressionTest extends EObjectSimilarityTest implements UsesExpressions, UsesTypeReferences {
	protected CastExpression initElement(TypeReference[] trefs, MultiplicativeExpressionChild child,
			Expression gChild) {
		var ceInit = new CastExpressionInitialiser();
		var ce = ceInit.instantiate();
		ceInit.minimalInitialisation(ce);
		ceInit.addAdditionalBounds(ce, trefs);
		ceInit.setChild(ce, child);
		ceInit.setGeneralChild(ce, gChild);
		return ce;
	}
	
	@Test
	public void testAdditionalBould() {
		this.setResourceFileTestIdentifier("testAdditionalBould");
		
		this.compareX(
				this.initElement(new TypeReference[] {this.createMinimalClsRef("cls1")}, null, null),
				this.initElement(new TypeReference[] {this.createMinimalClsRef("cls2")}, null, null),
				false);
	}
	
	@Test
	public void testChild() {
		this.setResourceFileTestIdentifier("testChild");
		
		this.compareX(
				this.initElement(null, this.createInteger(1), null),
				this.initElement(null, this.createInteger(2), null),
				false);
	}
	
	@Test
	public void testGeneralChild() {
		this.setResourceFileTestIdentifier("testGeneralChild");
		
		this.compareX(
				this.initElement(null, null, this.createInteger(1)),
				this.initElement(null, null, this.createInteger(2)),
				false);
	}
}
