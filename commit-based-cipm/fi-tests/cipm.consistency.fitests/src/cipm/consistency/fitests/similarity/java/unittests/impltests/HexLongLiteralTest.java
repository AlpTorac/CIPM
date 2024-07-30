package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.literals.HexLongLiteral;
import org.emftext.language.java.literals.LiteralsPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.literals.HexLongLiteralInitialiser;

public class HexLongLiteralTest extends EObjectSimilarityTest {
	protected HexLongLiteral initElement(long val) {
		var init = new HexLongLiteralInitialiser();
		var lit = init.instantiate();
		Assertions.assertTrue(init.setHexValue(lit, val));
		return lit;
	}

	@Test
	public void testHexValue() {
		this.setResourceFileTestIdentifier("testHexValue");

		this.testSimilarity(this.initElement(1), this.initElement(2),
				LiteralsPackage.Literals.HEX_LONG_LITERAL__HEX_VALUE);
	}
}