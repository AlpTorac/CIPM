package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.literals.DecimalIntegerLiteral;
import org.emftext.language.java.literals.LiteralsPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.literals.DecimalIntegerLiteralInitialiser;

public class DecimalIntegerLiteralTest extends EObjectSimilarityTest {
	protected DecimalIntegerLiteral initElement(int val) {
		var init = new DecimalIntegerLiteralInitialiser();
		var lit = init.instantiate();
		Assertions.assertTrue(init.setDecimalValue(lit, val));
		return lit;
	}

	@Test
	public void testDecimalValue() {
		this.setResourceFileTestIdentifier("testDecimalValue");

		this.testSimilarity(this.initElement(1), this.initElement(2),
				LiteralsPackage.Literals.DECIMAL_INTEGER_LITERAL__DECIMAL_VALUE);
	}
}