package cipm.consistency.fitests.similarity.emftext.unittests.impltests;

import org.emftext.language.java.references.ReferencesPackage;
import org.emftext.language.java.references.TextBlockReference;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.emftext.AbstractEMFTextSimilarityTest;
import cipm.consistency.initialisers.emftext.references.TextBlockReferenceInitialiser;

public class TextBlockReferenceTest extends AbstractEMFTextSimilarityTest {
	protected TextBlockReference initElement(String val) {
		var tbrInit = new TextBlockReferenceInitialiser();
		var tbr = tbrInit.instantiate();
		Assertions.assertTrue(tbrInit.setValue(tbr, val));
		return tbr;
	}

	@Test
	public void testValue() {
		var objOne = this.initElement("val1");
		var objTwo = this.initElement("val2");

		this.testSimilarity(objOne, objTwo, ReferencesPackage.Literals.TEXT_BLOCK_REFERENCE__VALUE);
	}

	@Test
	public void testValueNullCheck() {
		this.testSimilarityNullCheck(this.initElement("val1"), new TextBlockReferenceInitialiser(), false,
				ReferencesPackage.Literals.TEXT_BLOCK_REFERENCE__VALUE);
	}
}