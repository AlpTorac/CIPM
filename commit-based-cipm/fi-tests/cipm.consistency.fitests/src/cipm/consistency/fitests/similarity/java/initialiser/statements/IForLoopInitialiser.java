package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.ForLoop;
import org.emftext.language.java.statements.ForLoopInitializer;

public interface IForLoopInitialiser extends IConditionalInitialiser,
	IStatementInitialiser,
	IStatementContainerInitialiser {

	public default void setInit(ForLoop fl, ForLoopInitializer fli) {
		if (fli != null) {
			fl.setInit(fli);
			assert fl.getInit().equals(fli);
		}
	}
	
	public default void addUpdate(ForLoop fl, Expression expr) {
		if (expr != null) {
			fl.getUpdates().add(expr);
			assert fl.getUpdates().contains(expr);
		}
	}
	
	public default void addUpdates(ForLoop fl, Expression[] exprs) {
		this.addXs(fl, exprs, this::addUpdate);
	}
}
