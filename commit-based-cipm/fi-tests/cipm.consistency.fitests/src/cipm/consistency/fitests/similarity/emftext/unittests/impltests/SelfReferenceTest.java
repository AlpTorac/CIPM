package cipm.consistency.fitests.similarity.emftext.unittests.impltests;

import org.emftext.language.java.literals.Self;
import org.emftext.language.java.references.SelfReference;
import org.emftext.language.java.references.ReferencesPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.emftext.AbstractEMFTextSimilarityTest;
import cipm.consistency.fitests.similarity.emftext.unittests.UsesLiterals;
import cipm.consistency.initialisers.emftext.references.SelfReferenceInitialiser;

public class SelfReferenceTest extends AbstractEMFTextSimilarityTest implements UsesLiterals {
	protected SelfReference initElement(Self self) {
		var srInit = new SelfReferenceInitialiser();
		var sr = srInit.instantiate();
		Assertions.assertTrue(srInit.setSelf(sr, self));
		return sr;
	}

	@Test
	public void testSelf() {
		var objOne = this.initElement(this.createThis());
		var objTwo = this.initElement(this.createSuper());

		this.testSimilarity(objOne, objTwo, ReferencesPackage.Literals.SELF_REFERENCE__SELF);
	}

	@Test
	public void testSelfNullCheck() {
		this.testSimilarityNullCheck(this.initElement(this.createThis()), new SelfReferenceInitialiser(), false,
				ReferencesPackage.Literals.SELF_REFERENCE__SELF);
	}
}
