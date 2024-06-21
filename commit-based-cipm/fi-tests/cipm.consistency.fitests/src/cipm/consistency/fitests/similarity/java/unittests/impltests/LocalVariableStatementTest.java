package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.statements.LocalVariableStatement;
import org.emftext.language.java.statements.StatementsPackage;
import org.emftext.language.java.variables.LocalVariable;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.statements.LocalVariableStatementInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesLocalVariables;

public class LocalVariableStatementTest extends EObjectSimilarityTest implements UsesLocalVariables {
	protected LocalVariableStatement initElement(LocalVariable lv) {
		var lvsInit = new LocalVariableStatementInitialiser();
		var lvs = lvsInit.instantiate();
		lvsInit.minimalInitialisation(lvs);
		lvsInit.setVariable(lvs, lv);
		return lvs;
	}
	
	@Test
	public void testVariable() {
		this.setResourceFileTestIdentifier("testVariable");
		
		var objOne = this.initElement(this.createMinimalLV("lv1"));
		var objTwo = this.initElement(this.createMinimalLV("lv2"));
		
		this.testX(objOne, objTwo, StatementsPackage.Literals.LOCAL_VARIABLE_STATEMENT__VARIABLE);
	}
}
