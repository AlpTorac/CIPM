package cipm.consistency.fitests.similarity.jamopp.unittests.impltests;

import org.emftext.language.java.generics.GenericsPackage;
import org.emftext.language.java.generics.TypeParameter;
import org.emftext.language.java.types.TypeReference;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesNames;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesTypeReferences;
import cipm.consistency.initialisers.jamopp.generics.TypeParameterInitialiser;

public class TypeParameterTest extends AbstractJaMoPPSimilarityTest implements UsesTypeReferences, UsesNames {
	private TypeReference et1;
	private TypeReference et2;

	protected TypeParameter initElement(TypeReference[] extTypes) {
		var tpInit = new TypeParameterInitialiser();
		var tp = tpInit.instantiate();
		Assertions.assertTrue(tpInit.setName(tp, this.getDefaultName()));
		Assertions.assertTrue(tpInit.addExtendTypes(tp, extTypes));
		return tp;
	}

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		et1 = this.createMinimalClsRef("cls1");
		et2 = this.createMinimalClsRef("cls2");
		Assertions.assertFalse(this.isSimilar(et1, et2));
	}

	@Test
	public void testExtendType() {
		var objOne = this.initElement(new TypeReference[] { this.cloneEObjWithContainers(et1) });
		var objTwo = this.initElement(new TypeReference[] { this.cloneEObjWithContainers(et2) });

		this.testSimilarity(objOne, objTwo, GenericsPackage.Literals.TYPE_PARAMETER__EXTEND_TYPES);
	}

	@Test
	public void testExtendTypeSize() {
		var objOne = this.initElement(
				new TypeReference[] { this.cloneEObjWithContainers(et1), this.cloneEObjWithContainers(et2) });
		var objTwo = this.initElement(new TypeReference[] { this.cloneEObjWithContainers(et1) });

		this.testSimilarity(objOne, objTwo, GenericsPackage.Literals.TYPE_PARAMETER__EXTEND_TYPES);
	}

	@Test
	public void testExtendTypePosition() {
		var objOne = this.initElement(
				new TypeReference[] { this.cloneEObjWithContainers(et1), this.cloneEObjWithContainers(et2) });
		var objTwo = this.initElement(
				new TypeReference[] { this.cloneEObjWithContainers(et2), this.cloneEObjWithContainers(et1) });

		this.testSimilarity(objOne, objTwo, GenericsPackage.Literals.TYPE_PARAMETER__EXTEND_TYPES);
	}

	@Test
	public void testExtendTypeDuplication() {
		var objOne = this.initElement(
				new TypeReference[] { this.cloneEObjWithContainers(et1), this.cloneEObjWithContainers(et1) });
		var objTwo = this.initElement(new TypeReference[] { this.cloneEObjWithContainers(et1) });

		this.testSimilarity(objOne, objTwo, GenericsPackage.Literals.TYPE_PARAMETER__EXTEND_TYPES);
	}

	@Test
	public void testExtendTypeNullCheck() {
		this.testSimilarityNullCheck(this.initElement(new TypeReference[] { this.cloneEObjWithContainers(et1) }),
				new TypeParameterInitialiser(), false, GenericsPackage.Literals.TYPE_PARAMETER__EXTEND_TYPES);
	}
}
