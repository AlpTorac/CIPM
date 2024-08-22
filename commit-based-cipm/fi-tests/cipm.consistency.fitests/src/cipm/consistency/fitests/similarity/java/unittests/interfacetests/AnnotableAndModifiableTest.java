package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.annotations.AnnotationInstance;
import org.emftext.language.java.modifiers.ModifiersPackage;
import org.emftext.language.java.modifiers.Modifier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.InitialiserVisibilityModifier;
import cipm.consistency.fitests.similarity.java.initialiser.modifiers.IAnnotableAndModifiableInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesAnnotationInstances;
import cipm.consistency.fitests.similarity.java.unittests.UsesModifiers;

public class AnnotableAndModifiableTest extends EObjectSimilarityTest
		implements UsesAnnotationInstances, UsesModifiers {

	protected EObject initElement(IAnnotableAndModifiableInitialiser init, Modifier[] modifs,
			AnnotationInstance[] ais, InitialiserVisibilityModifier visibility) {

		var result = init.instantiate();
		Assertions.assertTrue(init.initialise(result));
		Assertions.assertTrue(init.addModifiers(result, modifs));
		Assertions.assertTrue(init.addAnnotationInstances(result, ais));
		Assertions.assertTrue(init.setVisibility(result, visibility));
		return result;
	}

	@ParameterizedTest()
	@ArgumentsSource(AnnotableAndModifiableTestParams.class)
	public void testModifier(IAnnotableAndModifiableInitialiser init) {
		this.setCurrentInitialiser(init);
		this.setResourceFileTestIdentifier("testModifier");

		var objOne = this.initElement(init, new Modifier[] { this.createAbstract(), this.createSynchronized() },
				null, null);
		var objTwo = this.initElement(init, new Modifier[] { this.createVolatile(), this.createProtected() },
				null, null);

		this.testSimilarity(objOne, objTwo,
				ModifiersPackage.Literals.ANNOTABLE_AND_MODIFIABLE__ANNOTATIONS_AND_MODIFIERS);
	}
	
	@ParameterizedTest()
	@ArgumentsSource(AnnotableAndModifiableTestParams.class)
	public void testModifierNull(IAnnotableAndModifiableInitialiser init) {
		this.setCurrentInitialiser(init);
		this.setResourceFileTestIdentifier("testModifierNull");
		
		var objOne = this.initElement(init, new Modifier[] { this.createAbstract(), this.createSynchronized() },
				null, null);
		var objTwo = init.instantiate();
		Assertions.assertTrue(init.initialise(objTwo));
		
		this.testSimilarity(objOne, objTwo,
				ModifiersPackage.Literals.ANNOTABLE_AND_MODIFIABLE__ANNOTATIONS_AND_MODIFIERS);
	}

	@ParameterizedTest()
	@ArgumentsSource(AnnotableAndModifiableTestParams.class)
	public void testAnnotationInstance(IAnnotableAndModifiableInitialiser init) {
		this.setCurrentInitialiser(init);
		this.setResourceFileTestIdentifier("testAnnotationInstance");

		var objOne = this.initElement(init, null,
				new AnnotationInstance[] { this.createMinimalAI(new String[] { "ns1" }, "anno1") }, null);
		var objTwo = this.initElement(init, null,
				new AnnotationInstance[] { this.createMinimalAI(new String[] { "ns2" }, "anno2") }, null);

		this.testSimilarity(objOne, objTwo,
				ModifiersPackage.Literals.ANNOTABLE_AND_MODIFIABLE__ANNOTATIONS_AND_MODIFIERS);
	}
	
	@ParameterizedTest()
	@ArgumentsSource(AnnotableAndModifiableTestParams.class)
	public void testAnnotationInstanceNull(IAnnotableAndModifiableInitialiser init) {
		this.setCurrentInitialiser(init);
		this.setResourceFileTestIdentifier("testAnnotationInstanceNull");
		
		var objOne = this.initElement(init, null,
				new AnnotationInstance[] { this.createMinimalAI(new String[] { "ns1" }, "anno1") }, null);
		var objTwo = init.instantiate();
		Assertions.assertTrue(init.initialise(objTwo));
		
		this.testSimilarity(objOne, objTwo,
				ModifiersPackage.Literals.ANNOTABLE_AND_MODIFIABLE__ANNOTATIONS_AND_MODIFIERS);
	}

	@ParameterizedTest()
	@ArgumentsSource(AnnotableAndModifiableTestParams.class)
	public void testVisibility(IAnnotableAndModifiableInitialiser init) {
		this.setCurrentInitialiser(init);
		this.setResourceFileTestIdentifier("testVisibility");

		var objOne = this.initElement(init, null, null, InitialiserVisibilityModifier.PRIVATE);
		var objTwo = this.initElement(init, null, null, InitialiserVisibilityModifier.PUBLIC);

		this.testSimilarity(objOne, objTwo,
				ModifiersPackage.Literals.ANNOTABLE_AND_MODIFIABLE__ANNOTATIONS_AND_MODIFIERS);
	}
	
	@ParameterizedTest()
	@ArgumentsSource(AnnotableAndModifiableTestParams.class)
	public void testVisibilityNull(IAnnotableAndModifiableInitialiser init) {
		this.setCurrentInitialiser(init);
		this.setResourceFileTestIdentifier("testVisibilityNull");
		
		var objOne = this.initElement(init, null, null, InitialiserVisibilityModifier.PRIVATE);
		var objTwo = init.instantiate();
		Assertions.assertTrue(init.initialise(objTwo));
		
		this.testSimilarity(objOne, objTwo,
				ModifiersPackage.Literals.ANNOTABLE_AND_MODIFIABLE__ANNOTATIONS_AND_MODIFIERS);
	}
}
