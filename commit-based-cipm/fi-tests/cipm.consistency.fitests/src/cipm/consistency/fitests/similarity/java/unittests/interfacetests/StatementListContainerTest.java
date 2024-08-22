package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import org.emftext.language.java.statements.Statement;
import org.emftext.language.java.statements.StatementListContainer;
import org.emftext.language.java.statements.StatementsPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.statements.IStatementListContainerInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesStatements;

/**
 * StatementListContainer has no attributes itself.
 * 
 * @author atora
 */
public class StatementListContainerTest extends EObjectSimilarityTest implements UsesStatements {
	protected StatementListContainer initElement(IStatementListContainerInitialiser init, Statement[] sts) {

		StatementListContainer result = init.instantiate();
		Assertions.assertTrue(init.initialise(result));

		var addStatementsRes = init.addStatements(result, sts);
		Assertions.assertEquals(init.canContainStatements(result), addStatementsRes);

		return result;
	}

	@ParameterizedTest
	@ArgumentsSource(StatementListContainerTestParams.class)
	public void testStatements(IStatementListContainerInitialiser init) {
		this.setCurrentInitialiser(init);
		this.setResourceFileTestIdentifier("testStatements");

		var objOne = this.initElement(init, new Statement[] { this.createMinimalNullReturn() });
		var objTwo = this.initElement(init, new Statement[] { this.createMinimalTrivialAssert() });

		this.testSimilarity(objOne, objTwo,
				this.getExpectedSimilarityResult(StatementsPackage.Literals.BLOCK__STATEMENTS).booleanValue()
						|| (!init.canContainStatements(objOne) && !init.canContainStatements(objTwo)));
	}
	
	@ParameterizedTest
	@ArgumentsSource(StatementListContainerTestParams.class)
	public void testStatementsNull(IStatementListContainerInitialiser init) {
		this.setCurrentInitialiser(init);
		this.setResourceFileTestIdentifier("testStatementsNull");
		
		var objOne = this.initElement(init, new Statement[] { this.createMinimalNullReturn() });
		var objTwo = init.instantiate();
		Assertions.assertTrue(init.initialise(objTwo));
		
		this.testSimilarity(objOne, objTwo,
				this.getExpectedSimilarityResult(StatementsPackage.Literals.BLOCK__STATEMENTS).booleanValue()
				|| (!init.canContainStatements(objOne) && !init.canContainStatements(objTwo)));
	}
}
