package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import org.emftext.language.java.commons.NamedElement;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.testable.INamedElementInitialiser;

public class NamedElementSimilarityTest extends EObjectSimilarityTest {
	protected NamedElement initElement(INamedElementInitialiser initialiser, String name) {
		NamedElement result = initialiser.instantiate();
		initialiser.initialiseName(result, name);
		return result;
	}
	
	@ParameterizedTest
	@ArgumentsSource(NameTestParams.class)
	public void testName(INamedElementInitialiser initialiser) {
		this.setResourceFileTestIdentifier("testName");
		
		var objOne = this.initElement(initialiser, "name11");
		var objTwo = this.initElement(initialiser, "name22");
		
		this.testX(objOne, objTwo, false);
	}
}
