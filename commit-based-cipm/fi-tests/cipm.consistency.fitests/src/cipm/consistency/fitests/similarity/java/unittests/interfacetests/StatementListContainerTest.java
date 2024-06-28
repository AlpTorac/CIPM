package cipm.consistency.fitests.similarity.java.unittests.interfacetests;

import org.emftext.language.java.statements.Statement;
import org.emftext.language.java.statements.StatementListContainer;
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
	
	// FIXME: Implement missing impltests (statements work differently for each one)
	
	protected StatementListContainer initElement(IStatementListContainerInitialiser initialiser,
			Statement[] sts) {
		StatementListContainer result = initialiser.instantiate();
		initialiser.minimalInitialisationWithContainer(result);
		initialiser.addStatements(result, sts);
		return result;
	}

	// TODO: Clean up if useless
	
	@ParameterizedTest
	@ArgumentsSource(StatementListContainerTestParams.class)
	public void testStatements(IStatementListContainerInitialiser initialiser) {
		this.setResourceFileTestIdentifier("testStatements");
		
		var objOne = this.initElement(initialiser, new Statement[] {
				this.createMinimalNullReturn()
		});
		var objTwo = this.initElement(initialiser, new Statement[] {
				this.createMinimalTrivialAssert()
		});
		
		this.testX(objOne, objTwo, false);
	}
}
