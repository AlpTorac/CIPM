package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.literals.DecimalDoubleLiteral;
import org.emftext.language.java.literals.LiteralsPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.literals.DecimalDoubleLiteralInitialiser;

public class DecimalDoubleLiteralTest extends EObjectSimilarityTest {
	protected DecimalDoubleLiteral initElement(double val) {
		var init = new DecimalDoubleLiteralInitialiser();
		var lit = init.instantiate();
		Assertions.assertTrue(init.setDecimalValue(lit, val));
		return lit;
	}

	@Test
	public void testDecimalValue() {
		this.setResourceFileTestIdentifier("testDecimalValue");

		this.testSimilarity(this.initElement(1.5d), this.initElement(2.5d),
				LiteralsPackage.Literals.DECIMAL_DOUBLE_LITERAL__DECIMAL_VALUE);
	}
}