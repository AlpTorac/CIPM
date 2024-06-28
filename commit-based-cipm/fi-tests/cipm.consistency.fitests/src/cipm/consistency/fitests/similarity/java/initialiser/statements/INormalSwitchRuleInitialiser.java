package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.NormalSwitchRule;

public interface INormalSwitchRuleInitialiser extends IConditionalInitialiser,
	ISwitchRuleInitialiser {
	
	public default void addAdditionalCondition(NormalSwitchRule nsr, Expression expr) {
		if (expr != null) {
			nsr.getAdditionalConditions().add(expr);
			assert nsr.getAdditionalConditions().contains(expr);
		}
	}
	
	public default void addAdditionalConditions(NormalSwitchRule nsr, Expression[] exprs) {
		this.addXs(nsr, exprs, this::addAdditionalCondition);
	}
}
