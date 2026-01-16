package cipm.consistency.fitests.similarity.jamopp.unittests.impltests;

import java.util.function.Supplier;

import org.emftext.language.java.types.InferableType;
import org.emftext.language.java.types.TypeReference;
import org.emftext.language.java.types.TypesPackage;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;

/**
 * Since the "target" feature is stored in different attributes, this test class
 * ensures that the underlying target feature for InferableType is handled
 * accordingly during similarity checking.
 * 
 * @author Alp Torac Genc
 */
public class InferableTypeTest extends AbstractJaMoPPSimilarityTest {
	private final Supplier<TypeReference> target1 = () -> getAPI().newClassifierReference()
			.withTarget(getAPI().newClass().withName("cls1").createNow()).createNow();
	private final Supplier<TypeReference> target2 = () -> getAPI().newClassifierReference()
			.withTarget(getAPI().newClass().withName("cls2").createNow()).createNow();

	@Test
	public void testActualTarget() {
		this.testSimilarity(getAPI().newInferableType().withAddedActualTargets(target1.get()).createNow(),
				getAPI().newInferableType().withAddedActualTargets(target2.get()).createNow(), InferableType.class,
				TypesPackage.Literals.CLASSIFIER_REFERENCE__TARGET);
	}

	@Test
	public void testActualTargetSize() {
		this.testSimilarity(
				getAPI().newInferableType().withAddedActualTargets(new TypeReference[] { target1.get(), target2.get() })
						.createNow(),
				getAPI().newInferableType().withAddedActualTargets(target1.get()).createNow(), InferableType.class,
				TypesPackage.Literals.CLASSIFIER_REFERENCE__TARGET);
	}

	@Test
	public void testActualTargetNullCheck() {
		this.testSimilarityNullCheck(getAPI().newInferableType().withAddedActualTargets(target1.get()).createNow(),
				InferableType.class, TypesPackage.Literals.CLASSIFIER_REFERENCE__TARGET);
	}
}
