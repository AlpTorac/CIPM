package cipm.consistency.fitests.similarity.jamopp.unittests.impltests;

import org.emftext.language.java.classifiers.Enumeration;
import org.emftext.language.java.classifiers.ClassifiersPackage;
import org.emftext.language.java.members.EnumConstant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesEnumConstants;
import cipm.consistency.initialisers.jamopp.classifiers.EnumerationInitialiser;

public class EnumerationTest extends AbstractJaMoPPSimilarityTest implements UsesEnumConstants {
	private EnumConstant const1;
	private EnumConstant const2;

	protected Enumeration initElement(EnumConstant[] consts) {
		var enmInit = new EnumerationInitialiser();
		var enm = enmInit.instantiate();
		Assertions.assertTrue(enmInit.addConstants(enm, consts));
		return enm;
	}

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		const1 = this.createMinimalEnumConstant("cst1");
		const2 = this.createMinimalEnumConstant("cst2");
		Assertions.assertFalse(this.isSimilar(const1, const2));
	}

	@Test
	public void testConstant() {
		var objOne = this.initElement(new EnumConstant[] { this.cloneEObjWithContainers(const1) });
		var objTwo = this.initElement(new EnumConstant[] { this.cloneEObjWithContainers(const2) });

		this.testSimilarity(objOne, objTwo, ClassifiersPackage.Literals.ENUMERATION__CONSTANTS);
	}

	@Test
	public void testConstantSize() {
		var objOne = this.initElement(
				new EnumConstant[] { this.cloneEObjWithContainers(const1), this.cloneEObjWithContainers(const2) });
		var objTwo = this.initElement(new EnumConstant[] { this.cloneEObjWithContainers(const1) });

		this.testSimilarity(objOne, objTwo, ClassifiersPackage.Literals.ENUMERATION__CONSTANTS);
	}

	@Test
	public void testConstantPosition() {
		var objOne = this.initElement(
				new EnumConstant[] { this.cloneEObjWithContainers(const1), this.cloneEObjWithContainers(const2) });
		var objTwo = this.initElement(
				new EnumConstant[] { this.cloneEObjWithContainers(const2), this.cloneEObjWithContainers(const1) });

		this.testSimilarity(objOne, objTwo, ClassifiersPackage.Literals.ENUMERATION__CONSTANTS);
	}

	@Test
	public void testConstantDuplication() {
		var objOne = this.initElement(
				new EnumConstant[] { this.cloneEObjWithContainers(const1), this.cloneEObjWithContainers(const1) });
		var objTwo = this.initElement(new EnumConstant[] { this.cloneEObjWithContainers(const1) });

		this.testSimilarity(objOne, objTwo, ClassifiersPackage.Literals.ENUMERATION__CONSTANTS);
	}

	@Test
	public void testConstantNullCheck() {
		this.testSimilarityNullCheck(this.initElement(new EnumConstant[] { this.cloneEObjWithContainers(const1) }),
				new EnumerationInitialiser(), false, ClassifiersPackage.Literals.ENUMERATION__CONSTANTS);
	}
}
