package cipm.consistency.fitests.similarity.jamopp.unittests.impltests;

import org.emftext.language.java.statements.Condition;
import org.emftext.language.java.statements.StatementsPackage;
import org.emftext.language.java.statements.Statement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesStatements;
import cipm.consistency.initialisers.jamopp.statements.ConditionInitialiser;

public class ConditionTest extends AbstractJaMoPPSimilarityTest implements UsesStatements {
	private Statement elseSt1;
	private Statement elseSt2;

	protected Condition initElement(Statement elseSt) {
		var conInit = new ConditionInitialiser();
		var con = conInit.instantiate();
		Assertions.assertTrue(conInit.setElseStatement(con, elseSt));
		return con;
	}

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		elseSt1 = this.createMinimalTrivialAssert();
		elseSt2 = this.createMinimalNullReturn();
		Assertions.assertFalse(this.isSimilar(elseSt1, elseSt2));
	}

	@Test
	public void testElseStatement() {
		var objOne = this.initElement(this.cloneEObjWithContainers(elseSt1));
		var objTwo = this.initElement(this.cloneEObjWithContainers(elseSt2));

		this.testSimilarity(objOne, objTwo, StatementsPackage.Literals.CONDITION__ELSE_STATEMENT);
	}

	@Test
	public void testElseStatementNullCheck() {
		this.testSimilarityNullCheck(this.initElement(this.cloneEObjWithContainers(elseSt1)),
				new ConditionInitialiser(), false, StatementsPackage.Literals.CONDITION__ELSE_STATEMENT);
	}
}
