package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.ForLoop;
import org.emftext.language.java.statements.ForLoopInitializer;

public interface IForLoopInitialiser extends IConditionalInitialiser,
	IStatementInitialiser,
	IStatementContainerInitialiser {
	@Override
	public ForLoop instantiate();
	public default boolean setInit(ForLoop fl, ForLoopInitializer fli) {
		if (fli != null) {
			fl.setInit(fli);
			return fl.getInit().equals(fli);
		}
		return true;
	}
	public default boolean addUpdate(ForLoop fl, Expression expr) {
		if (expr != null) {
			fl.getUpdates().add(expr);
			return fl.getUpdates().contains(expr);
		}
		return true;
	}
	
	public default boolean addUpdates(ForLoop fl, Expression[] exprs) {
		return this.addXs(fl, exprs, this::addUpdate);
	}
}
