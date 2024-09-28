package cipm.consistency.fitests.similarity.emftext.unittests.interfacetests;

import java.util.stream.Stream;

import org.emftext.language.java.commons.CommonsPackage;
import org.emftext.language.java.commons.NamedElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import cipm.consistency.fitests.similarity.emftext.AbstractEMFTextSimilarityTest;
import cipm.consistency.initialisers.emftext.commons.INamedElementInitialiser;

public class NamedElementTest extends AbstractEMFTextSimilarityTest {

	private static Stream<Arguments> provideArguments() {
		return AbstractEMFTextSimilarityTest.getAllInitialiserArgumentsFor(INamedElementInitialiser.class);
	}

	protected NamedElement initElement(INamedElementInitialiser init, String name) {
		NamedElement result = init.instantiate();
		Assertions.assertEquals(init.canSetName(result), init.setName(result, name));
		return result;
	}

	@ParameterizedTest
	@MethodSource("provideArguments")
	public void testName(INamedElementInitialiser init) {
		var objOne = this.initElement(init, "name11");
		var objTwo = this.initElement(init, "name22");

		this.testSimilarity(objOne, objTwo, CommonsPackage.Literals.NAMED_ELEMENT__NAME);
	}

	@ParameterizedTest
	@MethodSource("provideArguments")
	public void testNameNullCheck(INamedElementInitialiser init) {
		this.testSimilarityNullCheck(this.initElement(init, "name11"), init, false,
				CommonsPackage.Literals.NAMED_ELEMENT__NAME);
	}
}
