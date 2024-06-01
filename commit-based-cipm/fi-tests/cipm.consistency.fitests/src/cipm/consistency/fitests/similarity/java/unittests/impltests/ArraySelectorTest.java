package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.arrays.ArraySelector;
import org.emftext.language.java.expressions.Expression;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.arrays.ArraySelectorInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesExpressions;

public class ArraySelectorTest extends EObjectSimilarityTest implements UsesExpressions {
	protected ArraySelector initElement(Expression pos) {
		var asInit = new ArraySelectorInitialiser();
		var as = asInit.instantiate();
		asInit.minimalInitialisation(as);
		asInit.setPosition(as, pos);
		return as;
	}
	
	@Test
	public void testPosition() {
		this.setResourceFileTestIdentifier("testPosition");
		
		var objOne = this.initElement(this.createInteger(1));
		var objTwo = this.initElement(this.createInteger(2));
		
		this.compareX(objOne, objTwo, false);
	}
}
