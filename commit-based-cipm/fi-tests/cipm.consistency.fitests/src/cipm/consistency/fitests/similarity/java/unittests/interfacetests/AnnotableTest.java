package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import org.emftext.language.java.annotations.Annotable;
import org.emftext.language.java.annotations.AnnotationInstance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.annotations.AnnotationInstanceInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.classifiers.AnnotationInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IAnnotableInitialiser;

public class AnnotableTest extends EObjectSimilarityTest {
	private AnnotationInstance aii1;
	private AnnotationInstance aii2;
	
	@BeforeEach
	@Override
	public void setUp() {
		this.setResourceFileTestPrefix(NamedElementSimilarityTest.class.getSimpleName());
		
		// TODO: Figure out a way to structurally initialise elements that require a container to function
		// For example by creating minimal JavaRoot instances and adding the said element into them
		
		var ai = new AnnotationInitialiser();
		var aii = new AnnotationInstanceInitialiser();
		
		aii1 = aii.instantiate();
		aii.minimalInitialisation(aii1);
		aii.addNamespace(aii1, "ns1");
		
		var ai1 = ai.instantiate();
		ai.minimalInitialisation(ai1);
		ai.initialiseName(ai1, "anno1");
		aii.setAnnotation(aii1, ai1);
		
		aii2 = aii.instantiate();
		aii.minimalInitialisation(aii2);
		aii.addNamespace(aii2, "ns2");
		
		var ai2 = ai.instantiate();
		ai.minimalInitialisation(ai2);
		ai.initialiseName(ai2, "anno2");
		aii.setAnnotation(aii2, ai2);
		
		super.setUp();
	}
	
	protected Annotable initElement(IAnnotableInitialiser initialiser, AnnotationInstance... annotations) {
		Annotable result = initialiser.instantiate();
		initialiser.minimalInitialisation(result);
		initialiser.addAnnotations(result, annotations);
		return result;
	}
	
	@ParameterizedTest
	@ArgumentsSource(AnnotableTestParams.class)
	public void testAnnotation(IAnnotableInitialiser initialiser) {
		this.setResourceFileTestIdentifier("testAnnotation");
		
		var objOne = this.initElement(initialiser, this.aii1);
		var objTwo = this.initElement(initialiser, this.aii2);
		
		// TODO: Replace last parameter
		this.testX(objOne, objTwo, initialiser, false);
	}
}
