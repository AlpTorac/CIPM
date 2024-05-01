package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.NormalSwitchCase;

public interface INormalSwitchCaseInitialiser extends IConditionalInitialiser,
	ISwitchCaseInitialiser {
	
	public default void addAdditionalCondition(NormalSwitchCase nsc, Expression expr) {
		if (expr != null) {
			nsc.getAdditionalConditions().add(expr);
			assert nsc.getAdditionalConditions().contains(expr);
		}
	}
}
