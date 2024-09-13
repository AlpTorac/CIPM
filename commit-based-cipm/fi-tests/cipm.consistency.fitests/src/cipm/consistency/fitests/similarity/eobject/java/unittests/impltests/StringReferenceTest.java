package cipm.consistency.fitests.similarity.eobject.java.unittests.impltests;

import org.emftext.language.java.references.ReferencesPackage;
import org.emftext.language.java.references.StringReference;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.eobject.java.AbstractEObjectJavaSimilarityTest;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.references.StringReferenceInitialiser;

public class StringReferenceTest extends AbstractEObjectJavaSimilarityTest {
	protected StringReference initElement(String val) {
		var srInit = new StringReferenceInitialiser();
		var sr = srInit.instantiate();
		Assertions.assertTrue(srInit.setValue(sr, val));
		return sr;
	}

	@Test
	public void testValue() {
		var objOne = this.initElement("val1");
		var objTwo = this.initElement("val2");

		this.testSimilarity(objOne, objTwo, ReferencesPackage.Literals.STRING_REFERENCE__VALUE);
	}

	@Test
	public void testValueNullCheck() {
		this.testSimilarityNullCheck(this.initElement("val1"), new StringReferenceInitialiser(), false,
				ReferencesPackage.Literals.STRING_REFERENCE__VALUE);
	}
}
