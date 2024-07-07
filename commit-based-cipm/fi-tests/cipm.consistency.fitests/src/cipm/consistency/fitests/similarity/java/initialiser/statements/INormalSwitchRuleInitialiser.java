package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.NormalSwitchRule;

import cipm.consistency.fitests.similarity.java.initialiser.ModificationMethod;

import org.emftext.language.java.statements.NormalSwitchRule;

public interface INormalSwitchRuleInitialiser extends IConditionalInitialiser,
	ISwitchRuleInitialiser {
	@Override
	public NormalSwitchRule instantiate();
	@ModificationMethod
	public default boolean addAdditionalCondition(NormalSwitchRule nsr, Expression expr) {
		if (expr != null) {
			nsr.getAdditionalConditions().add(expr);
			return nsr.getAdditionalConditions().contains(expr);
		}
		return true;
	}
	
	public default boolean addAdditionalConditions(NormalSwitchRule nsr, Expression[] exprs) {
		return this.addXs(nsr, exprs, this::addAdditionalCondition);
	}
}
