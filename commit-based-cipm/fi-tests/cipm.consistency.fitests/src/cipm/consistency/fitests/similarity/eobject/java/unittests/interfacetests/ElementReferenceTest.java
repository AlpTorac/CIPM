package cipm.consistency.fitests.similarity.eobject.java.unittests.interfacetests;

import org.emftext.language.java.references.ElementReference;
import org.emftext.language.java.references.ReferenceableElement;
import org.emftext.language.java.references.ReferencesPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.eobject.java.AbstractEObjectJavaSimilarityTest;
import cipm.consistency.initialisers.eobject.java.references.IElementReferenceInitialiser;
import cipm.consistency.fitests.similarity.eobject.java.unittests.UsesConcreteClassifiers;

public class ElementReferenceTest extends AbstractEObjectJavaSimilarityTest implements UsesConcreteClassifiers {
	protected ElementReference initElement(IElementReferenceInitialiser init, ReferenceableElement target,
			ReferenceableElement cTarget) {
		ElementReference result = init.instantiate();
		Assertions.assertTrue(init.setTarget(result, target));
		Assertions.assertTrue(init.setContainedTarget(result, cTarget));
		return result;
	}

	@ParameterizedTest
	@ArgumentsSource(ElementReferenceTestParams.class)
	public void testTarget(IElementReferenceInitialiser init) {
		var objOne = this.initElement(init, this.createMinimalClass("cls1"), null);
		var objTwo = this.initElement(init, this.createMinimalClass("cls2"), null);

		this.testSimilarity(objOne, objTwo, ReferencesPackage.Literals.ELEMENT_REFERENCE__TARGET);
	}

	@ParameterizedTest
	@ArgumentsSource(ElementReferenceTestParams.class)
	public void testTargetNullCheck(IElementReferenceInitialiser init) {
		this.testSimilarityNullCheck(this.initElement(init, this.createMinimalClass("cls1"), null), init, false,
				ReferencesPackage.Literals.ELEMENT_REFERENCE__TARGET);
	}

	/**
	 * Makes sure that not providing a container for the created element does not
	 * result in an exception.
	 */
	@ParameterizedTest
	@ArgumentsSource(ElementReferenceTestParams.class)
	public void testTargetNoException(IElementReferenceInitialiser init) {
		var objOne = this.initElement(init, this.createMinimalClass("cls1"), null);
		var objTwo = this.initElement(init, this.createMinimalClass("cls2"), null);

		Assertions.assertDoesNotThrow(
				() -> this.testSimilarity(objOne, objTwo, ReferencesPackage.Literals.ELEMENT_REFERENCE__TARGET));
	}

	@ParameterizedTest
	@ArgumentsSource(ElementReferenceTestParams.class)
	public void testTargetNoExceptionNullCheck(IElementReferenceInitialiser init) {
		var objOne = this.initElement(init, this.createMinimalClass("cls1"), null);
		var objTwo = init.instantiate();

		Assertions.assertDoesNotThrow(
				() -> this.testSimilarity(objOne, objTwo, ReferencesPackage.Literals.ELEMENT_REFERENCE__TARGET));
	}

	@ParameterizedTest
	@ArgumentsSource(ElementReferenceTestParams.class)
	public void testContainedTarget(IElementReferenceInitialiser init) {
		var objOne = this.initElement(init, null, this.createMinimalClass("cls1"));
		var objTwo = this.initElement(init, null, this.createMinimalClass("cls2"));

		this.testSimilarity(objOne, objTwo, ReferencesPackage.Literals.ELEMENT_REFERENCE__CONTAINED_TARGET);
	}

	@ParameterizedTest
	@ArgumentsSource(ElementReferenceTestParams.class)
	public void testContainedTargetNullCheck(IElementReferenceInitialiser init) {
		this.testSimilarityNullCheck(this.initElement(init, null, this.createMinimalClass("cls1")), init, false,
				ReferencesPackage.Literals.ELEMENT_REFERENCE__CONTAINED_TARGET);
	}
}
