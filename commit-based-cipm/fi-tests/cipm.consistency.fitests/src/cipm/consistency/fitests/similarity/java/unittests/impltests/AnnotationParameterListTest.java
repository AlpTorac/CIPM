package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.annotations.AnnotationAttributeSetting;
import org.emftext.language.java.annotations.AnnotationParameterList;
import org.emftext.language.java.annotations.AnnotationsPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.annotations.AnnotationParameterListInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesAnnotationAttributeSettings;
import cipm.consistency.fitests.similarity.java.unittests.UsesAnnotationParameters;

public class AnnotationParameterListTest extends EObjectSimilarityTest
		implements UsesAnnotationParameters, UsesAnnotationAttributeSettings {
	protected AnnotationParameterList initElement(AnnotationAttributeSetting[] annoAttrSettingsArr) {
		var aplInit = new AnnotationParameterListInitialiser();
		var apl = aplInit.instantiate();
		Assertions.assertTrue(aplInit.addSettings(apl, annoAttrSettingsArr));
		return apl;
	}

	@Test
	public void testSetting() {
		this.setResourceFileTestIdentifier("testSetting");

		var objOne = this.initElement(new AnnotationAttributeSetting[] { this.createEmptyAAS() });
		var objTwo = this.initElement(new AnnotationAttributeSetting[] { this.createNullAAS() });

		this.testSimilarity(objOne, objTwo, AnnotationsPackage.Literals.ANNOTATION_PARAMETER_LIST__SETTINGS);
	}
}
