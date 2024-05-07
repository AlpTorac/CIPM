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
import cipm.consistency.fitests.similarity.java.initialiser.classifiers.IAnnotationInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.literals.NullLiteralInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.references.StringReferenceInitialiser;

public class AnnotationInstanceTest extends EObjectSimilarityTest {
	private IAnnotationInstanceInitialiser aiInit;
	
	private Classifier cls1;
	private Classifier cls2;
	
	private SingleAnnotationParameter param1;
	private SingleAnnotationParameter param2;
	
	private NullLiteral val1;
	private StringReference val2;
	
	@BeforeEach
	@Override
	public void setUp() {
		aiInit = new AnnotationInstanceInitialiser();
		
		var annoInit = new AnnotationInitialiser();
		
		cls1 = annoInit.instantiate();
		annoInit.minimalInitialisation(cls1);
		annoInit.initialiseName(cls1, "cls1Name");
		
		cls2 = annoInit.instantiate();
		annoInit.minimalInitialisation(cls2);
		annoInit.initialiseName(cls2, "cls2Name");
		
		var val1Init = new NullLiteralInitialiser();
		var val2Init = new StringReferenceInitialiser();
		
		val1 = val1Init.instantiate();
		val1Init.minimalInitialisation(val1);
		
		val2 = val2Init.instantiate();
		val2Init.minimalInitialisation(val2);
		val2Init.setValue(val2, "someVal");
		
		var paramInit = new SingleAnnotationParameterInitialiser();
		
		param1 = paramInit.instantiate();
		paramInit.minimalInitialisation(param1);
		paramInit.setValue(param1, val1);
		
		param2 = paramInit.instantiate();
		paramInit.minimalInitialisation(param2);
		paramInit.setValue(param2, val2);
		
		super.setUp();
	}
	
	protected AnnotationInstance initElement(IAnnotationInstanceInitialiser initialiser,
			Classifier cls, AnnotationParameter param) {
		AnnotationInstance ai = initialiser.instantiate();
		initialiser.minimalInitialisation(ai);
		initialiser.setAnnotation(ai, cls);
		initialiser.setAnnotationParameter(ai, param);
		return ai;
	}
	
	@Test
	public void testSameClassifier() {
		this.setResourceFileTestIdentifier("testSameClassifier");
		
		var objOne = this.initElement(aiInit, cls1, null);
		
		this.sameX(objOne, aiInit);
	}
	
	@Test
	public void testDifferentClassifier() {
		this.setResourceFileTestIdentifier("testDifferentClassifier");
		
		var objOne = this.initElement(aiInit, cls1, null);
		var objTwo = this.initElement(aiInit, cls2, null);
		
		this.differentX(objOne, objTwo);
	}
	
	@Test
	public void testSameParameter() {
		this.setResourceFileTestIdentifier("testSameParameter");
		
		var objOne = this.initElement(aiInit, null, param1);
		
		this.sameX(objOne, aiInit);
	}
	
	@Test
	public void testDifferentParameter() {
		this.setResourceFileTestIdentifier("testDifferentParameter");
		
		var objOne = this.initElement(aiInit, null, param1);
		var objTwo = this.initElement(aiInit, null, param2);
		
		this.differentX(objOne, objTwo);
	}
}
