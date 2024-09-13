package cipm.consistency.fitests.similarity.eobject.java.unittests.impltests;

import org.emftext.language.java.literals.DecimalFloatLiteral;
import org.emftext.language.java.literals.LiteralsPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.eobject.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.literals.DecimalFloatLiteralInitialiser;

public class DecimalFloatLiteralTest extends EObjectSimilarityTest {
	protected DecimalFloatLiteral initElement(float val) {
		var init = new DecimalFloatLiteralInitialiser();
		var lit = init.instantiate();
		Assertions.assertTrue(init.setDecimalValue(lit, val));
		return lit;
	}

	@Test
	public void testDecimalValue() {
		this.testSimilarity(this.initElement(1.5f), this.initElement(2.5f),
				LiteralsPackage.Literals.DECIMAL_FLOAT_LITERAL__DECIMAL_VALUE);
	}

	@Test
	public void testDecimalValueNullCheck() {
		this.testSimilarityNullCheck(this.initElement(1.5f), new DecimalFloatLiteralInitialiser(), false,
				LiteralsPackage.Literals.DECIMAL_FLOAT_LITERAL__DECIMAL_VALUE);
	}
}