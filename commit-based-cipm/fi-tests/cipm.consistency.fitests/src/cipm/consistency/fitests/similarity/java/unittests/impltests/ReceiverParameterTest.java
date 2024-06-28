package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.parameters.ReceiverParameter;
import org.emftext.language.java.literals.This;
import org.emftext.language.java.parameters.ParametersPackage;
import org.emftext.language.java.types.TypeReference;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.parameters.ReceiverParameterInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesLiterals;
import cipm.consistency.fitests.similarity.java.unittests.UsesTypeReferences;

public class ReceiverParameterTest extends EObjectSimilarityTest implements UsesTypeReferences,
UsesLiterals{
	protected ReceiverParameter initElement(This th, TypeReference otRef) {
		var rpInit = new ReceiverParameterInitialiser();
		var rp = rpInit.instantiate();
		rpInit.minimalInitialisation(rp);
		rpInit.setThisReference(rp, th);
		rpInit.setOuterTypeReference(rp, otRef);
		return rp;
	}
	
	@Test
	public void testThisTypeReference() {
		this.setResourceFileTestIdentifier("testThisTypeReference");
		
		var objOne = this.initElement(this.createThis(), null);
		var objTwo = this.initElement(null, null);
		
		this.testX(objOne, objTwo, ParametersPackage.Literals.RECEIVER_PARAMETER__THIS_REFERENCE);
	}
	
	@Test
	public void testOuterTypeReference() {
		this.setResourceFileTestIdentifier("testOuterTypeReference");
		
		var objOne = this.initElement(null, this.createMinimalClsRef("cls1"));
		var objTwo = this.initElement(null, this.createMinimalClsRef("cls2"));
		
		this.testX(objOne, objTwo, ParametersPackage.Literals.RECEIVER_PARAMETER__OUTER_TYPE_REFERENCE);
	}
}
