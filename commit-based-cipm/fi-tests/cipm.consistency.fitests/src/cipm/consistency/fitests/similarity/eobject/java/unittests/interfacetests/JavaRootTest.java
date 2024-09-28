package cipm.consistency.fitests.similarity.eobject.java.unittests.interfacetests;

import org.emftext.language.java.containers.ContainersPackage;
import org.emftext.language.java.containers.JavaRoot;
import org.emftext.language.java.containers.Origin;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.eobject.java.AbstractEObjectJavaSimilarityTest;
import cipm.consistency.initialisers.eobject.java.containers.IJavaRootInitialiser;

public class JavaRootTest extends AbstractEObjectJavaSimilarityTest {
	protected JavaRoot initElement(IJavaRootInitialiser init, Origin origin) {
		JavaRoot result = init.instantiate();
		Assertions.assertTrue(init.initialise(result));
		init.setOrigin(result, origin);
		return result;
	}

	@ParameterizedTest
	@ArgumentsSource(JavaRootTestParams.class)
	public void testOrigin(IJavaRootInitialiser init) {
		var objOne = this.initElement(init, Origin.BINDING);
		var objTwo = this.initElement(init, Origin.CLASS);

		this.testSimilarity(objOne, objTwo, ContainersPackage.Literals.JAVA_ROOT__ORIGIN);
	}

	@ParameterizedTest
	@ArgumentsSource(JavaRootTestParams.class)
	public void testOriginNullCheck(IJavaRootInitialiser init) {
		this.testSimilarityNullCheck(this.initElement(init, Origin.BINDING), init, true,
				ContainersPackage.Literals.JAVA_ROOT__ORIGIN);
	}
}
