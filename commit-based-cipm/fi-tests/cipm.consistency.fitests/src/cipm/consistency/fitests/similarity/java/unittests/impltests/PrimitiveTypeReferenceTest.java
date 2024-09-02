package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.references.PrimitiveTypeReference;
import org.emftext.language.java.references.ReferencesPackage;
import org.emftext.language.java.types.PrimitiveType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.references.PrimitiveTypeReferenceInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.types.BooleanInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.types.IntInitialiser;

public class PrimitiveTypeReferenceTest extends EObjectSimilarityTest {
	protected PrimitiveTypeReference initElement(PrimitiveType pType) {
		var ptInit = new PrimitiveTypeReferenceInitialiser();
		var pt = ptInit.instantiate();
		Assertions.assertTrue(ptInit.setPrimitiveType(pt, pType));
		return pt;
	}

	@Test
	public void testPrimitiveType() {
		this.setResourceFileTestIdentifier("testPrimitiveType");

		var objOne = this.initElement(new BooleanInitialiser().instantiate());
		var objTwo = this.initElement(new IntInitialiser().instantiate());

		this.testSimilarity(objOne, objTwo, ReferencesPackage.Literals.PRIMITIVE_TYPE_REFERENCE__PRIMITIVE_TYPE);
	}

	@Test
	public void testPrimitiveTypeNullCheck() {
		this.setResourceFileTestIdentifier("testPrimitiveTypeNullCheck");

		var objOne = this.initElement(new BooleanInitialiser().instantiate());
		var objTwo = new PrimitiveTypeReferenceInitialiser().instantiate();

		this.testSimilarity(objOne, objTwo, ReferencesPackage.Literals.PRIMITIVE_TYPE_REFERENCE__PRIMITIVE_TYPE);
	}
}
