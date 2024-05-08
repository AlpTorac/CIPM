package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import org.emftext.language.java.commons.NamespaceAwareElement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.generators.NamespaceGenerator;
import cipm.consistency.fitests.similarity.java.initialiser.testable.INamespaceAwareElementInitialiser;

public class NamespaceAwareElementTest extends EObjectSimilarityTest {
	private NamespaceGenerator nsGen;
	
	@BeforeEach
	@Override
	public void setUp() {
		this.nsGen = new NamespaceGenerator();
		this.registerGenerator(nsGen);
		super.setUp();
	}
	
	protected String[] generateNS(int nsCount) {
		return this.nsGen.generateNamespace(nsCount);
	}
	
	protected NamespaceAwareElement initElement(INamespaceAwareElementInitialiser initialiser, String[] nss) {
		NamespaceAwareElement result = initialiser.instantiate();
		initialiser.minimalInitialisation(result);
		initialiser.addNamespaces(result, nss);
		return result;
	}
	
	@ParameterizedTest
	@ArgumentsSource(NamespaceTestParams.class)
	public void testNamespace(INamespaceAwareElementInitialiser initialiser) {
		this.setResourceFileTestIdentifier("testNamespace");
		
		var objOne = this.initElement(initialiser, this.generateNS(2));
		var objTwo = this.initElement(initialiser, this.generateNS(3));
		
		// TODO: Replace last parameter
		this.testX(objOne, objTwo, initialiser, false);
	}
}
