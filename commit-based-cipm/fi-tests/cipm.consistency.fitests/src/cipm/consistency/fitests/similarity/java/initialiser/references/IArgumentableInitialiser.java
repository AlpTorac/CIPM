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
    @Override
    public Argumentable instantiate();
	public default boolean addArgument(Argumentable argable, Expression expr) {
		if (expr != null) {
			argable.getArguments().add(expr);
			return argable.getArguments().contains(expr);
		}
		return true;
	}
	
	public default boolean addArguments(Argumentable argable, Expression[] exprs) {
		return this.addXs(argable, exprs, this::addArgument);
	}
}
