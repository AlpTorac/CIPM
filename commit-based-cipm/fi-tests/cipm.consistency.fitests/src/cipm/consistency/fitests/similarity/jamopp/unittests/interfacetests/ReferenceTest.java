package cipm.consistency.fitests.similarity.jamopp.unittests.interfacetests;

import java.util.stream.Stream;

import org.emftext.language.java.arrays.ArraySelector;
import org.emftext.language.java.arrays.impl.ArraySelectorImpl;
import org.emftext.language.java.references.Reference;
import org.emftext.language.java.references.ReferencesPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesArraySelectors;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesReferences;
import cipm.consistency.initialisers.jamopp.references.IReferenceInitialiser;

public class ReferenceTest extends AbstractJaMoPPSimilarityTest implements UsesReferences, UsesArraySelectors {
	private Reference next1;
	private Reference next2;
	private ArraySelector as1;
	private ArraySelector as2;

	private static Stream<Arguments> provideArguments() {
		return AbstractJaMoPPSimilarityTest.getAllInitialiserArgumentsFor(IReferenceInitialiser.class);
	}

	protected Reference initElement(IReferenceInitialiser init, Reference next, ArraySelector[] arrSels) {
		Reference ref = init.instantiate();
		Assertions.assertTrue(init.initialise(ref));
		Assertions.assertTrue(init.setNext(ref, next));
		Assertions.assertTrue(init.addArraySelectors(ref, arrSels));
		return ref;
	}

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		next1 = this.createMinimalSR("str1");
		next2 = this.createMinimalSR("str2");
		Assertions.assertFalse(this.isSimilar(next1, next2));

		as1 = this.createAS(this.createDecimalIntegerLiteral(1));
		/*
		 * Since it is currently not possible to make different ArraySelector instances,
		 * use an anonymous class instance to force difference
		 */
		as2 = new ArraySelectorImpl() {
		};
		Assertions.assertFalse(this.isSimilar(as1, as2));
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testNext(IReferenceInitialiser init, String displayName) {
		var objOne = this.initElement(init, this.cloneEObjWithContainers(next1), null);
		var objTwo = this.initElement(init, this.cloneEObjWithContainers(next2), null);

		this.testSimilarity(objOne, objTwo, ReferencesPackage.Literals.REFERENCE__NEXT);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testNextNullCheck(IReferenceInitialiser init, String displayName) {
		this.testSimilarityNullCheck(this.initElement(init, this.cloneEObjWithContainers(next1), null), init, true,
				ReferencesPackage.Literals.REFERENCE__NEXT);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testArraySelector(IReferenceInitialiser init, String displayName) {
		var objOne = this.initElement(init, null, new ArraySelector[] { this.cloneEObjWithContainers(as1) });
		var objTwo = this.initElement(init, null, new ArraySelector[] { this.cloneEObjWithContainers(as2) });

		this.testSimilarity(objOne, objTwo, ReferencesPackage.Literals.REFERENCE__ARRAY_SELECTORS);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testArraySelectorSize(IReferenceInitialiser init, String displayName) {
		var objOne = this.initElement(init, null,
				new ArraySelector[] { this.cloneEObjWithContainers(as1), this.cloneEObjWithContainers(as2) });
		var objTwo = this.initElement(init, null, new ArraySelector[] { this.cloneEObjWithContainers(as1) });

		this.testSimilarity(objOne, objTwo, ReferencesPackage.Literals.REFERENCE__ARRAY_SELECTORS);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testArraySelectorPosition(IReferenceInitialiser init, String displayName) {
		var objOne = this.initElement(init, null,
				new ArraySelector[] { this.cloneEObjWithContainers(as1), this.cloneEObjWithContainers(as2) });
		var objTwo = this.initElement(init, null,
				new ArraySelector[] { this.cloneEObjWithContainers(as2), this.cloneEObjWithContainers(as1) });

		this.testSimilarity(objOne, objTwo, ReferencesPackage.Literals.REFERENCE__ARRAY_SELECTORS);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testArraySelectorDuplication(IReferenceInitialiser init, String displayName) {
		var objOne = this.initElement(init, null,
				new ArraySelector[] { this.cloneEObjWithContainers(as1), this.cloneEObjWithContainers(as1) });
		var objTwo = this.initElement(init, null, new ArraySelector[] { this.cloneEObjWithContainers(as1) });

		this.testSimilarity(objOne, objTwo, ReferencesPackage.Literals.REFERENCE__ARRAY_SELECTORS);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testArraySelectorNullCheck(IReferenceInitialiser init, String displayName) {
		this.testSimilarityNullCheck(
				this.initElement(init, null, new ArraySelector[] { this.cloneEObjWithContainers(as1) }), init, true,
				ReferencesPackage.Literals.REFERENCE__ARRAY_SELECTORS);
	}
}
