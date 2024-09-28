package cipm.consistency.fitests.similarity.emftext.unittests.interfacetests;

import org.emftext.language.java.arrays.ArraySelector;
import org.emftext.language.java.references.Reference;
import org.emftext.language.java.references.ReferencesPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.emftext.AbstractEMFTextSimilarityTest;
import cipm.consistency.fitests.similarity.emftext.unittests.UsesArraySelectors;
import cipm.consistency.fitests.similarity.emftext.unittests.UsesReferences;
import cipm.consistency.initialisers.emftext.references.IReferenceInitialiser;

public class ReferenceTest extends AbstractEMFTextSimilarityTest implements UsesReferences, UsesArraySelectors {
	protected Reference initElement(IReferenceInitialiser init, Reference next, ArraySelector[] arrSels) {
		Reference ref = init.instantiate();
		Assertions.assertTrue(init.initialise(ref));
		Assertions.assertTrue(init.setNext(ref, next));
		Assertions.assertTrue(init.addArraySelectors(ref, arrSels));
		return ref;
	}

	@ParameterizedTest
	@ArgumentsSource(ReferenceTestParams.class)
	public void testNext(IReferenceInitialiser init) {
		var objOne = this.initElement(init, this.createMinimalSR("str1"), null);
		var objTwo = this.initElement(init, this.createMinimalSR("str2"), null);

		this.testSimilarity(objOne, objTwo, ReferencesPackage.Literals.REFERENCE__NEXT);
	}

	@ParameterizedTest
	@ArgumentsSource(ReferenceTestParams.class)
	public void testNextNullCheck(IReferenceInitialiser init) {
		this.testSimilarityNullCheck(this.initElement(init, this.createMinimalSR("str1"), null), init, true,
				ReferencesPackage.Literals.REFERENCE__NEXT);
	}

	@ParameterizedTest
	@ArgumentsSource(ReferenceTestParams.class)
	public void testArraySelector(IReferenceInitialiser init) {
		var objOne = this.initElement(init, null, new ArraySelector[] { this.createMinimalAS(0) });
		var objTwo = this.initElement(init, null, new ArraySelector[] { this.createMinimalAS(1) });

		this.testSimilarity(objOne, objTwo, ReferencesPackage.Literals.REFERENCE__ARRAY_SELECTORS);
	}

	@ParameterizedTest
	@ArgumentsSource(ReferenceTestParams.class)
	public void testArraySelectorSize(IReferenceInitialiser init) {
		var objOne = this.initElement(init, null,
				new ArraySelector[] { this.createMinimalAS(0), this.createMinimalAS(1) });
		var objTwo = this.initElement(init, null, new ArraySelector[] { this.createMinimalAS(0) });

		this.testSimilarity(objOne, objTwo, ReferencesPackage.Literals.REFERENCE__ARRAY_SELECTORS);
	}

	@ParameterizedTest
	@ArgumentsSource(ReferenceTestParams.class)
	public void testArraySelectorNullCheck(IReferenceInitialiser init) {
		this.testSimilarityNullCheck(this.initElement(init, null, new ArraySelector[] { this.createMinimalAS(0) }),
				init, true, ReferencesPackage.Literals.REFERENCE__ARRAY_SELECTORS);
	}
}
