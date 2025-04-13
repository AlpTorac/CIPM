package cipm.consistency.fitests.similarity.jamopp.unittests.interfacetests;

import java.util.stream.Stream;

import org.emftext.language.java.generics.GenericsPackage;
import org.emftext.language.java.generics.TypeParameter;
import org.emftext.language.java.generics.TypeParametrizable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesTypeParameters;
import cipm.consistency.initialisers.jamopp.generics.ITypeParametrizableInitialiser;

public class TypeParametrizableTest extends AbstractJaMoPPSimilarityTest implements UsesTypeParameters {
	private TypeParameter tp1;
	private TypeParameter tp2;

	private static Stream<Arguments> provideArguments() {
		return AbstractJaMoPPSimilarityTest.getAllInitialiserArgumentsFor(ITypeParametrizableInitialiser.class);
	}

	protected TypeParametrizable initElement(ITypeParametrizableInitialiser init, TypeParameter[] tParams) {
		TypeParametrizable result = init.instantiate();
		Assertions.assertTrue(init.initialise(result));
		Assertions.assertTrue(init.addTypeParameters(result, tParams));
		return result;
	}

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		tp1 = this.createMinimalTypeParamWithClsRef("cls1");
		tp2 = this.createMinimalTypeParamWithClsRef("cls2");
		Assertions.assertFalse(this.isSimilar(tp1, tp2));
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testTypeParameters(ITypeParametrizableInitialiser init, String displayName) {
		var objOne = this.initElement(init, new TypeParameter[] { this.cloneEObjWithContainers(tp1) });
		var objTwo = this.initElement(init, new TypeParameter[] { this.cloneEObjWithContainers(tp2) });

		this.testSimilarity(objOne, objTwo, GenericsPackage.Literals.TYPE_PARAMETRIZABLE__TYPE_PARAMETERS);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testTypeParametersSize(ITypeParametrizableInitialiser init, String displayName) {
		var objOne = this.initElement(init,
				new TypeParameter[] { this.cloneEObjWithContainers(tp1), this.cloneEObjWithContainers(tp2) });
		var objTwo = this.initElement(init, new TypeParameter[] { this.cloneEObjWithContainers(tp1) });

		this.testSimilarity(objOne, objTwo, GenericsPackage.Literals.TYPE_PARAMETRIZABLE__TYPE_PARAMETERS);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testTypeParametersPosition(ITypeParametrizableInitialiser init, String displayName) {
		var objOne = this.initElement(init,
				new TypeParameter[] { this.cloneEObjWithContainers(tp1), this.cloneEObjWithContainers(tp2) });
		var objTwo = this.initElement(init,
				new TypeParameter[] { this.cloneEObjWithContainers(tp2), this.cloneEObjWithContainers(tp1) });

		this.testSimilarity(objOne, objTwo, GenericsPackage.Literals.TYPE_PARAMETRIZABLE__TYPE_PARAMETERS);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testTypeParametersDuplication(ITypeParametrizableInitialiser init, String displayName) {
		var objOne = this.initElement(init,
				new TypeParameter[] { this.cloneEObjWithContainers(tp1), this.cloneEObjWithContainers(tp1) });
		var objTwo = this.initElement(init, new TypeParameter[] { this.cloneEObjWithContainers(tp1) });

		this.testSimilarity(objOne, objTwo, GenericsPackage.Literals.TYPE_PARAMETRIZABLE__TYPE_PARAMETERS);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testTypeParametersNullCheck(ITypeParametrizableInitialiser init, String displayName) {
		this.testSimilarityNullCheck(this.initElement(init, new TypeParameter[] { this.cloneEObjWithContainers(tp1) }),
				init, true, GenericsPackage.Literals.TYPE_PARAMETRIZABLE__TYPE_PARAMETERS);
	}
}
