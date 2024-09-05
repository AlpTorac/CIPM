package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.parameters.ReceiverParameter;
import org.emftext.language.java.literals.This;
import org.emftext.language.java.parameters.ParametersPackage;
import org.emftext.language.java.types.TypeReference;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.parameters.ReceiverParameterInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesLiterals;
import cipm.consistency.fitests.similarity.java.unittests.UsesTypeReferences;

public class ReceiverParameterTest extends EObjectSimilarityTest implements UsesTypeReferences, UsesLiterals {
	protected ReceiverParameter initElement(This thisRef, TypeReference otRef) {
		var rpInit = new ReceiverParameterInitialiser();
		var rp = rpInit.instantiate();
		Assertions.assertTrue(rpInit.setThisReference(rp, thisRef));
		Assertions.assertTrue(rpInit.setOuterTypeReference(rp, otRef));
		return rp;
	}

	@Test
	public void testThisTypeReference() {
		var objOne = this.initElement(this.createThis(), null);
		var objTwo = this.initElement(null, null);

		this.testSimilarity(objOne, objTwo, ParametersPackage.Literals.RECEIVER_PARAMETER__THIS_REFERENCE);
	}

	@Test
	public void testThisTypeReferenceNullCheck() {
		var objOne = this.initElement(this.createThis(), null);

		this.testSimilarityNullCheck(objOne, new ReceiverParameterInitialiser(), false, ParametersPackage.Literals.RECEIVER_PARAMETER__THIS_REFERENCE);
	}

	@Test
	public void testOuterTypeReference() {
		var objOne = this.initElement(null, this.createMinimalClsRef("cls1"));
		var objTwo = this.initElement(null, this.createMinimalClsRef("cls2"));

		this.testSimilarity(objOne, objTwo, ParametersPackage.Literals.RECEIVER_PARAMETER__OUTER_TYPE_REFERENCE);
	}

	@Test
	public void testOuterTypeReferenceNullCheck() {
		var objOne = this.initElement(null, this.createMinimalClsRef("cls1"));

		this.testSimilarityNullCheck(objOne, new ReceiverParameterInitialiser(), false, ParametersPackage.Literals.RECEIVER_PARAMETER__OUTER_TYPE_REFERENCE);
	}
}
