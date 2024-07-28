package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.literals.HexFloatLiteral;
import org.emftext.language.java.literals.LiteralsPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.literals.HexFloatLiteralInitialiser;

public class HexFloatLiteralTest extends EObjectSimilarityTest {
	protected HexFloatLiteral initElement(float val) {
		var init = new HexFloatLiteralInitialiser();
		var lit = init.instantiate();
		Assertions.assertTrue(init.setHexValue(lit, val));
		return lit;
	}
	
	@Test
	public void testHexValue() {
		this.setResourceFileTestIdentifier("testHexValue");
		
		this.testSimilarity(this.initElement(1.5f),
				this.initElement(2.5f),
				LiteralsPackage.Literals.HEX_FLOAT_LITERAL__HEX_VALUE);
	}
}