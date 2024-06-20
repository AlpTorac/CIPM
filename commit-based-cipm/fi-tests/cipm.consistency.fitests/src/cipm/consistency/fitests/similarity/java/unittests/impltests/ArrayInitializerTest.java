package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.arrays.ArrayInitializationValue;
import org.emftext.language.java.arrays.ArrayInitializer;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.arrays.ArrayInitializerInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesExpressions;

public class ArrayInitializerTest extends EObjectSimilarityTest implements UsesExpressions {
	protected ArrayInitializer initElement(ArrayInitializationValue[] aivs) {
		var aiInit = new ArrayInitializerInitialiser();
		var ai = aiInit.instantiate();
		aiInit.minimalInitialisation(ai);
		aiInit.addInitialValues(ai, aivs);
		return ai;
	}
	
	@Test
	public void testInitialValues() {
		this.setResourceFileTestIdentifier("testInitialValues");
		
		var objOne = this.initElement(new ArrayInitializationValue[] {this.createInteger(1)});
		var objTwo = this.initElement(new ArrayInitializationValue[] {this.createInteger(2)});
		
		this.testX(objOne, objTwo, false);
	}
}
