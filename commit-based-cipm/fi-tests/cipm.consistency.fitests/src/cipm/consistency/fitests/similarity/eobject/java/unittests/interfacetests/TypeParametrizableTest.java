package cipm.consistency.fitests.similarity.eobject.java.unittests.interfacetests;

import org.emftext.language.java.generics.GenericsPackage;
import org.emftext.language.java.generics.TypeParameter;
import org.emftext.language.java.generics.TypeParametrizable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.eobject.java.AbstractEObjectJavaSimilarityTest;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.generics.ITypeParametrizableInitialiser;
import cipm.consistency.fitests.similarity.eobject.java.unittests.UsesTypeParameters;

public class TypeParametrizableTest extends AbstractEObjectJavaSimilarityTest implements UsesTypeParameters {
	protected TypeParametrizable initElement(ITypeParametrizableInitialiser init, TypeParameter[] tParams) {
		TypeParametrizable result = init.instantiate();
		Assertions.assertTrue(init.initialise(result));
		Assertions.assertTrue(init.addTypeParameters(result, tParams));
		return result;
	}

	@ParameterizedTest
	@ArgumentsSource(TypeParametrizableTestParams.class)
	public void testTypeParameters(ITypeParametrizableInitialiser init) {
		var objOne = this.initElement(init, new TypeParameter[] { this.createMinimalTypeParamWithClsRef("cls1") });
		var objTwo = this.initElement(init, new TypeParameter[] { this.createMinimalTypeParamWithClsRef("cls2") });

		this.testSimilarity(objOne, objTwo, GenericsPackage.Literals.TYPE_PARAMETRIZABLE__TYPE_PARAMETERS);
	}

	@ParameterizedTest
	@ArgumentsSource(TypeParametrizableTestParams.class)
	public void testTypeParametersSize(ITypeParametrizableInitialiser init) {
		var objOne = this.initElement(init, new TypeParameter[] { this.createMinimalTypeParamWithClsRef("cls1"),
				this.createMinimalTypeParamWithClsRef("cls2") });
		var objTwo = this.initElement(init, new TypeParameter[] { this.createMinimalTypeParamWithClsRef("cls1") });

		this.testSimilarity(objOne, objTwo, GenericsPackage.Literals.TYPE_PARAMETRIZABLE__TYPE_PARAMETERS);
	}

	@ParameterizedTest
	@ArgumentsSource(TypeParametrizableTestParams.class)
	public void testTypeParametersNullCheck(ITypeParametrizableInitialiser init) {
		this.testSimilarityNullCheck(
				this.initElement(init, new TypeParameter[] { this.createMinimalTypeParamWithClsRef("cls1") }), init,
				true, GenericsPackage.Literals.TYPE_PARAMETRIZABLE__TYPE_PARAMETERS);
	}
}
