package cipm.consistency.fitests.similarity.eobject.java.unittests.impltests;

import org.emftext.language.java.generics.GenericsPackage;
import org.emftext.language.java.generics.TypeParameter;
import org.emftext.language.java.types.TypeReference;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.eobject.java.AbstractEObjectJavaSimilarityTest;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.generics.TypeParameterInitialiser;
import cipm.consistency.fitests.similarity.eobject.java.unittests.UsesNames;
import cipm.consistency.fitests.similarity.eobject.java.unittests.UsesTypeReferences;

public class TypeParameterTest extends AbstractEObjectJavaSimilarityTest implements UsesTypeReferences, UsesNames {
	protected TypeParameter initElement(TypeReference[] extTypes) {
		var tpInit = new TypeParameterInitialiser();
		var tp = tpInit.instantiate();
		Assertions.assertTrue(tpInit.setName(tp, this.getDefaultName()));
		Assertions.assertTrue(tpInit.addExtendTypes(tp, extTypes));
		return tp;
	}

	@Test
	public void testExtendType() {
		var objOne = this.initElement(new TypeReference[] { this.createMinimalClsRef("cls1") });
		var objTwo = this.initElement(new TypeReference[] { this.createMinimalClsRef("cls2") });

		this.testSimilarity(objOne, objTwo, GenericsPackage.Literals.TYPE_PARAMETER__EXTEND_TYPES);
	}

	@Test
	public void testExtendTypeSize() {
		var objOne = this.initElement(
				new TypeReference[] { this.createMinimalClsRef("cls1"), this.createMinimalClsRef("cls2") });
		var objTwo = this.initElement(new TypeReference[] { this.createMinimalClsRef("cls1") });

		this.testSimilarity(objOne, objTwo, GenericsPackage.Literals.TYPE_PARAMETER__EXTEND_TYPES);
	}

	@Test
	public void testExtendTypeNullCheck() {
		this.testSimilarityNullCheck(this.initElement(new TypeReference[] { this.createMinimalClsRef("cls1") }),
				new TypeParameterInitialiser(), false, GenericsPackage.Literals.TYPE_PARAMETER__EXTEND_TYPES);
	}
}
