package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.members.AdditionalField;
import org.emftext.language.java.members.Field;
import org.emftext.language.java.members.MembersPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.members.FieldInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesAdditionalFields;

public class FieldTest extends EObjectSimilarityTest implements UsesAdditionalFields {
	protected Field initElement(AdditionalField[] additionalFields) {
		var fieldInit = new FieldInitialiser();
		var field = fieldInit.instantiate();
		Assertions.assertTrue(fieldInit.addAdditionalFields(field, additionalFields));
		return field;
	}

	@Test
	public void testAdditionalField() {
		var objOne = this.initElement(new AdditionalField[] { this.createMinimalAF("af1") });
		var objTwo = this.initElement(new AdditionalField[] { this.createMinimalAF("af2") });

		this.testSimilarity(objOne, objTwo, MembersPackage.Literals.FIELD__ADDITIONAL_FIELDS);
	}

	@Test
	public void testAdditionalFieldSize() {
		var objOne = this
				.initElement(new AdditionalField[] { this.createMinimalAF("af1"), this.createMinimalAF("af2") });
		var objTwo = this.initElement(new AdditionalField[] { this.createMinimalAF("af1") });

		this.testSimilarity(objOne, objTwo, MembersPackage.Literals.FIELD__ADDITIONAL_FIELDS);
	}

	@Test
	public void testAdditionalFieldNullCheck() {
		var objOne = this.initElement(new AdditionalField[] { this.createMinimalAF("af1") });
		var objTwo = new FieldInitialiser().instantiate();

		this.testSimilarity(objOne, objTwo, MembersPackage.Literals.FIELD__ADDITIONAL_FIELDS);
	}
}
