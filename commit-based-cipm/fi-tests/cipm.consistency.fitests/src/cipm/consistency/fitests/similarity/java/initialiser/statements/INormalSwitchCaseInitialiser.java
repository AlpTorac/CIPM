package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.NormalSwitchCase;

import cipm.consistency.fitests.similarity.java.initialiser.ModificationMethod;

import org.emftext.language.java.statements.NormalSwitchCase;

public interface INormalSwitchCaseInitialiser extends IConditionalInitialiser,
	ISwitchCaseInitialiser {
	@Override
	public NormalSwitchCase instantiate();
	@ModificationMethod
	public default boolean addAdditionalCondition(NormalSwitchCase nsc, Expression expr) {
		if (expr != null) {
			nsc.getAdditionalConditions().add(expr);
			return nsc.getAdditionalConditions().contains(expr);
		}
		return true;
	}
	
	public default boolean addAdditionalConditions(NormalSwitchCase nsc, Expression[] exprs) {
		return this.addXs(nsc, exprs, this::addAdditionalCondition);
	}
}
