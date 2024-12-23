package cipm.consistency.fitests.similarity.jamopp.gens;

import org.emftext.language.java.expressions.AdditiveExpression;
import org.emftext.language.java.expressions.AdditiveExpressionChild;
import org.emftext.language.java.expressions.ExpressionsPackage;
import org.emftext.language.java.operators.AdditiveOperator;

import cipm.consistency.fitests.similarity.jamopp.unittests.UsesExpressions;
import cipm.consistency.initialisers.jamopp.expressions.AdditiveExpressionInitialiser;

public class AdditiveExpressionGen extends AbstractGenerator implements UsesExpressions {
	protected AdditiveExpression initElement(AdditiveExpressionChild[] children, AdditiveOperator[] ops) {
		var aeInit = new AdditiveExpressionInitialiser();
		var ae = aeInit.instantiate();
		aeInit.addChildren(ae, children);
		aeInit.addAdditiveOperators(ae, ops);
		return ae;
	}

	public GeneratorOutput testChild() {
		return this.generateOutput(
				this.initElement(new AdditiveExpressionChild[] { this.createDecimalIntegerLiteral(1) }, null),
				this.initElement(new AdditiveExpressionChild[] { this.createDecimalIntegerLiteral(2) }, null),
				ExpressionsPackage.Literals.ADDITIVE_EXPRESSION__CHILDREN);
	}

	public GeneratorOutput testChildSize() {
		return this.generateOutput(
				this.initElement(new AdditiveExpressionChild[] { this.createDecimalIntegerLiteral(1),
						this.createDecimalIntegerLiteral(2) }, null),
				this.initElement(new AdditiveExpressionChild[] { this.createDecimalIntegerLiteral(1) }, null),
				ExpressionsPackage.Literals.ADDITIVE_EXPRESSION__CHILDREN);
	}

	public GeneratorOutput testChildNullCheck() {
		return this.generateOutput(
				this.initElement(
						new AdditiveExpressionChild[] { this.createDecimalIntegerLiteral(1) }, null),
				new AdditiveExpressionInitialiser().instantiate(),
				ExpressionsPackage.Literals.ADDITIVE_EXPRESSION__CHILDREN);
	}

	public GeneratorOutput testAdditiveOperator() {
		return this.generateOutput(
				this.initElement(null, new AdditiveOperator[] { this.createAdditionOperator() }),
				this.initElement(null, new AdditiveOperator[] { this.createSubtractionOperator() }),
				ExpressionsPackage.Literals.ADDITIVE_EXPRESSION__ADDITIVE_OPERATORS);
	}

	public GeneratorOutput testAdditiveOperatorSize() {
		return this.generateOutput(
				this.initElement(null,
						new AdditiveOperator[] { this.createAdditionOperator(), this.createSubtractionOperator() }),
				this.initElement(null, new AdditiveOperator[] { this.createAdditionOperator() }),
				ExpressionsPackage.Literals.ADDITIVE_EXPRESSION__ADDITIVE_OPERATORS);
	}

	public GeneratorOutput testAdditiveOperatorNullCheck() {
		return this.generateOutput(
				this.initElement(null, new AdditiveOperator[] { this.createAdditionOperator() }),
				new AdditiveExpressionInitialiser().instantiate(),
				ExpressionsPackage.Literals.ADDITIVE_EXPRESSION__ADDITIVE_OPERATORS);
	}
}
