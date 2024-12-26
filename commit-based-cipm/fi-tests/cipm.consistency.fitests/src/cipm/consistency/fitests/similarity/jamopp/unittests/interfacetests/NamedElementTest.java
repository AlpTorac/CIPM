package cipm.consistency.fitests.similarity.jamopp.unittests.interfacetests;

import java.util.stream.Stream;

import org.eclipse.emf.compare.AttributeChange;
import org.eclipse.emf.compare.DifferenceKind;
import org.emftext.language.java.commons.CommonsPackage;
import org.emftext.language.java.commons.NamedElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.initialisers.jamopp.commons.INamedElementInitialiser;

public class NamedElementTest extends AbstractJaMoPPSimilarityTest {

	private static Stream<Arguments> provideArguments() {
		return AbstractJaMoPPSimilarityTest.getAllInitialiserArgumentsFor(INamedElementInitialiser.class);
	}

	protected NamedElement initElement(INamedElementInitialiser init, String name) {
		NamedElement result = init.instantiate();
		Assertions.assertTrue(init.initialise(result));
		Assertions.assertEquals(init.canSetName(result) || name == null, init.setName(result, name));
		return result;
	}

	@ParameterizedTest
	@MethodSource("provideArguments")
	public void testName(INamedElementInitialiser init) {
		var objOne = this.initElement(init, "name11");
		var objTwo = this.initElement(init, "name22");

		this.testSimilarity(objOne, objTwo, CommonsPackage.Literals.NAMED_ELEMENT__NAME);

		if (init.canSetName(objOne)) {
			// FIXME Comparison test sample, extract in the future
			var objOneClone = this.cloneEObjWithContainers(objOne);
			var objTwoClone = this.cloneEObjWithContainers(objTwo);

			var cmp = this.compareModels(objOneClone, objTwoClone);
			Assertions.assertEquals(1, cmp.getDifferences().size());
			var diff = cmp.getDifferences().get(0);
			Assertions.assertEquals(DifferenceKind.CHANGE, diff.getKind());
			var castedDiff = (AttributeChange) diff;
			Assertions.assertEquals(CommonsPackage.Literals.NAMED_ELEMENT__NAME,
					castedDiff.getAttribute());

			// FIXME Change replay test sample, extract in the future
			this.replayChanges(objOneClone, objTwoClone);
			Assertions.assertEquals(0, this.compareModels(objOneClone, objTwoClone).getDifferences().size());
			this.testSimilarity(objOneClone, objTwoClone, Boolean.TRUE);
		}
	}

	@ParameterizedTest
	@MethodSource("provideArguments")
	public void testNameNullCheck(INamedElementInitialiser init) {
		this.testSimilarityNullCheck(this.initElement(init, "name11"), init, false,
				CommonsPackage.Literals.NAMED_ELEMENT__NAME);
	}
}
