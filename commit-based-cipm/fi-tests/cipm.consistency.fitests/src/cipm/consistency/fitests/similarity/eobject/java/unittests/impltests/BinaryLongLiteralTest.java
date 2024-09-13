package cipm.consistency.fitests.similarity.eobject.java.unittests.impltests;

import org.emftext.language.java.literals.BinaryLongLiteral;
import org.emftext.language.java.literals.LiteralsPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.eobject.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.literals.BinaryLongLiteralInitialiser;

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
		this.testSimilarityNullCheck(this.initElement(1), new BinaryLongLiteralInitialiser(), false,
				LiteralsPackage.Literals.BINARY_LONG_LITERAL__BINARY_VALUE);
	}
}