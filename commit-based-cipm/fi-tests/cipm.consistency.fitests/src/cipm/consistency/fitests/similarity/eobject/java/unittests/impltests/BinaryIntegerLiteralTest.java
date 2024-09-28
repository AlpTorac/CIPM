package cipm.consistency.fitests.similarity.eobject.java.unittests.impltests;

import org.emftext.language.java.literals.BinaryIntegerLiteral;
import org.emftext.language.java.literals.LiteralsPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.eobject.java.AbstractEObjectJavaSimilarityTest;
import cipm.consistency.initialisers.eobject.java.literals.BinaryIntegerLiteralInitialiser;

public class BinaryIntegerLiteralTest extends AbstractEObjectJavaSimilarityTest {
	protected BinaryIntegerLiteral initElement(int val) {
		var init = new BinaryIntegerLiteralInitialiser();
		var lit = init.instantiate();
		Assertions.assertTrue(init.setBinaryValue(lit, val));
		return lit;
	}

	@Test
	public void testBinaryValue() {
		this.testSimilarity(this.initElement(1), this.initElement(2),
				LiteralsPackage.Literals.BINARY_INTEGER_LITERAL__BINARY_VALUE);
	}

	@Test
	public void testBinaryValueNullCheck() {
		this.testSimilarityNullCheck(this.initElement(1), new BinaryIntegerLiteralInitialiser(), false,
				LiteralsPackage.Literals.BINARY_INTEGER_LITERAL__BINARY_VALUE);
	}
}