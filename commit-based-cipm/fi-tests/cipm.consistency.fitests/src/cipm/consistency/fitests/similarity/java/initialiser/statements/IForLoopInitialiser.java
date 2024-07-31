package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.ForLoop;
import org.emftext.language.java.statements.ForLoopInitializer;

public interface IForLoopInitialiser
		extends IConditionalInitialiser, IStatementInitialiser, IStatementContainerInitialiser {
	@Override
	public ForLoop instantiate();

	public default boolean setInit(ForLoop fl, ForLoopInitializer init) {
		if (init != null) {
			fl.setInit(init);
			return fl.getInit().equals(init);
		}
		return true;
	}

	public default boolean addUpdate(ForLoop fl, Expression update) {
		if (update != null) {
			fl.getUpdates().add(update);
			return fl.getUpdates().contains(update);
		}
		return true;
	}

	public default boolean addUpdates(ForLoop fl, Expression[] updates) {
		return this.doMultipleModifications(fl, updates, this::addUpdate);
	}
}
