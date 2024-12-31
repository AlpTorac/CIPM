package cipm.consistency.fitests.similarity.jamopp.unittests.impltests;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.compare.CompareFactory;
import org.eclipse.emf.compare.DifferenceKind;
import org.eclipse.emf.compare.ReferenceChange;
import org.emftext.language.java.annotations.AnnotationInstance;
import org.emftext.language.java.annotations.AnnotationParameter;
import org.emftext.language.java.annotations.AnnotationsPackage;
import org.emftext.language.java.classifiers.Classifier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.JaMoPPChangeDetectionTestGenerator;
import cipm.consistency.fitests.similarity.jamopp.JaMoPPSimilarityTestGenerator;
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

	@TestFactory
	public Collection<DynamicTest> testAnnotation() {
		var tests = new ArrayList<DynamicTest>();
		
		var objOne = this.initElement(this.createMinimalClass("cls1"), null);
		var objTwo = this.initElement(this.createMinimalClass("cls2"), null);

		tests.addAll(new JaMoPPSimilarityTestGenerator().generateTestsFor(objOne, objTwo, AnnotationsPackage.Literals.ANNOTATION_INSTANCE__ANNOTATION));

		var objOneClone = this.cloneEObjWithContainers(objOne);
		var objTwoClone = this.cloneEObjWithContainers(objTwo);

		// FIXME Extract methods into an abstract class to clean up the mess
		var fac = CompareFactory.eINSTANCE;
		var diff1 = fac.createReferenceChange();
		var match1 = fac.createMatch();
		match1.setLeft(objTwoClone);
		diff1.setMatch(match1);
		diff1.setKind(DifferenceKind.CHANGE);
		diff1.setReference(AnnotationsPackage.Literals.ANNOTATION_INSTANCE__ANNOTATION);

		var diff2 = fac.createReferenceChange();
		var match2 = fac.createMatch();
		match2.setRight(objOneClone);
		diff2.setMatch(match2);
		diff2.setKind(DifferenceKind.CHANGE);
		diff2.setReference(AnnotationsPackage.Literals.ANNOTATION_INSTANCE__ANNOTATION);
		
		tests.addAll(new JaMoPPChangeDetectionTestGenerator().generateTestsFor(objTwoClone, objOneClone, List.of(diff1, diff2)));

		// FIXME Change replay test sample, extract in the future
		this.replayChanges(objOneClone, objTwoClone);
		Assertions.assertEquals(0, this.compareModels(objOneClone, objTwoClone).getDifferences().size());
		this.testSimilarity(objOneClone, objTwoClone, Boolean.TRUE);
		
		return tests;
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
