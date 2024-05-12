package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import org.emftext.language.java.generics.TypeParameter;
import org.emftext.language.java.generics.TypeParametrizable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.testable.ITypeParametrizableInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesTypeParameters;

public class TypeParametrizableTest extends EObjectSimilarityTest implements UsesTypeParameters {
	protected TypeParametrizable initElement(ITypeParametrizableInitialiser init, TypeParameter[] tParams) {
		TypeParametrizable result = init.instantiate();
		init.minimalInitialisation(result);
		init.addTypeParameters(result, tParams);
		return result;
	}
	
	@ParameterizedTest
	@ArgumentsSource(TypeParametrizableTestParams.class)
	public void testTypeParameters(ITypeParametrizableInitialiser init) {
		this.setResourceFileTestIdentifier("testTypeParameters");
		
		var objOne = this.initElement(init, new TypeParameter[] {this.createMinimalTypeParamWithClsRef("cls1")});
		var objTwo = this.initElement(init, new TypeParameter[] {this.createMinimalTypeParamWithClsRef("cls2")});
		
		this.testX(objOne, objTwo, false);
	}
}
