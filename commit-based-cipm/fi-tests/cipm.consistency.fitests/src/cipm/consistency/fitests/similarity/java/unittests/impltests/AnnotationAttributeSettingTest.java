package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.annotations.AnnotationAttributeSetting;
import org.emftext.language.java.annotations.AnnotationValue;
import org.emftext.language.java.literals.NullLiteral;
import org.emftext.language.java.members.InterfaceMethod;
import org.emftext.language.java.references.StringReference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.annotations.AnnotationAttributeSettingInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.annotations.IAnnotationAttributeSettingInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.literals.NullLiteralInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.members.InterfaceMethodInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.references.StringReferenceInitialiser;

public class AnnotationAttributeSettingTest extends EObjectSimilarityTest {
	private IAnnotationAttributeSettingInitialiser aasInit;
	
	private InterfaceMethod im1;
	private InterfaceMethod im2;
	
	private NullLiteral val1;
	private StringReference val2;
	
	@BeforeEach
	@Override
	public void setUp() {
		aasInit = new AnnotationAttributeSettingInitialiser();
		
		var imInit = new InterfaceMethodInitialiser();
		
		
		im1 = imInit.instantiate();
		imInit.minimalInitialisation(im1);
		imInit.initialiseName(im1, "im1Name");
		
		im2 = imInit.instantiate();
		imInit.minimalInitialisation(im2);
		imInit.initialiseName(im2, "im2Name");

		var val1Init = new NullLiteralInitialiser();
		var val2Init = new StringReferenceInitialiser();
		
		val1 = val1Init.instantiate();
		val1Init.minimalInitialisation(val1);
		
		val2 = val2Init.instantiate();
		val2Init.minimalInitialisation(val2);
		val2Init.setValue(val2, "someVal");
		
		super.setUp();
	}
	
	protected AnnotationAttributeSetting initElement(IAnnotationAttributeSettingInitialiser initialiser,
			InterfaceMethod attr, AnnotationValue val) {
		AnnotationAttributeSetting result = initialiser.instantiate();
		initialiser.minimalInitialisation(result);
		initialiser.setAttribute(result, attr);
		initialiser.setValue(result, val);
		
		return result;
	}
	
	@Test
	public void testSameAttribute() {
		this.setResourceFileTestIdentifier("testSameAttribute");
		
		var objOne = this.initElement(this.aasInit, im1, null);
		
		this.sameX(objOne);
	}
	
	@Test
	public void testDifferentAttribute() {
		this.setResourceFileTestIdentifier("testDifferentAttribute");
		
		var objOne = this.initElement(this.aasInit, im1, null);
		var objTwo = this.initElement(this.aasInit, im2, null);
		
		this.differentX(objOne, objTwo);
	}
	
	@Test
	public void testSameValue() {
		this.setResourceFileTestIdentifier("testSameValue");
		
		var objOne = this.initElement(aasInit, null, val1);
		
		this.sameX(objOne);
	}
	
	@Test
	public void testDifferentValue() {
		this.setResourceFileTestIdentifier("testDifferentValue");
		
		var objOne = this.initElement(aasInit, null, val1);
		var objTwo = this.initElement(aasInit, null, val2);
		
		this.differentX(objOne, objTwo);
	}
}
