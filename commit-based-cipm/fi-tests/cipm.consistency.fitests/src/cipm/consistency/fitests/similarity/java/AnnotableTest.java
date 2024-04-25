package cipm.consistency.fitests.similarity.java;

import org.emftext.language.java.annotations.Annotable;
import org.emftext.language.java.annotations.AnnotationInstance;
import org.emftext.language.java.annotations.AnnotationsFactory;
import org.emftext.language.java.commons.NamedElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.initialiser.IAnnotableInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IAnnotationInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IAnnotationInstanceInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.INamedElementInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.impl.AnnotationInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.impl.AnnotationInstanceInitialiser;
import cipm.consistency.fitests.similarity.java.params.AnnotableTestParams;
import cipm.consistency.fitests.similarity.java.params.NameTestParams;

public class AnnotableTest extends EObjectSimilarityTest {
	private IAnnotationInitialiser ai;
	private IAnnotationInstanceInitialiser aii;
	
	private AnnotationInstance aii1;
	private AnnotationInstance aii2;
	
	@BeforeEach
	@Override
	public void setUp() {
		this.setResourceFileTestPrefix(NamedElementSimilarityTest.class.getSimpleName());
		this.ai = new AnnotationInitialiser();
		this.aii = new AnnotationInstanceInitialiser();
		
		aii1 = this.aii.instantiate();
		this.aii.initialiseNamespace(aii1, "ns1");
		
		var ai1 = this.ai.instantiate();
		this.ai.initialiseName(ai1, "anno1");
		this.aii.setAnnotation(aii1, ai1);
		
		aii2 = this.aii.instantiate();
		this.aii.initialiseNamespace(aii2, "ns2");
		
		var ai2 = this.ai.minimalInstantiation();
		this.ai.initialiseName(ai2, "anno2");
		this.aii.setAnnotation(aii2, ai2);
		
		super.setUp();
	}
	
	protected Annotable initElement(IAnnotableInitialiser initialiser, AnnotationInstance... annotations) {
		var result = initialiser.minimalInstantiation();
		initialiser.addAnnotations(result, annotations);
		return result;
	}
	
	@ParameterizedTest
	@ArgumentsSource(AnnotableTestParams.class)
	public void testSameAnnotation(IAnnotableInitialiser initialiser) {
		this.setResourceFileTestIdentifier("testSameAnnotation");
		
		var objOne = this.initElement(initialiser, this.aii1);
		
		this.sameX(objOne, initialiser);
	}
	
	@Disabled("Disabled until parameters are befitting")
	@ParameterizedTest
	@ArgumentsSource(AnnotableTestParams.class)
	public void testDifferentAnnotation(IAnnotableInitialiser initialiser) {
		this.setResourceFileTestIdentifier("testDifferentAnnotation");
		
		var objOne = this.initElement(initialiser, this.aii1);
		var objTwo = this.initElement(initialiser, this.aii2);
		
		this.differentX(objOne, objTwo);
	}
}
