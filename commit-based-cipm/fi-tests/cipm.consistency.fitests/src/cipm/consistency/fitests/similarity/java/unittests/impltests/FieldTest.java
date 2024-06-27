package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.members.AdditionalField;
import org.emftext.language.java.members.Field;
import org.emftext.language.java.members.MembersPackage;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.members.FieldInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesAdditionalFields;

public class FieldTest extends EObjectSimilarityTest implements UsesAdditionalFields {
	protected Field initElement(AdditionalField[] afs) {
		var fieldInit = new FieldInitialiser();
		var field = fieldInit.instantiate();
		fieldInit.minimalInitialisation(field);
		fieldInit.addAdditionalFields(field, afs);
		return field;
	}
	
	@Test
	public void testAdditionalField() {
		this.setResourceFileTestIdentifier("testAdditionalField");
		
		var objOne = this.initElement(new AdditionalField[] {this.createMinimalAF("af1")});
		var objTwo = this.initElement(new AdditionalField[] {this.createMinimalAF("af2")});
		
		this.testX(objOne, objTwo, MembersPackage.Literals.FIELD__ADDITIONAL_FIELDS);
	}
}