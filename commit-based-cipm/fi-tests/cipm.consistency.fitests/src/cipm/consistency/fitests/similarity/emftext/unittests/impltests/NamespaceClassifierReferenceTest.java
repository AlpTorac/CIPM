package cipm.consistency.fitests.similarity.emftext.unittests.impltests;

import org.emftext.language.java.types.ClassifierReference;
import org.emftext.language.java.types.NamespaceClassifierReference;
import org.emftext.language.java.types.TypesPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.emftext.AbstractEMFTextSimilarityTest;
import cipm.consistency.fitests.similarity.emftext.unittests.UsesTypeReferences;
import cipm.consistency.initialisers.emftext.types.NamespaceClassifierReferenceInitialiser;

public class NamespaceClassifierReferenceTest extends AbstractEMFTextSimilarityTest implements UsesTypeReferences {
	protected NamespaceClassifierReference initElement(ClassifierReference[] clsRefs) {
		var ncrInit = new NamespaceClassifierReferenceInitialiser();
		var ncr = ncrInit.instantiate();
		Assertions.assertTrue(ncrInit.addClassifierReferences(ncr, clsRefs));
		return ncr;
	}

	@Test
	public void testClassifierReference() {
		var objOne = this.initElement(new ClassifierReference[] { this.createMinimalClsRef("cls1") });
		var objTwo = this.initElement(new ClassifierReference[] { this.createMinimalClsRef("cls2") });

		this.testSimilarity(objOne, objTwo,
				TypesPackage.Literals.NAMESPACE_CLASSIFIER_REFERENCE__CLASSIFIER_REFERENCES);
	}

	@Test
	public void testClassifierReferenceSize() {
		var objOne = this.initElement(
				new ClassifierReference[] { this.createMinimalClsRef("cls1"), this.createMinimalClsRef("cls2") });
		var objTwo = this.initElement(new ClassifierReference[] { this.createMinimalClsRef("cls1") });

		this.testSimilarity(objOne, objTwo,
				TypesPackage.Literals.NAMESPACE_CLASSIFIER_REFERENCE__CLASSIFIER_REFERENCES);
	}

	@Test
	public void testClassifierReferenceNullCheck() {
		this.testSimilarityNullCheck(this.initElement(new ClassifierReference[] { this.createMinimalClsRef("cls1") }),
				new NamespaceClassifierReferenceInitialiser(), false,
				TypesPackage.Literals.NAMESPACE_CLASSIFIER_REFERENCE__CLASSIFIER_REFERENCES);
	}
}
