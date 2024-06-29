package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.generics.GenericsPackage;
import org.emftext.language.java.generics.TypeParameter;
import org.emftext.language.java.types.TypeReference;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.generics.TypeParameterInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesTypeReferences;

public class TypeParameterTest extends EObjectSimilarityTest implements UsesTypeReferences {
	protected TypeParameter initElement(TypeReference[] exts) {
		var tpInit = new TypeParameterInitialiser();
		var tp = tpInit.instantiate();
		Assertions.assertTrue(tpInit.minimalInitialisation(tp));
		Assertions.assertTrue(tpInit.addExtendTypes(tp, exts));
		return tp;
	}
	
	@Test
	public void testExtendType() {
		this.setResourceFileTestIdentifier("testExtendType");
		
		var objOne = this.initElement(new TypeReference[] {this.createMinimalClsRef("cls1")});
		var objTwo = this.initElement(new TypeReference[] {this.createMinimalClsRef("cls2")});
		
		this.testX(objOne, objTwo, GenericsPackage.Literals.TYPE_PARAMETER__EXTEND_TYPES);
	}
}
