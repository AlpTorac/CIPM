package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.NormalSwitchCase;

import cipm.consistency.fitests.similarity.java.initialiser.IConditionalInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.ISwitchCaseInitialiser;

public interface INormalSwitchCaseInitialiser extends IConditionalInitialiser,
	ISwitchCaseInitialiser {
	
	public default void addAdditionalCondition(NormalSwitchCase nsc, Expression expr) {
		if (expr != null) {
			nsc.getAdditionalConditions().add(expr);
			assert nsc.getAdditionalConditions().contains(expr);
		}
	}
}
