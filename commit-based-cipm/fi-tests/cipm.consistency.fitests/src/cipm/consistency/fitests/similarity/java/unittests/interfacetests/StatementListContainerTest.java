package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import java.math.BigInteger;

import org.emftext.language.java.statements.Statement;
import org.emftext.language.java.statements.StatementListContainer;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.params.LiteralFactory;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IStatementListContainerInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesStatements;

public class StatementListContainerTest extends EObjectSimilarityTest implements UsesStatements {
	protected StatementListContainer initElement(IStatementListContainerInitialiser initialiser,
			Statement[] sts) {
		StatementListContainer result = initialiser.instantiate();
		initialiser.minimalInitialisationWithContainer(result);
		initialiser.addStatements(result, sts);
		return result;
	}
	
	@ParameterizedTest
	@ArgumentsSource(StatementListContainerTestParams.class)
	public void testStatements(IStatementListContainerInitialiser initialiser) {
		this.setResourceFileTestIdentifier("testStatements");
		
		var objOne = this.initElement(initialiser, new Statement[] {
				this.createMinimalNullReturn()
		});
		var objTwo = this.initElement(initialiser, new Statement[] {
				this.createMinimalReturn(new LiteralFactory().createDecIntegerLiteral(BigInteger.ZERO))
		});
		
		this.testX(objOne, objTwo, false);
	}
}
