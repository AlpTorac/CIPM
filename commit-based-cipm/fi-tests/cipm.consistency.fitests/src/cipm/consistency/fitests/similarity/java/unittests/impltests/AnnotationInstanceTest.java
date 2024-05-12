package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.annotations.AnnotationInstance;
import org.emftext.language.java.annotations.AnnotationParameter;
import org.emftext.language.java.annotations.SingleAnnotationParameter;
import org.emftext.language.java.classifiers.Classifier;
import org.emftext.language.java.literals.NullLiteral;
import org.emftext.language.java.references.StringReference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.annotations.AnnotationInstanceInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.annotations.IAnnotationInstanceInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.annotations.SingleAnnotationParameterInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.classifiers.AnnotationInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.literals.NullLiteralInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.references.StringReferenceInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesAnnotationParameters;
import cipm.consistency.fitests.similarity.java.unittests.UsesConcreteClassifiers;

public class AnnotationInstanceTest extends EObjectSimilarityTest
	implements UsesConcreteClassifiers, UsesAnnotationParameters {
	
	protected AnnotationInstance initElement(Classifier cls, AnnotationParameter param) {
		var initialiser = new AnnotationInstanceInitialiser();
		AnnotationInstance ai = initialiser.instantiate();
		initialiser.minimalInitialisation(ai);
		initialiser.setAnnotation(ai, cls);
		initialiser.setAnnotationParameter(ai, param);
		return ai;
	}
	
	@Test
	public void testClassifier() {
		this.setResourceFileTestIdentifier("testClassifier");
		
		var objOne = this.initElement(this.createMinimalClass("cls1"), null);
		var objTwo = this.initElement(this.createMinimalClass("cls2"), null);
		
		this.testX(objOne, objTwo, false);
	}
	
	@Test
	public void testParameter() {
		this.setResourceFileTestIdentifier("testParameter");
		
		var objOne = this.initElement(null, this.createSingleNullAnnoParam());
		var objTwo = this.initElement(null, this.createSingleStrAnnoParam("val"));
		
		this.testX(objOne, objTwo, false);
	}
}
