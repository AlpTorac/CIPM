package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.NormalSwitchRule;

import cipm.consistency.fitests.similarity.java.initialiser.IConditionalInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.ISwitchRuleInitialiser;

public interface INormalSwitchRuleInitialiser extends IConditionalInitialiser,
	ISwitchRuleInitialiser {
	
	public default void addAdditionalConditions(NormalSwitchRule nsr, Expression expr) {
		if (expr != null) {
			nsr.getAdditionalConditions().add(expr);
			assert nsr.getAdditionalConditions().contains(expr);
		}
	}
}
