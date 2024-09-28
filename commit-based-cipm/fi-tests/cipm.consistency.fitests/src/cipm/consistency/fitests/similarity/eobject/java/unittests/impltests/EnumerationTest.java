package cipm.consistency.fitests.similarity.eobject.java.unittests.impltests;

import org.emftext.language.java.classifiers.Enumeration;
import org.emftext.language.java.classifiers.ClassifiersPackage;
import org.emftext.language.java.members.EnumConstant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.eobject.java.AbstractEObjectJavaSimilarityTest;
import cipm.consistency.initialisers.eobject.java.classifiers.EnumerationInitialiser;
import cipm.consistency.fitests.similarity.eobject.java.unittests.UsesEnumConstants;

public class EnumerationTest extends AbstractEObjectJavaSimilarityTest implements UsesEnumConstants {
	protected Enumeration initElement(EnumConstant[] consts) {
		var enmInit = new EnumerationInitialiser();
		var enm = enmInit.instantiate();
		Assertions.assertTrue(enmInit.addConstants(enm, consts));
		return enm;
	}

	@Test
	public void testConstant() {
		var objOne = this.initElement(new EnumConstant[] { this.createMinimalEnumConstant("cst1") });
		var objTwo = this.initElement(new EnumConstant[] { this.createMinimalEnumConstant("cst2") });

		this.testSimilarity(objOne, objTwo, ClassifiersPackage.Literals.ENUMERATION__CONSTANTS);
	}

	@Test
	public void testConstantSize() {
		var objOne = this.initElement(
				new EnumConstant[] { this.createMinimalEnumConstant("cst1"), this.createMinimalEnumConstant("cst2") });
		var objTwo = this.initElement(new EnumConstant[] { this.createMinimalEnumConstant("cst1") });

		this.testSimilarity(objOne, objTwo, ClassifiersPackage.Literals.ENUMERATION__CONSTANTS);
	}

	@Test
	public void testConstantNullCheck() {
		this.testSimilarityNullCheck(this.initElement(new EnumConstant[] { this.createMinimalEnumConstant("cst1") }),
				new EnumerationInitialiser(), false, ClassifiersPackage.Literals.ENUMERATION__CONSTANTS);
	}
}
