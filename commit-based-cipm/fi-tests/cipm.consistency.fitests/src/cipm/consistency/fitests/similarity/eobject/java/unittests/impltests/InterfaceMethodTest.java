package cipm.consistency.fitests.similarity.eobject.java.unittests.impltests;

import org.emftext.language.java.annotations.AnnotationValue;
import org.emftext.language.java.members.InterfaceMethod;
import org.emftext.language.java.members.MembersPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.eobject.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.members.InterfaceMethodInitialiser;
import cipm.consistency.fitests.similarity.eobject.java.unittests.UsesAnnotationValues;
import cipm.consistency.fitests.similarity.eobject.java.unittests.UsesNames;

public class InterfaceMethodTest extends EObjectSimilarityTest implements UsesAnnotationValues, UsesNames {
	protected InterfaceMethod initElement(AnnotationValue defVal) {
		var imInit = new InterfaceMethodInitialiser();
		var im = imInit.instantiate();
		Assertions.assertTrue(imInit.setName(im, this.getDefaultName()));
		Assertions.assertTrue(imInit.setDefaultValue(im, defVal));
		return im;
	}

	@Test
	public void testDefaultValue() {
		var objOne = this.initElement(this.createNullLiteral());
		var objTwo = this.initElement(this.createMinimalSR("strval"));

		this.testSimilarity(objOne, objTwo, MembersPackage.Literals.INTERFACE_METHOD__DEFAULT_VALUE);
	}

	@Test
	public void testDefaultValueNullCheck() {
		this.testSimilarityNullCheck(this.initElement(this.createNullLiteral()), new InterfaceMethodInitialiser(),
				false, MembersPackage.Literals.INTERFACE_METHOD__DEFAULT_VALUE);
	}
}
