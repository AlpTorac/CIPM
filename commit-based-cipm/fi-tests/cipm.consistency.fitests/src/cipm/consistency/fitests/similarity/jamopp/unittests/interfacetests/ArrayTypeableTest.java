package cipm.consistency.fitests.similarity.jamopp.unittests.interfacetests;

import java.util.stream.Stream;

import org.emftext.language.java.arrays.ArrayDimension;
import org.emftext.language.java.arrays.ArrayTypeable;
import org.emftext.language.java.arrays.ArraysPackage;
import org.emftext.language.java.arrays.impl.ArrayDimensionImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesArrayDimensions;
import cipm.consistency.initialisers.jamopp.arrays.IArrayTypeableInitialiser;

public class ArrayTypeableTest extends AbstractJaMoPPSimilarityTest implements UsesArrayDimensions {
	private ArrayDimension adb1;
	private ArrayDimension adb2;
	private ArrayDimension ada1;
	private ArrayDimension ada2;

	private static Stream<Arguments> provideArguments() {
		return AbstractJaMoPPSimilarityTest.getAllInitialiserArgumentsFor(IArrayTypeableInitialiser.class);
	}

	protected ArrayTypeable initElement(IArrayTypeableInitialiser init, ArrayDimension[] arrDimsBefore,
			ArrayDimension[] arrDimsAfter) {
		ArrayTypeable result = init.instantiate();
		Assertions.assertTrue(init.initialise(result));
		Assertions.assertTrue(init.addArrayDimensionsBefore(result, arrDimsBefore));
		Assertions.assertTrue(init.addArrayDimensionsAfter(result, arrDimsAfter));
		return result;
	}

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		adb1 = this.createMinimalArrayDimension();
		/*
		 * Since it is currently not possible to make different ArrayDimension
		 * instances, use an anonymous class instance to force difference
		 */
		adb2 = new ArrayDimensionImpl() {
		};
		Assertions.assertFalse(this.isSimilar(adb1, adb2));

		ada1 = this.createMinimalArrayDimension();
		/*
		 * Since it is currently not possible to make different ArrayDimension
		 * instances, use an anonymous class instance to force difference
		 */
		ada2 = new ArrayDimensionImpl() {
		};
		Assertions.assertFalse(this.isSimilar(ada1, ada2));
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testArrayDimensionsBefore(IArrayTypeableInitialiser init, String displayName) {
		var objOne = this.initElement(init, new ArrayDimension[] { this.cloneEObjWithContainers(adb1) }, null);
		var objTwo = this.initElement(init, new ArrayDimension[] { this.cloneEObjWithContainers(adb2) }, null);

		this.testSimilarity(objOne, objTwo, ArraysPackage.Literals.ARRAY_TYPEABLE__ARRAY_DIMENSIONS_BEFORE);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testArrayDimensionsBeforeSize(IArrayTypeableInitialiser init, String displayName) {
		var objOne = this.initElement(init,
				new ArrayDimension[] { this.cloneEObjWithContainers(adb1), this.cloneEObjWithContainers(adb2) }, null);
		var objTwo = this.initElement(init, new ArrayDimension[] { this.cloneEObjWithContainers(adb1) }, null);

		this.testSimilarity(objOne, objTwo, ArraysPackage.Literals.ARRAY_TYPEABLE__ARRAY_DIMENSIONS_BEFORE);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testArrayDimensionsBeforePosition(IArrayTypeableInitialiser init, String displayName) {
		var objOne = this.initElement(init,
				new ArrayDimension[] { this.cloneEObjWithContainers(adb1), this.cloneEObjWithContainers(adb2) }, null);
		var objTwo = this.initElement(init,
				new ArrayDimension[] { this.cloneEObjWithContainers(adb2), this.cloneEObjWithContainers(adb1) }, null);

		this.testSimilarity(objOne, objTwo, ArraysPackage.Literals.ARRAY_TYPEABLE__ARRAY_DIMENSIONS_BEFORE);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testArrayDimensionsBeforeDuplication(IArrayTypeableInitialiser init, String displayName) {
		var objOne = this.initElement(init,
				new ArrayDimension[] { this.cloneEObjWithContainers(adb1), this.cloneEObjWithContainers(adb1) }, null);
		var objTwo = this.initElement(init, new ArrayDimension[] { this.cloneEObjWithContainers(adb1) }, null);

		this.testSimilarity(objOne, objTwo, ArraysPackage.Literals.ARRAY_TYPEABLE__ARRAY_DIMENSIONS_BEFORE);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testArrayDimensionsBeforeNullCheck(IArrayTypeableInitialiser init, String displayName) {
		this.testSimilarityNullCheck(
				this.initElement(init, new ArrayDimension[] { this.cloneEObjWithContainers(adb1) }, null), init, true,
				ArraysPackage.Literals.ARRAY_TYPEABLE__ARRAY_DIMENSIONS_BEFORE);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testArrayDimensionsAfter(IArrayTypeableInitialiser init, String displayName) {
		var objOne = this.initElement(init, null, new ArrayDimension[] { this.cloneEObjWithContainers(ada1) });
		var objTwo = this.initElement(init, null, new ArrayDimension[] { this.cloneEObjWithContainers(ada2) });

		this.testSimilarity(objOne, objTwo, ArraysPackage.Literals.ARRAY_TYPEABLE__ARRAY_DIMENSIONS_AFTER);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testArrayDimensionsAfterSize(IArrayTypeableInitialiser init, String displayName) {
		var objOne = this.initElement(init, null,
				new ArrayDimension[] { this.cloneEObjWithContainers(ada1), this.cloneEObjWithContainers(ada2) });
		var objTwo = this.initElement(init, null, new ArrayDimension[] { this.cloneEObjWithContainers(ada1) });

		this.testSimilarity(objOne, objTwo, ArraysPackage.Literals.ARRAY_TYPEABLE__ARRAY_DIMENSIONS_AFTER);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testArrayDimensionsAfterPosition(IArrayTypeableInitialiser init, String displayName) {
		var objOne = this.initElement(init, null,
				new ArrayDimension[] { this.cloneEObjWithContainers(ada1), this.cloneEObjWithContainers(ada2) });
		var objTwo = this.initElement(init, null,
				new ArrayDimension[] { this.cloneEObjWithContainers(ada2), this.cloneEObjWithContainers(ada1) });

		this.testSimilarity(objOne, objTwo, ArraysPackage.Literals.ARRAY_TYPEABLE__ARRAY_DIMENSIONS_AFTER);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testArrayDimensionsAfterDuplication(IArrayTypeableInitialiser init, String displayName) {
		var objOne = this.initElement(init, null,
				new ArrayDimension[] { this.cloneEObjWithContainers(ada1), this.cloneEObjWithContainers(ada1) });
		var objTwo = this.initElement(init, null, new ArrayDimension[] { this.cloneEObjWithContainers(ada1) });

		this.testSimilarity(objOne, objTwo, ArraysPackage.Literals.ARRAY_TYPEABLE__ARRAY_DIMENSIONS_AFTER);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testArrayDimensionsAfterNullCheck(IArrayTypeableInitialiser init, String displayName) {
		this.testSimilarityNullCheck(
				this.initElement(init, null, new ArrayDimension[] { this.cloneEObjWithContainers(ada1) }), init, true,
				ArraysPackage.Literals.ARRAY_TYPEABLE__ARRAY_DIMENSIONS_AFTER);
	}
}
