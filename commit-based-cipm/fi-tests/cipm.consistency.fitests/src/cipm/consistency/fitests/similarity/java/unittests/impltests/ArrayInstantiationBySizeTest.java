package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.arrays.ArrayInstantiationBySize;
import org.emftext.language.java.expressions.Expression;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.arrays.ArrayInstantiationBySizeInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesExpressions;

public class ArrayInstantiationBySizeTest extends EObjectSimilarityTest implements UsesExpressions {
	protected ArrayInstantiationBySize initElement(Expression[] sizes) {
		var aibsInit = new ArrayInstantiationBySizeInitialiser();
		var aibs = aibsInit.instantiate();
		aibsInit.addSizes(aibs, sizes);
		return aibs;
	}
	
	@Test
	public void testSize() {
		this.setResourceFileTestIdentifier("testSize");
		
		var objOne = this.initElement(new Expression[] {this.createInteger(1)});
		var objTwo = this.initElement(new Expression[] {this.createInteger(2)});
		
		this.testX(objOne, objTwo, false);
	}
}
