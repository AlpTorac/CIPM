package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.variables.AdditionalLocalVariable;
import org.emftext.language.java.variables.LocalVariable;
import org.emftext.language.java.variables.VariablesPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.variables.LocalVariableInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesAdditionalLocalVariables;
import cipm.consistency.fitests.similarity.java.unittests.UsesNames;

public class LocalVariableTest extends EObjectSimilarityTest implements UsesAdditionalLocalVariables, UsesNames {
	protected LocalVariable initElement(AdditionalLocalVariable[] alvs) {
		var lvInit = new LocalVariableInitialiser();
		var lv = lvInit.instantiate();
		Assertions.assertTrue(lvInit.setName(lv, this.getDefaultName()));
		Assertions.assertTrue(lvInit.addAdditionalLocalVariables(lv, alvs));
		return lv;
	}

	@Test
	public void testLocalVariable() {
		this.setResourceFileTestIdentifier("testLocalVariable");

		var objOne = this.initElement(new AdditionalLocalVariable[] { this.createMinimalALV("alv1") });
		var objTwo = this.initElement(new AdditionalLocalVariable[] { this.createMinimalALV("alv2") });

		this.testSimilarity(objOne, objTwo, VariablesPackage.Literals.LOCAL_VARIABLE__ADDITIONAL_LOCAL_VARIABLES);
	}
	
	@Test
	public void testLocalVariableNull() {
		this.setResourceFileTestIdentifier("testLocalVariableNull");
		
		var objOne = this.initElement(new AdditionalLocalVariable[] { this.createMinimalALV("alv1") });
		var objTwo = new LocalVariableInitialiser().instantiate();
		
		this.testSimilarity(objOne, objTwo, VariablesPackage.Literals.LOCAL_VARIABLE__ADDITIONAL_LOCAL_VARIABLES);
	}
}
