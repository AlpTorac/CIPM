package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.annotations.AnnotationInstance;
import org.emftext.language.java.classifiers.Annotation;
import org.emftext.language.java.modifiers.AnnotableAndModifiable;
import org.emftext.language.java.modifiers.Modifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.generators.AnnotationInstanceGenerator;
import cipm.consistency.fitests.similarity.java.generators.IModifierFactory;
import cipm.consistency.fitests.similarity.java.generators.ModifierFactory;
import cipm.consistency.fitests.similarity.java.initialiser.InitialiserVisibilityModifier;
import cipm.consistency.fitests.similarity.java.initialiser.annotations.AnnotationInstanceInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.classifiers.AnnotationInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.classifiers.IAnnotationInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IAnnotableAndModifiableInitialiser;

/**
 * TODO: Write proper commentary
 * <br><br>
 * If loggers are enabled, the "X in unknown container: Y
 * (can be empty, if a minimal instance is used) : null"
 * warnings are to be expected. They are, however, not
 * important in this case.
 * 
 * @author atora
 */
public class AnnotableAndModifiableTest extends EObjectSimilarityTest {
	private AnnotationInstanceGenerator aiGen;
	private IModifierFactory modFac;
	
	@BeforeEach
	@Override
	public void setUp() {
		this.aiGen = new AnnotationInstanceGenerator();
		this.modFac = new ModifierFactory();
		
		this.registerGenerator(aiGen);
		
		super.setUp();
	}
	
	protected EObject initElement(IAnnotableAndModifiableInitialiser initialiser,
			Modifier[] mods,
			Collection<AnnotationInstance> ais,
			InitialiserVisibilityModifier visibility) {
		
		AnnotableAndModifiable result = initialiser.instantiate();
		initialiser.minimalInitialisation(result);
		
		initialiser.addModifiers(result, mods);
		initialiser.addAnnotationInstances(result, ais);
		
		initialiser.setVisibility(result, visibility);
		return result;
	}
	
	@ParameterizedTest(name = "{index}: {1}")
	@ArgumentsSource(AnnotableAndModifiableTestParams.class)
	public void testModifier(IAnnotableAndModifiableInitialiser initialiser, String testName) {
		this.setResourceFileTestIdentifier("testModifier");
		
		var objOne = this.initElement(initialiser, new Modifier[] {this.modFac.createAbstract(),
				this.modFac.createSynchronized()}, null, null);
		var objTwo = this.initElement(initialiser, new Modifier[] {this.modFac.createVolatile(),
				this.modFac.createProtected()}, null, null);
		
		// TODO: Replace last parameter
		this.testX(objOne, objTwo, false);
	}
	
	@ParameterizedTest(name = "{index}: {1}")
	@ArgumentsSource(AnnotableAndModifiableTestParams.class)
	public void testAnnoInstance(IAnnotableAndModifiableInitialiser initialiser, String testName) {
		this.setResourceFileTestIdentifier("testAnnoInstance");
		
		var objOne = this.initElement(initialiser, null, this.aiGen.generateElements(1), null);
		var objTwo = this.initElement(initialiser, null, this.aiGen.generateElements(1), null);
		
		// TODO: Replace last parameter
		this.testX(objOne, objTwo, false);
	}
	
	@ParameterizedTest(name = "{index}: {1}")
	@ArgumentsSource(AnnotableAndModifiableTestParams.class)
	public void testVisibility(IAnnotableAndModifiableInitialiser initialiser, String testName) {
		this.setResourceFileTestIdentifier("testVisibility");
		
		var objOne = this.initElement(initialiser, null, null, InitialiserVisibilityModifier.PRIVATE);
		var objTwo = this.initElement(initialiser, null, null, InitialiserVisibilityModifier.PUBLIC);
		
		// TODO: Replace last parameter
		this.testX(objOne, objTwo, false);
	}
}
