package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.literals.Self;
import org.emftext.language.java.references.SelfReference;
import org.emftext.language.java.references.ReferencesPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.references.SelfReferenceInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesLiterals;

public class SelfReferenceTest extends EObjectSimilarityTest implements UsesLiterals {
	protected SelfReference initElement(Self self) {
		var srInit = new SelfReferenceInitialiser();
		var sr = srInit.instantiate();
		Assertions.assertTrue(srInit.setSelf(sr, self));
		return sr;
	}

	@Test
	public void testSelf() {
		this.setResourceFileTestIdentifier("testSelf");

		var objOne = this.initElement(this.createThis());
		var objTwo = this.initElement(this.createSuper());

		this.testSimilarity(objOne, objTwo, ReferencesPackage.Literals.SELF_REFERENCE__SELF);
	}
}
