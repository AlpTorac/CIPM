package cipm.consistency.fitests.similarity.jamopp.unittests.interfacetests;

import java.util.stream.Stream;

import org.emftext.language.java.types.TypeReference;
import org.emftext.language.java.types.TypedElementExtension;
import org.emftext.language.java.types.TypesPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesTypeReferences;
import cipm.consistency.initialisers.jamopp.types.ITypedElementExtensionInitialiser;

public class TypedElementExtensionTest extends AbstractJaMoPPSimilarityTest implements UsesTypeReferences {
	private TypeReference at1;
	private TypeReference at2;

	private static Stream<Arguments> provideArguments() {
		return AbstractJaMoPPSimilarityTest.getAllInitialiserArgumentsFor(ITypedElementExtensionInitialiser.class);
	}

	protected TypedElementExtension initElement(ITypedElementExtensionInitialiser init, TypeReference[] actualTargets) {
		TypedElementExtension result = init.instantiate();
		Assertions.assertTrue(init.initialise(result));
		Assertions.assertTrue(init.addActualTargets(result, actualTargets));
		return result;
	}

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		at1 = this.createMinimalClsRef("cls1");
		at2 = this.createMinimalClsRef("cls2");
		Assertions.assertFalse(this.isSimilar(at1, at2));
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testActualTarget(ITypedElementExtensionInitialiser init, String displayName) {
		var objOne = this.initElement(init, new TypeReference[] { this.cloneEObjWithContainers(at1) });
		var objTwo = this.initElement(init, new TypeReference[] { this.cloneEObjWithContainers(at2) });

		this.testSimilarity(objOne, objTwo, TypesPackage.Literals.TYPED_ELEMENT_EXTENSION__ACTUAL_TARGETS);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testActualTargetSize(ITypedElementExtensionInitialiser init, String displayName) {
		var objOne = this.initElement(init,
				new TypeReference[] { this.cloneEObjWithContainers(at1), this.cloneEObjWithContainers(at2) });
		var objTwo = this.initElement(init, new TypeReference[] { this.cloneEObjWithContainers(at1) });

		this.testSimilarity(objOne, objTwo, TypesPackage.Literals.TYPED_ELEMENT_EXTENSION__ACTUAL_TARGETS);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testActualTargetPosition(ITypedElementExtensionInitialiser init, String displayName) {
		var objOne = this.initElement(init,
				new TypeReference[] { this.cloneEObjWithContainers(at1), this.cloneEObjWithContainers(at2) });
		var objTwo = this.initElement(init,
				new TypeReference[] { this.cloneEObjWithContainers(at2), this.cloneEObjWithContainers(at1) });

		this.testSimilarity(objOne, objTwo, TypesPackage.Literals.TYPED_ELEMENT_EXTENSION__ACTUAL_TARGETS);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testActualTargetDuplication(ITypedElementExtensionInitialiser init, String displayName) {
		var objOne = this.initElement(init,
				new TypeReference[] { this.cloneEObjWithContainers(at1), this.cloneEObjWithContainers(at1) });
		var objTwo = this.initElement(init, new TypeReference[] { this.cloneEObjWithContainers(at1) });

		this.testSimilarity(objOne, objTwo, TypesPackage.Literals.TYPED_ELEMENT_EXTENSION__ACTUAL_TARGETS);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testActualTargetNullCheck(ITypedElementExtensionInitialiser init, String displayName) {
		this.testSimilarityNullCheck(this.initElement(init, new TypeReference[] { this.cloneEObjWithContainers(at1) }),
				init, true, TypesPackage.Literals.TYPED_ELEMENT_EXTENSION__ACTUAL_TARGETS);
	}
}
