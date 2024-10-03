package cipm.consistency.initialisers.jamopp.references;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.references.Argumentable;

import cipm.consistency.initialisers.jamopp.commons.ICommentableInitialiser;

/**
 * An interface meant for {@link IInitialiser} implementors that are supposed to
 * create {@link Argumentable} instances. <br>
 * <br>
 * <b>{@code argumentable.getArgumentTypes()} cannot be used to modify an
 * attribute</b>
 * 
 * @author Alp Torac Genc
 */
public interface IArgumentableInitialiser extends ICommentableInitialiser {
	@Override
	public Argumentable instantiate();

	public default boolean addArgument(Argumentable argable, Expression arg) {
		if (arg != null) {
			argable.getArguments().add(arg);
			return argable.getArguments().contains(arg);
		}
		return true;
	}

	public default boolean addArguments(Argumentable argable, Expression[] args) {
		return this.doMultipleModifications(argable, args, this::addArgument);
	}
}
