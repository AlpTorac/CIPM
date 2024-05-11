package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import java.util.Collection;

import org.emftext.language.java.annotations.Annotable;
import org.emftext.language.java.annotations.AnnotationInstance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.generators.AnnotationGenerator;
import cipm.consistency.fitests.similarity.java.generators.AnnotationInstanceGenerator;
import cipm.consistency.fitests.similarity.java.initialiser.annotations.AnnotationInstanceInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.classifiers.AnnotationInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IAnnotableInitialiser;

public class AnnotableTest extends EObjectSimilarityTest {
	private AnnotationInstanceGenerator aiGen;
	
	@BeforeEach
	@Override
	public void setUp() {
		this.aiGen = new AnnotationInstanceGenerator();
		this.registerGenerator(aiGen);
		
		super.setUp();
	}
	
	protected Annotable initElement(IAnnotableInitialiser initialiser, Collection<AnnotationInstance> annotations) {
		Annotable result = initialiser.instantiate();
		initialiser.minimalInitialisation(result);
		initialiser.addAnnotations(result, annotations);
		return result;
	}
	
	@ParameterizedTest
	@ArgumentsSource(AnnotableTestParams.class)
	public void testAnnotation(IAnnotableInitialiser initialiser) {
		this.setResourceFileTestIdentifier("testAnnotation");
		
		var objOne = this.initElement(initialiser, this.aiGen.generateElements(2));
		var objTwo = this.initElement(initialiser, this.aiGen.generateElements(1));
		
		// TODO: Replace last parameter
		this.testX(objOne, objTwo, false);
	}
}
