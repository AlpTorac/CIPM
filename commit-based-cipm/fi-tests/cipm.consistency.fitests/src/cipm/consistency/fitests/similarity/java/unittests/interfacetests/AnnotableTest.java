package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import org.emftext.language.java.annotations.Annotable;
import org.emftext.language.java.annotations.AnnotationInstance;
import org.emftext.language.java.annotations.AnnotationsPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.annotations.IAnnotableInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesAnnotationInstances;

public class AnnotableTest extends EObjectSimilarityTest implements UsesAnnotationInstances {
	protected Annotable initElement(IAnnotableInitialiser init, AnnotationInstance[] annotations) {
		Annotable result = init.instantiate();
		Assertions.assertTrue(init.initialise(result));
		Assertions.assertTrue(init.addAnnotations(result, annotations));
		return result;
	}

	@ParameterizedTest
	@ArgumentsSource(AnnotableTestParams.class)
	public void testAnnotation(IAnnotableInitialiser init) {
		var objOne = this.initElement(init,
				new AnnotationInstance[] { this.createMinimalAI(new String[] { "ns1" }, "anno1") });
		var objTwo = this.initElement(init,
				new AnnotationInstance[] { this.createMinimalAI(new String[] { "ns2" }, "anno2") });

		this.testSimilarity(objOne, objTwo, AnnotationsPackage.Literals.ANNOTABLE__ANNOTATIONS);
	}

	@ParameterizedTest
	@ArgumentsSource(AnnotableTestParams.class)
	public void testAnnotationSize(IAnnotableInitialiser init) {
		var objOne = this.initElement(init,
				new AnnotationInstance[] { this.createMinimalAI(new String[] { "ns1" }, "anno1"),
						this.createMinimalAI(new String[] { "ns2" }, "anno2") });
		var objTwo = this.initElement(init,
				new AnnotationInstance[] { this.createMinimalAI(new String[] { "ns1" }, "anno1") });

		this.testSimilarity(objOne, objTwo, AnnotationsPackage.Literals.ANNOTABLE__ANNOTATIONS);
	}

	@ParameterizedTest
	@ArgumentsSource(AnnotableTestParams.class)
	public void testAnnotationNullCheck(IAnnotableInitialiser init) {
		this.testSimilarityNullCheck(
				this.initElement(init,
						new AnnotationInstance[] { this.createMinimalAI(new String[] { "ns1" }, "anno1") }),
				init, true, AnnotationsPackage.Literals.ANNOTABLE__ANNOTATIONS);
	}
}
