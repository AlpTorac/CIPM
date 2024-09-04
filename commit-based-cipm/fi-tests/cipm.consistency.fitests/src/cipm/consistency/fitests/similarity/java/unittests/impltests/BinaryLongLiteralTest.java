package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.literals.BinaryLongLiteral;
import org.emftext.language.java.literals.LiteralsPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.literals.BinaryLongLiteralInitialiser;

public class BinaryLongLiteralTest extends EObjectSimilarityTest {
	protected BinaryLongLiteral initElement(long val) {
		var init = new BinaryLongLiteralInitialiser();
		var lit = init.instantiate();
		Assertions.assertTrue(init.setBinaryValue(lit, val));
		return lit;
	}

	@Test
	public void testBinaryValue() {
		this.testSimilarity(this.initElement(1), this.initElement(2),
				LiteralsPackage.Literals.BINARY_LONG_LITERAL__BINARY_VALUE);
	}

	@Test
	public void testBinaryValueNullCheck() {
		this.testSimilarity(this.initElement(1), new BinaryLongLiteralInitialiser().instantiate(),
				LiteralsPackage.Literals.BINARY_LONG_LITERAL__BINARY_VALUE);
	}
}