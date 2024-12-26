package cipm.consistency.fitests.similarity.jamopp.unittests.impltests;

import org.eclipse.emf.compare.DifferenceKind;
import org.eclipse.emf.compare.ReferenceChange;
import org.emftext.language.java.annotations.AnnotationInstance;
import org.emftext.language.java.annotations.AnnotationParameter;
import org.emftext.language.java.annotations.AnnotationsPackage;
import org.emftext.language.java.classifiers.Classifier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesAnnotationParameters;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesConcreteClassifiers;
import cipm.consistency.initialisers.jamopp.annotations.AnnotationInstanceInitialiser;

public class AnnotationInstanceTest extends AbstractJaMoPPSimilarityTest
		implements UsesConcreteClassifiers, UsesAnnotationParameters {

	protected AnnotationInstance initElement(Classifier annotation, AnnotationParameter annoParam) {
		var initialiser = new AnnotationInstanceInitialiser();
		AnnotationInstance ai = initialiser.instantiate();
		Assertions.assertTrue(initialiser.setAnnotation(ai, annotation));
		Assertions.assertTrue(initialiser.setParameter(ai, annoParam));
		return ai;
	}

	@Test
	public void testAnnotation() {
		var objOne = this.initElement(this.createMinimalClass("cls1"), null);
		var objTwo = this.initElement(this.createMinimalClass("cls2"), null);

		this.testSimilarity(objOne, objTwo, AnnotationsPackage.Literals.ANNOTATION_INSTANCE__ANNOTATION);

		var objOneClone = this.cloneEObjWithContainers(objOne);
		var objTwoClone = this.cloneEObjWithContainers(objTwo);

		// FIXME Comparison test sample, extract in the future
		var cmp = this.compareModels(objOneClone, objTwoClone);
		Assertions.assertEquals(1, cmp.getDifferences().size());
		var diff = cmp.getDifferences().get(0);
		Assertions.assertEquals(DifferenceKind.CHANGE, diff.getKind());
		var castedDiff = (ReferenceChange) diff;
		Assertions.assertEquals(AnnotationsPackage.Literals.ANNOTATION_INSTANCE__ANNOTATION,
				castedDiff.getReference());

		// FIXME Change replay test sample, extract in the future
		this.replayChanges(objOneClone, objTwoClone);
		Assertions.assertEquals(0, this.compareModels(objOneClone, objTwoClone).getDifferences().size());
		this.testSimilarity(objOneClone, objTwoClone, Boolean.TRUE);
	}

	@Test
	public void testAnnotationNullCheck() {
		this.testSimilarityNullCheck(this.initElement(this.createMinimalClass("cls1"), null),
				new AnnotationInstanceInitialiser(), false,
				AnnotationsPackage.Literals.ANNOTATION_INSTANCE__ANNOTATION);
	}

	@Test
	public void testParameter() {
		var objOne = this.initElement(null, this.createSingleNullAnnoParam());
		var objTwo = this.initElement(null, this.createSingleStrAnnoParam("val"));

		this.testSimilarity(objOne, objTwo, AnnotationsPackage.Literals.ANNOTATION_INSTANCE__PARAMETER);
	}

	@Test
	public void testParameterNullCheck() {
		this.testSimilarityNullCheck(this.initElement(null, this.createSingleNullAnnoParam()),
				new AnnotationInstanceInitialiser(), false, AnnotationsPackage.Literals.ANNOTATION_INSTANCE__PARAMETER);
	}
}
