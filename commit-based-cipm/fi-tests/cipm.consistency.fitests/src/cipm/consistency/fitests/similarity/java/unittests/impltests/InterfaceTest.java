package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.classifiers.Interface;
import org.emftext.language.java.classifiers.ClassifiersPackage;
import org.emftext.language.java.types.TypeReference;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.classifiers.InterfaceInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesTypeReferences;

public class InterfaceTest extends EObjectSimilarityTest implements UsesTypeReferences {
	protected Interface initElement(TypeReference[] defExts, TypeReference[] exts) {
		var intfcInit = new InterfaceInitialiser();
		var intfc = intfcInit.instantiate();
		Assertions.assertTrue(intfcInit.addDefaultExtends(intfc, defExts));
		Assertions.assertTrue(intfcInit.addExtends(intfc, exts));
		return intfc;
	}

	@Test
	public void testDefaultExtends() {
		this.setResourceFileTestIdentifier("testDefaultExtends");

		var objOne = this.initElement(new TypeReference[] { this.createMinimalClsRef("cls1") }, null);
		var objTwo = this.initElement(new TypeReference[] { this.createMinimalClsRef("cls2") }, null);

		this.testSimilarity(objOne, objTwo, ClassifiersPackage.Literals.INTERFACE__DEFAULT_EXTENDS);
	}

	@Test
	public void testDefaultExtendsNullCheck() {
		this.setResourceFileTestIdentifier("testDefaultExtendsNullCheck");

		var objOne = this.initElement(new TypeReference[] { this.createMinimalClsRef("cls1") }, null);
		var objTwo = new InterfaceInitialiser().instantiate();

		this.testSimilarity(objOne, objTwo, ClassifiersPackage.Literals.INTERFACE__DEFAULT_EXTENDS);
	}

	@Test
	public void testExtends() {
		this.setResourceFileTestIdentifier("testExtends");

		var objOne = this.initElement(null, new TypeReference[] { this.createMinimalClsRef("cls1") });
		var objTwo = this.initElement(null, new TypeReference[] { this.createMinimalClsRef("cls2") });

		this.testSimilarity(objOne, objTwo, ClassifiersPackage.Literals.INTERFACE__EXTENDS);
	}

	@Test
	public void testExtendsNullCheck() {
		this.setResourceFileTestIdentifier("testExtendsNullCheck");

		var objOne = this.initElement(null, new TypeReference[] { this.createMinimalClsRef("cls1") });
		var objTwo = new InterfaceInitialiser().instantiate();

		this.testSimilarity(objOne, objTwo, ClassifiersPackage.Literals.INTERFACE__EXTENDS);
	}
}
