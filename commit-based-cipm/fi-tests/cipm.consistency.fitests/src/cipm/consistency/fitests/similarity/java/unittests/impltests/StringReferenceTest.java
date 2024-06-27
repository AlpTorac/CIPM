package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.references.ReferencesPackage;
import org.emftext.language.java.references.StringReference;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.references.StringReferenceInitialiser;

public class StringReferenceTest extends EObjectSimilarityTest {
	protected StringReference initElement(String val) {
		var srInit = new StringReferenceInitialiser();
		var sr = srInit.instantiate();
		srInit.minimalInitialisation(sr);
		srInit.setValue(sr, val);
		return sr;
	}
	
	@Test
	public void testValue() {
		this.setResourceFileTestIdentifier("testValue");
		
		var objOne = this.initElement("val1");
		var objTwo = this.initElement("val2");
		
		this.testX(objOne, objTwo, ReferencesPackage.Literals.STRING_REFERENCE__VALUE);
	}
}