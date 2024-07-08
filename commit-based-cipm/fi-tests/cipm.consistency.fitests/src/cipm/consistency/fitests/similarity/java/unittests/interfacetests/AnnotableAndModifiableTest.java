package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.annotations.AnnotationInstance;
import org.emftext.language.java.modifiers.AnnotableAndModifiable;
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
	
	protected EObject initElement(IAnnotableAndModifiableInitialiser initialiser,
			Modifier[] mods,
			AnnotationInstance[] ais,
			InitialiserVisibilityModifier visibility) {
		
		var result = initialiser.instantiate();
		Assertions.assertTrue(initialiser.initialise(result));
		Assertions.assertTrue(initialiser.addModifiers(result, mods));
		Assertions.assertTrue(initialiser.addAnnotationInstances(result, ais));
		Assertions.assertTrue(initialiser.setVisibility(result, visibility));
		return result;
	}
	
	@ParameterizedTest()
	@ArgumentsSource(AnnotableAndModifiableTestParams.class)
	public void testModifier(IAnnotableAndModifiableInitialiser initialiser) {
		this.setResourceFileTestIdentifier("testModifier");
		
		var objOne = this.initElement(initialiser, new Modifier[] {this.createAbstractModifier(), this.createSynchronizedModifier()}, null, null);
		var objTwo = this.initElement(initialiser, new Modifier[] {this.createVolatileModifier(), this.createProtectedModifier()}, null, null);
		
		this.testX(objOne, objTwo, ModifiersPackage.Literals.ANNOTABLE_AND_MODIFIABLE__ANNOTATIONS_AND_MODIFIERS);
	}
	
	@ParameterizedTest()
	@ArgumentsSource(AnnotableAndModifiableTestParams.class)
	public void testAnnoInstance(IAnnotableAndModifiableInitialiser initialiser) {
		this.setResourceFileTestIdentifier("testAnnoInstance");
		
		var objOne = this.initElement(initialiser, null, new AnnotationInstance[] {
				this.createMinimalAI(new String[] {"ns1"}, "anno1")}, null);
		var objTwo = this.initElement(initialiser, null, new AnnotationInstance[] {
				this.createMinimalAI(new String[] {"ns2"}, "anno2")}, null);
		
		this.testX(objOne, objTwo, ModifiersPackage.Literals.ANNOTABLE_AND_MODIFIABLE__ANNOTATIONS_AND_MODIFIERS);
	}
	
	@ParameterizedTest()
	@ArgumentsSource(AnnotableAndModifiableTestParams.class)
	public void testVisibility(IAnnotableAndModifiableInitialiser initialiser) {
		this.setResourceFileTestIdentifier("testVisibility");
		
		var objOne = this.initElement(initialiser, null, null, InitialiserVisibilityModifier.PRIVATE);
		var objTwo = this.initElement(initialiser, null, null, InitialiserVisibilityModifier.PUBLIC);
		
		this.testX(objOne, objTwo, ModifiersPackage.Literals.ANNOTABLE_AND_MODIFIABLE__ANNOTATIONS_AND_MODIFIERS);
	}
}
