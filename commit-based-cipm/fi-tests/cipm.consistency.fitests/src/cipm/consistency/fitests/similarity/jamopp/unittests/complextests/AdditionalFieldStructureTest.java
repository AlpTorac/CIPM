package cipm.consistency.fitests.similarity.jamopp.unittests.complextests;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesFields;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesTypeReferences;
import cipm.consistency.initialisers.jamopp.members.AdditionalFieldInitialiser;
import cipm.consistency.initialisers.jamopp.members.FieldInitialiser;
import cipm.consistency.initialisers.jamopp.members.IMemberContainerInitialiser;

/**
 * TODO Add commentary
 */
public class AdditionalFieldStructureTest extends AbstractJaMoPPSimilarityTest implements UsesFields, UsesTypeReferences {
	private static Stream<Arguments> genTestParams() {
		return AbstractJaMoPPSimilarityTest.getAllInitialiserArgumentsFor(IMemberContainerInitialiser.class);
	}

	@Test
	public void testDifferentContainer_OneContainer_IsNull() {
		var afInit = new AdditionalFieldInitialiser();
		var fieldInit = new FieldInitialiser();

		var af1 = afInit.instantiate();
		var af2 = afInit.instantiate();

		var field1 = fieldInit.instantiate();

		fieldInit.addAdditionalField(field1, af1);
		
		this.testSimilarity(af1, af2, false);
	}
	
	@Test
	public void testDifferentContainer_OneContainer_HasTypeReference() {
		var tref = this.createMinimalClsRef("cls");

		var afInit = new AdditionalFieldInitialiser();
		var fieldInit = new FieldInitialiser();

		var af1 = afInit.instantiate();
		var af2 = afInit.instantiate();

		var field1 = fieldInit.instantiate();
		fieldInit.setTypeReference(field1, tref);
		var field2 = fieldInit.instantiate();

		fieldInit.addAdditionalField(field1, af1);
		fieldInit.addAdditionalField(field2, af2);
		
		this.testSimilarity(af1, af2, false);
	}

	@Test
	public void testDifferentContainer_BothContainers_HaveDifferentTypeReference() {
		var tref1 = this.createMinimalClsRef("cls1");
		var tref2 = this.createMinimalClsRef("cls2");

		// Make sure that the type references are different
		this.assertSimilarityResult(tref1, tref2, false);
		
		var fieldInit = new FieldInitialiser();

		var field1 = fieldInit.instantiate();
		fieldInit.setTypeReference(field1, tref1);
		var field2 = fieldInit.instantiate();
		fieldInit.setTypeReference(field2, tref2);

		var afInit = new AdditionalFieldInitialiser();
		var af1 = afInit.instantiate();
		var af2 = afInit.instantiate();

		fieldInit.addAdditionalField(field1, af1);
		fieldInit.addAdditionalField(field2, af2);

		this.testSimilarity(af1, af2, false);
	}

	@ParameterizedTest
	@MethodSource("genTestParams")
	public void testDifferentConOfCon_OneContainer_IsNull(IMemberContainerInitialiser init) {
		var afInit = new AdditionalFieldInitialiser();
		var fieldInit = new FieldInitialiser();

		var af1 = afInit.instantiate();
		var af2 = afInit.instantiate();

		var field1 = fieldInit.instantiate();

		fieldInit.addAdditionalField(field1, af1);

		var conOfCon = init.instantiate();
		init.addMember(conOfCon, field1);

		this.testSimilarity(af1, af2, false);
	}

	@ParameterizedTest
	@MethodSource("genTestParams")
	public void testDifferentConOfCon_OneConOfCon_IsNull(IMemberContainerInitialiser init) {
		var afInit = new AdditionalFieldInitialiser();
		var fieldInit = new FieldInitialiser();

		var af1 = afInit.instantiate();
		var af2 = afInit.instantiate();

		var field1 = fieldInit.instantiate();
		var field2 = fieldInit.instantiate();

		fieldInit.addAdditionalField(field1, af1);
		fieldInit.addAdditionalField(field2, af2);

		var conOfCon = init.instantiate();
		init.addMember(conOfCon, field1);

		this.testSimilarity(af1, af2, false);
	}
}
