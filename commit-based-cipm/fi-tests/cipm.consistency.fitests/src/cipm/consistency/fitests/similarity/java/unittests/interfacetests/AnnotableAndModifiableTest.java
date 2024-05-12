package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.annotations.AnnotationInstance;
import org.emftext.language.java.classifiers.Annotation;
import org.emftext.language.java.modifiers.AnnotableAndModifiable;
import org.emftext.language.java.modifiers.Modifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.InitialiserVisibilityModifier;
import cipm.consistency.fitests.similarity.java.initialiser.annotations.AnnotationInstanceInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.classifiers.AnnotationInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.classifiers.IAnnotationInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.params.ModifierFactory;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IAnnotableAndModifiableInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesAnnotationInstances;

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
public class AnnotableAndModifiableTest extends EObjectSimilarityTest implements UsesAnnotationInstances {
	private Modifier mod1;
	private Modifier mod2;
	private Modifier mod3;
	private Modifier mod4;
	
	@BeforeEach
	@Override
	public void setUp() {
		var modFac = new ModifierFactory();
		
		mod1 = modFac.createAbstract();
		mod2 = modFac.createSynchronized();
		mod3 = modFac.createVolatile();
		mod4 = modFac.createProtected();
		
		super.setUp();
	}
	
	/**
	 * Returning {@code result} on its own works for the tests.
	 * 
	 * Uncommenting and returning {@code root} also works. If this is preferred,
	 * the inner initialisers have to be set.
	 */
	protected EObject initElement(IAnnotableAndModifiableInitialiser initialiser,
			Modifier[] mods,
			AnnotationInstance[] ais,
			InitialiserVisibilityModifier visibility) {
		
		AnnotableAndModifiable result = initialiser.instantiate();
//		var root = initialiser.minimalInitialisationWithContainer(result);
		initialiser.minimalInitialisation(result);
		initialiser.addModifiers(result, mods);
		initialiser.addAnnotationInstances(result, ais);
		initialiser.setVisibility(result, visibility);
		
		// TODO: Extract the code underneath, when there is a systematic way to initialise elements that need a container
		
//		var cuInit = new CompilationUnitInitialiser();
//		var unit = cuInit.instantiate();
//		cuInit.minimalInitialisation(unit);
//		
//		if (result instanceof ConcreteClassifier) {
//			cuInit.addClassifier(unit, (ConcreteClassifier) result);
//			return unit;
//		}
//		
//		var clsInit = new ClassInitialiser();
//		var cls = clsInit.instantiate();
//		clsInit.minimalInitialisation(cls);
//		
//		if (result instanceof Member) { // Method or Constructor
//			clsInit.addMember(cls, (Member) result);
//			cuInit.addClassifier(unit, cls);
//			
//			return unit;
//		}
//		
//		var ctorInit = new ConstructorInitialiser();
//		var ctor = ctorInit.instantiate();
//		ctorInit.minimalInitialisation(ctor);
//		
//		if (result instanceof Parameter) {
//			ctorInit.addParameter(ctor, (Parameter) result);
//			clsInit.addMember(cls, ctor);
//			cuInit.addClassifier(unit, cls);
//			
//			return unit;
//		}
		
//		return root;
		return result;
	}
	
	@ParameterizedTest(name = "{index}: {1}")
	@ArgumentsSource(AnnotableAndModifiableTestParams.class)
	public void testModifier(IAnnotableAndModifiableInitialiser initialiser, String testName) {
		this.setResourceFileTestIdentifier("testModifier");
		
		var objOne = this.initElement(initialiser, new Modifier[] {mod1, mod2}, null, null);
		var objTwo = this.initElement(initialiser, new Modifier[] {mod3, mod4}, null, null);
		
		this.testX(objOne, objTwo, false);
	}
	
	@ParameterizedTest(name = "{index}: {1}")
	@ArgumentsSource(AnnotableAndModifiableTestParams.class)
	public void testAnnoInstance(IAnnotableAndModifiableInitialiser initialiser, String testName) {
		this.setResourceFileTestIdentifier("testAnnoInstance");
		
		var objOne = this.initElement(initialiser, null, new AnnotationInstance[] {
				this.createMinimalAI(new String[] {"ns1"}, "anno1")}, null);
		var objTwo = this.initElement(initialiser, null, new AnnotationInstance[] {
				this.createMinimalAI(new String[] {"ns2"}, "anno2")}, null);
		
		this.testX(objOne, objTwo, false);
	}
	
	@ParameterizedTest(name = "{index}: {1}")
	@ArgumentsSource(AnnotableAndModifiableTestParams.class)
	public void testVisibility(IAnnotableAndModifiableInitialiser initialiser, String testName) {
		this.setResourceFileTestIdentifier("testVisibility");
		
		var objOne = this.initElement(initialiser, null, null, InitialiserVisibilityModifier.PRIVATE);
		var objTwo = this.initElement(initialiser, null, null, InitialiserVisibilityModifier.PUBLIC);
		
		this.testX(objOne, objTwo, false);
	}
}
