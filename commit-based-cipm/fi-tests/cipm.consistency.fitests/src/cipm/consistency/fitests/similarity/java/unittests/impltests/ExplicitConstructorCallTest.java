package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.instantiations.ExplicitConstructorCall;
import org.emftext.language.java.instantiations.InstantiationsPackage;
import org.emftext.language.java.literals.Self;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.instantiations.ExplicitConstructorCallInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesExpressions;

public class ExplicitConstructorCallTest extends EObjectSimilarityTest implements UsesExpressions {
	protected ExplicitConstructorCall initElement(Self self) {
		var eccInit = new ExplicitConstructorCallInitialiser();
		var ecc = eccInit.instantiate();
		Assertions.assertTrue(eccInit.setCallTarget(ecc, self));
		return ecc;
	}
	
	@Test
	public void testCallTarget() {
		this.setResourceFileTestIdentifier("testCallTarget");
		
		var objOne = this.initElement(this.createThis());
		var objTwo = this.initElement(this.createSuper());
		
		this.testSimilarity(objOne, objTwo, InstantiationsPackage.Literals.EXPLICIT_CONSTRUCTOR_CALL__CALL_TARGET);
	}
}
