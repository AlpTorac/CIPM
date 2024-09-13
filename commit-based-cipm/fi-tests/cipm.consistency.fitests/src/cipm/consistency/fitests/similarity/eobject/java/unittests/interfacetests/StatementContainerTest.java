package cipm.consistency.fitests.similarity.eobject.java.unittests.interfacetests;

import org.emftext.language.java.statements.Statement;
import org.emftext.language.java.statements.StatementContainer;
import org.emftext.language.java.statements.StatementsPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.eobject.java.AbstractEObjectJavaSimilarityTest;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.statements.IStatementContainerInitialiser;
import cipm.consistency.fitests.similarity.eobject.java.unittests.UsesStatements;

public class StatementContainerTest extends AbstractEObjectJavaSimilarityTest implements UsesStatements {
	protected StatementContainer initElement(IStatementContainerInitialiser init, Statement st) {
		StatementContainer result = init.instantiate();
		Assertions.assertTrue(init.initialise(result));
		Assertions.assertTrue(init.setStatement(result, st));
		return result;
	}

	@ParameterizedTest
	@ArgumentsSource(StatementContainerTestParams.class)
	public void testStatement(IStatementContainerInitialiser init) {
		var objOne = this.initElement(init, this.createMinimalNullReturn());
		var objTwo = this.initElement(init, this.createMinimalTrivialAssert());

		this.testSimilarity(objOne, objTwo, StatementsPackage.Literals.STATEMENT_CONTAINER__STATEMENT);
	}

	@ParameterizedTest
	@ArgumentsSource(StatementContainerTestParams.class)
	public void testStatementNullCheck(IStatementContainerInitialiser init) {
		this.testSimilarityNullCheck(this.initElement(init, this.createMinimalNullReturn()), init, true,
				StatementsPackage.Literals.STATEMENT_CONTAINER__STATEMENT);
	}
}
