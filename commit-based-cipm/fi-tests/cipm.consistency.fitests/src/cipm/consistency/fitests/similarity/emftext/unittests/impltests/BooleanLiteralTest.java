package cipm.consistency.fitests.similarity.emftext.unittests.impltests;

import org.emftext.language.java.literals.BooleanLiteral;
import org.emftext.language.java.literals.LiteralsPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.emftext.AbstractEMFTextSimilarityTest;
import cipm.consistency.initialisers.emftext.literals.BooleanLiteralInitialiser;

public class BooleanLiteralTest extends AbstractEMFTextSimilarityTest {
	protected BooleanLiteral initElement(boolean val) {
		var init = new BooleanLiteralInitialiser();
		var lit = init.instantiate();
		Assertions.assertTrue(init.setValue(lit, val));
		return lit;
	}

	@Test
	public void testValue() {
		this.testSimilarity(this.initElement(true), this.initElement(false),
				LiteralsPackage.Literals.BOOLEAN_LITERAL__VALUE);
	}

	@Test
	public void testValueNullCheck() {
		this.testSimilarityNullCheck(this.initElement(true), new BooleanLiteralInitialiser(), false,
				LiteralsPackage.Literals.BOOLEAN_LITERAL__VALUE);
	}
}