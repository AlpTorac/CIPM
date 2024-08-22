package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.literals.HexDoubleLiteral;
import org.emftext.language.java.literals.LiteralsPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.literals.HexDoubleLiteralInitialiser;

public class HexDoubleLiteralTest extends EObjectSimilarityTest {
	protected HexDoubleLiteral initElement(double val) {
		var init = new HexDoubleLiteralInitialiser();
		var lit = init.instantiate();
		Assertions.assertTrue(init.setHexValue(lit, val));
		return lit;
	}

	@Test
	public void testHexValue() {
		this.setResourceFileTestIdentifier("testHexValue");

		this.testSimilarity(this.initElement(1.5d), this.initElement(2.5d),
				LiteralsPackage.Literals.HEX_DOUBLE_LITERAL__HEX_VALUE);
	}
	
	@Test
	public void testHexValueNull() {
		this.setResourceFileTestIdentifier("testHexValueNull");
		
		this.testSimilarity(this.initElement(1.5d), new HexDoubleLiteralInitialiser().instantiate(),
				LiteralsPackage.Literals.HEX_DOUBLE_LITERAL__HEX_VALUE);
	}
}