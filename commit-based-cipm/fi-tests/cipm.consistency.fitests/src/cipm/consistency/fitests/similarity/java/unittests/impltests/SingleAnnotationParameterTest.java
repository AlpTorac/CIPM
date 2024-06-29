package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.annotations.AnnotationValue;
import org.emftext.language.java.annotations.AnnotationsPackage;
import org.emftext.language.java.annotations.SingleAnnotationParameter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.annotations.SingleAnnotationParameterInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesAnnotationValues;

public class SingleAnnotationParameterTest extends EObjectSimilarityTest implements UsesAnnotationValues {
	protected SingleAnnotationParameter initElement(AnnotationValue val) {
		var sapInit = new SingleAnnotationParameterInitialiser();
		var sap = sapInit.instantiate();
		Assertions.assertTrue(sapInit.minimalInitialisation(sap));
		Assertions.assertTrue(sapInit.setValue(sap, val));
		return sap;
	}
	
	@Test
	public void testValue() {
		this.setResourceFileTestIdentifier("testValue");
		
		var objOne = this.initElement(this.createNullVal());
		var objTwo = this.initElement(this.createStringRefVal("strVal"));
		
		this.testX(objOne, objTwo, AnnotationsPackage.Literals.SINGLE_ANNOTATION_PARAMETER__VALUE);
	}
}
