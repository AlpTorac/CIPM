package cipm.consistency.fitests.similarity.java.unittests;

import org.emftext.language.java.arrays.ArraySelector;
import org.emftext.language.java.expressions.Expression;

import cipm.consistency.fitests.similarity.java.initialiser.arrays.ArraySelectorInitialiser;

/**
 * An interface that can be implemented by tests, which work with
 * {@link ArraySelector} instances. <br>
 * <br>
 * Contains methods that can be used to create {@link ArraySelector} instances.
 */
public interface UsesArraySelectors extends UsesExpressions {
	/**
	 * @param pos The position of the instance to be constructed
	 * @return An {@link ArraySelector} instance with the given parameter.
	 */
	public default ArraySelector createAS(Expression pos) {
		var init = new ArraySelectorInitialiser();
		var as = init.instantiate();
		init.setPosition(as, pos);
		return as;
	}

	/**
	 * A variant of {@link #createAS(Expression)}, where the given parameter is
	 * wrapped by an {@link IntegerLiteral}.
	 * 
	 * @param idx The position of the instance to be constructed
	 * 
	 * @see {@link #createDecimalIntegerLiteral(int)}
	 */
	public default ArraySelector createMinimalAS(int idx) {
		return this.createAS(this.createDecimalIntegerLiteral(idx));
	}
}
