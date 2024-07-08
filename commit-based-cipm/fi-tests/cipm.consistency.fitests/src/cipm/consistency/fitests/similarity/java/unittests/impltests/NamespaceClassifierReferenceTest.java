package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.types.ClassifierReference;
import org.emftext.language.java.types.NamespaceClassifierReference;
import org.emftext.language.java.types.TypesPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.types.NamespaceClassifierReferenceInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesTypeReferences;

public class NamespaceClassifierReferenceTest extends EObjectSimilarityTest implements UsesTypeReferences {
	protected NamespaceClassifierReference initElement(ClassifierReference[] refs) {
		var ncrInit = new NamespaceClassifierReferenceInitialiser();
		var ncr = ncrInit.instantiate();
		Assertions.assertTrue(ncrInit.addClassifierReferences(ncr, refs));
		return ncr;
	}
	
	@Test
	public void testClassifierReference() {
		this.setResourceFileTestIdentifier("testClassifierReference");
		
		var objOne = this.initElement(new ClassifierReference[] {this.createMinimalClsRef("cls1")});
		var objTwo = this.initElement(new ClassifierReference[] {this.createMinimalClsRef("cls2")});
		
		this.testX(objOne, objTwo, TypesPackage.Literals.NAMESPACE_CLASSIFIER_REFERENCE__CLASSIFIER_REFERENCES);
	}
}
