package cipm.consistency.fitests.similarity.jamopp.unittests.interfacetests;

import java.util.stream.Stream;

import org.emftext.language.java.annotations.AnnotationInstance;
import org.emftext.language.java.modifiers.ModifiersPackage;
import org.emftext.language.java.modifiers.AnnotableAndModifiable;
import org.emftext.language.java.modifiers.Modifier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesAnnotationInstances;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesModifiers;
import cipm.consistency.initialisers.jamopp.modifiers.IAnnotableAndModifiableInitialiser;

public class AnnotableAndModifiableTest extends AbstractJaMoPPSimilarityTest
		implements UsesAnnotationInstances, UsesModifiers {
	private Modifier modif1;
	private Modifier modif2;
	private AnnotationInstance ai1;
	private AnnotationInstance ai2;

	private static Stream<Arguments> provideArguments() {
		return AbstractJaMoPPSimilarityTest.getAllInitialiserArgumentsFor(IAnnotableAndModifiableInitialiser.class);
	}

	protected AnnotableAndModifiable initElement(IAnnotableAndModifiableInitialiser init, Modifier[] modifs,
			AnnotationInstance[] ais) {

		var result = init.instantiate();
		Assertions.assertTrue(init.initialise(result));
		Assertions.assertTrue(init.addModifiers(result, modifs));
		Assertions.assertTrue(init.addAnnotationInstances(result, ais));

		return result;
	}

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		modif1 = this.createAbstract();
		modif2 = this.createVolatile();
		Assertions.assertFalse(this.isSimilar(modif1, modif2));

		ai1 = this.createMinimalAI(new String[] { "ns1" }, "anno1");
		ai2 = this.createMinimalAI(new String[] { "ns2" }, "anno2");
		Assertions.assertFalse(this.isSimilar(ai1, ai2));
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testModifier(IAnnotableAndModifiableInitialiser init, String displayName) {
		var objOne = this.initElement(init, new Modifier[] { this.cloneEObjWithContainers(modif1) }, null);
		var objTwo = this.initElement(init, new Modifier[] { this.cloneEObjWithContainers(modif2) }, null);

		this.testSimilarity(objOne, objTwo,
				ModifiersPackage.Literals.ANNOTABLE_AND_MODIFIABLE__ANNOTATIONS_AND_MODIFIERS);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testModifierSize(IAnnotableAndModifiableInitialiser init, String displayName) {
		var objOne = this.initElement(init,
				new Modifier[] { this.cloneEObjWithContainers(modif1), this.cloneEObjWithContainers(modif2) }, null);
		var objTwo = this.initElement(init, new Modifier[] { this.cloneEObjWithContainers(modif1) }, null);

		this.testSimilarity(objOne, objTwo,
				ModifiersPackage.Literals.ANNOTABLE_AND_MODIFIABLE__ANNOTATIONS_AND_MODIFIERS);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testModifierPosition(IAnnotableAndModifiableInitialiser init, String displayName) {
		var objOne = this.initElement(init,
				new Modifier[] { this.cloneEObjWithContainers(modif1), this.cloneEObjWithContainers(modif2) }, null);
		var objTwo = this.initElement(init,
				new Modifier[] { this.cloneEObjWithContainers(modif2), this.cloneEObjWithContainers(modif1) }, null);

		this.testSimilarity(objOne, objTwo,
				ModifiersPackage.Literals.ANNOTABLE_AND_MODIFIABLE__ANNOTATIONS_AND_MODIFIERS);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testModifierDuplication(IAnnotableAndModifiableInitialiser init, String displayName) {
		var objOne = this.initElement(init,
				new Modifier[] { this.cloneEObjWithContainers(modif1), this.cloneEObjWithContainers(modif1) }, null);
		var objTwo = this.initElement(init, new Modifier[] { this.cloneEObjWithContainers(modif1) }, null);

		this.testSimilarity(objOne, objTwo,
				ModifiersPackage.Literals.ANNOTABLE_AND_MODIFIABLE__ANNOTATIONS_AND_MODIFIERS);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testModifierNullCheck(IAnnotableAndModifiableInitialiser init, String displayName) {
		this.testSimilarityNullCheck(
				this.initElement(init, new Modifier[] { this.cloneEObjWithContainers(modif1) }, null), init, true,
				ModifiersPackage.Literals.ANNOTABLE_AND_MODIFIABLE__ANNOTATIONS_AND_MODIFIERS);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testAnnotationInstance(IAnnotableAndModifiableInitialiser init, String displayName) {
		var objOne = this.initElement(init, null, new AnnotationInstance[] { this.cloneEObjWithContainers(ai1) });
		var objTwo = this.initElement(init, null, new AnnotationInstance[] { this.cloneEObjWithContainers(ai2) });

		this.testSimilarity(objOne, objTwo,
				ModifiersPackage.Literals.ANNOTABLE_AND_MODIFIABLE__ANNOTATIONS_AND_MODIFIERS);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testAnnotationInstanceSize(IAnnotableAndModifiableInitialiser init, String displayName) {
		var objOne = this.initElement(init, null,
				new AnnotationInstance[] { this.cloneEObjWithContainers(ai1), this.cloneEObjWithContainers(ai2) });
		var objTwo = this.initElement(init, null, new AnnotationInstance[] { this.cloneEObjWithContainers(ai1) });

		this.testSimilarity(objOne, objTwo,
				ModifiersPackage.Literals.ANNOTABLE_AND_MODIFIABLE__ANNOTATIONS_AND_MODIFIERS);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testAnnotationInstancePosition(IAnnotableAndModifiableInitialiser init, String displayName) {
		var objOne = this.initElement(init, null,
				new AnnotationInstance[] { this.cloneEObjWithContainers(ai1), this.cloneEObjWithContainers(ai2) });
		var objTwo = this.initElement(init, null,
				new AnnotationInstance[] { this.cloneEObjWithContainers(ai2), this.cloneEObjWithContainers(ai1), });

		this.testSimilarity(objOne, objTwo,
				ModifiersPackage.Literals.ANNOTABLE_AND_MODIFIABLE__ANNOTATIONS_AND_MODIFIERS);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testAnnotationInstanceDuplication(IAnnotableAndModifiableInitialiser init, String displayName) {
		var objOne = this.initElement(init, null,
				new AnnotationInstance[] { this.cloneEObjWithContainers(ai1), this.cloneEObjWithContainers(ai1) });
		var objTwo = this.initElement(init, null, new AnnotationInstance[] { this.cloneEObjWithContainers(ai1) });

		this.testSimilarity(objOne, objTwo,
				ModifiersPackage.Literals.ANNOTABLE_AND_MODIFIABLE__ANNOTATIONS_AND_MODIFIERS);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testAnnotationInstanceNullCheck(IAnnotableAndModifiableInitialiser init, String displayName) {
		this.testSimilarityNullCheck(
				this.initElement(init, null, new AnnotationInstance[] { this.cloneEObjWithContainers(ai1) }), init,
				true, ModifiersPackage.Literals.ANNOTABLE_AND_MODIFIABLE__ANNOTATIONS_AND_MODIFIERS);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testPrivate(IAnnotableAndModifiableInitialiser init, String displayName) {
		var objOne = this.initElement(init, null, null);
		init.makePrivate(objOne);

		var objTwo = this.initElement(init, null, null);
		init.makePublic(objTwo);

		this.testSimilarity(objOne, objTwo,
				ModifiersPackage.Literals.ANNOTABLE_AND_MODIFIABLE__ANNOTATIONS_AND_MODIFIERS);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testPrivateNullCheck(IAnnotableAndModifiableInitialiser init, String displayName) {
		var objOne = this.initElement(init, null, null);
		init.makePrivate(objOne);

		this.testSimilarityNullCheck(objOne, init, true,
				ModifiersPackage.Literals.ANNOTABLE_AND_MODIFIABLE__ANNOTATIONS_AND_MODIFIERS);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testProtected(IAnnotableAndModifiableInitialiser init, String displayName) {
		var objOne = this.initElement(init, null, null);
		init.makeProtected(objOne);

		var objTwo = this.initElement(init, null, null);
		init.makePublic(objTwo);

		this.testSimilarity(objOne, objTwo,
				ModifiersPackage.Literals.ANNOTABLE_AND_MODIFIABLE__ANNOTATIONS_AND_MODIFIERS);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testProtectedNullCheck(IAnnotableAndModifiableInitialiser init, String displayName) {
		var objOne = this.initElement(init, null, null);
		init.makeProtected(objOne);

		this.testSimilarityNullCheck(objOne, init, true,
				ModifiersPackage.Literals.ANNOTABLE_AND_MODIFIABLE__ANNOTATIONS_AND_MODIFIERS);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testPublic(IAnnotableAndModifiableInitialiser init, String displayName) {
		var objOne = this.initElement(init, null, null);
		init.makePublic(objOne);

		var objTwo = this.initElement(init, null, null);
		init.makePrivate(objTwo);

		this.testSimilarity(objOne, objTwo,
				ModifiersPackage.Literals.ANNOTABLE_AND_MODIFIABLE__ANNOTATIONS_AND_MODIFIERS);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testPublicNullCheck(IAnnotableAndModifiableInitialiser init, String displayName) {
		var objOne = this.initElement(init, null, null);
		init.makePublic(objOne);

		this.testSimilarityNullCheck(objOne, init, true,
				ModifiersPackage.Literals.ANNOTABLE_AND_MODIFIABLE__ANNOTATIONS_AND_MODIFIERS);
	}
}
