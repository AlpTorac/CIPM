package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.literals.Self;
import org.emftext.language.java.references.SelfReference;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.references.SelfReferenceInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesLiterals;

public class SelfReferenceTest extends EObjectSimilarityTest implements UsesLiterals {
	protected SelfReference initElement(Self self) {
		var srInit = new SelfReferenceInitialiser();
		var sr = srInit.instantiate();
		srInit.minimalInitialisation(sr);
		srInit.setSelf(sr, self);
		return sr;
	}
	
	@Test
	public void testSelf() {
		this.setResourceFileTestIdentifier("testSelf");
		
		var objOne = this.initElement(this.createThis());
		var objTwo = this.initElement(this.createSuper());
		
		this.compareX(objOne, objTwo, false);
	}
}
