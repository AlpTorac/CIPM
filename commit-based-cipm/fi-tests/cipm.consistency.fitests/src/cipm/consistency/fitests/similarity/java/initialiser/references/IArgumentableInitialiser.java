package cipm.consistency.fitests.similarity.java.initialiser.references;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.references.Argumentable;

import cipm.consistency.fitests.similarity.java.initialiser.commons.ICommentableInitialiser;

/**
 * 
 * getArgumentTypes() does not modify an attribute
 * 
 * @author atora
 */
public interface IArgumentableInitialiser extends ICommentableInitialiser {
	public default void addArgument(Argumentable argable, Expression expr) {
		if (expr != null) {
			argable.getArguments().add(expr);
			assert argable.getArguments().contains(expr);
		}
	}
	
	public default void addArguments(Argumentable argable, Expression[] exprs) {
		this.addXs(argable, exprs, this::addArgument);
	}
}
