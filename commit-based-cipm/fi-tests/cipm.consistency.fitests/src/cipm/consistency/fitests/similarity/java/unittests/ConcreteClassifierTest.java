package cipm.consistency.fitests.similarity.java.unittests;

import org.emftext.language.java.containers.Package;

import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.containers.PackageInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IConcreteClassifierInitialiser;
import cipm.consistency.fitests.similarity.java.params.ConcreteClassifierTestParams;

public class ConcreteClassifierTest extends EObjectSimilarityTest {
	private final String[] pac1nss = new String[] {"pns", "p1ns1"};
	private Package pac1;
	
	private final String[] pac2nss = new String[] {"pns", "p2ns1"};
	private Package pac2;
	
	@BeforeEach
	@Override
	public void setUp() {
		this.setResourceFileTestPrefix(ConcreteClassifierTest.class.getSimpleName());
		
		var pacInit = new PackageInitialiser();
		
		this.pac1 = pacInit.instantiate();
		pacInit.initialiseNamespaces(pac1, pac1nss);
		
		this.pac2 = pacInit.instantiate();
		pacInit.initialiseNamespaces(pac2, pac2nss);
		
		super.setUp();
	}
	
	protected ConcreteClassifier initElement(IConcreteClassifierInitialiser initialiser,
			Package pac) {
		
		ConcreteClassifier result = initialiser.instantiate();
		initialiser.minimalInitialisation(result);
		initialiser.setPackage(result, pac);
		
		return result;
	}
	
	@ParameterizedTest
	@ArgumentsSource(ConcreteClassifierTestParams.class)
	public void testSamePackage(IConcreteClassifierInitialiser initialiser) {
		this.setResourceFileTestIdentifier("testSamePackage");
		
		var objOne = this.initElement(initialiser, pac1);
		
		this.sameX(objOne, initialiser);
	}
	
	/**
	 * Differences in Package do not break similarity
	 */
	@ParameterizedTest
	@ArgumentsSource(ConcreteClassifierTestParams.class)
	public void testDifferentPackage(IConcreteClassifierInitialiser initialiser) {
		this.setResourceFileTestIdentifier("testDifferentPackage");
		
		var objOne = this.initElement(initialiser, pac1);
		var objTwo = this.initElement(initialiser, pac2);
		
		this.compareX(objOne, objTwo, Boolean.TRUE);
	}
}
