package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.annotations.AnnotationInstance;
import org.emftext.language.java.annotations.AnnotationParameter;
import org.emftext.language.java.annotations.AnnotationsPackage;
import org.emftext.language.java.classifiers.Classifier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.annotations.AnnotationInstanceInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesAnnotationParameters;
import cipm.consistency.fitests.similarity.java.unittests.UsesConcreteClassifiers;

public class AnnotationInstanceTest extends EObjectSimilarityTest
		implements UsesConcreteClassifiers, UsesAnnotationParameters {

	protected AnnotationInstance initElement(Classifier annotation, AnnotationParameter annoParam) {
		var initialiser = new AnnotationInstanceInitialiser();
		AnnotationInstance ai = initialiser.instantiate();
		Assertions.assertTrue(initialiser.setAnnotation(ai, annotation));
		Assertions.assertTrue(initialiser.setAnnotationParameter(ai, annoParam));
		return ai;
	}

	@Test
	public void testAnnotation() {
		this.setResourceFileTestIdentifier("testAnnotation");

		var objOne = this.initElement(this.createMinimalClass("cls1"), null);
		var objTwo = this.initElement(this.createMinimalClass("cls2"), null);

		this.testSimilarity(objOne, objTwo, AnnotationsPackage.Literals.ANNOTATION_INSTANCE__ANNOTATION);
	}

	@Test
	public void testAnnotationNullCheck() {
		this.setResourceFileTestIdentifier("testAnnotationNullCheck");

		var objOne = this.initElement(this.createMinimalClass("cls1"), null);
		var objTwo = new AnnotationInstanceInitialiser().instantiate();

		this.testSimilarity(objOne, objTwo, AnnotationsPackage.Literals.ANNOTATION_INSTANCE__ANNOTATION);
	}

	@Test
	public void testParameter() {
		this.setResourceFileTestIdentifier("testParameter");

		var objOne = this.initElement(null, this.createSingleNullAnnoParam());
		var objTwo = this.initElement(null, this.createSingleStrAnnoParam("val"));

		this.testSimilarity(objOne, objTwo, AnnotationsPackage.Literals.ANNOTATION_INSTANCE__PARAMETER);
	}

	@Test
	public void testParameterNullCheck() {
		this.setResourceFileTestIdentifier("testParameterNullCheck");

		var objOne = this.initElement(null, this.createSingleNullAnnoParam());
		var objTwo = new AnnotationInstanceInitialiser().instantiate();

		this.testSimilarity(objOne, objTwo, AnnotationsPackage.Literals.ANNOTATION_INSTANCE__PARAMETER);
	}
}
