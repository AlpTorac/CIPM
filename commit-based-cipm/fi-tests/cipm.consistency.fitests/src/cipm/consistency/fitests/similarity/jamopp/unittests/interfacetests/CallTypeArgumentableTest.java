package cipm.consistency.fitests.similarity.jamopp.unittests.interfacetests;

import java.util.stream.Stream;

import org.emftext.language.java.generics.CallTypeArgumentable;
import org.emftext.language.java.generics.GenericsPackage;
import org.emftext.language.java.generics.TypeArgument;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesTypeArguments;
import cipm.consistency.initialisers.jamopp.generics.ICallTypeArgumentableInitialiser;

public class CallTypeArgumentableTest extends AbstractJaMoPPSimilarityTest implements UsesTypeArguments {
	private TypeArgument cta1;
	private TypeArgument cta2;

	private static Stream<Arguments> provideArguments() {
		return AbstractJaMoPPSimilarityTest.getAllInitialiserArgumentsFor(ICallTypeArgumentableInitialiser.class);
	}

	protected CallTypeArgumentable initElement(ICallTypeArgumentableInitialiser init, TypeArgument[] callTypeArgs) {
		CallTypeArgumentable result = init.instantiate();
		Assertions.assertTrue(init.initialise(result));
		Assertions.assertTrue(init.addCallTypeArguments(result, callTypeArgs));
		return result;
	}

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		cta1 = this.createMinimalExtendsTAWithCls("cls1");
		cta2 = this.createMinimalSuperTAWithCls("cls2");
		Assertions.assertFalse(this.isSimilar(cta1, cta2));
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testCallTypeArguments(ICallTypeArgumentableInitialiser init, String displayName) {
		var objOne = this.initElement(init, new TypeArgument[] { this.cloneEObjWithContainers(cta1) });
		var objTwo = this.initElement(init, new TypeArgument[] { this.cloneEObjWithContainers(cta2) });

		this.testSimilarity(objOne, objTwo, GenericsPackage.Literals.CALL_TYPE_ARGUMENTABLE__CALL_TYPE_ARGUMENTS);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testCallTypeArgumentsSize(ICallTypeArgumentableInitialiser init, String displayName) {
		var objOne = this.initElement(init,
				new TypeArgument[] { this.cloneEObjWithContainers(cta1), this.cloneEObjWithContainers(cta2) });
		var objTwo = this.initElement(init, new TypeArgument[] { this.cloneEObjWithContainers(cta1) });

		this.testSimilarity(objOne, objTwo, GenericsPackage.Literals.CALL_TYPE_ARGUMENTABLE__CALL_TYPE_ARGUMENTS);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testCallTypeArgumentsPosition(ICallTypeArgumentableInitialiser init, String displayName) {
		var objOne = this.initElement(init,
				new TypeArgument[] { this.cloneEObjWithContainers(cta1), this.cloneEObjWithContainers(cta2) });
		var objTwo = this.initElement(init,
				new TypeArgument[] { this.cloneEObjWithContainers(cta2), this.cloneEObjWithContainers(cta1) });

		this.testSimilarity(objOne, objTwo, GenericsPackage.Literals.CALL_TYPE_ARGUMENTABLE__CALL_TYPE_ARGUMENTS);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testCallTypeArgumentsDuplication(ICallTypeArgumentableInitialiser init, String displayName) {
		var objOne = this.initElement(init,
				new TypeArgument[] { this.cloneEObjWithContainers(cta1), this.cloneEObjWithContainers(cta1) });
		var objTwo = this.initElement(init, new TypeArgument[] { this.cloneEObjWithContainers(cta1) });

		this.testSimilarity(objOne, objTwo, GenericsPackage.Literals.CALL_TYPE_ARGUMENTABLE__CALL_TYPE_ARGUMENTS);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testCallTypeArgumentsNullCheck(ICallTypeArgumentableInitialiser init, String displayName) {
		this.testSimilarityNullCheck(this.initElement(init, new TypeArgument[] { this.cloneEObjWithContainers(cta1) }),
				init, true, GenericsPackage.Literals.CALL_TYPE_ARGUMENTABLE__CALL_TYPE_ARGUMENTS);
	}
}
