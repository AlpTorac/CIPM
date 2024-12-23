package cipm.consistency.fitests.similarity.jamopp.gens;

import org.emftext.language.java.expressions.AndExpression;
import org.emftext.language.java.expressions.AndExpressionChild;
import org.emftext.language.java.expressions.ExpressionsPackage;
import cipm.consistency.fitests.similarity.jamopp.unittests.UsesExpressions;
import cipm.consistency.initialisers.jamopp.expressions.AndExpressionInitialiser;

public class AndExpressionGen extends AbstractGenerator implements UsesExpressions {
	protected AndExpression initElement(AndExpressionChild[] children) {
		var aeInit = new AndExpressionInitialiser();
		var ae = aeInit.instantiate();
		aeInit.addChildren(ae, children);
		return ae;
	}

	public GeneratorOutput testChild() {
		return this.generateOutput(
				this.initElement(new AndExpressionChild[] { this.createDecimalIntegerLiteral(1) }),
				this.initElement(new AndExpressionChild[] { this.createDecimalIntegerLiteral(2) }),
				ExpressionsPackage.Literals.AND_EXPRESSION__CHILDREN);
	}

	public GeneratorOutput testChildSize() {
		return this.generateOutput(
				this.initElement(
				new AndExpressionChild[] { this.createDecimalIntegerLiteral(1), this.createDecimalIntegerLiteral(2) }),
				this.initElement(new AndExpressionChild[] { this.createDecimalIntegerLiteral(1) }),
				ExpressionsPackage.Literals.AND_EXPRESSION__CHILDREN);
	}

	public GeneratorOutput testChildNullCheck() {
		return this.generateOutput(
				this.initElement(new AndExpressionChild[] { this.createDecimalIntegerLiteral(1) }),
				new AndExpressionInitialiser().instantiate(), ExpressionsPackage.Literals.AND_EXPRESSION__CHILDREN);
	}
}
