package cipm.consistency.fitests.similarity.eobject.java.unittests.impltests;

import org.emftext.language.java.literals.LiteralsPackage;
import org.emftext.language.java.literals.OctalLongLiteral;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.eobject.java.AbstractEObjectJavaSimilarityTest;
import cipm.consistency.initialisers.eobject.java.literals.OctalLongLiteralInitialiser;

public class OctalLongLiteralTest extends AbstractEObjectJavaSimilarityTest {
	protected OctalLongLiteral initElement(long val) {
		var init = new OctalLongLiteralInitialiser();
		var lit = init.instantiate();
		Assertions.assertTrue(init.setOctalValue(lit, val));
		return lit;
	}

	@Test
	public void testOctalValue() {
		this.testSimilarity(this.initElement(1), this.initElement(2),
				LiteralsPackage.Literals.OCTAL_LONG_LITERAL__OCTAL_VALUE);
	}

	@Test
	public void testOctalValueNullCheck() {
		this.testSimilarityNullCheck(this.initElement(1), new OctalLongLiteralInitialiser(), false,
				LiteralsPackage.Literals.OCTAL_LONG_LITERAL__OCTAL_VALUE);
	}
}