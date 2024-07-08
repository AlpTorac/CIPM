package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import org.emftext.language.java.commons.CommonsPackage;
import org.emftext.language.java.commons.NamedElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.commons.INamedElementInitialiser;

public class NamedElementTest extends EObjectSimilarityTest {
	protected NamedElement initElement(INamedElementInitialiser initialiser, String name) {
		NamedElement result = initialiser.instantiate();
		Assertions.assertTrue(initialiser.setName(result, name));
		return result;
	}
	
	@ParameterizedTest
	@ArgumentsSource(NameTestParams.class)
	public void testName(INamedElementInitialiser initialiser) {
		this.setResourceFileTestIdentifier("testName");
		
		var objOne = this.initElement(initialiser, "name11");
		var objTwo = this.initElement(initialiser, "name22");
		
		this.testX(objOne, objTwo, CommonsPackage.Literals.NAMED_ELEMENT__NAME);
	}
}
