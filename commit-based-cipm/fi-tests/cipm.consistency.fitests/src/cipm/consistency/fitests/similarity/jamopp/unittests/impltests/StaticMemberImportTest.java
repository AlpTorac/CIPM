package cipm.consistency.fitests.similarity.jamopp.unittests.impltests;

import org.emftext.language.java.imports.ImportsPackage;
import org.emftext.language.java.imports.StaticMemberImport;
import org.emftext.language.java.references.ReferenceableElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesMethods;
import cipm.consistency.initialisers.jamopp.imports.StaticMemberImportInitialiser;

public class StaticMemberImportTest extends AbstractJaMoPPSimilarityTest implements UsesMethods {
	private ReferenceableElement sm1;
	private ReferenceableElement sm2;

	protected StaticMemberImport initElement(ReferenceableElement[] staticMems) {
		var smiInit = new StaticMemberImportInitialiser();
		var smi = smiInit.instantiate();
		Assertions.assertTrue(smiInit.addStaticMembers(smi, staticMems));
		return smi;
	}

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		sm1 = this.createMinimalClsMethodWithNullReturn("met1");
		sm2 = this.createMinimalClsMethodWithNullReturn("met2");
		Assertions.assertFalse(this.isSimilar(sm1, sm2));
	}

	@Test
	public void testStaticMember() {
		var objOne = this.initElement(new ReferenceableElement[] { this.cloneEObjWithContainers(sm1) });
		var objTwo = this.initElement(new ReferenceableElement[] { this.cloneEObjWithContainers(sm2) });

		this.testSimilarity(objOne, objTwo, ImportsPackage.Literals.STATIC_MEMBER_IMPORT__STATIC_MEMBERS);
	}

	@Test
	public void testStaticMemberSize() {
		var objOne = this.initElement(
				new ReferenceableElement[] { this.cloneEObjWithContainers(sm1), this.cloneEObjWithContainers(sm2) });
		var objTwo = this.initElement(new ReferenceableElement[] { this.cloneEObjWithContainers(sm1) });

		this.testSimilarity(objOne, objTwo, ImportsPackage.Literals.STATIC_MEMBER_IMPORT__STATIC_MEMBERS);
	}

	@Test
	public void testStaticMemberPosition() {
		var objOne = this.initElement(
				new ReferenceableElement[] { this.cloneEObjWithContainers(sm1), this.cloneEObjWithContainers(sm2) });
		var objTwo = this.initElement(
				new ReferenceableElement[] { this.cloneEObjWithContainers(sm2), this.cloneEObjWithContainers(sm1) });

		this.testSimilarity(objOne, objTwo, ImportsPackage.Literals.STATIC_MEMBER_IMPORT__STATIC_MEMBERS);
	}

	@Test
	public void testStaticMemberDuplication() {
		var objOne = this.initElement(
				new ReferenceableElement[] { this.cloneEObjWithContainers(sm1), this.cloneEObjWithContainers(sm1) });
		var objTwo = this.initElement(new ReferenceableElement[] { this.cloneEObjWithContainers(sm1) });

		this.testSimilarity(objOne, objTwo, ImportsPackage.Literals.STATIC_MEMBER_IMPORT__STATIC_MEMBERS);
	}

	@Test
	public void testStaticMemberNullCheck() {
		this.testSimilarityNullCheck(this.initElement(new ReferenceableElement[] { this.cloneEObjWithContainers(sm1) }),
				new StaticMemberImportInitialiser(), false,
				ImportsPackage.Literals.STATIC_MEMBER_IMPORT__STATIC_MEMBERS);
	}
}
