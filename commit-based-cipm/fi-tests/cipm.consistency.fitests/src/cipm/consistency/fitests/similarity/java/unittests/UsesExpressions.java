package cipm.consistency.fitests.similarity.java.unittests;

import org.emftext.language.java.expressions.EqualityExpression;
import org.emftext.language.java.expressions.EqualityExpressionChild;
import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.expressions.ExpressionList;

import cipm.consistency.fitests.similarity.java.initialiser.expressions.EqualityExpressionInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.expressions.ExpressionListInitialiser;

/**
 * An interface that can be implemented by tests, which work with
 * {@link Expression} instances. <br>
 * <br>
 * Contains methods that can be used to create {@link Expression} instances.
 */
public interface UsesExpressions extends UsesOperators, UsesLiterals, UsesStringReferences {
	/**
	 * @return A minimal {@link ExpressionList} instance
	 */
	public default ExpressionList createExprList() {
		return new ExpressionListInitialiser().instantiate();
	}

	/**
	 * @param exprs The {@link Expression} instances the constructed instance will
	 *              contain
	 * @return A {@link ExpressionList} instance with the given parameters
	 */
	public default ExpressionList createExprList(Expression... exprs) {
		var elInit = new ExpressionListInitialiser();
		var el = this.createExprList();
		elInit.addExpressions(el, exprs);
		return el;
	}

	/**
	 * A variant of
	 * {@link #createMinimalEE(EqualityExpressionChild, EqualityExpressionChild)}
	 * that equates to {@code 1 == 1} in {@link Expression} form.
	 * 
	 * @see {@link #createDecimalIntegerLiteral(int)}
	 */
	public default EqualityExpression createMinimalTrueEE() {
		return this.createMinimalEE(this.createDecimalIntegerLiteral(1), this.createDecimalIntegerLiteral(1));
	}

	/**
	 * A variant of
	 * {@link #createMinimalEE(EqualityExpressionChild, EqualityExpressionChild)}
	 * that equates to {@code 1 == 2} in {@link Expression} form.
	 * 
	 * @see {@link #createDecimalIntegerLiteral(int)}
	 */
	public default EqualityExpression createMinimalFalseEE() {
		return this.createMinimalEE(this.createDecimalIntegerLiteral(1), this.createDecimalIntegerLiteral(2));
	}

	/**
	 * @param lhs The left hand side of the instance to be constructed
	 * @param rhs The right hand side of the instance to be constructed
	 * @return Constructs an {@link EqualityExpression} instance that represents
	 *         {@code lhs == rhs}
	 */
	public default EqualityExpression createMinimalEE(EqualityExpressionChild lhs, EqualityExpressionChild rhs) {
		var eqInit = new EqualityExpressionInitialiser();

		EqualityExpression result = eqInit.instantiate();
		eqInit.addEqualityOperator(result, this.createEqualityOperator());
		eqInit.addChild(result, lhs);
		eqInit.addChild(result, rhs);

		return result;
	}

	/**
	 * A variant of
	 * {@link #createMinimalNEE(EqualityExpressionChild, EqualityExpressionChild)}
	 * that equates to {@code 1 =/= 1} in {@link Expression} form.
	 * 
	 * @see {@link #createDecimalIntegerLiteral(int)}
	 */
	public default EqualityExpression createMinimalFalseNEE() {
		return this.createMinimalNEE(this.createDecimalIntegerLiteral(1), this.createDecimalIntegerLiteral(1));
	}

	/**
	 * A variant of
	 * {@link #createMinimalNEE(EqualityExpressionChild, EqualityExpressionChild)}
	 * that equates to {@code 1 =/= 2} in {@link Expression} form.
	 * 
	 * @see {@link #createDecimalIntegerLiteral(int)}
	 */
	public default EqualityExpression createMinimalTrueNEE() {
		return this.createMinimalNEE(this.createDecimalIntegerLiteral(1), this.createDecimalIntegerLiteral(2));
	}

	/**
	 * @param lhs The left hand side of the instance to be constructed
	 * @param rhs The right hand side of the instance to be constructed
	 * @return Constructs an {@link EqualityExpression} instance that represents
	 *         {@code lhs =/= rhs}
	 */
	public default EqualityExpression createMinimalNEE(EqualityExpressionChild lhs, EqualityExpressionChild rhs) {
		var eqInit = new EqualityExpressionInitialiser();

		EqualityExpression result = eqInit.instantiate();
		eqInit.addEqualityOperator(result, this.createNotEqualOperator());
		eqInit.addChild(result, lhs);
		eqInit.addChild(result, rhs);

		return result;
	}
}
