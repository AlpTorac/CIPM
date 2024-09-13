package cipm.consistency.fitests.similarity.java.eobject.unittests.impltests;

import org.emftext.language.java.literals.CharacterLiteral;
import org.emftext.language.java.literals.LiteralsPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.eobject.initialiser.literals.CharacterLiteralInitialiser;

public class CharacterLiteralTest extends EObjectSimilarityTest {
	protected CharacterLiteral initElement(String val) {
		var init = new CharacterLiteralInitialiser();
		var lit = init.instantiate();
		Assertions.assertTrue(init.setValue(lit, val));
		return lit;
	}

	@Test
	public void testValue() {
		this.testSimilarity(this.initElement("a"), this.initElement("b"),
				LiteralsPackage.Literals.CHARACTER_LITERAL__VALUE);
	}

	@Test
	public void testValueNullCheck() {
		this.testSimilarityNullCheck(this.initElement("a"), new CharacterLiteralInitialiser(), false,
				LiteralsPackage.Literals.CHARACTER_LITERAL__VALUE);
	}
}