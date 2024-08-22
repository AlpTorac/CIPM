package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.references.ReferencesPackage;
import org.emftext.language.java.references.TextBlockReference;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.references.TextBlockReferenceInitialiser;

public class TextBlockReferenceTest extends EObjectSimilarityTest {
	protected TextBlockReference initElement(String val) {
		var tbrInit = new TextBlockReferenceInitialiser();
		var tbr = tbrInit.instantiate();
		Assertions.assertTrue(tbrInit.setValue(tbr, val));
		return tbr;
	}

	@Test
	public void testValue() {
		this.setResourceFileTestIdentifier("testValue");

		var objOne = this.initElement("val1");
		var objTwo = this.initElement("val2");

		this.testSimilarity(objOne, objTwo, ReferencesPackage.Literals.TEXT_BLOCK_REFERENCE__VALUE);
	}
	
	@Test
	public void testValueNull() {
		this.setResourceFileTestIdentifier("testValueNull");
		
		var objOne = this.initElement("val1");
		var objTwo = new TextBlockReferenceInitialiser().instantiate();
		
		this.testSimilarity(objOne, objTwo, ReferencesPackage.Literals.TEXT_BLOCK_REFERENCE__VALUE);
	}
}
