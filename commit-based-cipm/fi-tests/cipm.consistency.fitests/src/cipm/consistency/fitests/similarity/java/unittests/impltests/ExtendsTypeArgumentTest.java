package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.generics.ExtendsTypeArgument;
import org.emftext.language.java.generics.GenericsPackage;
import org.emftext.language.java.types.TypeReference;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.generics.ExtendsTypeArgumentInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesTypeReferences;

public class ExtendsTypeArgumentTest extends EObjectSimilarityTest implements UsesTypeReferences {
	protected ExtendsTypeArgument initElement(TypeReference tref) {
		var etaInit = new ExtendsTypeArgumentInitialiser();
		var eta = etaInit.instantiate();
		Assertions.assertTrue(etaInit.setExtendType(eta, tref));
		return eta;
	}
	
	@Test
	public void testExtendType() {
		this.setResourceFileTestIdentifier("testExtendType");
		
		var objOne = this.initElement(this.createMinimalClsRef("cls1"));
		var objTwo = this.initElement(this.createMinimalClsRef("cls2"));
		
		this.testSimilarity(objOne, objTwo, GenericsPackage.Literals.EXTENDS_TYPE_ARGUMENT__EXTEND_TYPE);
	}
}
