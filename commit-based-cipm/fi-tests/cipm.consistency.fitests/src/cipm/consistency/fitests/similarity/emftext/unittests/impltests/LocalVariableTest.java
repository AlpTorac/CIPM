package cipm.consistency.fitests.similarity.emftext.unittests.impltests;

import org.emftext.language.java.variables.AdditionalLocalVariable;
import org.emftext.language.java.variables.LocalVariable;
import org.emftext.language.java.variables.VariablesPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.emftext.AbstractEMFTextSimilarityTest;
import cipm.consistency.fitests.similarity.emftext.unittests.UsesAdditionalLocalVariables;
import cipm.consistency.fitests.similarity.emftext.unittests.UsesNames;
import cipm.consistency.initialisers.emftext.variables.LocalVariableInitialiser;

public class LocalVariableTest extends AbstractEMFTextSimilarityTest implements UsesAdditionalLocalVariables, UsesNames {
	protected LocalVariable initElement(AdditionalLocalVariable[] alvs) {
		var lvInit = new LocalVariableInitialiser();
		var lv = lvInit.instantiate();
		Assertions.assertTrue(lvInit.setName(lv, this.getDefaultName()));
		Assertions.assertTrue(lvInit.addAdditionalLocalVariables(lv, alvs));
		return lv;
	}

	@Test
	public void testLocalVariable() {
		var objOne = this.initElement(new AdditionalLocalVariable[] { this.createMinimalALV("alv1") });
		var objTwo = this.initElement(new AdditionalLocalVariable[] { this.createMinimalALV("alv2") });

		this.testSimilarity(objOne, objTwo, VariablesPackage.Literals.LOCAL_VARIABLE__ADDITIONAL_LOCAL_VARIABLES);
	}

	@Test
	public void testLocalVariableSize() {
		var objOne = this.initElement(
				new AdditionalLocalVariable[] { this.createMinimalALV("alv1"), this.createMinimalALV("alv2") });
		var objTwo = this.initElement(new AdditionalLocalVariable[] { this.createMinimalALV("alv1") });

		this.testSimilarity(objOne, objTwo, VariablesPackage.Literals.LOCAL_VARIABLE__ADDITIONAL_LOCAL_VARIABLES);
	}

	@Test
	public void testLocalVariableNullCheck() {
		this.testSimilarityNullCheck(this.initElement(new AdditionalLocalVariable[] { this.createMinimalALV("alv1") }),
				new LocalVariableInitialiser(), false,
				VariablesPackage.Literals.LOCAL_VARIABLE__ADDITIONAL_LOCAL_VARIABLES);
	}
}
