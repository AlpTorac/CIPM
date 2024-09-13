package cipm.consistency.fitests.similarity.eobject.java.unittests.impltests;

import org.emftext.language.java.classifiers.Classifier;
import org.emftext.language.java.types.ClassifierReference;
import org.emftext.language.java.types.TypesPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.eobject.java.AbstractEObjectJavaSimilarityTest;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.types.ClassifierReferenceInitialiser;
import cipm.consistency.fitests.similarity.eobject.java.unittests.UsesConcreteClassifiers;

public class ClassifierReferenceTest extends AbstractEObjectJavaSimilarityTest implements UsesConcreteClassifiers {
	protected ClassifierReference initElement(Classifier target) {
		var init = new ClassifierReferenceInitialiser();
		var res = init.instantiate();

		Assertions.assertTrue(init.setTarget(res, target));
		return res;
	}

	@Test
	public void testTarget() {
		var objOne = this.initElement(this.createMinimalClass("cls1"));
		var objTwo = this.initElement(this.createMinimalClass("cls2"));

		this.testSimilarity(objOne, objTwo, TypesPackage.Literals.CLASSIFIER_REFERENCE__TARGET);
	}

	@Test
	public void testTargetNullCheck() {
		this.testSimilarityNullCheck(this.initElement(this.createMinimalClass("cls1")),
				new ClassifierReferenceInitialiser(), false, TypesPackage.Literals.CLASSIFIER_REFERENCE__TARGET);
	}
}
