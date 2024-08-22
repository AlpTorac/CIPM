package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.literals.BinaryIntegerLiteral;
import org.emftext.language.java.literals.LiteralsPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.literals.BinaryIntegerLiteralInitialiser;

public class BinaryIntegerLiteralTest extends EObjectSimilarityTest {
	protected BinaryIntegerLiteral initElement(int val) {
		var init = new BinaryIntegerLiteralInitialiser();
		var lit = init.instantiate();
		Assertions.assertTrue(init.setBinaryValue(lit, val));
		return lit;
	}

	@Test
	public void testBinaryValue() {
		this.setResourceFileTestIdentifier("testBinaryValue");

		this.testSimilarity(this.initElement(1), this.initElement(2),
				LiteralsPackage.Literals.BINARY_INTEGER_LITERAL__BINARY_VALUE);
	}

	@Test
	public void testBinaryValueNullCheck() {
		this.setResourceFileTestIdentifier("testBinaryValueNullCheck");

		this.testSimilarity(this.initElement(1), new BinaryIntegerLiteralInitialiser().instantiate(),
				LiteralsPackage.Literals.BINARY_INTEGER_LITERAL__BINARY_VALUE);
	}
}