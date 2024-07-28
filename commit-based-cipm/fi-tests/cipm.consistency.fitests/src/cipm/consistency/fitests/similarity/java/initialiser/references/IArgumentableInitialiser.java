package cipm.consistency.fitests.similarity.java.initialiser.references;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.references.Argumentable;

import cipm.consistency.fitests.similarity.java.initialiser.commons.ICommentableInitialiser;

/**
 * An interface meant for {@link IInitialiser} implementors that are supposed to
 * create {@link Argumentable} instances. <br>
 * <br>
 * <b>{@code argumentable.getArgumentTypes()} cannot be used to modify an
 * attribute</b>
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
		return this.doMultipleModifications(argable, exprs, this::addArgument);
	}
}
