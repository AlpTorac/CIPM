package cipm.consistency.fitests.similarity.java;

import org.emftext.language.java.annotations.Annotable;
import org.emftext.language.java.annotations.AnnotationInstance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.initialiser.IAnnotableInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.impl.AnnotationInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.impl.AnnotationInstanceInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.impl.IInitialiser;
import cipm.consistency.fitests.similarity.java.params.AnnotableTestParams;

public class AnnotableTest extends EObjectSimilarityTest {
	private AnnotationInstance aii1;
	private AnnotationInstance aii2;
	
	@BeforeEach
	@Override
	public void setUp() {
		this.setResourceFileTestPrefix(NamedElementSimilarityTest.class.getSimpleName());
		var ai = new AnnotationInitialiser();
		var aii = new AnnotationInstanceInitialiser();
		
		aii1 = aii.instantiate();
		aii.minimalInitialisation(aii1);
		aii.initialiseNamespace(aii1, "ns1");
		
		var ai1 = ai.instantiate();
		ai.minimalInitialisation(ai1);
		ai.initialiseName(ai1, "anno1");
		aii.setAnnotation(aii1, ai1);
		
		aii2 = aii.instantiate();
		aii.minimalInitialisation(aii2);
		aii.initialiseNamespace(aii2, "ns2");
		
		var ai2 = ai.instantiate();
		ai.minimalInitialisation(ai2);
		ai.initialiseName(ai2, "anno2");
		aii.setAnnotation(aii2, ai2);
		
		super.setUp();
	}
	
	protected <T extends IAnnotableInitialiser & IInitialiser> Annotable initElement(T initialiser, AnnotationInstance... annotations) {
		var result = (Annotable) initialiser.instantiate();
		initialiser.minimalInitialisation(result);
		initialiser.addAnnotations(result, annotations);
		return result;
	}
	
	@ParameterizedTest
	@ArgumentsSource(AnnotableTestParams.class)
	public <T extends IAnnotableInitialiser & IInitialiser> void testSameAnnotation(T initialiser) {
		this.setResourceFileTestIdentifier("testSameAnnotation");
		
		var objOne = this.initElement(initialiser, this.aii1);
		
		this.sameX(objOne, initialiser);
	}
	
	@Disabled("Disabled until parameters are befitting")
	@ParameterizedTest
	@ArgumentsSource(AnnotableTestParams.class)
	public <T extends IAnnotableInitialiser & IInitialiser> void testDifferentAnnotation(T initialiser) {
		this.setResourceFileTestIdentifier("testDifferentAnnotation");
		
		var objOne = this.initElement(initialiser, this.aii1);
		var objTwo = this.initElement(initialiser, this.aii2);
		
		this.differentX(objOne, objTwo);
	}
}
