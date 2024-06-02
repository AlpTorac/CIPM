package cipm.consistency.fitests.similarity.java.unittests;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.NormalSwitchCase;

import cipm.consistency.fitests.similarity.java.initialiser.statements.NormalSwitchCaseInitialiser;

public interface UsesSwitchCases extends UsesExpressions {
	public default NormalSwitchCase createMinimalNSC(Expression[] exprs) {
		var nscInit = new NormalSwitchCaseInitialiser();
		var nsc = nscInit.instantiate();
		nscInit.minimalInitialisation(nsc);
		nscInit.addAdditionalConditions(nsc, exprs);
		return nsc;
	}
	
	public default NormalSwitchCase createMinimalNSC() {
		return this.createMinimalNSC(new Expression[] {this.createMinimalFalseEE()});
	}
	
	public default NormalSwitchCase createEmptyNSC() {
		return this.createMinimalNSC(new Expression[] {});
	}
}
