package cipm.consistency.fitests.similarity.emftext.unittests.interfacetests;

import org.emftext.language.java.types.TypeReference;
import org.emftext.language.java.types.TypedElementExtension;
import org.emftext.language.java.types.TypesPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.emftext.AbstractEMFTextSimilarityTest;
import cipm.consistency.fitests.similarity.emftext.unittests.UsesTypeReferences;
import cipm.consistency.initialisers.emftext.types.ITypedElementExtensionInitialiser;

public class TypedElementExtensionTest extends AbstractEMFTextSimilarityTest implements UsesTypeReferences {
	protected TypedElementExtension initElement(ITypedElementExtensionInitialiser init, TypeReference actualTarget) {
		TypedElementExtension result = init.instantiate();
		Assertions.assertTrue(init.initialise(result));
		Assertions.assertTrue(init.addActualTarget(result, actualTarget));
		return result;
	}

	@ParameterizedTest
	@ArgumentsSource(TypedElementExtensionTestParams.class)
	public void testActualTarget(ITypedElementExtensionInitialiser init) {
		var objOne = this.initElement(init, this.createMinimalClsRef("cls1"));
		var objTwo = this.initElement(init, this.createMinimalClsRef("cls2"));

		this.testSimilarity(objOne, objTwo, TypesPackage.Literals.TYPED_ELEMENT_EXTENSION__ACTUAL_TARGETS);
	}

	@ParameterizedTest
	@ArgumentsSource(TypedElementExtensionTestParams.class)
	public void testActualTargetNullCheck(ITypedElementExtensionInitialiser init) {
		this.testSimilarityNullCheck(this.initElement(init, this.createMinimalClsRef("cls1")), init, true,
				TypesPackage.Literals.TYPED_ELEMENT_EXTENSION__ACTUAL_TARGETS);
	}
}
