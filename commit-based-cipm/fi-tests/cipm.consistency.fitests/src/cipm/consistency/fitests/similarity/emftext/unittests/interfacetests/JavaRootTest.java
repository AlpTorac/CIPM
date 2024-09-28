package cipm.consistency.fitests.similarity.emftext.unittests.interfacetests;

import java.util.stream.Stream;

import org.emftext.language.java.containers.ContainersPackage;
import org.emftext.language.java.containers.JavaRoot;
import org.emftext.language.java.containers.Origin;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import cipm.consistency.fitests.similarity.emftext.AbstractEMFTextSimilarityTest;
import cipm.consistency.initialisers.emftext.containers.IJavaRootInitialiser;

public class JavaRootTest extends AbstractEMFTextSimilarityTest {

	private static Stream<Arguments> provideArguments() {
		return AbstractEMFTextSimilarityTest.getAllInitialiserArgumentsFor(IJavaRootInitialiser.class);
	}

	protected JavaRoot initElement(IJavaRootInitialiser init, Origin origin) {
		JavaRoot result = init.instantiate();
		Assertions.assertTrue(init.initialise(result));
		init.setOrigin(result, origin);
		return result;
	}

	@ParameterizedTest
	@MethodSource("provideArguments")
	public void testOrigin(IJavaRootInitialiser init) {
		var objOne = this.initElement(init, Origin.BINDING);
		var objTwo = this.initElement(init, Origin.CLASS);

		this.testSimilarity(objOne, objTwo, ContainersPackage.Literals.JAVA_ROOT__ORIGIN);
	}

	@ParameterizedTest
	@MethodSource("provideArguments")
	public void testOriginNullCheck(IJavaRootInitialiser init) {
		this.testSimilarityNullCheck(this.initElement(init, Origin.BINDING), init, true,
				ContainersPackage.Literals.JAVA_ROOT__ORIGIN);
	}
}