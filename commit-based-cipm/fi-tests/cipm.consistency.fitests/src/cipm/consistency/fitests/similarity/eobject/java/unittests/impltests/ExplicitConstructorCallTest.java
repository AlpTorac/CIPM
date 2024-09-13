package cipm.consistency.fitests.similarity.eobject.java.unittests.impltests;

import org.emftext.language.java.instantiations.ExplicitConstructorCall;
import org.emftext.language.java.instantiations.InstantiationsPackage;
import org.emftext.language.java.literals.Self;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.eobject.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.instantiations.ExplicitConstructorCallInitialiser;
import cipm.consistency.fitests.similarity.eobject.java.unittests.UsesExpressions;

public class ExplicitConstructorCallTest extends EObjectSimilarityTest implements UsesExpressions {
	protected ExplicitConstructorCall initElement(Self callTarget) {
		var eccInit = new ExplicitConstructorCallInitialiser();
		var ecc = eccInit.instantiate();
		Assertions.assertTrue(eccInit.setCallTarget(ecc, callTarget));
		return ecc;
	}

	@Test
	public void testCallTarget() {
		var objOne = this.initElement(this.createThis());
		var objTwo = this.initElement(this.createSuper());

		this.testSimilarity(objOne, objTwo, InstantiationsPackage.Literals.EXPLICIT_CONSTRUCTOR_CALL__CALL_TARGET);
	}

	@Test
	public void testCallTargetNullCheck() {
		this.testSimilarityNullCheck(this.initElement(this.createThis()), new ExplicitConstructorCallInitialiser(),
				false, InstantiationsPackage.Literals.EXPLICIT_CONSTRUCTOR_CALL__CALL_TARGET);
	}
}
