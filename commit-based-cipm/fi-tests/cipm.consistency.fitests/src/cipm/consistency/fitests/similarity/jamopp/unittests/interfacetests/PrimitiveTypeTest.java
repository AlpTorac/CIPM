package cipm.consistency.fitests.similarity.jamopp.unittests.interfacetests;

import java.util.function.Supplier;
import java.util.stream.Stream;

import org.emftext.language.java.classifiers.Classifier;
import org.emftext.language.java.types.PrimitiveType;
import org.emftext.language.java.types.TypesPackage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.JaMoPPArguments;

public class PrimitiveTypeTest extends AbstractJaMoPPSimilarityTest {
	private final Supplier<Classifier> target1 = () -> getAPI().newClass().withName("cls1").createNow();
	private final Supplier<Classifier> target2 = () -> getAPI().newClass().withName("cls2").createNow();

	private static Stream<Arguments> provideArguments() {
		return JaMoPPArguments.getAllConcreteClassesBySuperAsArgs(PrimitiveType.class);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testTarget(Class<?> cls, String displayName) {
		this.testSimilarity(
				getAPI().newX(cls).xWithFeat(TypesPackage.Literals.CLASSIFIER_REFERENCE__TARGET, target1.get())
						.createNow(),
				getAPI().newX(cls).xWithFeat(TypesPackage.Literals.CLASSIFIER_REFERENCE__TARGET, target2.get())
						.createNow(),
				PrimitiveType.class, TypesPackage.Literals.CLASSIFIER_REFERENCE__TARGET);
	}

	@ParameterizedTest(name = "{1}")
	@MethodSource("provideArguments")
	public void testTargetNullCheck(Class<?> cls, String displayName) {
		this.testSimilarityNullCheck(getAPI().newX(cls)
				.xWithFeat(TypesPackage.Literals.CLASSIFIER_REFERENCE__TARGET, target1.get()).createNow(),
				PrimitiveType.class, TypesPackage.Literals.CLASSIFIER_REFERENCE__TARGET);
	}
}