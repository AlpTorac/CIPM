package cipm.consistency.fitests.similarity.jamopp.unittests.interfacetests;

import java.util.stream.Stream;

import org.emftext.language.java.references.ElementReference;
import org.emftext.language.java.references.ReferenceableElement;
import org.emftext.language.java.references.ReferencesPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesConcreteClassifiers;
import cipm.consistency.initialisers.jamopp.references.IElementReferenceInitialiser;

public class ElementReferenceTest extends AbstractJaMoPPSimilarityTest implements UsesConcreteClassifiers {
	private ReferenceableElement target1;
	private ReferenceableElement target2;
	private ReferenceableElement cTarget1;
	private ReferenceableElement cTarget2;

	private static Stream<Arguments> provideArguments() {
		return AbstractJaMoPPSimilarityTest.getAllInitialiserArgumentsFor(IElementReferenceInitialiser.class);
	}

	protected ElementReference initElement(IElementReferenceInitialiser init, ReferenceableElement target,
			ReferenceableElement cTarget) {
		ElementReference result = init.instantiate();
		Assertions.assertTrue(init.initialise(result));
		Assertions.assertTrue(init.setTarget(result, target));
		Assertions.assertTrue(init.setContainedTarget(result, cTarget));
		return result;
	}

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		target1 = this.createMinimalClass("cls1");
		target2 = this.createMinimalClass("cls2");
		Assertions.assertFalse(this.isSimilar(target1, target2));

		cTarget1 = this.createMinimalClass("cls1");
		cTarget2 = this.createMinimalClass("cls2");
		Assertions.assertFalse(this.isSimilar(cTarget1, cTarget2));
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testTarget(IElementReferenceInitialiser init, String displayName) {
		var objOne = this.initElement(init, this.cloneEObjWithContainers(target1), null);
		var objTwo = this.initElement(init, this.cloneEObjWithContainers(target2), null);

		this.testSimilarity(objOne, objTwo, ReferencesPackage.Literals.ELEMENT_REFERENCE__TARGET);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testTargetNullCheck(IElementReferenceInitialiser init, String displayName) {
		this.testSimilarityNullCheck(this.initElement(init, this.cloneEObjWithContainers(target1), null), init, true,
				ReferencesPackage.Literals.ELEMENT_REFERENCE__TARGET);
	}

	/**
	 * Makes sure that not providing a container for the created element reference
	 * does not result in an exception.
	 */
	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testTargetNoException(IElementReferenceInitialiser init, String displayName) {
		var objOne = this.initElement(init, this.cloneEObjWithContainers(target1), null);
		var objTwo = this.initElement(init, this.cloneEObjWithContainers(target2), null);

		Assertions.assertDoesNotThrow(
				() -> this.testSimilarity(objOne, objTwo, ReferencesPackage.Literals.ELEMENT_REFERENCE__TARGET));
	}

	/**
	 * Makes sure that not providing a container for the created element reference
	 * does not result in an exception, if it is compared to an uninitialised
	 * element reference.
	 */
	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testTargetNoExceptionNullCheck(IElementReferenceInitialiser init, String displayName) {
		var objOne = this.initElement(init, this.cloneEObjWithContainers(target1), null);
		var objTwo = init.instantiate();
		Assertions.assertTrue(init.initialise(objTwo));

		Assertions.assertDoesNotThrow(
				() -> this.testSimilarity(objOne, objTwo, ReferencesPackage.Literals.ELEMENT_REFERENCE__TARGET));
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testContainedTarget(IElementReferenceInitialiser init, String displayName) {
		var objOne = this.initElement(init, null, this.cloneEObjWithContainers(cTarget1));
		var objTwo = this.initElement(init, null, this.cloneEObjWithContainers(cTarget2));

		this.testSimilarity(objOne, objTwo, ReferencesPackage.Literals.ELEMENT_REFERENCE__CONTAINED_TARGET);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testContainedTargetNullCheck(IElementReferenceInitialiser init, String displayName) {
		this.testSimilarityNullCheck(this.initElement(init, null, this.cloneEObjWithContainers(cTarget1)), init, true,
				ReferencesPackage.Literals.ELEMENT_REFERENCE__CONTAINED_TARGET);
	}
}
