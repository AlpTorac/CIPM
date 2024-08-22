package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import org.emftext.language.java.commons.CommonsPackage;
import org.emftext.language.java.commons.NamedElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.commons.INamedElementInitialiser;

public class NamedElementTest extends EObjectSimilarityTest {
	protected NamedElement initElement(INamedElementInitialiser init, String name) {
		NamedElement result = init.instantiate();
		Assertions.assertEquals(init.canSetName(result), init.setName(result, name));
		return result;
	}

	@ParameterizedTest
	@ArgumentsSource(NameTestParams.class)
	public void testName(INamedElementInitialiser init) {
		this.setCurrentInitialiser(init);
		this.setResourceFileTestIdentifier("testName");

		var objOne = this.initElement(init, "name11");
		var objTwo = this.initElement(init, "name22");

		this.testSimilarity(objOne, objTwo, CommonsPackage.Literals.NAMED_ELEMENT__NAME);
	}
	
	@ParameterizedTest
	@ArgumentsSource(NameTestParams.class)
	public void testNameNull(INamedElementInitialiser init) {
		this.setCurrentInitialiser(init);
		this.setResourceFileTestIdentifier("testNameNull");
		
		var objOne = this.initElement(init, "name11");
		var objTwo = init.instantiate();
		
		this.testSimilarity(objOne, objTwo, CommonsPackage.Literals.NAMED_ELEMENT__NAME);
	}
}
