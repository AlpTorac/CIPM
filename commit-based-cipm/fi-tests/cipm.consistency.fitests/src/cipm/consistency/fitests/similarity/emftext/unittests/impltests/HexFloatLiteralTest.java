package cipm.consistency.fitests.similarity.emftext.unittests.impltests;

import org.emftext.language.java.literals.HexFloatLiteral;
import org.emftext.language.java.literals.LiteralsPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.emftext.AbstractEMFTextSimilarityTest;
import cipm.consistency.initialisers.emftext.literals.HexFloatLiteralInitialiser;

public class HexFloatLiteralTest extends AbstractEMFTextSimilarityTest {
	protected HexFloatLiteral initElement(float val) {
		var init = new HexFloatLiteralInitialiser();
		var lit = init.instantiate();
		Assertions.assertTrue(init.setHexValue(lit, val));
		return lit;
	}

	@Test
	public void testHexValue() {
		this.testSimilarity(this.initElement(1.5f), this.initElement(2.5f),
				LiteralsPackage.Literals.HEX_FLOAT_LITERAL__HEX_VALUE);
	}

	@Test
	public void testHexValueNullCheck() {
		this.testSimilarityNullCheck(this.initElement(1.5f), new HexFloatLiteralInitialiser(), false,
				LiteralsPackage.Literals.HEX_FLOAT_LITERAL__HEX_VALUE);
	}
}