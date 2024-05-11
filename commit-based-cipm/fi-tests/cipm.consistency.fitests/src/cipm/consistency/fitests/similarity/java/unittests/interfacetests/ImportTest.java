package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.imports.Import;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.generators.ConcreteClassifierGenerator;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IImportInitialiser;

public class ImportTest extends EObjectSimilarityTest {
	private ConcreteClassifierGenerator ccGen;
	
	@BeforeEach
	@Override
	public void setUp() {
		this.ccGen = new ConcreteClassifierGenerator();
		this.registerGenerator(ccGen);
		super.setUp();
	}
	
	protected ConcreteClassifier generateCC() {
		return this.ccGen.generateElement();
	}
	
	protected Import initElement(IImportInitialiser initialiser, ConcreteClassifier cls) {
		Import result = initialiser.instantiate();
		
		initialiser.minimalInitialisation(result);
		initialiser.setClassifier(result, cls);
		
		return result;
	}
	
	@ParameterizedTest
	@ArgumentsSource(ImportTestParams.class)
	public void testClassifier(IImportInitialiser initialiser) {
		this.setResourceFileTestIdentifier("testClassifier");
		
		var objOne = this.initElement(initialiser, this.generateCC());
		var objTwo = this.initElement(initialiser, this.generateCC());
		
		// TODO: Replace last parameter
		this.testX(objOne, objTwo, false);
	}
}
