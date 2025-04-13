package cipm.consistency.fitests.similarity.jamopp.unittests.interfacetests;

import java.util.stream.Stream;

import org.emftext.language.java.annotations.Annotable;
import org.emftext.language.java.annotations.AnnotationInstance;
import org.emftext.language.java.annotations.AnnotationsPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesAnnotationInstances;
import cipm.consistency.initialisers.jamopp.annotations.IAnnotableInitialiser;

public class AnnotableTest extends AbstractJaMoPPSimilarityTest implements UsesAnnotationInstances {
	private AnnotationInstance anno1;
	private AnnotationInstance anno2;

	private static Stream<Arguments> provideArguments() {
		return AbstractJaMoPPSimilarityTest.getAllInitialiserArgumentsFor(IAnnotableInitialiser.class);
	}

	protected Annotable initElement(IAnnotableInitialiser init, AnnotationInstance[] annotations) {
		Annotable result = init.instantiate();
		Assertions.assertTrue(init.initialise(result));
		Assertions.assertTrue(init.addAnnotations(result, annotations));
		return result;
	}

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		anno1 = this.createMinimalAI(new String[] { "ns1" }, "anno1");
		anno2 = this.createMinimalAI(new String[] { "ns2" }, "anno2");
		Assertions.assertFalse(this.isSimilar(anno1, anno2));
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testAnnotation(IAnnotableInitialiser init, String displayName) {
		var objOne = this.initElement(init, new AnnotationInstance[] { this.cloneEObjWithContainers(anno1) });
		var objTwo = this.initElement(init, new AnnotationInstance[] { this.cloneEObjWithContainers(anno2) });

		this.testSimilarity(objOne, objTwo, AnnotationsPackage.Literals.ANNOTABLE__ANNOTATIONS);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testAnnotationSize(IAnnotableInitialiser init, String displayName) {
		var objOne = this.initElement(init,
				new AnnotationInstance[] { this.cloneEObjWithContainers(anno1), this.cloneEObjWithContainers(anno2) });
		var objTwo = this.initElement(init, new AnnotationInstance[] { this.cloneEObjWithContainers(anno1) });

		this.testSimilarity(objOne, objTwo, AnnotationsPackage.Literals.ANNOTABLE__ANNOTATIONS);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testAnnotationPosition(IAnnotableInitialiser init, String displayName) {
		var objOne = this.initElement(init,
				new AnnotationInstance[] { this.cloneEObjWithContainers(anno1), this.cloneEObjWithContainers(anno2) });
		var objTwo = this.initElement(init,
				new AnnotationInstance[] { this.cloneEObjWithContainers(anno2), this.cloneEObjWithContainers(anno1) });

		this.testSimilarity(objOne, objTwo, AnnotationsPackage.Literals.ANNOTABLE__ANNOTATIONS);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testAnnotationDuplication(IAnnotableInitialiser init, String displayName) {
		var objOne = this.initElement(init,
				new AnnotationInstance[] { this.cloneEObjWithContainers(anno1), this.cloneEObjWithContainers(anno1) });
		var objTwo = this.initElement(init, new AnnotationInstance[] { this.cloneEObjWithContainers(anno1) });

		this.testSimilarity(objOne, objTwo, AnnotationsPackage.Literals.ANNOTABLE__ANNOTATIONS);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testAnnotationNullCheck(IAnnotableInitialiser init, String displayName) {
		this.testSimilarityNullCheck(
				this.initElement(init, new AnnotationInstance[] { this.cloneEObjWithContainers(anno1) }), init, true,
				AnnotationsPackage.Literals.ANNOTABLE__ANNOTATIONS);
	}
}
