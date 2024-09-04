package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import org.emftext.language.java.classifiers.Classifier;
import org.emftext.language.java.types.TypeReference;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.types.ITypeReferenceInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesConcreteClassifiers;

public class TypeReferenceTest extends EObjectSimilarityTest implements UsesConcreteClassifiers {
	protected TypeReference initElement(ITypeReferenceInitialiser init, Classifier target) {
		var res = init.instantiate();

		Assertions.assertEquals(init.canSetTargetTo(res, target), init.setTarget(res, target));
		return res;
	}

	@ParameterizedTest
	@ArgumentsSource(TypeReferenceTestParams.class)
	public void testTargetNullCheck(ITypeReferenceInitialiser init) {
		var objOne = this.initElement(init, this.createMinimalClass("cls"));
		var objTwo = init.instantiate();

		// No expected result, because TypeReference does not have the "target"
		// attribute, yet some of its implementors do.
		Assertions.assertDoesNotThrow(() -> this.isSimilar(objOne, objTwo));
	}
}
