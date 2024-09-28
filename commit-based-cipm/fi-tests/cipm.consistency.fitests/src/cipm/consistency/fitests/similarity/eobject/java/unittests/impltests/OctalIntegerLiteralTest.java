package cipm.consistency.fitests.similarity.eobject.java.unittests.impltests;

import org.emftext.language.java.literals.LiteralsPackage;
import org.emftext.language.java.literals.OctalIntegerLiteral;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.eobject.java.AbstractEObjectJavaSimilarityTest;
import cipm.consistency.initialisers.eobject.java.literals.OctalIntegerLiteralInitialiser;

public class OctalIntegerLiteralTest extends AbstractEObjectJavaSimilarityTest {
	protected OctalIntegerLiteral initElement(int val) {
		var init = new OctalIntegerLiteralInitialiser();
		var lit = init.instantiate();
		Assertions.assertTrue(init.setOctalValue(lit, val));
		return lit;
	}

	@Test
	public void testOctalValue() {
		this.testSimilarity(this.initElement(1), this.initElement(2),
				LiteralsPackage.Literals.OCTAL_INTEGER_LITERAL__OCTAL_VALUE);
	}

	@Test
	public void testOctalValueNullCheck() {
		this.testSimilarityNullCheck(this.initElement(1), new OctalIntegerLiteralInitialiser(), false,
				LiteralsPackage.Literals.OCTAL_INTEGER_LITERAL__OCTAL_VALUE);
	}
}