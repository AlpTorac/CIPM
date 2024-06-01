package cipm.consistency.fitests.similarity.java.unittests;

import java.math.BigInteger;

import org.emftext.language.java.expressions.EqualityExpression;
import org.emftext.language.java.expressions.EqualityExpressionChild;
import org.emftext.language.java.literals.Literal;

import cipm.consistency.fitests.similarity.java.initialiser.expressions.EqualityExpressionInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.operators.EqualInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.operators.NotEqualInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.params.LiteralFactory;

public interface UsesExpressions {
	/**
	 * @return {@code 1 == 1} in expression form
	 */
	public default EqualityExpression createMinimalTrueEE() {
		return this.createMinimalEE(
				new LiteralFactory().createDecIntegerLiteral(BigInteger.valueOf(1)),
				new LiteralFactory().createDecIntegerLiteral(BigInteger.valueOf(1)));
	}
	
	/**
	 * @return {@code 1 == 2} in expression form
	 */
	public default EqualityExpression createMinimalFalseEE() {
		return this.createMinimalEE(
				new LiteralFactory().createDecIntegerLiteral(BigInteger.valueOf(1)),
				new LiteralFactory().createDecIntegerLiteral(BigInteger.valueOf(2)));
	}
	
	/**
	 * @return {@code lhs == rhs} in expression form
	 */
	public default EqualityExpression createMinimalEE(EqualityExpressionChild lhs,
			EqualityExpressionChild rhs) {
		var eqInit = new EqualityExpressionInitialiser();
		
		EqualityExpression result = eqInit.instantiate();
		eqInit.minimalInitialisation(result);
		eqInit.addEqualityOperator(result, new EqualInitialiser().instantiate());
		eqInit.addChild(result, lhs);
		eqInit.addChild(result, rhs);
		
		return result;
	}
	
	/**
	 * @return {@code 1 =/= 1} in expression form
	 */
	public default EqualityExpression createMinimalFalseNEE() {
		return this.createMinimalNEE(
				new LiteralFactory().createDecIntegerLiteral(BigInteger.valueOf(1)),
				new LiteralFactory().createDecIntegerLiteral(BigInteger.valueOf(1)));
	}
	
	/**
	 * @return {@code 1 =/= 2} in expression form
	 */
	public default EqualityExpression createMinimalTrueNEE() {
		return this.createMinimalNEE(
				new LiteralFactory().createDecIntegerLiteral(BigInteger.valueOf(1)),
				new LiteralFactory().createDecIntegerLiteral(BigInteger.valueOf(2)));
	}
	
	/**
	 * @return {@code lhs =/= rhs} in expression form
	 */
	public default EqualityExpression createMinimalNEE(EqualityExpressionChild lhs,
			EqualityExpressionChild rhs) {
		var eqInit = new EqualityExpressionInitialiser();
		
		EqualityExpression result = eqInit.instantiate();
		eqInit.minimalInitialisation(result);
		eqInit.addEqualityOperator(result, new NotEqualInitialiser().instantiate());
		eqInit.addChild(result, lhs);
		eqInit.addChild(result, rhs);
		
		return result;
	}
	
	public default Literal createInteger(BigInteger val) {
		return new LiteralFactory().createDecIntegerLiteral(val);
	}
	
	public default Literal createInteger(int val) {
		return this.createInteger(BigInteger.valueOf(val));
	}
}
