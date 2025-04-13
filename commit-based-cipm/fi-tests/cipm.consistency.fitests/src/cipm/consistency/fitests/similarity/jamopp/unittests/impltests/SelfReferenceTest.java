package cipm.consistency.fitests.similarity.jamopp.unittests.impltests;

import org.emftext.language.java.literals.Self;
import org.emftext.language.java.references.SelfReference;
import org.emftext.language.java.references.ReferencesPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesLiterals;
import cipm.consistency.initialisers.jamopp.references.SelfReferenceInitialiser;

public class SelfReferenceTest extends AbstractJaMoPPSimilarityTest implements UsesLiterals {
	private Self self1;
	private Self self2;

	protected SelfReference initElement(Self self) {
		var srInit = new SelfReferenceInitialiser();
		var sr = srInit.instantiate();
		Assertions.assertTrue(srInit.setSelf(sr, self));
		return sr;
	}

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		self1 = this.createThis();
		self2 = this.createSuper();
		Assertions.assertFalse(this.isSimilar(self1, self2));
	}

	@Test
	public void testSelf() {
		var objOne = this.initElement(this.cloneEObjWithContainers(self1));
		var objTwo = this.initElement(this.cloneEObjWithContainers(self2));

		this.testSimilarity(objOne, objTwo, ReferencesPackage.Literals.SELF_REFERENCE__SELF);
	}

	@Test
	public void testSelfNullCheck() {
		this.testSimilarityNullCheck(this.initElement(this.cloneEObjWithContainers(self1)),
				new SelfReferenceInitialiser(), false, ReferencesPackage.Literals.SELF_REFERENCE__SELF);
	}
}
