package cipm.consistency.fitests.similarity.jamopp.unittests.impltests;

import org.emftext.language.java.annotations.AnnotationAttributeSetting;
import org.emftext.language.java.annotations.AnnotationParameterList;
import org.emftext.language.java.annotations.AnnotationsPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesAnnotationAttributeSettings;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesAnnotationParameters;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesMethods;
import cipm.consistency.initialisers.jamopp.annotations.AnnotationParameterListInitialiser;

public class AnnotationParameterListTest extends AbstractJaMoPPSimilarityTest
		implements UsesAnnotationParameters, UsesAnnotationAttributeSettings, UsesMethods {
	private AnnotationAttributeSetting settings1;
	private AnnotationAttributeSetting settings2;

	protected AnnotationParameterList initElement(AnnotationAttributeSetting[] annoAttrSettingsArr) {
		var aplInit = new AnnotationParameterListInitialiser();
		var apl = aplInit.instantiate();
		Assertions.assertTrue(aplInit.addSettings(apl, annoAttrSettingsArr));
		return apl;
	}

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		settings1 = this.createAAS(this.createMinimalInterfaceMethodWithNullReturn("met1"), null);
		settings2 = this.createAAS(this.createMinimalInterfaceMethodWithNullReturn("met2"), null);
		Assertions.assertFalse(this.isSimilar(settings1, settings2));
	}

	@Test
	public void testSetting() {
		var objOne = this.initElement(new AnnotationAttributeSetting[] { this.cloneEObjWithContainers(settings1) });
		var objTwo = this.initElement(new AnnotationAttributeSetting[] { this.cloneEObjWithContainers(settings2) });

		this.testSimilarity(objOne, objTwo, AnnotationsPackage.Literals.ANNOTATION_PARAMETER_LIST__SETTINGS);
	}

	@Test
	public void testSettingSize() {
		var objOne = this.initElement(new AnnotationAttributeSetting[] { this.cloneEObjWithContainers(settings1),
				this.cloneEObjWithContainers(settings2) });
		var objTwo = this.initElement(new AnnotationAttributeSetting[] { this.cloneEObjWithContainers(settings1) });

		this.testSimilarity(objOne, objTwo, AnnotationsPackage.Literals.ANNOTATION_PARAMETER_LIST__SETTINGS);
	}

	@Test
	public void testSettingPosition() {
		var objOne = this.initElement(new AnnotationAttributeSetting[] { this.cloneEObjWithContainers(settings1),
				this.cloneEObjWithContainers(settings2) });
		var objTwo = this.initElement(new AnnotationAttributeSetting[] { this.cloneEObjWithContainers(settings2),
				this.cloneEObjWithContainers(settings1) });

		this.testSimilarity(objOne, objTwo, AnnotationsPackage.Literals.ANNOTATION_PARAMETER_LIST__SETTINGS);
	}

	@Test
	public void testSettingDuplication() {
		var objOne = this.initElement(new AnnotationAttributeSetting[] { this.cloneEObjWithContainers(settings1),
				this.cloneEObjWithContainers(settings1) });
		var objTwo = this.initElement(new AnnotationAttributeSetting[] { this.cloneEObjWithContainers(settings1) });

		this.testSimilarity(objOne, objTwo, AnnotationsPackage.Literals.ANNOTATION_PARAMETER_LIST__SETTINGS);
	}

	@Test
	public void testSettingNullCheck() {
		this.testSimilarityNullCheck(
				this.initElement(new AnnotationAttributeSetting[] { this.cloneEObjWithContainers(settings1) }),
				new AnnotationParameterListInitialiser(), false,
				AnnotationsPackage.Literals.ANNOTATION_PARAMETER_LIST__SETTINGS);
	}
}
