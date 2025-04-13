package cipm.consistency.fitests.similarity.jamopp.unittests.impltests;

import org.emftext.language.java.annotations.AnnotationValue;
import org.emftext.language.java.members.InterfaceMethod;
import org.emftext.language.java.members.MembersPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesAnnotationValues;
import cipm.consistency.initialisers.jamopp.members.InterfaceMethodInitialiser;

public class InterfaceMethodTest extends AbstractJaMoPPSimilarityTest implements UsesAnnotationValues {
	private AnnotationValue defVal1;
	private AnnotationValue defVal2;

	protected InterfaceMethod initElement(AnnotationValue defVal) {
		var imInit = new InterfaceMethodInitialiser();
		var im = imInit.instantiate();
		Assertions.assertTrue(imInit.setDefaultValue(im, defVal));
		return im;
	}

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		defVal1 = this.createNullLiteral();
		defVal2 = this.createMinimalSR("strval");
		Assertions.assertFalse(this.isSimilar(defVal1, defVal2));
	}

	@Test
	public void testDefaultValue() {
		var objOne = this.initElement(this.cloneEObjWithContainers(defVal1));
		var objTwo = this.initElement(this.cloneEObjWithContainers(defVal2));

		this.testSimilarity(objOne, objTwo, MembersPackage.Literals.INTERFACE_METHOD__DEFAULT_VALUE);
	}

	@Test
	public void testDefaultValueNullCheck() {
		this.testSimilarityNullCheck(this.initElement(this.cloneEObjWithContainers(defVal1)),
				new InterfaceMethodInitialiser(), false, MembersPackage.Literals.INTERFACE_METHOD__DEFAULT_VALUE);
	}
}
