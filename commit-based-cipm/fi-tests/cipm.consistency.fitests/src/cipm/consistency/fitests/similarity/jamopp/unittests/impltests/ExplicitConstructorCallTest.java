package cipm.consistency.fitests.similarity.jamopp.unittests.impltests;

import org.emftext.language.java.instantiations.ExplicitConstructorCall;
import org.emftext.language.java.instantiations.InstantiationsPackage;
import org.emftext.language.java.literals.Self;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesExpressions;
import cipm.consistency.initialisers.jamopp.instantiations.ExplicitConstructorCallInitialiser;

public class ExplicitConstructorCallTest extends AbstractJaMoPPSimilarityTest implements UsesExpressions {
	private Self callTarget1;
	private Self callTarget2;

	protected ExplicitConstructorCall initElement(Self callTarget) {
		var eccInit = new ExplicitConstructorCallInitialiser();
		var ecc = eccInit.instantiate();
		Assertions.assertTrue(eccInit.setCallTarget(ecc, callTarget));
		return ecc;
	}

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		callTarget1 = this.createThis();
		callTarget2 = this.createSuper();
		Assertions.assertFalse(this.isSimilar(callTarget1, callTarget2));
	}

	@Test
	public void testCallTarget() {
		var objOne = this.initElement(this.cloneEObjWithContainers(callTarget1));
		var objTwo = this.initElement(this.cloneEObjWithContainers(callTarget2));

		this.testSimilarity(objOne, objTwo, InstantiationsPackage.Literals.EXPLICIT_CONSTRUCTOR_CALL__CALL_TARGET);
	}

	@Test
	public void testCallTargetNullCheck() {
		this.testSimilarityNullCheck(this.initElement(this.cloneEObjWithContainers(callTarget1)),
				new ExplicitConstructorCallInitialiser(), false,
				InstantiationsPackage.Literals.EXPLICIT_CONSTRUCTOR_CALL__CALL_TARGET);
	}
}
