package cipm.consistency.fitests.similarity.eobject.java.unittests.impltests;

import org.emftext.language.java.references.PrimitiveTypeReference;
import org.emftext.language.java.references.ReferencesPackage;
import org.emftext.language.java.types.PrimitiveType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.eobject.java.AbstractEObjectJavaSimilarityTest;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.references.PrimitiveTypeReferenceInitialiser;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.types.BooleanInitialiser;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.types.IntInitialiser;

public class PrimitiveTypeReferenceTest extends AbstractEObjectJavaSimilarityTest {
	protected PrimitiveTypeReference initElement(PrimitiveType pType) {
		var ptInit = new PrimitiveTypeReferenceInitialiser();
		var pt = ptInit.instantiate();
		Assertions.assertTrue(ptInit.setPrimitiveType(pt, pType));
		return pt;
	}

	@Test
	public void testPrimitiveType() {
		var objOne = this.initElement(new BooleanInitialiser().instantiate());
		var objTwo = this.initElement(new IntInitialiser().instantiate());

		this.testSimilarity(objOne, objTwo, ReferencesPackage.Literals.PRIMITIVE_TYPE_REFERENCE__PRIMITIVE_TYPE);
	}

	@Test
	public void testPrimitiveTypeNullCheck() {
		this.testSimilarityNullCheck(this.initElement(new BooleanInitialiser().instantiate()),
				new PrimitiveTypeReferenceInitialiser(), false,
				ReferencesPackage.Literals.PRIMITIVE_TYPE_REFERENCE__PRIMITIVE_TYPE);
	}
}
