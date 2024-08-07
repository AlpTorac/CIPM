package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.literals.DecimalLongLiteral;
import org.emftext.language.java.literals.LiteralsPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.literals.DecimalLongLiteralInitialiser;

public class DecimalLongLiteralTest extends EObjectSimilarityTest {
	protected DecimalLongLiteral initElement(long val) {
		var init = new DecimalLongLiteralInitialiser();
		var lit = init.instantiate();
		Assertions.assertTrue(init.setDecimalValue(lit, val));
		return lit;
	}

	@Test
	public void testDecimalValue() {
		this.setResourceFileTestIdentifier("testDecimalValue");

		this.testSimilarity(this.initElement(1), this.initElement(2),
				LiteralsPackage.Literals.DECIMAL_LONG_LITERAL__DECIMAL_VALUE);
	}
}