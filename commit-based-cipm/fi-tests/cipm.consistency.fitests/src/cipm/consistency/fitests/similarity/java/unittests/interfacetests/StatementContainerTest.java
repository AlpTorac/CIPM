package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import org.emftext.language.java.statements.Statement;
import org.emftext.language.java.statements.StatementContainer;
import org.emftext.language.java.statements.StatementsPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.statements.IStatementContainerInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesStatements;

public class StatementContainerTest extends EObjectSimilarityTest implements UsesStatements {
	protected StatementContainer initElement(IStatementContainerInitialiser init, Statement st) {
		StatementContainer result = init.instantiate();
		init.minimalInitialisationWithContainer(result);
		Assertions.assertTrue(init.setStatement(result, st));
		return result;
	}
	
	@ParameterizedTest
	@ArgumentsSource(StatementContainerTestParams.class)
	public void testStatement(IStatementContainerInitialiser init) {
		this.setResourceFileTestIdentifier("testStatement");
		
		var objOne = this.initElement(init, this.createMinimalNullReturn());
		var objTwo = this.initElement(init, this.createMinimalTrivialAssert());
		
		this.testX(objOne, objTwo, StatementsPackage.Literals.STATEMENT_CONTAINER__STATEMENT);
	}
}
