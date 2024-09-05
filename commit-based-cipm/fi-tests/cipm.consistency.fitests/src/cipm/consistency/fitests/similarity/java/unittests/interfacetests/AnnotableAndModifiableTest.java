package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import org.emftext.language.java.annotations.AnnotationInstance;
import org.emftext.language.java.modifiers.ModifiersPackage;
import org.emftext.language.java.modifiers.AnnotableAndModifiable;
import org.emftext.language.java.modifiers.Modifier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.modifiers.IAnnotableAndModifiableInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesAnnotationInstances;
import cipm.consistency.fitests.similarity.java.unittests.UsesModifiers;

public class AnnotableAndModifiableTest extends EObjectSimilarityTest
		implements UsesAnnotationInstances, UsesModifiers {

	protected AnnotableAndModifiable initElement(IAnnotableAndModifiableInitialiser init, Modifier[] modifs,
			AnnotationInstance[] ais) {

		var result = init.instantiate();
		Assertions.assertTrue(init.initialise(result));
		Assertions.assertTrue(init.addModifiers(result, modifs));
		Assertions.assertTrue(init.addAnnotationInstances(result, ais));

		return result;
	}

	@ParameterizedTest()
	@ArgumentsSource(AnnotableAndModifiableTestParams.class)
	public void testModifier(IAnnotableAndModifiableInitialiser init) {
		this.setCurrentInitialiser(init);
		var objOne = this.initElement(init, new Modifier[] { this.createAbstract(), this.createSynchronized() }, null);
		var objTwo = this.initElement(init, new Modifier[] { this.createVolatile(), this.createProtected() }, null);

		this.testSimilarity(objOne, objTwo,
				ModifiersPackage.Literals.ANNOTABLE_AND_MODIFIABLE__ANNOTATIONS_AND_MODIFIERS);
	}

	@ParameterizedTest()
	@ArgumentsSource(AnnotableAndModifiableTestParams.class)
	public void testModifierSize(IAnnotableAndModifiableInitialiser init) {
		this.setCurrentInitialiser(init);
		var objOne = this.initElement(init, new Modifier[] { this.createAbstract(), this.createSynchronized() }, null);
		var objTwo = this.initElement(init, new Modifier[] { this.createAbstract() }, null);

		this.testSimilarity(objOne, objTwo,
				ModifiersPackage.Literals.ANNOTABLE_AND_MODIFIABLE__ANNOTATIONS_AND_MODIFIERS);
	}

	@ParameterizedTest()
	@ArgumentsSource(AnnotableAndModifiableTestParams.class)
	public void testModifierNullCheck(IAnnotableAndModifiableInitialiser init) {
		this.setCurrentInitialiser(init);

		this.testSimilarityNullCheck(this.initElement(init, new Modifier[] { this.createAbstract(), this.createSynchronized() }, null), init, true,
				ModifiersPackage.Literals.ANNOTABLE_AND_MODIFIABLE__ANNOTATIONS_AND_MODIFIERS);
	}

	@ParameterizedTest()
	@ArgumentsSource(AnnotableAndModifiableTestParams.class)
	public void testAnnotationInstance(IAnnotableAndModifiableInitialiser init) {
		this.setCurrentInitialiser(init);
		var objOne = this.initElement(init, null,
				new AnnotationInstance[] { this.createMinimalAI(new String[] { "ns1" }, "anno1") });
		var objTwo = this.initElement(init, null,
				new AnnotationInstance[] { this.createMinimalAI(new String[] { "ns2" }, "anno2") });

		this.testSimilarity(objOne, objTwo,
				ModifiersPackage.Literals.ANNOTABLE_AND_MODIFIABLE__ANNOTATIONS_AND_MODIFIERS);
	}

	@ParameterizedTest()
	@ArgumentsSource(AnnotableAndModifiableTestParams.class)
	public void testAnnotationInstanceSize(IAnnotableAndModifiableInitialiser init) {
		this.setCurrentInitialiser(init);
		var objOne = this.initElement(init, null,
				new AnnotationInstance[] { this.createMinimalAI(new String[] { "ns1" }, "anno1") });
		var objTwo = this.initElement(init, null,
				new AnnotationInstance[] { this.createMinimalAI(new String[] { "ns1" }, "anno1"),
						this.createMinimalAI(new String[] { "ns2" }, "anno2") });

		this.testSimilarity(objOne, objTwo,
				ModifiersPackage.Literals.ANNOTABLE_AND_MODIFIABLE__ANNOTATIONS_AND_MODIFIERS);
	}

	@ParameterizedTest()
	@ArgumentsSource(AnnotableAndModifiableTestParams.class)
	public void testAnnotationInstanceNullCheck(IAnnotableAndModifiableInitialiser init) {
		this.setCurrentInitialiser(init);

		this.testSimilarityNullCheck(this.initElement(init, null,
				new AnnotationInstance[] { this.createMinimalAI(new String[] { "ns1" }, "anno1") }), init, true,
				ModifiersPackage.Literals.ANNOTABLE_AND_MODIFIABLE__ANNOTATIONS_AND_MODIFIERS);
	}

	@ParameterizedTest()
	@ArgumentsSource(AnnotableAndModifiableTestParams.class)
	public void testPrivate(IAnnotableAndModifiableInitialiser init) {
		this.setCurrentInitialiser(init);

		var objOne = this.initElement(init, null, null);
		init.makePrivate(objOne);

		var objTwo = this.initElement(init, null, null);
		init.makePublic(objTwo);

		this.testSimilarity(objOne, objTwo,
				ModifiersPackage.Literals.ANNOTABLE_AND_MODIFIABLE__ANNOTATIONS_AND_MODIFIERS);
	}

	@ParameterizedTest()
	@ArgumentsSource(AnnotableAndModifiableTestParams.class)
	public void testPrivateNullCheck(IAnnotableAndModifiableInitialiser init) {
		this.setCurrentInitialiser(init);

		var objOne = this.initElement(init, null, null);
		init.makePrivate(objOne);

		this.testSimilarityNullCheck(objOne, init, true,
				ModifiersPackage.Literals.ANNOTABLE_AND_MODIFIABLE__ANNOTATIONS_AND_MODIFIERS);
	}

	@ParameterizedTest()
	@ArgumentsSource(AnnotableAndModifiableTestParams.class)
	public void testProtected(IAnnotableAndModifiableInitialiser init) {
		this.setCurrentInitialiser(init);

		var objOne = this.initElement(init, null, null);
		init.makeProtected(objOne);

		var objTwo = this.initElement(init, null, null);
		init.makePublic(objTwo);

		this.testSimilarity(objOne, objTwo,
				ModifiersPackage.Literals.ANNOTABLE_AND_MODIFIABLE__ANNOTATIONS_AND_MODIFIERS);
	}

	@ParameterizedTest()
	@ArgumentsSource(AnnotableAndModifiableTestParams.class)
	public void testProtectedNullCheck(IAnnotableAndModifiableInitialiser init) {
		this.setCurrentInitialiser(init);

		var objOne = this.initElement(init, null, null);
		init.makeProtected(objOne);

		this.testSimilarityNullCheck(objOne, init, true,
				ModifiersPackage.Literals.ANNOTABLE_AND_MODIFIABLE__ANNOTATIONS_AND_MODIFIERS);
	}

	@ParameterizedTest()
	@ArgumentsSource(AnnotableAndModifiableTestParams.class)
	public void testPublic(IAnnotableAndModifiableInitialiser init) {
		this.setCurrentInitialiser(init);

		var objOne = this.initElement(init, null, null);
		init.makePublic(objOne);

		var objTwo = this.initElement(init, null, null);
		init.makePrivate(objTwo);

		this.testSimilarity(objOne, objTwo,
				ModifiersPackage.Literals.ANNOTABLE_AND_MODIFIABLE__ANNOTATIONS_AND_MODIFIERS);
	}

	@ParameterizedTest()
	@ArgumentsSource(AnnotableAndModifiableTestParams.class)
	public void testPublicNullCheck(IAnnotableAndModifiableInitialiser init) {
		this.setCurrentInitialiser(init);

		var objOne = this.initElement(init, null, null);
		init.makePublic(objOne);

		this.testSimilarityNullCheck(objOne, init, true,
				ModifiersPackage.Literals.ANNOTABLE_AND_MODIFIABLE__ANNOTATIONS_AND_MODIFIERS);
	}
}
