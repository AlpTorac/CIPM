package cipm.consistency.fitests.similarity.eobject.java.unittests.impltests;

import org.emftext.language.java.generics.ExtendsTypeArgument;
import org.emftext.language.java.generics.GenericsPackage;
import org.emftext.language.java.types.TypeReference;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.eobject.java.AbstractEObjectJavaSimilarityTest;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.generics.ExtendsTypeArgumentInitialiser;
import cipm.consistency.fitests.similarity.eobject.java.unittests.UsesTypeReferences;

public class ExtendsTypeArgumentTest extends AbstractEObjectJavaSimilarityTest implements UsesTypeReferences {
	protected ExtendsTypeArgument initElement(TypeReference extType) {
		var etaInit = new ExtendsTypeArgumentInitialiser();
		var eta = etaInit.instantiate();
		Assertions.assertTrue(etaInit.setExtendType(eta, extType));
		return eta;
	}

	@Test
	public void testExtendType() {
		var objOne = this.initElement(this.createMinimalClsRef("cls1"));
		var objTwo = this.initElement(this.createMinimalClsRef("cls2"));

		this.testSimilarity(objOne, objTwo, GenericsPackage.Literals.EXTENDS_TYPE_ARGUMENT__EXTEND_TYPE);
	}

	@Test
	public void testExtendTypeNullCheck() {
		this.testSimilarityNullCheck(this.initElement(this.createMinimalClsRef("cls1")),
				new ExtendsTypeArgumentInitialiser(), false,
				GenericsPackage.Literals.EXTENDS_TYPE_ARGUMENT__EXTEND_TYPE);
	}
}
