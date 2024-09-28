package cipm.consistency.fitests.similarity.eobject.java.unittests.interfacetests;

import org.emftext.language.java.commons.CommonsPackage;
import org.emftext.language.java.commons.NamedElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.eobject.java.AbstractEObjectJavaSimilarityTest;
import cipm.consistency.initialisers.eobject.java.commons.INamedElementInitialiser;

public class NamedElementTest extends AbstractEObjectJavaSimilarityTest {
	protected NamedElement initElement(INamedElementInitialiser init, String name) {
		NamedElement result = init.instantiate();
		Assertions.assertEquals(init.canSetName(result), init.setName(result, name));
		return result;
	}

	@ParameterizedTest
	@ArgumentsSource(NameTestParams.class)
	public void testName(INamedElementInitialiser init) {
		var objOne = this.initElement(init, "name11");
		var objTwo = this.initElement(init, "name22");

		this.testSimilarity(objOne, objTwo, CommonsPackage.Literals.NAMED_ELEMENT__NAME);
	}

	@ParameterizedTest
	@ArgumentsSource(NameTestParams.class)
	public void testNameNullCheck(INamedElementInitialiser init) {
		this.testSimilarityNullCheck(this.initElement(init, "name11"), init, false,
				CommonsPackage.Literals.NAMED_ELEMENT__NAME);
	}
}
