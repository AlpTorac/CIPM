package cipm.consistency.fitests.similarity.jamopp.unittests.impltests;

import org.emftext.language.java.annotations.AnnotationInstance;
import org.emftext.language.java.annotations.AnnotationParameter;
import org.emftext.language.java.annotations.AnnotationsPackage;
import org.emftext.language.java.classifiers.Classifier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesAnnotationParameters;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesConcreteClassifiers;
import cipm.consistency.initialisers.jamopp.annotations.AnnotationInstanceInitialiser;

public class AnnotationInstanceTest extends AbstractJaMoPPSimilarityTest
		implements UsesConcreteClassifiers, UsesAnnotationParameters {
	private Classifier anno1;
	private Classifier anno2;
	private AnnotationParameter annoParam1;
	private AnnotationParameter annoParam2;

	protected AnnotationInstance initElement(Classifier annotation, AnnotationParameter annoParam) {
		var initialiser = new AnnotationInstanceInitialiser();
		AnnotationInstance ai = initialiser.instantiate();
		Assertions.assertTrue(initialiser.setAnnotation(ai, annotation));
		Assertions.assertTrue(initialiser.setParameter(ai, annoParam));
		return ai;
	}

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		anno1 = this.createMinimalClass("cls1");
		anno2 = this.createMinimalClass("cls2");
		Assertions.assertFalse(this.isSimilar(anno1, anno2));

		annoParam1 = this.createSingleNullAnnoParam();
		annoParam2 = this.createMinimalAnnoParamList();
		Assertions.assertFalse(this.isSimilar(annoParam1, annoParam2));
	}

	@Test
	public void testAnnotation() {
		var objOne = this.initElement(this.cloneEObjWithContainers(anno1), null);
		var objTwo = this.initElement(this.cloneEObjWithContainers(anno2), null);

		this.testSimilarity(objOne, objTwo, AnnotationsPackage.Literals.ANNOTATION_INSTANCE__ANNOTATION);
	}

	@Test
	public void testAnnotationNullCheck() {
		this.testSimilarityNullCheck(this.initElement(this.cloneEObjWithContainers(anno1), null),
				new AnnotationInstanceInitialiser(), false,
				AnnotationsPackage.Literals.ANNOTATION_INSTANCE__ANNOTATION);
	}

	@Test
	public void testParameter() {
		var objOne = this.initElement(null, this.cloneEObjWithContainers(annoParam1));
		var objTwo = this.initElement(null, this.cloneEObjWithContainers(annoParam2));

		this.testSimilarity(objOne, objTwo, AnnotationsPackage.Literals.ANNOTATION_INSTANCE__PARAMETER);
	}

	@Test
	public void testParameterNullCheck() {
		this.testSimilarityNullCheck(this.initElement(null, this.cloneEObjWithContainers(annoParam1)),
				new AnnotationInstanceInitialiser(), false, AnnotationsPackage.Literals.ANNOTATION_INSTANCE__PARAMETER);
	}
}
