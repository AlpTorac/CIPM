package cipm.consistency.fitests.similarity.java;

import org.emftext.language.java.annotations.Annotable;
import org.emftext.language.java.annotations.AnnotationInstance;
import org.emftext.language.java.annotations.AnnotationsFactory;
import org.emftext.language.java.commons.NamedElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.initialiser.AnnotationClassifierInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IAnnotableInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IAnnotationInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.INamedElementInitialiser;
import cipm.consistency.fitests.similarity.java.params.NameTestParams;

public class AnnotableTest extends EObjectSimilarityTest {
	private IAnnotationInitialiser annotationInitialiser;
	
	@BeforeEach
	@Override
	public void setUp() {
		this.setResourceFileTestPrefix(NamedElementSimilarityTest.class.getSimpleName());
		this.annotationInitialiser = new AnnotationClassifierInitialiser();
		super.setUp();
	}
	
	protected Annotable initElement(IAnnotableInitialiser initialiser, AnnotationInstance[] annotations) {
		var result = initialiser.minimalInstantiation();
		initialiser.addAnnotations(result, annotations);
		return result;
	}

	// ToDo: Implement after AnnotationInstanceInitialiser
	
//	@ParameterizedTest
//	@ArgumentsSource(NameTestParams.class)
//	public void testSameAnnotation(IAnnotableInitialiser initialiser) {
//		this.setResourceFileTestIdentifier("testSameName");
//		
//		this.annotationInitialiser.instantiate();
//		
//		
//		var objOne = this.initElement(initialiser, this.annotationInitialiser.instantiate());
//		
//		this.sameX(objOne, initialiser);
//	}
//	
//	@ParameterizedTest
//	@ArgumentsSource(NameTestParams.class)
//	public void testDifferentName(IAnnotableInitialiser initialiser) {
//		this.setResourceFileTestIdentifier("testDifferentName");
//		
//		var objOne = this.initElement(initialiser, name11);
//		var objTwo = this.initElement(initialiser, name22);
//		
//		this.differentX(objOne, objTwo);
//	}
}
