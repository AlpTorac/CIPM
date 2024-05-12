package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import org.emftext.language.java.annotations.Annotable;
import org.emftext.language.java.annotations.AnnotationInstance;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IAnnotableInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesAnnotationInstances;

public class AnnotableTest extends EObjectSimilarityTest implements UsesAnnotationInstances {
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
		
		var objOne = this.initElement(initialiser, this.createMinimalAI(new String[] {"ns1"}, "anno1"));
		var objTwo = this.initElement(initialiser, this.createMinimalAI(new String[] {"ns2"}, "anno2"));
		
		this.testX(objOne, objTwo, false);
	}
}
