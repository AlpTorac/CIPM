package cipm.consistency.fitests.similarity.eobject.initialiser.java.statements;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.parameters.OrdinaryParameter;
import org.emftext.language.java.statements.ForEachLoop;

public interface IForEachLoopInitialiser extends IStatementInitialiser, IStatementContainerInitialiser {
	@Override
	public ForEachLoop instantiate();

	public default boolean setCollection(ForEachLoop fel, Expression col) {
		if (col != null) {
			fel.setCollection(col);
			return fel.getCollection().equals(col);
		}
		return true;
	}

	public default boolean setNext(ForEachLoop fel, OrdinaryParameter next) {
		if (next != null) {
			fel.setNext(next);
			return fel.getNext().equals(next);
		}
		return true;
	}
}
