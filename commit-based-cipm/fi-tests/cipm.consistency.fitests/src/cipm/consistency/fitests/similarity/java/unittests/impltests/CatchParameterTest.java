package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.parameters.CatchParameter;
import org.emftext.language.java.types.TypeReference;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.parameters.CatchParameterInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesTypeReferences;

public class CatchParameterTest extends EObjectSimilarityTest implements UsesTypeReferences {
	protected CatchParameter initElement(TypeReference[] trefs) {
		var cpInit = new CatchParameterInitialiser();
		var cp = cpInit.instantiate();
		cpInit.minimalInitialisation(cp);
		cpInit.addTypeReferences(cp, trefs);
		return cp;
	}
	
	@Test
	public void testTypeReference() {
		this.setResourceFileTestIdentifier("testTypeReference");
		
		var objOne = this.initElement(new TypeReference[] {this.createMinimalClsRef("cls1")});
		var objTwo = this.initElement(new TypeReference[] {this.createMinimalClsRef("cls2")});
		
		this.testX(objOne, objTwo, false);
	}
}
