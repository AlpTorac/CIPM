package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.literals.CharacterLiteral;
import org.emftext.language.java.literals.LiteralsPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.literals.CharacterLiteralInitialiser;

public class CharacterLiteralTest extends EObjectSimilarityTest {
	protected CharacterLiteral initElement(String val) {
		var init = new CharacterLiteralInitialiser();
		var lit = init.instantiate();
		Assertions.assertTrue(init.setValue(lit, val));
		return lit;
	}
	
	@Test
	public void testValue() {
		this.setResourceFileTestIdentifier("testValue");
		
		this.testSimilarity(this.initElement("a"),
				this.initElement("b"),
				LiteralsPackage.Literals.CHARACTER_LITERAL__VALUE);
	}
}