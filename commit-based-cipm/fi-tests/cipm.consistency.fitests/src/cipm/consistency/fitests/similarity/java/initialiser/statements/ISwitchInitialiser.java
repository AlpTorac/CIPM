package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.Switch;
import org.emftext.language.java.statements.SwitchCase;

import cipm.consistency.fitests.similarity.java.initialiser.ModificationMethod;
import cipm.consistency.fitests.similarity.java.initialiser.expressions.IUnaryModificationExpressionChildInitialiser;

public interface ISwitchInitialiser extends IStatementInitialiser,
	IUnaryModificationExpressionChildInitialiser {
	@Override
	public Switch instantiate();
	@ModificationMethod
	public default boolean addCase(Switch sw, SwitchCase sc) {
		if (sc != null) {
			sw.getCases().add(sc);
			return sw.getCases().contains(sc);
		}
		return true;
	}
	
	public default boolean addCases(Switch sw, SwitchCase[] scs) {
		return this.addXs(sw, scs, this::addCase);
	}
	@ModificationMethod
	public default boolean setVariable(Switch sw, Expression expr) {
		if (expr != null) {
			sw.setVariable(expr);
			return sw.getVariable().equals(expr);
		}
		return true;
	}
}
