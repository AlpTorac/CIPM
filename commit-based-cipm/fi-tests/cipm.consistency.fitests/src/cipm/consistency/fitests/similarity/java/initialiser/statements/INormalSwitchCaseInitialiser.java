package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.NormalSwitchCase;

public interface INormalSwitchCaseInitialiser extends IConditionalInitialiser,
	ISwitchCaseInitialiser {
	@Override
	public NormalSwitchCase instantiate();
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
