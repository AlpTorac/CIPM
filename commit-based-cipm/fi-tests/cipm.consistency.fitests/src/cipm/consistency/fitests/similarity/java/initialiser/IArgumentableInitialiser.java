package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.references.Argumentable;
import org.emftext.language.java.types.Type;

public interface IArgumentableInitialiser extends ICommentableInitialiser {
	@Override
	public Argumentable instantiate();
	
	@Override
	public default Argumentable minimalInstantiation() {
		return (Argumentable) ICommentableInitialiser.super.minimalInstantiation();
	}
	
	public default void addArgument(Argumentable argable, Expression expr) {
		if (expr != null) {
			argable.getArguments().add(expr);
			assert argable.getArguments().contains(expr);
		}
	}
	
	public default void addArgumentType(Argumentable argable, Type type) {
		if (type != null) {
			argable.getArgumentTypes().add(type);
			assert argable.getArgumentTypes().contains(type);
		}
	}
}
