package cipm.consistency.fitests.similarity.eobject.java.unittests.impltests;

import org.emftext.language.java.annotations.AnnotationAttributeSetting;
import org.emftext.language.java.annotations.AnnotationParameterList;
import org.emftext.language.java.annotations.AnnotationsPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.eobject.java.AbstractEObjectJavaSimilarityTest;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.annotations.AnnotationParameterListInitialiser;
import cipm.consistency.fitests.similarity.eobject.java.unittests.UsesAnnotationAttributeSettings;
import cipm.consistency.fitests.similarity.eobject.java.unittests.UsesAnnotationParameters;

public class AnnotationParameterListTest extends AbstractEObjectJavaSimilarityTest
		implements UsesAnnotationParameters, UsesAnnotationAttributeSettings {
	protected AnnotationParameterList initElement(AnnotationAttributeSetting[] annoAttrSettingsArr) {
		var aplInit = new AnnotationParameterListInitialiser();
		var apl = aplInit.instantiate();
		Assertions.assertTrue(aplInit.addSettings(apl, annoAttrSettingsArr));
		return apl;
	}

	@Test
	public void testSetting() {
		var objOne = this.initElement(new AnnotationAttributeSetting[] { this.createEmptyAAS() });
		var objTwo = this.initElement(new AnnotationAttributeSetting[] { this.createNullAAS() });

		this.testSimilarity(objOne, objTwo, AnnotationsPackage.Literals.ANNOTATION_PARAMETER_LIST__SETTINGS);
	}

	@Test
	public void testSettingSize() {
		var objOne = this
				.initElement(new AnnotationAttributeSetting[] { this.createEmptyAAS(), this.createEmptyAAS() });
		var objTwo = this.initElement(new AnnotationAttributeSetting[] { this.createEmptyAAS() });

		this.testSimilarity(objOne, objTwo, AnnotationsPackage.Literals.ANNOTATION_PARAMETER_LIST__SETTINGS);
	}

	@Test
	public void testSettingNullCheck() {
		this.testSimilarityNullCheck(this.initElement(new AnnotationAttributeSetting[] { this.createEmptyAAS() }),
				new AnnotationParameterListInitialiser(), false,
				AnnotationsPackage.Literals.ANNOTATION_PARAMETER_LIST__SETTINGS);
	}
}
