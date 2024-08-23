package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.classifiers.Classifier;
import org.emftext.language.java.types.ClassifierReference;
import org.emftext.language.java.types.TypesPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.types.ClassifierReferenceInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesConcreteClassifiers;

public class ClassifierReferenceTest extends EObjectSimilarityTest implements UsesConcreteClassifiers {
	protected ClassifierReference initElement(Classifier target) {
		var init = new ClassifierReferenceInitialiser();
		var res = init.instantiate();
		
		Assertions.assertTrue(init.setTarget(res, target));
		return res;
	}
	
	@Test
	public void testTarget() {
		this.setResourceFileTestIdentifier("testTarget");
		
		var objOne = this.initElement(this.createMinimalClass("cls1"));
		var objTwo = this.initElement(this.createMinimalClass("cls2"));
		
		this.testSimilarity(objOne, objTwo, TypesPackage.Literals.CLASSIFIER_REFERENCE__TARGET);
	}
	
	
	@Test
	public void testTargetNullCheck() {
		this.setResourceFileTestIdentifier("testTargetNullCheck");
		
		var objOne = this.initElement(this.createMinimalClass("cls1"));
		var objTwo = new ClassifierReferenceInitialiser().instantiate();
		
		this.testSimilarity(objOne, objTwo, TypesPackage.Literals.CLASSIFIER_REFERENCE__TARGET);
	}
}
