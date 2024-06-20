package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.variables.AdditionalLocalVariable;
import org.emftext.language.java.variables.LocalVariable;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.variables.LocalVariableInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesAdditionalLocalVariables;

public class LocalVariableTest extends EObjectSimilarityTest implements UsesAdditionalLocalVariables {
	protected LocalVariable initElement(AdditionalLocalVariable[] alvs) {
		var lvInit = new LocalVariableInitialiser();
		var lv = lvInit.instantiate();
		lvInit.minimalInitialisation(lv);
		lvInit.addAdditionalLocalVariables(lv, alvs);
		return lv;
	}
	
	@Test
	public void testLocalVariable() {
		this.setResourceFileTestIdentifier("testLocalVariable");
		
		var objOne = this.initElement(new AdditionalLocalVariable[] {this.createMinimalALV("alv1")});
		var objTwo = this.initElement(new AdditionalLocalVariable[] {this.createMinimalALV("alv2")});
		
		this.testX(objOne, objTwo, false);
	}
}
