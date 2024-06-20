package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.parameters.ReceiverParameter;
import org.emftext.language.java.types.TypeReference;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.parameters.ReceiverParameterInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesTypeReferences;

public class ReceiverParameterTest extends EObjectSimilarityTest implements UsesTypeReferences {
	protected ReceiverParameter initElement(TypeReference tref) {
		var rpInit = new ReceiverParameterInitialiser();
		var rp = rpInit.instantiate();
		rpInit.minimalInitialisation(rp);
		rpInit.setOuterTypeReference(rp, tref);
		return rp;
	}
	
	@Test
	public void testOuterTypeReference() {
		this.setResourceFileTestIdentifier("testOuterTypeReference");
		
		var objOne = this.initElement(this.createMinimalClsRef("cls1"));
		var objTwo = this.initElement(this.createMinimalClsRef("cls2"));
		
		this.testX(objOne, objTwo, false);
	}
}
