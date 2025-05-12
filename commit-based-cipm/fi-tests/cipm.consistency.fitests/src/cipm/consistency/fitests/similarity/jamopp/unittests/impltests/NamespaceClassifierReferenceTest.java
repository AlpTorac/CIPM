package cipm.consistency.fitests.similarity.jamopp.unittests.impltests;

import org.emftext.language.java.classifiers.Classifier;
import org.emftext.language.java.types.ClassifierReference;
import org.emftext.language.java.types.NamespaceClassifierReference;
import org.emftext.language.java.types.TypesPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesTypeReferences;
import cipm.consistency.initialisers.jamopp.types.ClassifierReferenceInitialiser;
import cipm.consistency.initialisers.jamopp.types.NamespaceClassifierReferenceInitialiser;

public class NamespaceClassifierReferenceTest extends AbstractJaMoPPSimilarityTest implements UsesTypeReferences {
	private Classifier target1;
	private Classifier target2;
	private ClassifierReference clsRef1;
	private ClassifierReference clsRef2;

	protected NamespaceClassifierReference initElement(Classifier target, ClassifierReference[] clsRefs) {
		var ncrInit = new NamespaceClassifierReferenceInitialiser();
		var ncr = ncrInit.instantiate();
		Assertions.assertTrue(ncrInit.setTarget(ncr, target));
		Assertions.assertTrue(ncrInit.addClassifierReferences(ncr, clsRefs));
		return ncr;
	}

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		target1 = this.createMinimalClassWithCU("cls1");
		target2 = this.createMinimalClassWithCU("cls2");
		Assertions.assertFalse(this.isSimilar(target1, target2));

		clsRef1 = this.createMinimalClsRef("cls1");
		clsRef2 = this.createMinimalClsRef("cls2");
		Assertions.assertFalse(this.isSimilar(clsRef1, clsRef2));
	}

	@Test
	public void testTarget() {
		var objOne = this.initElement(this.cloneEObjWithContainers(target1), null);
		var objTwo = this.initElement(this.cloneEObjWithContainers(target2), null);

		this.testSimilarity(objOne, objTwo, NamespaceClassifierReference.class,
				TypesPackage.Literals.CLASSIFIER_REFERENCE__TARGET);
	}

	@Test
	public void testTargetNullCheck() {
		this.testSimilarityNullCheck(this.initElement(this.cloneEObjWithContainers(target1), null),
				new ClassifierReferenceInitialiser(), false, NamespaceClassifierReference.class,
				TypesPackage.Literals.CLASSIFIER_REFERENCE__TARGET);
	}

	@Test
	public void testClassifierReference() {
		var objOne = this.initElement(null, new ClassifierReference[] { this.cloneEObjWithContainers(clsRef1) });
		var objTwo = this.initElement(null, new ClassifierReference[] { this.cloneEObjWithContainers(clsRef2) });

		this.testSimilarity(objOne, objTwo,
				TypesPackage.Literals.NAMESPACE_CLASSIFIER_REFERENCE__CLASSIFIER_REFERENCES);
	}

	@Test
	public void testClassifierReferenceSize() {
		var objOne = this.initElement(null, new ClassifierReference[] { this.cloneEObjWithContainers(clsRef1),
				this.cloneEObjWithContainers(clsRef2) });
		var objTwo = this.initElement(null, new ClassifierReference[] { this.cloneEObjWithContainers(clsRef1) });

		this.testSimilarity(objOne, objTwo,
				TypesPackage.Literals.NAMESPACE_CLASSIFIER_REFERENCE__CLASSIFIER_REFERENCES);
	}

	@Test
	public void testClassifierReferencePosition() {
		var objOne = this.initElement(null, new ClassifierReference[] { this.cloneEObjWithContainers(clsRef1),
				this.cloneEObjWithContainers(clsRef2) });
		var objTwo = this.initElement(null, new ClassifierReference[] { this.cloneEObjWithContainers(clsRef2),
				this.cloneEObjWithContainers(clsRef1) });

		this.testSimilarity(objOne, objTwo,
				TypesPackage.Literals.NAMESPACE_CLASSIFIER_REFERENCE__CLASSIFIER_REFERENCES);
	}
	
	// Do not test for duplicated elements here, not possible in Java

	@Test
	public void testClassifierReferenceNullCheck() {
		this.testSimilarityNullCheck(
				this.initElement(null, new ClassifierReference[] { this.cloneEObjWithContainers(clsRef1) }),
				new NamespaceClassifierReferenceInitialiser(), false,
				TypesPackage.Literals.NAMESPACE_CLASSIFIER_REFERENCE__CLASSIFIER_REFERENCES);
	}
}
