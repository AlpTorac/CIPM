package cipm.consistency.fitests.similarity.java.unittests;

import org.emftext.language.java.expressions.EqualityExpression;
import org.emftext.language.java.expressions.EqualityExpressionChild;
import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.expressions.ExpressionList;

import cipm.consistency.fitests.similarity.java.initialiser.expressions.EqualityExpressionInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.expressions.ExpressionListInitialiser;

public interface UsesExpressions extends UsesOperators, UsesLiterals, UsesStringReferences {
	public default ExpressionList createExprList() {
		return new ExpressionListInitialiser().instantiate();
	}

	public default ExpressionList createExprList(Expression... exprs) {
		var elInit = new ExpressionListInitialiser();
		var el = this.createExprList();
		elInit.addExpressions(el, exprs);
		return el;
	}

	/**
	 * @return {@code 1 == 1} in expression form
	 */
	public default EqualityExpression createMinimalTrueEE() {
		return this.createMinimalEE(this.createDecimalIntegerLiteral(1), this.createDecimalIntegerLiteral(1));
	}

	/**
	 * @return {@code 1 == 2} in expression form
	 */
	public default EqualityExpression createMinimalFalseEE() {
		return this.createMinimalEE(this.createDecimalIntegerLiteral(1), this.createDecimalIntegerLiteral(2));
	}

	/**
	 * @return {@code lhs == rhs} in expression form
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
	 * @return {@code 1 =/= 1} in expression form
	 */
	public default EqualityExpression createMinimalFalseNEE() {
		return this.createMinimalNEE(this.createDecimalIntegerLiteral(1), this.createDecimalIntegerLiteral(1));
	}

	/**
	 * @return {@code 1 =/= 2} in expression form
	 */
	public default EqualityExpression createMinimalTrueNEE() {
		return this.createMinimalNEE(this.createDecimalIntegerLiteral(1), this.createDecimalIntegerLiteral(2));
	}

	/**
	 * @return {@code lhs =/= rhs} in expression form
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
