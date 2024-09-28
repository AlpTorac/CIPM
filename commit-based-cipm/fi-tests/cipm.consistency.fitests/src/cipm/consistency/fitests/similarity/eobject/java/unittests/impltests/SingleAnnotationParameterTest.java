package cipm.consistency.fitests.similarity.eobject.java.unittests.impltests;

import org.emftext.language.java.annotations.AnnotationValue;
import org.emftext.language.java.annotations.AnnotationsPackage;
import org.emftext.language.java.annotations.SingleAnnotationParameter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.eobject.java.AbstractEObjectJavaSimilarityTest;
import cipm.consistency.initialisers.eobject.java.annotations.SingleAnnotationParameterInitialiser;
import cipm.consistency.fitests.similarity.eobject.java.unittests.UsesAnnotationValues;

public class SingleAnnotationParameterTest extends AbstractEObjectJavaSimilarityTest implements UsesAnnotationValues {
	protected SingleAnnotationParameter initElement(AnnotationValue val) {
		var sapInit = new SingleAnnotationParameterInitialiser();
		var sap = sapInit.instantiate();
		Assertions.assertTrue(sapInit.setValue(sap, val));
		return sap;
	}

	@Test
	public void testValue() {
		var objOne = this.initElement(this.createNullLiteral());
		var objTwo = this.initElement(this.createMinimalSR("strVal"));

		this.testSimilarity(objOne, objTwo, AnnotationsPackage.Literals.SINGLE_ANNOTATION_PARAMETER__VALUE);
	}

	@Test
	public void testValueNullCheck() {
		this.testSimilarityNullCheck(this.initElement(this.createNullLiteral()),
				new SingleAnnotationParameterInitialiser(), false,
				AnnotationsPackage.Literals.SINGLE_ANNOTATION_PARAMETER__VALUE);
	}
}
