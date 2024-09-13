package cipm.consistency.fitests.similarity.eobject.java.unittests.impltests;

import org.emftext.language.java.literals.HexIntegerLiteral;
import org.emftext.language.java.literals.LiteralsPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.eobject.java.AbstractEObjectJavaSimilarityTest;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.literals.HexIntegerLiteralInitialiser;

public class HexIntegerLiteralTest extends AbstractEObjectJavaSimilarityTest {
	protected HexIntegerLiteral initElement(int val) {
		var init = new HexIntegerLiteralInitialiser();
		var lit = init.instantiate();
		Assertions.assertTrue(init.setHexValue(lit, val));
		return lit;
	}

	@Test
	public void testHexValue() {
		this.testSimilarity(this.initElement(1), this.initElement(2),
				LiteralsPackage.Literals.HEX_INTEGER_LITERAL__HEX_VALUE);
	}

	@Test
	public void testHexValueNullCheck() {
		this.testSimilarityNullCheck(this.initElement(1), new HexIntegerLiteralInitialiser(), false,
				LiteralsPackage.Literals.HEX_INTEGER_LITERAL__HEX_VALUE);
	}
}