package cipm.consistency.fitests.similarity.jamopp.unittests.impltests;

import org.emftext.language.java.parameters.ReceiverParameter;
import org.emftext.language.java.literals.This;
import org.emftext.language.java.literals.impl.ThisImpl;
import org.emftext.language.java.parameters.ParametersPackage;
import org.emftext.language.java.types.TypeReference;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesLiterals;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesTypeReferences;
import cipm.consistency.initialisers.jamopp.parameters.ReceiverParameterInitialiser;

public class ReceiverParameterTest extends AbstractJaMoPPSimilarityTest implements UsesTypeReferences, UsesLiterals {
	private This thisRef1;
	private This thisRef2;
	private TypeReference otRef1;
	private TypeReference otRef2;

	protected ReceiverParameter initElement(This thisRef, TypeReference otRef) {
		var rpInit = new ReceiverParameterInitialiser();
		var rp = rpInit.instantiate();
		Assertions.assertTrue(rpInit.setThisReference(rp, thisRef));
		Assertions.assertTrue(rpInit.setOuterTypeReference(rp, otRef));
		return rp;
	}

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		thisRef1 = this.createThis();
		/*
		 * Since there is currently no way to make This instances different, use an
		 * anonymous class instance to force difference.
		 */
		thisRef2 = new ThisImpl() {
		};
		Assertions.assertFalse(this.isSimilar(thisRef1, thisRef2));

		otRef1 = this.createMinimalClsRef("cls1");
		otRef2 = this.createMinimalClsRef("cls2");
		Assertions.assertFalse(this.isSimilar(otRef1, otRef2));
	}

	@Test
	public void testThisTypeReference() {
		var objOne = this.initElement(this.cloneEObjWithContainers(thisRef1), null);
		var objTwo = this.initElement(this.cloneEObjWithContainers(thisRef2), null);

		this.testSimilarity(objOne, objTwo, ParametersPackage.Literals.RECEIVER_PARAMETER__THIS_REFERENCE);
	}

	@Test
	public void testThisTypeReferenceNullCheck() {
		this.testSimilarityNullCheck(this.initElement(this.cloneEObjWithContainers(thisRef1), null),
				new ReceiverParameterInitialiser(), false,
				ParametersPackage.Literals.RECEIVER_PARAMETER__THIS_REFERENCE);
	}

	@Test
	public void testOuterTypeReference() {
		var objOne = this.initElement(null, this.cloneEObjWithContainers(otRef1));
		var objTwo = this.initElement(null, this.cloneEObjWithContainers(otRef2));

		this.testSimilarity(objOne, objTwo, ParametersPackage.Literals.RECEIVER_PARAMETER__OUTER_TYPE_REFERENCE);
	}

	@Test
	public void testOuterTypeReferenceNullCheck() {
		this.testSimilarityNullCheck(this.initElement(null, this.cloneEObjWithContainers(otRef1)),
				new ReceiverParameterInitialiser(), false,
				ParametersPackage.Literals.RECEIVER_PARAMETER__OUTER_TYPE_REFERENCE);
	}
}
