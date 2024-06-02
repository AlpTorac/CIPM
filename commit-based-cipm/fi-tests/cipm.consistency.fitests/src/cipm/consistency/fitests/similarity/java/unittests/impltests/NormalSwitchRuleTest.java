package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.NormalSwitchRule;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.statements.NormalSwitchRuleInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesExpressions;

public class NormalSwitchRuleTest extends EObjectSimilarityTest implements UsesExpressions {
	protected NormalSwitchRule initElement(Expression[] exprs) {
		var nsrInit = new NormalSwitchRuleInitialiser();
		var nsr = nsrInit.instantiate();
		nsrInit.minimalInitialisation(nsr);
		nsrInit.addAdditionalConditions(nsr, exprs);
		return nsr;
	}
	
	@Test
	public void testAdditionalCondition() {
		this.setResourceFileTestIdentifier("testAdditionalCondition");
		
		var objOne = this.initElement(new Expression[] {this.createMinimalFalseEE()});
		var objTwo = this.initElement(new Expression[] {this.createMinimalTrueNEE()});
		
		this.compareX(objOne, objTwo, false);
	}
}
