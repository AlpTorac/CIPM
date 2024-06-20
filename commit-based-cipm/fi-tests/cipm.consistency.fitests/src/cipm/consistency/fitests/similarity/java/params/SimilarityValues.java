package cipm.consistency.fitests.similarity.java.params;

import org.emftext.language.java.expressions.ExpressionsPackage;

public class SimilarityValues extends AbstractSimilarityValues {
	public SimilarityValues() {
		// Expressions
		this.addSimilarityEntry(ExpressionsPackage.Literals.ADDITIVE_EXPRESSION__CHILDREN, Boolean.FALSE);
		this.addSimilarityEntry(ExpressionsPackage.Literals.ADDITIVE_EXPRESSION__ADDITIVE_OPERATORS, Boolean.FALSE);
		this.addSimilarityEntry(ExpressionsPackage.Literals.AND_EXPRESSION__CHILDREN, Boolean.FALSE);
		this.addSimilarityEntry(ExpressionsPackage.Literals.ASSIGNMENT_EXPRESSION__ASSIGNMENT_OPERATOR, Boolean.FALSE);
		this.addSimilarityEntry(ExpressionsPackage.Literals.ASSIGNMENT_EXPRESSION__CHILD, Boolean.FALSE);
		this.addSimilarityEntry(ExpressionsPackage.Literals.ASSIGNMENT_EXPRESSION__VALUE, Boolean.FALSE);
		this.addSimilarityEntry(ExpressionsPackage.Literals.CAST_EXPRESSION__ADDITIONAL_BOUNDS, Boolean.TRUE);
		this.addSimilarityEntry(ExpressionsPackage.Literals.CAST_EXPRESSION__GENERAL_CHILD, Boolean.TRUE);
		this.addSimilarityEntry(ExpressionsPackage.Literals.CONDITIONAL_AND_EXPRESSION__CHILDREN, Boolean.FALSE);
		this.addSimilarityEntry(ExpressionsPackage.Literals.CONDITIONAL_EXPRESSION__CHILD, Boolean.TRUE);
		this.addSimilarityEntry(ExpressionsPackage.Literals.CONDITIONAL_EXPRESSION__EXPRESSION_IF, Boolean.TRUE);
		this.addSimilarityEntry(ExpressionsPackage.Literals.CONDITIONAL_EXPRESSION__GENERAL_EXPRESSION_ELSE, Boolean.TRUE);
		this.addSimilarityEntry(ExpressionsPackage.Literals.CONDITIONAL_OR_EXPRESSION__CHILDREN, Boolean.FALSE);
		this.addSimilarityEntry(ExpressionsPackage.Literals.EQUALITY_EXPRESSION__CHILDREN, Boolean.FALSE);
		this.addSimilarityEntry(ExpressionsPackage.Literals.EQUALITY_EXPRESSION__EQUALITY_OPERATORS, Boolean.FALSE);
		this.addSimilarityEntry(ExpressionsPackage.Literals.EXCLUSIVE_OR_EXPRESSION__CHILDREN, Boolean.TRUE);
		this.addSimilarityEntry(ExpressionsPackage.Literals.EXPRESSION_LIST__EXPRESSIONS, Boolean.TRUE);
		this.addSimilarityEntry(ExpressionsPackage.Literals.INCLUSIVE_OR_EXPRESSION__CHILDREN, Boolean.TRUE);
		this.addSimilarityEntry(ExpressionsPackage.Literals.INSTANCE_OF_EXPRESSION__CHILD, Boolean.FALSE);
		this.addSimilarityEntry(ExpressionsPackage.Literals.LAMBDA_EXPRESSION__BODY, Boolean.FALSE);
		this.addSimilarityEntry(ExpressionsPackage.Literals.LAMBDA_EXPRESSION__PARAMETERS, Boolean.FALSE);
		this.addSimilarityEntry(ExpressionsPackage.Literals.MULTIPLICATIVE_EXPRESSION__CHILDREN, Boolean.TRUE);
		this.addSimilarityEntry(ExpressionsPackage.Literals.MULTIPLICATIVE_EXPRESSION__MULTIPLICATIVE_OPERATORS, Boolean.TRUE);
		this.addSimilarityEntry(ExpressionsPackage.Literals.NESTED_EXPRESSION__EXPRESSION, Boolean.FALSE);
		this.addSimilarityEntry(ExpressionsPackage.Literals.PRIMARY_EXPRESSION_REFERENCE_EXPRESSION__CHILD, Boolean.TRUE);
		this.addSimilarityEntry(ExpressionsPackage.Literals.PRIMARY_EXPRESSION_REFERENCE_EXPRESSION__METHOD_REFERENCE, Boolean.TRUE);
		this.addSimilarityEntry(ExpressionsPackage.Literals.RELATION_EXPRESSION__CHILDREN, Boolean.FALSE);
		this.addSimilarityEntry(ExpressionsPackage.Literals.RELATION_EXPRESSION__RELATION_OPERATORS, Boolean.FALSE);
		this.addSimilarityEntry(ExpressionsPackage.Literals.SHIFT_EXPRESSION__CHILDREN, Boolean.TRUE);
		this.addSimilarityEntry(ExpressionsPackage.Literals.SHIFT_EXPRESSION__SHIFT_OPERATORS, Boolean.TRUE);
		this.addSimilarityEntry(ExpressionsPackage.Literals.UNARY_EXPRESSION__CHILD, Boolean.FALSE);
		this.addSimilarityEntry(ExpressionsPackage.Literals.UNARY_EXPRESSION__OPERATORS, Boolean.FALSE);
		this.addSimilarityEntry(ExpressionsPackage.Literals.UNARY_MODIFICATION_EXPRESSION__CHILD, Boolean.TRUE);
		this.addSimilarityEntry(ExpressionsPackage.Literals.UNARY_MODIFICATION_EXPRESSION__OPERATOR, Boolean.TRUE);
	}
}