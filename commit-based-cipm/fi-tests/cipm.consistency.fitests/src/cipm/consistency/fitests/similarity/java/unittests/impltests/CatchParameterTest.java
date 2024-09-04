package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.parameters.CatchParameter;
import org.emftext.language.java.parameters.ParametersPackage;
import org.emftext.language.java.types.TypeReference;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.parameters.CatchParameterInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesTypeReferences;

public class CatchParameterTest extends EObjectSimilarityTest implements UsesTypeReferences {
	protected CatchParameter initElement(TypeReference[] trefs) {
		var cpInit = new CatchParameterInitialiser();
		var cp = cpInit.instantiate();
		Assertions.assertTrue(cpInit.addTypeReferences(cp, trefs));
		return cp;
	}

	@Test
	public void testTypeReference() {
		var objOne = this.initElement(new TypeReference[] { this.createMinimalClsRef("cls1") });
		var objTwo = this.initElement(new TypeReference[] { this.createMinimalClsRef("cls2") });

		this.testSimilarity(objOne, objTwo, ParametersPackage.Literals.CATCH_PARAMETER__TYPE_REFERENCES);
	}

	@Test
	public void testTypeReferenceSize() {
		var objOne = this.initElement(
				new TypeReference[] { this.createMinimalClsRef("cls1"), this.createMinimalClsRef("cls2") });
		var objTwo = this.initElement(new TypeReference[] { this.createMinimalClsRef("cls1") });

		this.testSimilarity(objOne, objTwo, ParametersPackage.Literals.CATCH_PARAMETER__TYPE_REFERENCES);
	}

	@Test
	public void testTypeReferenceNullCheck() {
		var objOne = this.initElement(new TypeReference[] { this.createMinimalClsRef("cls1") });
		var objTwo = new CatchParameterInitialiser().instantiate();

		this.testSimilarity(objOne, objTwo, ParametersPackage.Literals.CATCH_PARAMETER__TYPE_REFERENCES);
	}
}
