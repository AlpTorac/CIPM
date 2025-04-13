package cipm.consistency.fitests.similarity.jamopp.unittests.impltests;

import org.emftext.language.java.annotations.AnnotationValue;
import org.emftext.language.java.annotations.AnnotationsPackage;
import org.emftext.language.java.annotations.SingleAnnotationParameter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesAnnotationValues;
import cipm.consistency.initialisers.jamopp.annotations.SingleAnnotationParameterInitialiser;

public class SingleAnnotationParameterTest extends AbstractJaMoPPSimilarityTest implements UsesAnnotationValues {
	private AnnotationValue val1;
	private AnnotationValue val2;

	protected SingleAnnotationParameter initElement(AnnotationValue val) {
		var sapInit = new SingleAnnotationParameterInitialiser();
		var sap = sapInit.instantiate();
		Assertions.assertTrue(sapInit.setValue(sap, val));
		return sap;
	}

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		val1 = this.createNullLiteral();
		val2 = this.createMinimalSR("strVal");
		Assertions.assertFalse(this.isSimilar(val1, val2));
	}

	@Test
	public void testValue() {
		var objOne = this.initElement(this.cloneEObjWithContainers(val1));
		var objTwo = this.initElement(this.cloneEObjWithContainers(val2));

		this.testSimilarity(objOne, objTwo, AnnotationsPackage.Literals.SINGLE_ANNOTATION_PARAMETER__VALUE);
	}

	@Test
	public void testValueNullCheck() {
		this.testSimilarityNullCheck(this.initElement(this.cloneEObjWithContainers(val1)),
				new SingleAnnotationParameterInitialiser(), false,
				AnnotationsPackage.Literals.SINGLE_ANNOTATION_PARAMETER__VALUE);
	}
}
