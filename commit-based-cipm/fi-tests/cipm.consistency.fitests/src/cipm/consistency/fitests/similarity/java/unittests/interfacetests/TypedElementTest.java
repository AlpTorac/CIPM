package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import org.emftext.language.java.types.TypeReference;
import org.emftext.language.java.types.TypedElement;
import org.emftext.language.java.types.TypesPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.types.ITypedElementInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesTypeReferences;

public class TypedElementTest extends EObjectSimilarityTest implements UsesTypeReferences {
	protected TypedElement initElement(ITypedElementInitialiser init, TypeReference tRef) {
		TypedElement result = init.instantiate();
		Assertions.assertTrue(init.initialise(result));
		Assertions.assertTrue(init.setTypeReference(result, tRef));
		return result;
	}

	@ParameterizedTest
	@ArgumentsSource(TypedElementTestParams.class)
	public void testTypeReference(ITypedElementInitialiser init) {
		this.setCurrentInitialiser(init);
		var objOne = this.initElement(init, this.createMinimalClsRef("cls1"));
		var objTwo = this.initElement(init, this.createMinimalClsRef("cls2"));

		this.testSimilarity(objOne, objTwo, TypesPackage.Literals.TYPED_ELEMENT__TYPE_REFERENCE);
	}

	@ParameterizedTest
	@ArgumentsSource(TypedElementTestParams.class)
	public void testTypeReferenceNullCheck(ITypedElementInitialiser init) {
		this.setCurrentInitialiser(init);
		var objOne = this.initElement(init, this.createMinimalClsRef("cls1"));
		var objTwo = init.instantiate();
		Assertions.assertTrue(init.initialise(objTwo));

		this.testSimilarity(objOne, objTwo, TypesPackage.Literals.TYPED_ELEMENT__TYPE_REFERENCE);
	}
}
