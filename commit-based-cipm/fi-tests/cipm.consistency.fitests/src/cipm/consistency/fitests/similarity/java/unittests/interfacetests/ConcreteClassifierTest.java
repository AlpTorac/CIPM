package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import org.emftext.language.java.containers.Package;

import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IConcreteClassifierInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesPackages;

public class ConcreteClassifierTest extends EObjectSimilarityTest implements UsesPackages {
	protected ConcreteClassifier initElement(IConcreteClassifierInitialiser initialiser,
			Package pac) {
		
		ConcreteClassifier result = initialiser.instantiate();
		initialiser.minimalInitialisation(result);
		initialiser.setPackage(result, pac);
		
		return result;
	}
	
	@ParameterizedTest
	@ArgumentsSource(ConcreteClassifierTestParams.class)
	public void testPackage(IConcreteClassifierInitialiser initialiser) {
		this.setResourceFileTestIdentifier("testPackage");
		
		var objOne = this.initElement(initialiser, this.createMinimalPackage("pOneNS", 2));
		var objTwo = this.initElement(initialiser, this.createMinimalPackage("pTwoNS", 2));
		
		this.testX(objOne, objTwo, false);
	}
}