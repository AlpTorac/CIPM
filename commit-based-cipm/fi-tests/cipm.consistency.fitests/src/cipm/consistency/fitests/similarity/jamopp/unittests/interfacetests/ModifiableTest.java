package cipm.consistency.fitests.similarity.jamopp.unittests.interfacetests;

import java.util.stream.Stream;

import org.emftext.language.java.modifiers.Modifiable;
import org.emftext.language.java.modifiers.Modifier;
import org.emftext.language.java.modifiers.ModifiersPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesModifiers;
import cipm.consistency.initialisers.jamopp.modifiers.IModifiableInitialiser;

public class ModifiableTest extends AbstractJaMoPPSimilarityTest implements UsesModifiers {
	private Modifier modif1;
	private Modifier modif2;

	private static Stream<Arguments> provideArguments() {
		return AbstractJaMoPPSimilarityTest.getAllInitialiserArgumentsFor(IModifiableInitialiser.class);
	}

	protected Modifiable initElement(IModifiableInitialiser init, Modifier[] modifs) {
		Modifiable result = init.instantiate();
		Assertions.assertTrue(init.initialise(result));
		Assertions.assertTrue(init.addModifiers(result, modifs));
		return result;
	}

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		modif1 = this.createFinal();
		modif2 = this.createAbstract();
		Assertions.assertFalse(this.isSimilar(modif1, modif2));
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testModifier(IModifiableInitialiser init, String displayName) {
		var objOne = this.initElement(init, new Modifier[] { this.cloneEObjWithContainers(modif1) });
		var objTwo = this.initElement(init, new Modifier[] { this.cloneEObjWithContainers(modif2) });

		this.testSimilarity(objOne, objTwo, ModifiersPackage.Literals.MODIFIABLE__MODIFIERS);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testModifierSize(IModifiableInitialiser init, String displayName) {
		var objOne = this.initElement(init,
				new Modifier[] { this.cloneEObjWithContainers(modif1), this.cloneEObjWithContainers(modif2) });
		var objTwo = this.initElement(init, new Modifier[] { this.cloneEObjWithContainers(modif1) });

		this.testSimilarity(objOne, objTwo, ModifiersPackage.Literals.MODIFIABLE__MODIFIERS);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testModifierPosition(IModifiableInitialiser init, String displayName) {
		var objOne = this.initElement(init,
				new Modifier[] { this.cloneEObjWithContainers(modif1), this.cloneEObjWithContainers(modif2) });
		var objTwo = this.initElement(init,
				new Modifier[] { this.cloneEObjWithContainers(modif2), this.cloneEObjWithContainers(modif1) });

		this.testSimilarity(objOne, objTwo, ModifiersPackage.Literals.MODIFIABLE__MODIFIERS);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testModifierDuplication(IModifiableInitialiser init, String displayName) {
		var objOne = this.initElement(init,
				new Modifier[] { this.cloneEObjWithContainers(modif1), this.cloneEObjWithContainers(modif1) });
		var objTwo = this.initElement(init, new Modifier[] { this.cloneEObjWithContainers(modif1) });

		this.testSimilarity(objOne, objTwo, ModifiersPackage.Literals.MODIFIABLE__MODIFIERS);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testModifierNullCheck(IModifiableInitialiser init, String displayName) {
		var objOne = this.initElement(init, new Modifier[] { this.cloneEObjWithContainers(modif1) });
		var objTwo = init.instantiate();
		Assertions.assertTrue(init.initialise(objTwo));

		this.testSimilarity(objOne, objTwo, ModifiersPackage.Literals.MODIFIABLE__MODIFIERS);
	}
}
