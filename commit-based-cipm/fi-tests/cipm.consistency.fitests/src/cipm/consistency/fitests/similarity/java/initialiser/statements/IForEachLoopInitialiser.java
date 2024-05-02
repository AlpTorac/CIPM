package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.parameters.OrdinaryParameter;
import org.emftext.language.java.statements.ForEachLoop;

import cipm.consistency.fitests.similarity.java.initialiser.IStatementContainerInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IStatementInitialiser;

public interface IForEachLoopInitialiser extends IStatementInitialiser,
	IStatementContainerInitialiser {

	public default void setCollection(ForEachLoop fel, Expression expr) {
		if (expr != null) {
			fel.setCollection(expr);
			assert fel.getCollection().equals(expr);
		}
	}
	
	public default void setNext(ForEachLoop fel, OrdinaryParameter op) {
		if (op != null) {
			fel.setNext(op);
			assert fel.getNext().equals(op);
		}
	}
}
