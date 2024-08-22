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
		this.setResourceFileTestIdentifier("testThisTypeReference");

		var objOne = this.initElement(this.createThis(), null);
		var objTwo = this.initElement(null, null);

		this.testSimilarity(objOne, objTwo, ParametersPackage.Literals.RECEIVER_PARAMETER__THIS_REFERENCE);
	}
	
	@Test
	public void testThisTypeReferenceNull() {
		this.setResourceFileTestIdentifier("testThisTypeReferenceNull");
		
		var objOne = this.initElement(this.createThis(), null);
		var objTwo = new ReceiverParameterInitialiser().instantiate();
		
		this.testSimilarity(objOne, objTwo, ParametersPackage.Literals.RECEIVER_PARAMETER__THIS_REFERENCE);
	}

	@Test
	public void testOuterTypeReference() {
		this.setResourceFileTestIdentifier("testOuterTypeReference");

		var objOne = this.initElement(null, this.createMinimalClsRef("cls1"));
		var objTwo = this.initElement(null, this.createMinimalClsRef("cls2"));

		this.testSimilarity(objOne, objTwo, ParametersPackage.Literals.RECEIVER_PARAMETER__OUTER_TYPE_REFERENCE);
	}
	
	@Test
	public void testOuterTypeReferenceNull() {
		this.setResourceFileTestIdentifier("testOuterTypeReferenceNull");
		
		var objOne = this.initElement(null, this.createMinimalClsRef("cls1"));
		var objTwo = new ReceiverParameterInitialiser().instantiate();
		
		this.testSimilarity(objOne, objTwo, ParametersPackage.Literals.RECEIVER_PARAMETER__OUTER_TYPE_REFERENCE);
	}
}
