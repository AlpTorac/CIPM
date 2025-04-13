package cipm.consistency.fitests.similarity.jamopp.unittests.impltests;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesTypeReferences;
import cipm.consistency.initialisers.jamopp.classifiers.ClassInitialiser;

import org.emftext.language.java.classifiers.Class;
import org.emftext.language.java.classifiers.ClassifiersPackage;
import org.emftext.language.java.types.TypeReference;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

public class ClassTest extends AbstractJaMoPPSimilarityTest implements UsesTypeReferences {
	private TypeReference defExt1;
	private TypeReference defExt2;
	private TypeReference ext1;
	private TypeReference ext2;

	protected Class initElement(TypeReference defExt, TypeReference ext) {
		var clsInit = new ClassInitialiser();
		var cls = clsInit.instantiate();
		Assertions.assertTrue(clsInit.setDefaultExtends(cls, defExt));
		Assertions.assertTrue(clsInit.setExtends(cls, ext));
		return cls;
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
		var objOne = this.initElement(this.cloneEObjWithContainers(defExt1), null);
		var objTwo = this.initElement(this.cloneEObjWithContainers(defExt2), null);

		this.testSimilarity(objOne, objTwo, ClassifiersPackage.Literals.CLASS__DEFAULT_EXTENDS);
	}

	@Test
	public void testDefaultExtendsNullCheck() {
		this.testSimilarityNullCheck(this.initElement(this.cloneEObjWithContainers(defExt1), null),
				new ClassInitialiser(), false, ClassifiersPackage.Literals.CLASS__DEFAULT_EXTENDS);
	}

	@Test
	public void testExtends() {
		var objOne = this.initElement(null, this.cloneEObjWithContainers(ext1));
		var objTwo = this.initElement(null, this.cloneEObjWithContainers(ext2));

		this.testSimilarity(objOne, objTwo, ClassifiersPackage.Literals.CLASS__EXTENDS);
	}

	@Test
	public void testExtendsNullCheck() {
		this.testSimilarityNullCheck(this.initElement(null, this.cloneEObjWithContainers(ext1)), new ClassInitialiser(),
				false, ClassifiersPackage.Literals.CLASS__EXTENDS);
	}
}
