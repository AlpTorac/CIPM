package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import org.emftext.language.java.containers.Package;

import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.generators.PackageGenerator;
import cipm.consistency.fitests.similarity.java.initialiser.containers.PackageInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IConcreteClassifierInitialiser;

public class ConcreteClassifierTest extends EObjectSimilarityTest {
	private PackageGenerator pacGen;
	
	@BeforeEach
	@Override
	public void setUp() {
		this.pacGen = new PackageGenerator();
		this.registerGenerator(pacGen);
		
		super.setUp();
	}
	
	protected Package generatePackage() {
		return this.pacGen.generateDefaultElement();
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
	public void testPackage(IConcreteClassifierInitialiser initialiser) {
		this.setResourceFileTestIdentifier("testPackage");
		
		var objOne = this.initElement(initialiser, this.generatePackage());
		var objTwo = this.initElement(initialiser, this.generatePackage());
		
		// TODO: Replace last parameter
		this.testX(objOne, objTwo, initialiser, false);
	}
}
