package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import org.emftext.language.java.commons.NamedElement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.generators.NameGenerator;
import cipm.consistency.fitests.similarity.java.initialiser.testable.INamedElementInitialiser;

public class NamedElementSimilarityTest extends EObjectSimilarityTest {
	private NameGenerator nGen;
	
	@BeforeEach
	@Override
	public void setUp() {
		this.nGen = new NameGenerator();
		this.registerGenerator(nGen);
		
		super.setUp();
	}
	
	protected String generateName() {
		return this.nGen.generateDefaultElement();
	}
	
	protected NamedElement initElement(INamedElementInitialiser initialiser, String name) {
		NamedElement result = initialiser.instantiate();
		initialiser.initialiseName(result, name);
		return result;
	}
	
	@ParameterizedTest
	@ArgumentsSource(NameTestParams.class)
	public void testName(INamedElementInitialiser initialiser) {
		this.setResourceFileTestIdentifier("testName");
		
		var objOne = this.initElement(initialiser, this.generateName());
		var objTwo = this.initElement(initialiser, this.generateName());
		
		// TODO: Replace last parameter
		this.testX(objOne, objTwo, initialiser, false);
	}
}
