package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.statements.Condition;
import org.emftext.language.java.statements.StatementsPackage;
import org.emftext.language.java.statements.Statement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.statements.ConditionInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesStatements;

public class ConditionTest extends EObjectSimilarityTest implements UsesStatements {
	protected Condition initElement(Statement st) {
		var conInit = new ConditionInitialiser();
		var con = conInit.instantiate();
		Assertions.assertTrue(conInit.minimalInitialisation(con));
		Assertions.assertTrue(conInit.setElseStatement(con, st));
		return con;
	}
	
	@Test
	public void testElseStatement() {
		this.setResourceFileTestIdentifier("testElseStatement");
		
		var objOne = this.initElement(this.createMinimalTrivialAssert());
		var objTwo = this.initElement(this.createMinimalNullReturn());
		
		this.testX(objOne, objTwo, StatementsPackage.Literals.CONDITION__ELSE_STATEMENT);
	}
}
