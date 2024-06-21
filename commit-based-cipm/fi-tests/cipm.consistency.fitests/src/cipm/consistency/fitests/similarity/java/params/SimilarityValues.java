package cipm.consistency.fitests.similarity.java.params;

import org.emftext.language.java.annotations.AnnotationsPackage;
import org.emftext.language.java.arrays.ArraysPackage;
import org.emftext.language.java.classifiers.ClassifiersPackage;
import org.emftext.language.java.commons.CommonsPackage;
import org.emftext.language.java.containers.ContainersPackage;
import org.emftext.language.java.expressions.ExpressionsPackage;
import org.emftext.language.java.instantiations.InstantiationsPackage;
import org.emftext.language.java.members.MembersPackage;
import org.emftext.language.java.modules.ModulesPackage;
import org.emftext.language.java.parameters.ParametersPackage;
import org.emftext.language.java.references.ReferencesPackage;
import org.emftext.language.java.statements.StatementsPackage;
import org.emftext.language.java.types.TypesPackage;
import org.emftext.language.java.variables.VariablesPackage;

public class SimilarityValues extends AbstractSimilarityValues {
	public SimilarityValues() {
		/*
		 * If a specific attribute and class pair is not specified below with Boolean.TRUE,
 		 * the value is implicitly assumed to be Boolean.FALSE.  
		 */
		this.setDefaultSimilarityResult(Boolean.FALSE);
		
		// Annotations
// 		this.addSimilarityEntry(AnnotationsPackage.Literals.ANNOTABLE__ANNOTATIONS, Boolean.FALSE);
// 		this.addSimilarityEntry(AnnotationsPackage.Literals.ANNOTATION_ATTRIBUTE_SETTING__ATTRIBUTE, Boolean.FALSE);
 		this.addSimilarityEntry(AnnotationsPackage.Literals.ANNOTATION_ATTRIBUTE_SETTING__VALUE, Boolean.TRUE);
// 		this.addSimilarityEntry(AnnotationsPackage.Literals.ANNOTATION_INSTANCE__ANNOTATION, Boolean.FALSE);
 		this.addSimilarityEntry(AnnotationsPackage.Literals.ANNOTATION_INSTANCE__PARAMETER, Boolean.TRUE);
 		this.addSimilarityEntry(AnnotationsPackage.Literals.ANNOTATION_PARAMETER_LIST__SETTINGS, Boolean.TRUE);
 		this.addSimilarityEntry(AnnotationsPackage.Literals.SINGLE_ANNOTATION_PARAMETER__VALUE, Boolean.TRUE);
		
		// Arrays
 		this.addSimilarityEntry(ArraysPackage.Literals.ARRAY_INITIALIZER__INITIAL_VALUES, Boolean.TRUE);
 		this.addSimilarityEntry(ArraysPackage.Literals.ARRAY_INSTANTIATION_BY_SIZE__SIZES, Boolean.TRUE);
 		this.addSimilarityEntry(ArraysPackage.Literals.ARRAY_INSTANTIATION_BY_VALUES__ARRAY_INITIALIZER, Boolean.TRUE);
 		this.addSimilarityEntry(ArraysPackage.Literals.ARRAY_SELECTOR__POSITION, Boolean.TRUE);
 		this.addSimilarityEntry(ArraysPackage.Literals.ARRAY_TYPEABLE__ARRAY_DIMENSIONS_AFTER, Boolean.TRUE);
 		this.addSimilarityEntry(ArraysPackage.Literals.ARRAY_TYPEABLE__ARRAY_DIMENSIONS_BEFORE, Boolean.TRUE);
		
 		// Containers
 		this.addSimilarityEntry(ContainersPackage.Literals.COMPILATION_UNIT__CLASSIFIERS, Boolean.TRUE);
 		this.addSimilarityEntry(ContainersPackage.Literals.MODULE__OPEN, Boolean.TRUE);
 		this.addSimilarityEntry(ContainersPackage.Literals.MODULE__PACKAGES, Boolean.TRUE);
 		this.addSimilarityEntry(ContainersPackage.Literals.PACKAGE__CLASSIFIERS, Boolean.TRUE);
 		this.addSimilarityEntry(ContainersPackage.Literals.PACKAGE__MODULE, Boolean.TRUE);
 		
		// Classifiers
 		this.addSimilarityEntry(ClassifiersPackage.Literals.CLASS__DEFAULT_EXTENDS, Boolean.TRUE);
 		this.addSimilarityEntry(ClassifiersPackage.Literals.CLASS__EXTENDS, Boolean.TRUE);
// 		this.addSimilarityEntry(ClassifiersPackage.Literals.CONCRETE_CLASSIFIER__PACKAGE, Boolean.FALSE);
 		this.addSimilarityEntry(ClassifiersPackage.Literals.ENUMERATION__CONSTANTS, Boolean.TRUE);
// 		this.addSimilarityEntry(ClassifiersPackage.Literals.IMPLEMENTOR__IMPLEMENTS, Boolean.FALSE);
 		this.addSimilarityEntry(ClassifiersPackage.Literals.INTERFACE__DEFAULT_EXTENDS, Boolean.TRUE);
 		this.addSimilarityEntry(ClassifiersPackage.Literals.INTERFACE__EXTENDS, Boolean.TRUE);
		
		// Expressions
// 		this.addSimilarityEntry(ExpressionsPackage.Literals.ADDITIVE_EXPRESSION__CHILDREN, Boolean.FALSE);
// 		this.addSimilarityEntry(ExpressionsPackage.Literals.ADDITIVE_EXPRESSION__ADDITIVE_OPERATORS, Boolean.FALSE);
// 		this.addSimilarityEntry(ExpressionsPackage.Literals.AND_EXPRESSION__CHILDREN, Boolean.FALSE);
// 		this.addSimilarityEntry(ExpressionsPackage.Literals.ASSIGNMENT_EXPRESSION__ASSIGNMENT_OPERATOR, Boolean.FALSE);
// 		this.addSimilarityEntry(ExpressionsPackage.Literals.ASSIGNMENT_EXPRESSION__CHILD, Boolean.FALSE);
// 		this.addSimilarityEntry(ExpressionsPackage.Literals.ASSIGNMENT_EXPRESSION__VALUE, Boolean.FALSE);
		this.addSimilarityEntry(ExpressionsPackage.Literals.CAST_EXPRESSION__ADDITIONAL_BOUNDS, Boolean.TRUE);
		this.addSimilarityEntry(ExpressionsPackage.Literals.CAST_EXPRESSION__GENERAL_CHILD, Boolean.TRUE);
// 		this.addSimilarityEntry(ExpressionsPackage.Literals.CONDITIONAL_AND_EXPRESSION__CHILDREN, Boolean.FALSE);
		this.addSimilarityEntry(ExpressionsPackage.Literals.CONDITIONAL_EXPRESSION__CHILD, Boolean.TRUE);
		this.addSimilarityEntry(ExpressionsPackage.Literals.CONDITIONAL_EXPRESSION__EXPRESSION_IF, Boolean.TRUE);
		this.addSimilarityEntry(ExpressionsPackage.Literals.CONDITIONAL_EXPRESSION__GENERAL_EXPRESSION_ELSE, Boolean.TRUE);
// 		this.addSimilarityEntry(ExpressionsPackage.Literals.CONDITIONAL_OR_EXPRESSION__CHILDREN, Boolean.FALSE);
// 		this.addSimilarityEntry(ExpressionsPackage.Literals.EQUALITY_EXPRESSION__CHILDREN, Boolean.FALSE);
// 		this.addSimilarityEntry(ExpressionsPackage.Literals.EQUALITY_EXPRESSION__EQUALITY_OPERATORS, Boolean.FALSE);
		this.addSimilarityEntry(ExpressionsPackage.Literals.EXCLUSIVE_OR_EXPRESSION__CHILDREN, Boolean.TRUE);
		this.addSimilarityEntry(ExpressionsPackage.Literals.EXPRESSION_LIST__EXPRESSIONS, Boolean.TRUE);
		this.addSimilarityEntry(ExpressionsPackage.Literals.INCLUSIVE_OR_EXPRESSION__CHILDREN, Boolean.TRUE);
// 		this.addSimilarityEntry(ExpressionsPackage.Literals.INSTANCE_OF_EXPRESSION__CHILD, Boolean.FALSE);
// 		this.addSimilarityEntry(ExpressionsPackage.Literals.LAMBDA_EXPRESSION__BODY, Boolean.FALSE);
// 		this.addSimilarityEntry(ExpressionsPackage.Literals.LAMBDA_EXPRESSION__PARAMETERS, Boolean.FALSE);
		this.addSimilarityEntry(ExpressionsPackage.Literals.MULTIPLICATIVE_EXPRESSION__CHILDREN, Boolean.TRUE);
		this.addSimilarityEntry(ExpressionsPackage.Literals.MULTIPLICATIVE_EXPRESSION__MULTIPLICATIVE_OPERATORS, Boolean.TRUE);
// 		this.addSimilarityEntry(ExpressionsPackage.Literals.NESTED_EXPRESSION__EXPRESSION, Boolean.FALSE);
		this.addSimilarityEntry(ExpressionsPackage.Literals.PRIMARY_EXPRESSION_REFERENCE_EXPRESSION__CHILD, Boolean.TRUE);
		this.addSimilarityEntry(ExpressionsPackage.Literals.PRIMARY_EXPRESSION_REFERENCE_EXPRESSION__METHOD_REFERENCE, Boolean.TRUE);
// 		this.addSimilarityEntry(ExpressionsPackage.Literals.RELATION_EXPRESSION__CHILDREN, Boolean.FALSE);
// 		this.addSimilarityEntry(ExpressionsPackage.Literals.RELATION_EXPRESSION__RELATION_OPERATORS, Boolean.FALSE);
		this.addSimilarityEntry(ExpressionsPackage.Literals.SHIFT_EXPRESSION__CHILDREN, Boolean.TRUE);
		this.addSimilarityEntry(ExpressionsPackage.Literals.SHIFT_EXPRESSION__SHIFT_OPERATORS, Boolean.TRUE);
// 		this.addSimilarityEntry(ExpressionsPackage.Literals.UNARY_EXPRESSION__CHILD, Boolean.FALSE);
// 		this.addSimilarityEntry(ExpressionsPackage.Literals.UNARY_EXPRESSION__OPERATORS, Boolean.FALSE);
		this.addSimilarityEntry(ExpressionsPackage.Literals.UNARY_MODIFICATION_EXPRESSION__CHILD, Boolean.TRUE);
		this.addSimilarityEntry(ExpressionsPackage.Literals.UNARY_MODIFICATION_EXPRESSION__OPERATOR, Boolean.TRUE);
		
		// Instantiations
		this.addSimilarityEntry(InstantiationsPackage.Literals.NEW_CONSTRUCTOR_CALL__ANONYMOUS_CLASS, Boolean.TRUE);
		
		
		// Members
		this.addSimilarityEntry(MembersPackage.Literals.ENUM_CONSTANT__ANONYMOUS_CLASS, Boolean.TRUE);
		this.addSimilarityEntry(MembersPackage.Literals.FIELD__ADDITIONAL_FIELDS, Boolean.TRUE);
		this.addSimilarityEntry(MembersPackage.Literals.INTERFACE_METHOD__DEFAULT_VALUE, Boolean.TRUE);
		
		// Modules
		this.addSimilarityEntry(ModulesPackage.Literals.MODULE_REFERENCE__TARGET, Boolean.TRUE);
		this.addSimilarityEntry(ModulesPackage.Literals.PROVIDES_MODULE_DIRECTIVE__SERVICE_PROVIDERS, Boolean.TRUE);
		this.addSimilarityEntry(ModulesPackage.Literals.REQUIRES_MODULE_DIRECTIVE__MODIFIER, Boolean.TRUE);
		
		// Statements
		this.addSimilarityEntry(StatementsPackage.Literals.ASSERT__ERROR_MESSAGE, Boolean.TRUE);
		this.addSimilarityEntry(StatementsPackage.Literals.CONDITION__ELSE_STATEMENT, Boolean.TRUE);
		this.addSimilarityEntry(StatementsPackage.Literals.FOR_EACH_LOOP__COLLECTION, Boolean.TRUE);
		this.addSimilarityEntry(StatementsPackage.Literals.FOR_EACH_LOOP__NEXT, Boolean.TRUE);
		this.addSimilarityEntry(StatementsPackage.Literals.FOR_LOOP__INIT, Boolean.TRUE);
		this.addSimilarityEntry(StatementsPackage.Literals.FOR_LOOP__UPDATES, Boolean.TRUE);
		this.addSimilarityEntry(StatementsPackage.Literals.NORMAL_SWITCH_CASE__ADDITIONAL_CONDITIONS, Boolean.TRUE);
		this.addSimilarityEntry(StatementsPackage.Literals.NORMAL_SWITCH_RULE__ADDITIONAL_CONDITIONS, Boolean.TRUE);
		this.addSimilarityEntry(StatementsPackage.Literals.SWITCH__CASES, Boolean.TRUE);
		this.addSimilarityEntry(StatementsPackage.Literals.THROW__THROWABLE, Boolean.TRUE);
		this.addSimilarityEntry(StatementsPackage.Literals.TRY_BLOCK__CATCH_BLOCKS, Boolean.TRUE);
		this.addSimilarityEntry(StatementsPackage.Literals.TRY_BLOCK__FINALLY_BLOCK, Boolean.TRUE);
		this.addSimilarityEntry(StatementsPackage.Literals.TRY_BLOCK__RESOURCES, Boolean.TRUE);
		this.addSimilarityEntry(StatementsPackage.Literals.YIELD_STATEMENT__YIELD_EXPRESSION, Boolean.TRUE);
		
		// Parameters
		this.addSimilarityEntry(ParametersPackage.Literals.CATCH_PARAMETER__TYPE_REFERENCES, Boolean.TRUE);
		this.addSimilarityEntry(ParametersPackage.Literals.RECEIVER_PARAMETER__OUTER_TYPE_REFERENCE, Boolean.TRUE);
		
		// References
		this.addSimilarityEntry(ReferencesPackage.Literals.PRIMITIVE_TYPE_REFERENCE__PRIMITIVE_TYPE, Boolean.TRUE);
		this.addSimilarityEntry(ReferencesPackage.Literals.SELF_REFERENCE__SELF, Boolean.TRUE);
		this.addSimilarityEntry(ReferencesPackage.Literals.TEXT_BLOCK_REFERENCE__VALUE, Boolean.TRUE);
		
		// Variables
		this.addSimilarityEntry(VariablesPackage.Literals.LOCAL_VARIABLE__ADDITIONAL_LOCAL_VARIABLES, Boolean.TRUE);
	}
}