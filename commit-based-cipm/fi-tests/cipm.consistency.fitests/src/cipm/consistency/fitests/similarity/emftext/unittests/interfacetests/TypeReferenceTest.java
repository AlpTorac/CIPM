package cipm.consistency.fitests.similarity.emftext.unittests.interfacetests;

import java.util.stream.Stream;

import org.emftext.language.java.classifiers.Classifier;
import org.emftext.language.java.types.TypeReference;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import cipm.consistency.fitests.similarity.emftext.AbstractEMFTextSimilarityTest;
import cipm.consistency.fitests.similarity.emftext.unittests.UsesConcreteClassifiers;
import cipm.consistency.initialisers.emftext.types.ITypeReferenceInitialiser;

public class TypeReferenceTest extends AbstractEMFTextSimilarityTest implements UsesConcreteClassifiers {

	private static Stream<Arguments> provideArguments() {
		return AbstractEMFTextSimilarityTest.getAllInitialiserArgumentsFor(ITypeReferenceInitialiser.class);
	}

	protected TypeReference initElement(ITypeReferenceInitialiser init, Classifier target) {
		var res = init.instantiate();

		Assertions.assertEquals(init.canSetTargetTo(res, target), init.setTarget(res, target));
		return res;
	}

	@ParameterizedTest
	@MethodSource("provideArguments")
	public void testTargetNullCheck(ITypeReferenceInitialiser init) {
		var objOne = this.initElement(init, this.createMinimalClass("cls"));
		var objTwo = init.instantiate();

		// No expected result, because TypeReference does not have the "target"
		// attribute, yet some of its implementors do.
		Assertions.assertDoesNotThrow(() -> this.isSimilar(objOne, objTwo));
	}
}