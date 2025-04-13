package cipm.consistency.fitests.similarity.jamopp.unittests.impltests;

import org.emftext.language.java.generics.ExtendsTypeArgument;
import org.emftext.language.java.generics.GenericsPackage;
import org.emftext.language.java.types.TypeReference;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesTypeReferences;
import cipm.consistency.initialisers.jamopp.generics.ExtendsTypeArgumentInitialiser;

public class ExtendsTypeArgumentTest extends AbstractJaMoPPSimilarityTest implements UsesTypeReferences {
	private TypeReference extType1;
	private TypeReference extType2;

	protected ExtendsTypeArgument initElement(TypeReference extType) {
		var etaInit = new ExtendsTypeArgumentInitialiser();
		var eta = etaInit.instantiate();
		Assertions.assertTrue(etaInit.setExtendType(eta, extType));
		return eta;
	}

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		extType1 = this.createMinimalClsRef("cls1");
		extType2 = this.createMinimalClsRef("cls2");
		Assertions.assertFalse(this.isSimilar(extType1, extType2));
	}

	@Test
	public void testExtendType() {
		var objOne = this.initElement(this.cloneEObjWithContainers(extType1));
		var objTwo = this.initElement(this.cloneEObjWithContainers(extType2));

		this.testSimilarity(objOne, objTwo, GenericsPackage.Literals.EXTENDS_TYPE_ARGUMENT__EXTEND_TYPE);
	}

	@Test
	public void testExtendTypeNullCheck() {
		this.testSimilarityNullCheck(this.initElement(this.cloneEObjWithContainers(extType1)),
				new ExtendsTypeArgumentInitialiser(), false,
				GenericsPackage.Literals.EXTENDS_TYPE_ARGUMENT__EXTEND_TYPE);
	}
}
