package cipm.consistency.fitests.similarity.java.unittests.impltests;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.Switch;
import org.emftext.language.java.statements.SwitchCase;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.java.initialiser.statements.SwitchInitialiser;
import cipm.consistency.fitests.similarity.java.unittests.UsesSwitchCases;

public class SwitchTest extends EObjectSimilarityTest implements UsesSwitchCases {
	protected Switch initElement(SwitchCase[] scs, Expression expr) {
		var swInit = new SwitchInitialiser();
		var sw = swInit.instantiate();
		swInit.minimalInitialisation(sw);
		swInit.addCases(sw, scs);
		swInit.setVariable(sw, expr);
		return sw;
	}
	
	@Test
	public void testCase() {
		this.setResourceFileTestIdentifier("testCase");
		
		var objOne = this.initElement(new SwitchCase[] {this.createEmptyNSC()}, null);
		var objTwo = this.initElement(new SwitchCase[] {this.createMinimalNSC()}, null);
		
		this.testX(objOne, objTwo, false);
	}
	
	@Test
	public void testVariable() {
		this.setResourceFileTestIdentifier("testVariable");
		
		var objOne = this.initElement(null, this.createMinimalSR("str1"));
		var objTwo = this.initElement(null, this.createMinimalSR("str2"));
		
		this.testX(objOne, objTwo, false);
	}
}
