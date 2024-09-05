package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import org.emftext.language.java.types.TypeReference;
import org.emftext.language.java.types.TypedElementExtension;
import org.emftext.language.java.types.TypesPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.types.ITypedElementExtensionInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesTypeReferences;

public class TypedElementExtensionTest extends EObjectSimilarityTest implements UsesTypeReferences {
	protected TypedElementExtension initElement(ITypedElementExtensionInitialiser init, TypeReference actualTarget) {
		TypedElementExtension result = init.instantiate();
		Assertions.assertTrue(init.initialise(result));
		Assertions.assertTrue(init.addActualTarget(result, actualTarget));
		return result;
	}

	@ParameterizedTest
	@ArgumentsSource(TypedElementExtensionTestParams.class)
	public void testActualTarget(ITypedElementExtensionInitialiser init) {
		this.setCurrentInitialiser(init);
		var objOne = this.initElement(init, this.createMinimalClsRef("cls1"));
		var objTwo = this.initElement(init, this.createMinimalClsRef("cls2"));

		this.testSimilarity(objOne, objTwo, TypesPackage.Literals.TYPED_ELEMENT_EXTENSION__ACTUAL_TARGETS);
	}

	@ParameterizedTest
	@ArgumentsSource(TypedElementExtensionTestParams.class)
	public void testActualTargetNullCheck(ITypedElementExtensionInitialiser init) {
		this.setCurrentInitialiser(init);

		this.testSimilarityNullCheck(this.initElement(init, this.createMinimalClsRef("cls1")), init, true,
				TypesPackage.Literals.TYPED_ELEMENT_EXTENSION__ACTUAL_TARGETS);
	}
}
