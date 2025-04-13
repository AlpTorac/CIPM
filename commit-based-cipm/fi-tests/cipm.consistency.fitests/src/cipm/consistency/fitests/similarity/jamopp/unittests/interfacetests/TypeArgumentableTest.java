package cipm.consistency.fitests.similarity.jamopp.unittests.interfacetests;

import java.util.stream.Stream;

import org.emftext.language.java.generics.GenericsPackage;
import org.emftext.language.java.generics.TypeArgument;
import org.emftext.language.java.generics.TypeArgumentable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesTypeArguments;
import cipm.consistency.initialisers.jamopp.generics.ITypeArgumentableInitialiser;

public class TypeArgumentableTest extends AbstractJaMoPPSimilarityTest implements UsesTypeArguments {
	private TypeArgument ta1;
	private TypeArgument ta2;

	private static Stream<Arguments> provideArguments() {
		return AbstractJaMoPPSimilarityTest.getAllInitialiserArgumentsFor(ITypeArgumentableInitialiser.class);
	}

	protected TypeArgumentable initElement(ITypeArgumentableInitialiser init, TypeArgument[] typeArgs) {
		TypeArgumentable result = init.instantiate();
		Assertions.assertTrue(init.initialise(result));
		Assertions.assertTrue(init.addTypeArguments(result, typeArgs));
		return result;
	}

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		ta1 = this.createMinimalExtendsTAWithCls("cls1");
		ta2 = this.createMinimalSuperTAWithCls("cls2");
		Assertions.assertFalse(this.isSimilar(ta1, ta2));
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testTypeArgument(ITypeArgumentableInitialiser init, String displayName) {
		var objOne = this.initElement(init, new TypeArgument[] { this.cloneEObjWithContainers(ta1) });
		var objTwo = this.initElement(init, new TypeArgument[] { this.cloneEObjWithContainers(ta2) });

		this.testSimilarity(objOne, objTwo, GenericsPackage.Literals.TYPE_ARGUMENTABLE__TYPE_ARGUMENTS);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testTypeArgumentSize(ITypeArgumentableInitialiser init, String displayName) {
		var objOne = this.initElement(init,
				new TypeArgument[] { this.cloneEObjWithContainers(ta1), this.cloneEObjWithContainers(ta2) });
		var objTwo = this.initElement(init, new TypeArgument[] { this.cloneEObjWithContainers(ta1) });

		this.testSimilarity(objOne, objTwo, GenericsPackage.Literals.TYPE_ARGUMENTABLE__TYPE_ARGUMENTS);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testTypeArgumentPosition(ITypeArgumentableInitialiser init, String displayName) {
		var objOne = this.initElement(init,
				new TypeArgument[] { this.cloneEObjWithContainers(ta1), this.cloneEObjWithContainers(ta2) });
		var objTwo = this.initElement(init,
				new TypeArgument[] { this.cloneEObjWithContainers(ta2), this.cloneEObjWithContainers(ta1) });

		this.testSimilarity(objOne, objTwo, GenericsPackage.Literals.TYPE_ARGUMENTABLE__TYPE_ARGUMENTS);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testTypeArgumentDuplication(ITypeArgumentableInitialiser init, String displayName) {
		var objOne = this.initElement(init,
				new TypeArgument[] { this.cloneEObjWithContainers(ta1), this.cloneEObjWithContainers(ta1) });
		var objTwo = this.initElement(init, new TypeArgument[] { this.cloneEObjWithContainers(ta1) });

		this.testSimilarity(objOne, objTwo, GenericsPackage.Literals.TYPE_ARGUMENTABLE__TYPE_ARGUMENTS);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testTypeArgumentNullCheck(ITypeArgumentableInitialiser init, String displayName) {
		this.testSimilarityNullCheck(this.initElement(init, new TypeArgument[] { this.cloneEObjWithContainers(ta1) }),
				init, true, GenericsPackage.Literals.TYPE_ARGUMENTABLE__TYPE_ARGUMENTS);
	}
}
