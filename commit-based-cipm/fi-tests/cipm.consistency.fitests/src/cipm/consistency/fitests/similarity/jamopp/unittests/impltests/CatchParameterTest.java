package cipm.consistency.fitests.similarity.jamopp.unittests.impltests;

import org.emftext.language.java.parameters.CatchParameter;
import org.emftext.language.java.parameters.ParametersPackage;
import org.emftext.language.java.types.TypeReference;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesTypeReferences;
import cipm.consistency.initialisers.jamopp.parameters.CatchParameterInitialiser;

public class CatchParameterTest extends AbstractJaMoPPSimilarityTest implements UsesTypeReferences {
	private TypeReference tref1;
	private TypeReference tref2;

	protected CatchParameter initElement(TypeReference[] trefs) {
		var cpInit = new CatchParameterInitialiser();
		var cp = cpInit.instantiate();
		Assertions.assertTrue(cpInit.addTypeReferences(cp, trefs));
		return cp;
	}

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		tref1 = this.createMinimalClsRef("cls1");
		tref2 = this.createMinimalClsRef("cls2");
		Assertions.assertFalse(this.isSimilar(tref1, tref2));
	}

	@Test
	public void testTypeReference() {
		var objOne = this.initElement(new TypeReference[] { this.cloneEObjWithContainers(tref1) });
		var objTwo = this.initElement(new TypeReference[] { this.cloneEObjWithContainers(tref2) });

		this.testSimilarity(objOne, objTwo, ParametersPackage.Literals.CATCH_PARAMETER__TYPE_REFERENCES);
	}

	@Test
	public void testTypeReferenceSize() {
		var objOne = this.initElement(
				new TypeReference[] { this.cloneEObjWithContainers(tref1), this.cloneEObjWithContainers(tref2) });
		var objTwo = this.initElement(new TypeReference[] { this.cloneEObjWithContainers(tref1) });

		this.testSimilarity(objOne, objTwo, ParametersPackage.Literals.CATCH_PARAMETER__TYPE_REFERENCES);
	}

	@Test
	public void testTypeReferencePosition() {
		var objOne = this.initElement(
				new TypeReference[] { this.cloneEObjWithContainers(tref1), this.cloneEObjWithContainers(tref2) });
		var objTwo = this.initElement(
				new TypeReference[] { this.cloneEObjWithContainers(tref2), this.cloneEObjWithContainers(tref1) });

		this.testSimilarity(objOne, objTwo, ParametersPackage.Literals.CATCH_PARAMETER__TYPE_REFERENCES);
	}

	@Test
	public void testTypeReferenceDuplication() {
		var objOne = this.initElement(
				new TypeReference[] { this.cloneEObjWithContainers(tref1), this.cloneEObjWithContainers(tref1) });
		var objTwo = this.initElement(new TypeReference[] { this.cloneEObjWithContainers(tref1) });

		this.testSimilarity(objOne, objTwo, ParametersPackage.Literals.CATCH_PARAMETER__TYPE_REFERENCES);
	}

	@Test
	public void testTypeReferenceNullCheck() {
		this.testSimilarityNullCheck(this.initElement(new TypeReference[] { this.cloneEObjWithContainers(tref1) }),
				new CatchParameterInitialiser(), false, ParametersPackage.Literals.CATCH_PARAMETER__TYPE_REFERENCES);
	}
}
