package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import org.emftext.language.java.containers.JavaRoot;
import org.emftext.language.java.containers.Origin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IJavaRootInitialiser;

public class JavaRootTest extends EObjectSimilarityTest {
	@BeforeEach
	@Override
	public void setUp() {
		this.setResourceFileTestPrefix(JavaRootTest.class.getSimpleName());
		
		super.setUp();
	}
	
	protected JavaRoot initElement(IJavaRootInitialiser initialiser, Origin origin) {
		JavaRoot result = initialiser.instantiate();
		initialiser.minimalInitialisation(result);
		initialiser.initialiseOrigin(result, origin);
		return result;
	}
	
	@ParameterizedTest
	@ArgumentsSource(JavaRootTestParams.class)
	public void testSameOrigin(IJavaRootInitialiser initialiser) {
		this.setResourceFileTestIdentifier("testSameOrigin");
		
		var objOne = this.initElement(initialiser, Origin.BINDING);
		
		this.sameX(objOne);
	}
	
	/**
	 * Differences in Origin do not break similarity
	 */
	@ParameterizedTest
	@ArgumentsSource(JavaRootTestParams.class)
	public void testDifferentOrigin(IJavaRootInitialiser initialiser) {
		this.setResourceFileTestIdentifier("testDifferentOrigin");
		
		var objOne = this.initElement(initialiser, Origin.BINDING);
		var objTwo = this.initElement(initialiser, Origin.CLASS);
		
		this.differentX(objOne, objTwo);
	}
}
