package cipm.consistency.fitests.similarity.java.unittests;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.annotations.AnnotationInstance;
import org.emftext.language.java.classifiers.Annotation;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.generics.TypeParametrizable;
import org.emftext.language.java.members.Member;
import org.emftext.language.java.modifiers.AnnotableAndModifiable;
import org.emftext.language.java.modifiers.Modifier;
import org.emftext.language.java.parameters.Parameter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.InitialiserVisibilityModifier;
import cipm.consistency.fitests.similarity.java.initialiser.annotations.AnnotationInstanceInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.classifiers.AnnotationInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.classifiers.ClassInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.classifiers.IAnnotationInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.containers.CompilationUnitInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.members.ConstructorInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.params.ModifierFactory;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IAnnotableAndModifiableInitialiser;
import cipm.consistency.fitests.similarity.java.params.AnnotableAndModifiableTestParams;

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
	private Modifier mod1;
	private Modifier mod2;
	private Modifier mod3;
	private Modifier mod4;
	
	private AnnotationInstance aii1;
	private AnnotationInstance aii2;
	
	@BeforeEach
	@Override
	public void setUp() {
		this.setResourceFileTestPrefix(AnnotableAndModifiableTest.class.getSimpleName());
		
		// TODO: Figure out a way to structurally initialise elements that require containers to function
		// For example by creating minimal JavaRoot instances and adding the said element into them
		
		IAnnotationInitialiser ai = new AnnotationInitialiser();
		var aii = new AnnotationInstanceInitialiser();
		
		aii1 = aii.instantiate();
		aii.minimalInitialisation(aii1);
		aii.initialiseNamespace(aii1, "ns1");
		
		Annotation ai1 = ai.instantiate();
		ai.minimalInitialisation(ai1);
		ai.initialiseName(ai1, "anno1");
		aii.setAnnotation(aii1, ai1);
		
		aii2 = aii.instantiate();
		aii.minimalInitialisation(aii2);
		aii.initialiseNamespace(aii2, "ns2");
		
		Annotation ai2 = ai.instantiate();
		ai.minimalInitialisation(ai2);
		ai.initialiseName(ai2, "anno2");
		aii.setAnnotation(aii2, ai2);
		
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
	 * the inner initialisers for {@link MemberContaineeInitialiser} and
	 * {@link ParameterInitialiser} have to be set. {@link ConcreteClassifierInitialiser}
	 * already has its inner initialiser set (since there is only one possibility).
	 */
	protected EObject initElement(IAnnotableAndModifiableInitialiser initialiser,
			Modifier[] mods,
			AnnotationInstance[] ais,
			InitialiserVisibilityModifier visibility) {
		
		AnnotableAndModifiable result = initialiser.instantiate();
//		var root = initialiser.minimalInitialisationWithContainer(result);
		initialiser.minimalInitialisation(result);
		
		if (mods != null) {
			for (var m : mods) initialiser.addModifier(result, m);
		}
		
		if (ais != null) {
			for (var ai : ais) initialiser.addAnnotationInstance(result, ai);
		}
		
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
	public void testSameModifier(IAnnotableAndModifiableInitialiser initialiser, String testName) {
		this.setResourceFileTestIdentifier("testSameModifier");
		
		var objOne = this.initElement(initialiser, new Modifier[] {mod1, mod2}, null, null);
		
		this.sameX(objOne, initialiser);
	}
	
	// TODO: Clarify whether such differences matter, currently they do not matter
//	@Disabled("See TODO")
	@ParameterizedTest(name = "{index}: {1}")
	@ArgumentsSource(AnnotableAndModifiableTestParams.class)
	public void testDifferentModifier(IAnnotableAndModifiableInitialiser initialiser, String testName) {
		this.setResourceFileTestIdentifier("testDifferentModifier");
		
		var objOne = this.initElement(initialiser, new Modifier[] {mod1, mod2}, null, null);
		var objTwo = this.initElement(initialiser, new Modifier[] {mod3, mod4}, null, null);
		
		this.compareX(objOne, objTwo, true);
	}
	
	@ParameterizedTest(name = "{index}: {1}")
	@ArgumentsSource(AnnotableAndModifiableTestParams.class)
	public void testSameAnnoInstance(IAnnotableAndModifiableInitialiser initialiser, String testName) {
		this.setResourceFileTestIdentifier("testSameAnnoInstance");
		
		var objOne = this.initElement(initialiser, null, new AnnotationInstance[] {aii1}, null);
		
		this.sameX(objOne, initialiser);
	}
	
	// TODO: Clarify whether such differences matter, currently they do not matter
//	@Disabled("See TODO")
	@ParameterizedTest(name = "{index}: {1}")
	@ArgumentsSource(AnnotableAndModifiableTestParams.class)
	public void testDifferentAnnoInstance(IAnnotableAndModifiableInitialiser initialiser, String testName) {
		this.setResourceFileTestIdentifier("testDifferentAnnoInstance");
		
		var objOne = this.initElement(initialiser, null, new AnnotationInstance[] {aii1}, null);
		var objTwo = this.initElement(initialiser, null, new AnnotationInstance[] {aii2}, null);
		
		this.compareX(objOne, objTwo, true);
	}
	
	@ParameterizedTest(name = "{index}: {1}")
	@ArgumentsSource(AnnotableAndModifiableTestParams.class)
	public void testSameVisibility(IAnnotableAndModifiableInitialiser initialiser, String testName) {
		this.setResourceFileTestIdentifier("testSameVisibility");
		
		var objOne = this.initElement(initialiser, null, null, InitialiserVisibilityModifier.PRIVATE);
		
		this.sameX(objOne, initialiser);
	}
	
	// TODO: Clarify whether such differences matter, currently they do not matter
//	@Disabled("See TODO")
	@ParameterizedTest(name = "{index}: {1}")
	@ArgumentsSource(AnnotableAndModifiableTestParams.class)
	public void testDifferentVisibility(IAnnotableAndModifiableInitialiser initialiser, String testName) {
		this.setResourceFileTestIdentifier("testDifferentVisibility");
		
		var objOne = this.initElement(initialiser, null, null, InitialiserVisibilityModifier.PRIVATE);
		var objTwo = this.initElement(initialiser, null, null, InitialiserVisibilityModifier.PUBLIC);
		
		this.compareX(objOne, objTwo, true);
	}
}
