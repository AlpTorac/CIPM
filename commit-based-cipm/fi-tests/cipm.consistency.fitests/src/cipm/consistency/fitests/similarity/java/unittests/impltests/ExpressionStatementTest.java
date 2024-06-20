package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.ExpressionStatement;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.statements.ExpressionStatementInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesExpressions;

public class ExpressionStatementTest extends EObjectSimilarityTest implements UsesExpressions {
	protected ExpressionStatement initElement(Expression expr) {
		var esInit = new ExpressionStatementInitialiser();
		var es = esInit.instantiate();
		esInit.minimalInitialisation(es);
		esInit.setExpression(es, expr);
		return es;
	}
	
	@Test
	public void testExpression() {
		this.setResourceFileTestIdentifier("testExpression");
		
		var objOne = this.initElement(this.createMinimalFalseEE());
		var objTwo = this.initElement(this.createMinimalTrueNEE());
		
		this.testX(objOne, objTwo, false);
	}
}
