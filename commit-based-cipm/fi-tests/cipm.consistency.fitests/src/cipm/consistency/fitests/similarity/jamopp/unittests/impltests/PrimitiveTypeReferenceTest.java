package cipm.consistency.fitests.similarity.jamopp.unittests.impltests;

import org.emftext.language.java.references.PrimitiveTypeReference;
import org.emftext.language.java.references.ReferencesPackage;
import org.emftext.language.java.types.PrimitiveType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesPrimitiveTypes;
import cipm.consistency.initialisers.jamopp.references.PrimitiveTypeReferenceInitialiser;

public class PrimitiveTypeReferenceTest extends AbstractJaMoPPSimilarityTest implements UsesPrimitiveTypes {
	private PrimitiveType pType1;
	private PrimitiveType pType2;

	protected PrimitiveTypeReference initElement(PrimitiveType pType) {
		var ptInit = new PrimitiveTypeReferenceInitialiser();
		var pt = ptInit.instantiate();
		Assertions.assertTrue(ptInit.setPrimitiveType(pt, pType));
		return pt;
	}

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		pType1 = this.createBoolean();
		pType2 = this.createInt();
		Assertions.assertFalse(this.isSimilar(pType1, pType2));
	}

	@Test
	public void testPrimitiveType() {
		var objOne = this.initElement(this.cloneEObjWithContainers(pType1));
		var objTwo = this.initElement(this.cloneEObjWithContainers(pType2));

		this.testSimilarity(objOne, objTwo, ReferencesPackage.Literals.PRIMITIVE_TYPE_REFERENCE__PRIMITIVE_TYPE);
	}

	@Test
	public void testPrimitiveTypeNullCheck() {
		this.testSimilarityNullCheck(this.initElement(this.cloneEObjWithContainers(pType1)),
				new PrimitiveTypeReferenceInitialiser(), false,
				ReferencesPackage.Literals.PRIMITIVE_TYPE_REFERENCE__PRIMITIVE_TYPE);
	}
}
