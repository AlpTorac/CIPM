package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import org.emftext.language.java.generics.GenericsPackage;
import org.emftext.language.java.generics.TypeParameter;
import org.emftext.language.java.generics.TypeParametrizable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.generics.ITypeParametrizableInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesTypeParameters;

public class TypeParametrizableTest extends EObjectSimilarityTest implements UsesTypeParameters {
	protected TypeParametrizable initElement(ITypeParametrizableInitialiser init, TypeParameter[] tParams) {
		TypeParametrizable result = init.instantiate();
		Assertions.assertTrue(init.initialise(result));
		Assertions.assertTrue(init.addTypeParameters(result, tParams));
		return result;
	}

	@ParameterizedTest
	@ArgumentsSource(TypeParametrizableTestParams.class)
	public void testTypeParameters(ITypeParametrizableInitialiser init) {
		this.setCurrentInitialiser(init);
		this.setResourceFileTestIdentifier("testTypeParameters");

		var objOne = this.initElement(init, new TypeParameter[] { this.createMinimalTypeParamWithClsRef("cls1") });
		var objTwo = this.initElement(init, new TypeParameter[] { this.createMinimalTypeParamWithClsRef("cls2") });

		this.testSimilarity(objOne, objTwo, GenericsPackage.Literals.TYPE_PARAMETRIZABLE__TYPE_PARAMETERS);
	}
	
	@ParameterizedTest
	@ArgumentsSource(TypeParametrizableTestParams.class)
	public void testTypeParametersNull(ITypeParametrizableInitialiser init) {
		this.setCurrentInitialiser(init);
		this.setResourceFileTestIdentifier("testTypeParametersNull");
		
		var objOne = this.initElement(init, new TypeParameter[] { this.createMinimalTypeParamWithClsRef("cls1") });
		var objTwo = init.instantiate();
		Assertions.assertTrue(init.initialise(objTwo));
		
		this.testSimilarity(objOne, objTwo, GenericsPackage.Literals.TYPE_PARAMETRIZABLE__TYPE_PARAMETERS);
	}
}
