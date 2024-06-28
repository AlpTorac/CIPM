package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.Switch;
import org.emftext.language.java.statements.SwitchCase;

import cipm.consistency.fitests.similarity.java.initialiser.expressions.IUnaryModificationExpressionChildInitialiser;

public interface ISwitchInitialiser extends IStatementInitialiser,
	IUnaryModificationExpressionChildInitialiser {
	
	public default void addCase(Switch sw, SwitchCase sc) {
		if (sc != null) {
			sw.getCases().add(sc);
			assert sw.getCases().contains(sc);
		}
	}
	
	public default void addCases(Switch sw, SwitchCase[] scs) {
		this.addXs(sw, scs, this::addCase);
	}
	
	public default void setVariable(Switch sw, Expression expr) {
		if (expr != null) {
			sw.setVariable(expr);
			assert sw.getVariable().equals(expr);
		}
	}
}
