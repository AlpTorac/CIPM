package cipm.consistency.fitests.similarity.jamopp.unittests.impltests;

import org.emftext.language.java.annotations.AnnotationAttributeSetting;
import org.emftext.language.java.annotations.AnnotationValue;
import org.emftext.language.java.annotations.AnnotationsPackage;
import org.emftext.language.java.members.InterfaceMethod;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesAnnotationValues;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesMethods;
import cipm.consistency.initialisers.jamopp.annotations.AnnotationAttributeSettingInitialiser;

public class AnnotationAttributeSettingTest extends AbstractJaMoPPSimilarityTest
		implements UsesMethods, UsesAnnotationValues {
	private InterfaceMethod attr1;
	private InterfaceMethod attr2;
	private AnnotationValue val1;
	private AnnotationValue val2;

	protected AnnotationAttributeSetting initElement(InterfaceMethod attr, AnnotationValue val) {
		var initialiser = new AnnotationAttributeSettingInitialiser();
		AnnotationAttributeSetting result = initialiser.instantiate();
		Assertions.assertTrue(initialiser.setAttribute(result, attr));
		Assertions.assertTrue(initialiser.setValue(result, val));

		return result;
	}

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		attr1 = this.createMinimalInterfaceMethodWithNullReturn("im1Name");
		attr2 = this.createMinimalInterfaceMethodWithNullReturn("im2Name");
		Assertions.assertFalse(this.isSimilar(attr1, attr2));

		val1 = this.createNullLiteral();
		val2 = this.createMinimalSR("val");
		Assertions.assertFalse(this.isSimilar(val1, val2));
	}

	@Test
	public void testAttribute() {
		var objOne = this.initElement(this.cloneEObjWithContainers(attr1), null);
		var objTwo = this.initElement(this.cloneEObjWithContainers(attr2), null);

		this.testSimilarity(objOne, objTwo, AnnotationsPackage.Literals.ANNOTATION_ATTRIBUTE_SETTING__ATTRIBUTE);
	}

	@Test
	public void testAttributeNullCheck() {
		this.testSimilarityNullCheck(this.initElement(this.cloneEObjWithContainers(attr1), null),
				new AnnotationAttributeSettingInitialiser(), false,
				AnnotationsPackage.Literals.ANNOTATION_ATTRIBUTE_SETTING__ATTRIBUTE);
	}

	@Test
	public void testValue() {
		var objOne = this.initElement(null, this.cloneEObjWithContainers(val1));
		var objTwo = this.initElement(null, this.cloneEObjWithContainers(val2));

		this.testSimilarity(objOne, objTwo, AnnotationsPackage.Literals.ANNOTATION_ATTRIBUTE_SETTING__VALUE);
	}

	@Test
	public void testValueNullCheck() {
		this.testSimilarityNullCheck(this.initElement(null, this.cloneEObjWithContainers(val1)),
				new AnnotationAttributeSettingInitialiser(), false,
				AnnotationsPackage.Literals.ANNOTATION_ATTRIBUTE_SETTING__VALUE);
	}
}
