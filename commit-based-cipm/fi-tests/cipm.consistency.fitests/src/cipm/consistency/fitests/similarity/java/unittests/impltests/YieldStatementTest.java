package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.StatementsPackage;
import org.emftext.language.java.statements.YieldStatement;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.statements.YieldStatementInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesExpressions;

public class YieldStatementTest extends EObjectSimilarityTest implements UsesExpressions {
	protected YieldStatement initElement(Expression expr) {
		var ysInit = new YieldStatementInitialiser();
		var ys = ysInit.instantiate();
		ysInit.minimalInitialisation(ys);
		ysInit.setYieldExpression(ys, expr);
		return ys;
	}
	
	@Test
	public void testYieldExpression() {
		this.setResourceFileTestIdentifier("testYieldExpression");
		
		var objOne = this.initElement(this.createInteger(1));
		var objTwo = this.initElement(this.createInteger(2));
		
		this.testX(objOne, objTwo, StatementsPackage.Literals.YIELD_STATEMENT__YIELD_EXPRESSION);
	}
}
