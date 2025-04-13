package cipm.consistency.fitests.similarity.jamopp.unittests.impltests;

import org.emftext.language.java.classifiers.Interface;
import org.emftext.language.java.classifiers.ClassifiersPackage;
import org.emftext.language.java.types.TypeReference;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesTypeReferences;
import cipm.consistency.initialisers.jamopp.classifiers.InterfaceInitialiser;

public class InterfaceTest extends AbstractJaMoPPSimilarityTest implements UsesTypeReferences {
	private TypeReference defExt1;
	private TypeReference defExt2;
	private TypeReference ext1;
	private TypeReference ext2;

	protected Interface initElement(TypeReference[] defExts, TypeReference[] exts) {
		var intfcInit = new InterfaceInitialiser();
		var intfc = intfcInit.instantiate();
		Assertions.assertTrue(intfcInit.addDefaultExtends(intfc, defExts));
		Assertions.assertTrue(intfcInit.addExtends(intfc, exts));
		return intfc;
	}

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		defExt1 = this.createMinimalClsRef("cls1");
		defExt2 = this.createMinimalClsRef("cls2");
		Assertions.assertFalse(this.isSimilar(defExt1, defExt2));

		ext1 = this.createMinimalClsRef("cls1");
		ext2 = this.createMinimalClsRef("cls2");
		Assertions.assertFalse(this.isSimilar(ext1, ext2));
	}

	@Test
	public void testDefaultExtends() {
		var objOne = this.initElement(new TypeReference[] { this.cloneEObjWithContainers(defExt1) }, null);
		var objTwo = this.initElement(new TypeReference[] { this.cloneEObjWithContainers(defExt2) }, null);

		this.testSimilarity(objOne, objTwo, ClassifiersPackage.Literals.INTERFACE__DEFAULT_EXTENDS);
	}

	@Test
	public void testDefaultExtendsSize() {
		var objOne = this.initElement(
				new TypeReference[] { this.cloneEObjWithContainers(defExt1), this.cloneEObjWithContainers(defExt2) },
				null);
		var objTwo = this.initElement(new TypeReference[] { this.cloneEObjWithContainers(defExt1) }, null);

		this.testSimilarity(objOne, objTwo, ClassifiersPackage.Literals.INTERFACE__DEFAULT_EXTENDS);
	}

	@Test
	public void testDefaultExtendsPosition() {
		var objOne = this.initElement(
				new TypeReference[] { this.cloneEObjWithContainers(defExt1), this.cloneEObjWithContainers(defExt2) },
				null);
		var objTwo = this.initElement(
				new TypeReference[] { this.cloneEObjWithContainers(defExt2), this.cloneEObjWithContainers(defExt1) },
				null);

		this.testSimilarity(objOne, objTwo, ClassifiersPackage.Literals.INTERFACE__DEFAULT_EXTENDS);
	}

	@Test
	public void testDefaultExtendsDuplication() {
		var objOne = this.initElement(
				new TypeReference[] { this.cloneEObjWithContainers(defExt1), this.cloneEObjWithContainers(defExt1) },
				null);
		var objTwo = this.initElement(new TypeReference[] { this.cloneEObjWithContainers(defExt1) }, null);

		this.testSimilarity(objOne, objTwo, ClassifiersPackage.Literals.INTERFACE__DEFAULT_EXTENDS);
	}

	@Test
	public void testDefaultExtendsNullCheck() {
		this.testSimilarityNullCheck(
				this.initElement(new TypeReference[] { this.cloneEObjWithContainers(defExt1) }, null),
				new InterfaceInitialiser(), false, ClassifiersPackage.Literals.INTERFACE__DEFAULT_EXTENDS);
	}

	@Test
	public void testExtends() {
		var objOne = this.initElement(null, new TypeReference[] { this.cloneEObjWithContainers(ext1) });
		var objTwo = this.initElement(null, new TypeReference[] { this.cloneEObjWithContainers(ext2) });

		this.testSimilarity(objOne, objTwo, ClassifiersPackage.Literals.INTERFACE__EXTENDS);
	}

	@Test
	public void testExtendsSize() {
		var objOne = this.initElement(null,
				new TypeReference[] { this.cloneEObjWithContainers(ext1), this.cloneEObjWithContainers(ext2) });
		var objTwo = this.initElement(null, new TypeReference[] { this.cloneEObjWithContainers(ext1) });

		this.testSimilarity(objOne, objTwo, ClassifiersPackage.Literals.INTERFACE__EXTENDS);
	}

	@Test
	public void testExtendsPosition() {
		var objOne = this.initElement(null,
				new TypeReference[] { this.cloneEObjWithContainers(ext1), this.cloneEObjWithContainers(ext2) });
		var objTwo = this.initElement(null,
				new TypeReference[] { this.cloneEObjWithContainers(ext2), this.cloneEObjWithContainers(ext1) });

		this.testSimilarity(objOne, objTwo, ClassifiersPackage.Literals.INTERFACE__EXTENDS);
	}

	@Test
	public void testExtendsDuplication() {
		var objOne = this.initElement(null,
				new TypeReference[] { this.cloneEObjWithContainers(ext1), this.cloneEObjWithContainers(ext1) });
		var objTwo = this.initElement(null, new TypeReference[] { this.cloneEObjWithContainers(ext1) });

		this.testSimilarity(objOne, objTwo, ClassifiersPackage.Literals.INTERFACE__EXTENDS);
	}

	@Test
	public void testExtendsNullCheck() {
		this.testSimilarityNullCheck(this.initElement(null, new TypeReference[] { this.cloneEObjWithContainers(ext1) }),
				new InterfaceInitialiser(), false, ClassifiersPackage.Literals.INTERFACE__EXTENDS);
	}
}
