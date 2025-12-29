package cipm.consistency.similarity.test;

import org.emftext.language.java.annotations.AnnotationsFactory;
import org.emftext.language.java.annotations.AnnotationsPackage;
import org.emftext.language.java.classifiers.ClassifiersFactory;
import org.emftext.language.java.classifiers.ClassifiersPackage;
import org.emftext.language.java.commons.CommonsPackage;
import org.emftext.language.java.containers.ContainersFactory;
import org.emftext.language.java.containers.ContainersPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.similarity.SimilarityCheckerConfig;

public class SingleValuedOriginalFeatureTest {
	@Test
	public void testEObject_True() {
		var ai1 = AnnotationsFactory.eINSTANCE.createAnnotationInstance();
		var anno1 = ClassifiersFactory.eINSTANCE.createAnnotation();
		ai1.setAnnotation(anno1);

		var ai2 = AnnotationsFactory.eINSTANCE.createAnnotationInstance();
		var anno2 = ClassifiersFactory.eINSTANCE.createAnnotation();
		ai2.setAnnotation(anno2);

		Assertions.assertTrue(SimilarityCheckerConfig.compare(anno1, anno2));

		Assertions.assertTrue(SimilarityCheckerConfig.isFeatureRelevant(AnnotationsPackage.Literals.ANNOTATION_INSTANCE,
				AnnotationsPackage.Literals.ANNOTATION_INSTANCE__ANNOTATION));
		Assertions.assertTrue(SimilarityCheckerConfig.compare(ai1, ai2));
	}

	@Test
	public void testEObject_False() {
		var ai1 = AnnotationsFactory.eINSTANCE.createAnnotationInstance();
		var anno1 = ClassifiersFactory.eINSTANCE.createAnnotation();
		anno1.setName("anno1");
		ai1.setAnnotation(anno1);

		var ai2 = AnnotationsFactory.eINSTANCE.createAnnotationInstance();
		var anno2 = ClassifiersFactory.eINSTANCE.createAnnotation();
		anno2.setName("anno2");
		ai2.setAnnotation(anno2);

		Assertions.assertTrue(SimilarityCheckerConfig.isFeatureRelevant(ClassifiersPackage.Literals.ANNOTATION,
				CommonsPackage.Literals.NAMED_ELEMENT__NAME));
		Assertions.assertFalse(SimilarityCheckerConfig.compare(anno1, anno2));

		Assertions.assertTrue(SimilarityCheckerConfig.isFeatureRelevant(AnnotationsPackage.Literals.ANNOTATION_INSTANCE,
				AnnotationsPackage.Literals.ANNOTATION_INSTANCE__ANNOTATION));
		Assertions.assertFalse(SimilarityCheckerConfig.compare(ai1, ai2));
	}

	@Test
	public void testLiteral_False() {
		var mod1 = ContainersFactory.eINSTANCE.createModule();
		mod1.setName("mod1");
		var mod2 = ContainersFactory.eINSTANCE.createModule();
		mod2.setName("mod2");

		Assertions.assertTrue(SimilarityCheckerConfig.isFeatureRelevant(ContainersPackage.Literals.MODULE,
				CommonsPackage.Literals.NAMED_ELEMENT__NAME));
		Assertions.assertFalse(SimilarityCheckerConfig.compare(mod1, mod2));
	}

	@Test
	public void testLiteral_True() {
		var mod1 = ContainersFactory.eINSTANCE.createModule();
		mod1.setName("mod1");
		var mod2 = ContainersFactory.eINSTANCE.createModule();
		mod2.setName(mod1.getName());

		Assertions.assertTrue(SimilarityCheckerConfig.isFeatureRelevant(ContainersPackage.Literals.MODULE,
				CommonsPackage.Literals.NAMED_ELEMENT__NAME));
		Assertions.assertTrue(SimilarityCheckerConfig.compare(mod1, mod2));
	}
}
