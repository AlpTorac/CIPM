package cipm.consistency.fitests.similarity.jamopp.unittests.impltests;

import org.emftext.language.java.members.AdditionalField;
import org.emftext.language.java.members.Field;
import org.emftext.language.java.members.MembersPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesAdditionalFields;
import cipm.consistency.initialisers.jamopp.members.FieldInitialiser;

public class FieldTest extends AbstractJaMoPPSimilarityTest implements UsesAdditionalFields {
	private AdditionalField af1;
	private AdditionalField af2;

	protected Field initElement(AdditionalField[] additionalFields) {
		var fieldInit = new FieldInitialiser();
		var field = fieldInit.instantiate();
		Assertions.assertTrue(fieldInit.addAdditionalFields(field, additionalFields));
		return field;
	}

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		af1 = this.createMinimalAF("af1");
		af2 = this.createMinimalAF("af2");
		Assertions.assertFalse(this.isSimilar(af1, af2));
	}

	@Test
	public void testAdditionalField() {
		var objOne = this.initElement(new AdditionalField[] { this.cloneEObjWithContainers(af1) });
		var objTwo = this.initElement(new AdditionalField[] { this.cloneEObjWithContainers(af2) });

		this.testSimilarity(objOne, objTwo, MembersPackage.Literals.FIELD__ADDITIONAL_FIELDS);
	}

	@Test
	public void testAdditionalFieldSize() {
		var objOne = this.initElement(
				new AdditionalField[] { this.cloneEObjWithContainers(af1), this.cloneEObjWithContainers(af2) });
		var objTwo = this.initElement(new AdditionalField[] { this.cloneEObjWithContainers(af1) });

		this.testSimilarity(objOne, objTwo, MembersPackage.Literals.FIELD__ADDITIONAL_FIELDS);
	}

	@Test
	public void testAdditionalFieldPosition() {
		var objOne = this.initElement(
				new AdditionalField[] { this.cloneEObjWithContainers(af1), this.cloneEObjWithContainers(af2) });
		var objTwo = this.initElement(
				new AdditionalField[] { this.cloneEObjWithContainers(af2), this.cloneEObjWithContainers(af1) });

		this.testSimilarity(objOne, objTwo, MembersPackage.Literals.FIELD__ADDITIONAL_FIELDS);
	}

	@Test
	public void testAdditionalFieldDuplication() {
		var objOne = this.initElement(
				new AdditionalField[] { this.cloneEObjWithContainers(af1), this.cloneEObjWithContainers(af1) });
		var objTwo = this.initElement(new AdditionalField[] { this.cloneEObjWithContainers(af1) });

		this.testSimilarity(objOne, objTwo, MembersPackage.Literals.FIELD__ADDITIONAL_FIELDS);
	}

	@Test
	public void testAdditionalFieldNullCheck() {
		this.testSimilarityNullCheck(this.initElement(new AdditionalField[] { this.cloneEObjWithContainers(af1) }),
				new FieldInitialiser(), false, MembersPackage.Literals.FIELD__ADDITIONAL_FIELDS);
	}
}
