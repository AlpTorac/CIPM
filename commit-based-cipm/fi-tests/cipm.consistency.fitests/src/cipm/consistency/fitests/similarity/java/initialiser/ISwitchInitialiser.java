package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.Switch;
import org.emftext.language.java.statements.SwitchCase;

public interface ISwitchInitialiser extends IStatementInitialiser,
	IUnaryModificationExpressionChildInitialiser {
	
	public default void addCase(Switch sw, SwitchCase sc) {
		if (sc != null) {
			sw.getCases().add(sc);
			assert sw.getCases().contains(sc);
		}
	}
	
	public default void setVariable(Switch sw, Expression expr) {
		if (expr != null) {
			sw.setVariable(expr);
			assert sw.getVariable().equals(expr);
		}
	}
}
