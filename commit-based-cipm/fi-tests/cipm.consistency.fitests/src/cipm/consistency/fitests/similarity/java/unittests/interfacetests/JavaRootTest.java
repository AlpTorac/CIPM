package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import org.emftext.language.java.containers.JavaRoot;
import org.emftext.language.java.containers.Origin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IJavaRootInitialiser;

public class JavaRootTest extends EObjectSimilarityTest {
	protected JavaRoot initElement(IJavaRootInitialiser initialiser, Origin origin) {
		JavaRoot result = initialiser.instantiate();
		initialiser.minimalInitialisation(result);
		initialiser.initialiseOrigin(result, origin);
		return result;
	}
	
	@ParameterizedTest
	@ArgumentsSource(JavaRootTestParams.class)
	public void testOrigin(IJavaRootInitialiser initialiser) {
		this.setResourceFileTestIdentifier("testOrigin");
		
		var objOne = this.initElement(initialiser, Origin.BINDING);
		var objTwo = this.initElement(initialiser, Origin.CLASS);
		
		this.testX(objOne, objTwo, false);
	}
}
