package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.parameters.OrdinaryParameter;
import org.emftext.language.java.statements.ForEachLoop;

public interface IForEachLoopInitialiser extends IStatementInitialiser,
	IStatementContainerInitialiser {

	public default boolean setCollection(ForEachLoop fel, Expression expr) {
		if (expr != null) {
			fel.setCollection(expr);
			return fel.getCollection().equals(expr);
		}
		return false;
	}
	
	public default boolean setNext(ForEachLoop fel, OrdinaryParameter op) {
		if (op != null) {
			fel.setNext(op);
			return fel.getNext().equals(op);
		}
		return false;
	}
}
