package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.annotations.AnnotationAttributeSetting;
import org.emftext.language.java.annotations.AnnotationValue;
import org.emftext.language.java.members.InterfaceMethod;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.annotations.AnnotationAttributeSettingInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesAnnotationValues;
import cipm.consistency.fitests.similarity.java.unittests.UsesMethods;

public class AnnotationAttributeSettingTest extends EObjectSimilarityTest
	implements UsesMethods, UsesAnnotationValues {
	protected AnnotationAttributeSetting initElement(InterfaceMethod attr, AnnotationValue val) {
		var initialiser = new AnnotationAttributeSettingInitialiser();
		AnnotationAttributeSetting result = initialiser.instantiate();
		initialiser.minimalInitialisation(result);
		initialiser.setAttribute(result, attr);
		initialiser.setValue(result, val);
		
		return result;
	}
	
	@Test
	public void testAttribute() {
		this.setResourceFileTestIdentifier("testAttribute");
		
		var objOne = this.initElement(this.createMinimalInterfaceMethod("im1Name"), null);
		var objTwo = this.initElement(this.createMinimalInterfaceMethod("im2Name"), null);
		
		this.testX(objOne, objTwo, false);
	}
	
	@Test
	public void testValue() {
		this.setResourceFileTestIdentifier("testValue");
		
		var objOne = this.initElement(null, this.createNullVal());
		var objTwo = this.initElement(null, this.createStringRefVal("val"));
		
		this.testX(objOne, objTwo, false);
	}
}
