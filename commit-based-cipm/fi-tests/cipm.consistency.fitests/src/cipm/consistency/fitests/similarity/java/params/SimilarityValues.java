package cipm.consistency.fitests.similarity.java.params;

import org.emftext.language.java.annotations.AnnotationsPackage;
import org.emftext.language.java.arrays.ArrayInstantiation;
import org.emftext.language.java.arrays.ArraysPackage;
import org.emftext.language.java.classifiers.ClassifiersPackage;
import org.emftext.language.java.commons.CommonsPackage;
import org.emftext.language.java.containers.ContainersPackage;
import org.emftext.language.java.containers.Module;
import org.emftext.language.java.containers.EmptyModel;
import org.emftext.language.java.containers.Package;
import org.emftext.language.java.expressions.ExplicitlyTypedLambdaParameters;
import org.emftext.language.java.expressions.ExpressionsPackage;
import org.emftext.language.java.expressions.ImplicitlyTypedLambdaParameters;
import org.emftext.language.java.expressions.InstanceOfExpression;
import org.emftext.language.java.expressions.NestedExpression;
import org.emftext.language.java.expressions.SingleImplicitLambdaParameter;
import org.emftext.language.java.generics.GenericsPackage;
import org.emftext.language.java.generics.QualifiedTypeArgument;
import org.emftext.language.java.imports.ClassifierImport;
import org.emftext.language.java.imports.ImportsPackage;
import org.emftext.language.java.imports.PackageImport;
import org.emftext.language.java.imports.StaticClassifierImport;
import org.emftext.language.java.instantiations.Instantiation;
import org.emftext.language.java.instantiations.InstantiationsPackage;
import org.emftext.language.java.instantiations.NewConstructorCall;
import org.emftext.language.java.instantiations.NewConstructorCallWithInferredTypeArguments;
import org.emftext.language.java.members.AdditionalField;
import org.emftext.language.java.members.EnumConstant;
import org.emftext.language.java.members.MembersPackage;
import org.emftext.language.java.modifiers.ModifiersPackage;
import org.emftext.language.java.modules.ModulesPackage;
import org.emftext.language.java.modules.ProvidesModuleDirective;
import org.emftext.language.java.modules.UsesModuleDirective;
import org.emftext.language.java.parameters.ParametersPackage;
import org.emftext.language.java.references.MethodCall;
import org.emftext.language.java.references.PackageReference;
import org.emftext.language.java.references.PrimitiveTypeReference;
import org.emftext.language.java.references.ReferencesPackage;
import org.emftext.language.java.references.ReflectiveClassReference;
import org.emftext.language.java.references.SelfReference;
import org.emftext.language.java.references.StringReference;
import org.emftext.language.java.references.TextBlockReference;
import org.emftext.language.java.statements.Block;
import org.emftext.language.java.statements.StatementListContainer;
import org.emftext.language.java.statements.StatementsPackage;
import org.emftext.language.java.types.TypesPackage;
import org.emftext.language.java.variables.VariablesPackage;

/**
 * Contains expected similarity values for tests, in which a certain
 * attribute is different.
 * <br><br>
 * Entries for sub-interfaces will take precedence
 * over their parent interfaces. If the behaviour of a certain sub-interface
 * differs from its parents' and/or sibling interfaces, an entry can
 * be created to account for it. This spares having to create explicit entries for
 * everything in a part of the hierarchy, if there are small deviations.
 * <br><br>
 * To spare creating entries for every possibility, a default similarity value can be defined
 * by using {@link #setDefaultSimilarityResult(Boolean)}. If there are no entries for a specific
 * attribute, the default similarity value will be assumed. The default value is null, if
 * not explicitly defined.
 * 
 * @see {@link ISimilarityValues}, {@link AbstractSimilarityValues}
 */
public class SimilarityValues extends AbstractSimilarityValues {
	public SimilarityValues() {
		/*
		 * setDefaultSimilarityResult(val): If there are no addSimilarityEntry calls
		 * below for a certain attribute, the similarity value will be val.
		 * 
		 * addSimilarityEntry(attr, val): If two EObject instances eo1 and eo2 are
		 * compared where attr in eo1 and eo2 is different, the similarity value
		 * should be val.
		 * 
		 * addSimilarityEntry(ifc, attr, val): Same as addSimilarityEntry(attr, val), but
		 * takes precedence over attr.getContainerClass(), if both EObject
		 * instances implement the interface ifc. If there are multiple such entries for
		 * the same hierarchy, the entry with the most specific ifc will be used to
		 * determine the similarity value.
		 */
		
		this.setDefaultSimilarityResult(Boolean.FALSE);

		// Annotations
		this.addSimilarityEntry(AnnotationsPackage.Literals.ANNOTABLE__ANNOTATIONS, Boolean.TRUE);
		this.addSimilarityEntry(AnnotationsPackage.Literals.ANNOTATION_ATTRIBUTE_SETTING__VALUE, Boolean.TRUE);
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
		this.addSimilarityEntry(ContainersPackage.Literals.JAVA_ROOT__ORIGIN, Boolean.TRUE);

		// Commons
		this.addSimilarityEntry(EmptyModel.class, CommonsPackage.Literals.NAMED_ELEMENT__NAME, Boolean.TRUE);
		this.addSimilarityEntry(AdditionalField.class, CommonsPackage.Literals.NAMED_ELEMENT__NAME, Boolean.TRUE);
		this.addSimilarityEntry(Package.class, CommonsPackage.Literals.NAMED_ELEMENT__NAME, Boolean.TRUE);
		this.addSimilarityEntry(PackageReference.class, CommonsPackage.Literals.NAMED_ELEMENT__NAME, Boolean.TRUE);
		this.addSimilarityEntry(Block.class, CommonsPackage.Literals.NAMED_ELEMENT__NAME, Boolean.TRUE);
		this.addSimilarityEntry(EmptyModel.class, CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES,
				Boolean.TRUE);
		this.addSimilarityEntry(PackageImport.class, CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES,
				Boolean.TRUE);
		this.addSimilarityEntry(StaticClassifierImport.class,
				CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES, Boolean.TRUE);
		this.addSimilarityEntry(PackageReference.class, CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES,
				Boolean.TRUE);
		this.addSimilarityEntry(Module.class, CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES,
				Boolean.TRUE);

		// Classifiers
		this.addSimilarityEntry(ClassifiersPackage.Literals.CLASS__DEFAULT_EXTENDS, Boolean.TRUE);
		this.addSimilarityEntry(ClassifiersPackage.Literals.CLASS__EXTENDS, Boolean.TRUE);
		this.addSimilarityEntry(ClassifiersPackage.Literals.CONCRETE_CLASSIFIER__PACKAGE, Boolean.TRUE);
		this.addSimilarityEntry(ClassifiersPackage.Literals.ENUMERATION__CONSTANTS, Boolean.TRUE);
		this.addSimilarityEntry(ClassifiersPackage.Literals.IMPLEMENTOR__IMPLEMENTS, Boolean.TRUE);
		this.addSimilarityEntry(ClassifiersPackage.Literals.INTERFACE__DEFAULT_EXTENDS, Boolean.TRUE);
		this.addSimilarityEntry(ClassifiersPackage.Literals.INTERFACE__EXTENDS, Boolean.TRUE);

		// Expressions
		this.addSimilarityEntry(ExpressionsPackage.Literals.CAST_EXPRESSION__ADDITIONAL_BOUNDS, Boolean.TRUE);
		this.addSimilarityEntry(ExpressionsPackage.Literals.CAST_EXPRESSION__GENERAL_CHILD, Boolean.TRUE);
		this.addSimilarityEntry(ExpressionsPackage.Literals.CONDITIONAL_EXPRESSION__CHILD, Boolean.TRUE);
		this.addSimilarityEntry(ExpressionsPackage.Literals.CONDITIONAL_EXPRESSION__EXPRESSION_IF, Boolean.TRUE);
		this.addSimilarityEntry(ExpressionsPackage.Literals.CONDITIONAL_EXPRESSION__GENERAL_EXPRESSION_ELSE,
				Boolean.TRUE);
		this.addSimilarityEntry(ExpressionsPackage.Literals.EXCLUSIVE_OR_EXPRESSION__CHILDREN, Boolean.TRUE);
		this.addSimilarityEntry(ExpressionsPackage.Literals.EXPRESSION_LIST__EXPRESSIONS, Boolean.TRUE);
		this.addSimilarityEntry(ExpressionsPackage.Literals.INCLUSIVE_OR_EXPRESSION__CHILDREN, Boolean.TRUE);
		this.addSimilarityEntry(ExpressionsPackage.Literals.MULTIPLICATIVE_EXPRESSION__CHILDREN, Boolean.TRUE);
		this.addSimilarityEntry(ExpressionsPackage.Literals.MULTIPLICATIVE_EXPRESSION__MULTIPLICATIVE_OPERATORS,
				Boolean.TRUE);
		this.addSimilarityEntry(ExpressionsPackage.Literals.PRIMARY_EXPRESSION_REFERENCE_EXPRESSION__CHILD,
				Boolean.TRUE);
		this.addSimilarityEntry(ExpressionsPackage.Literals.PRIMARY_EXPRESSION_REFERENCE_EXPRESSION__METHOD_REFERENCE,
				Boolean.TRUE);
		this.addSimilarityEntry(ExpressionsPackage.Literals.SHIFT_EXPRESSION__CHILDREN, Boolean.TRUE);
		this.addSimilarityEntry(ExpressionsPackage.Literals.SHIFT_EXPRESSION__SHIFT_OPERATORS, Boolean.TRUE);
		this.addSimilarityEntry(ExpressionsPackage.Literals.UNARY_MODIFICATION_EXPRESSION__CHILD, Boolean.TRUE);
		this.addSimilarityEntry(ExpressionsPackage.Literals.UNARY_MODIFICATION_EXPRESSION__OPERATOR, Boolean.TRUE);
		this.addSimilarityEntry(ExpressionsPackage.Literals.LAMBDA_EXPRESSION__BODY, Boolean.TRUE);
		this.addSimilarityEntry(ExpressionsPackage.Literals.LAMBDA_EXPRESSION__PARAMETERS, Boolean.TRUE);
		
		// Generics
		this.addSimilarityEntry(GenericsPackage.Literals.CALL_TYPE_ARGUMENTABLE__CALL_TYPE_ARGUMENTS, Boolean.TRUE);
		this.addSimilarityEntry(GenericsPackage.Literals.TYPE_ARGUMENTABLE__TYPE_ARGUMENTS, Boolean.TRUE);
		this.addSimilarityEntry(GenericsPackage.Literals.TYPE_PARAMETRIZABLE__TYPE_PARAMETERS, Boolean.TRUE);

		// Instantiations
		this.addSimilarityEntry(InstantiationsPackage.Literals.NEW_CONSTRUCTOR_CALL__ANONYMOUS_CLASS, Boolean.TRUE);
		this.addSimilarityEntry(InstantiationsPackage.Literals.INITIALIZABLE__INITIAL_VALUE, Boolean.TRUE);

		// Imports
		this.addSimilarityEntry(ImportsPackage.Literals.IMPORTING_ELEMENT__IMPORTS, Boolean.TRUE);
		this.addSimilarityEntry(ImportsPackage.Literals.IMPORT__CLASSIFIER, Boolean.TRUE);
		this.addSimilarityEntry(ImportsPackage.Literals.STATIC_IMPORT__STATIC, Boolean.TRUE);
		this.addSimilarityEntry(ClassifierImport.class, ImportsPackage.Literals.IMPORT__CLASSIFIER, Boolean.FALSE);

		// Members
		this.addSimilarityEntry(MembersPackage.Literals.ENUM_CONSTANT__ANONYMOUS_CLASS, Boolean.TRUE);
		this.addSimilarityEntry(MembersPackage.Literals.FIELD__ADDITIONAL_FIELDS, Boolean.TRUE);
		this.addSimilarityEntry(MembersPackage.Literals.INTERFACE_METHOD__DEFAULT_VALUE, Boolean.TRUE);
		this.addSimilarityEntry(MembersPackage.Literals.EXCEPTION_THROWER__EXCEPTIONS, Boolean.TRUE);
		this.addSimilarityEntry(MembersPackage.Literals.MEMBER_CONTAINER__MEMBERS, Boolean.TRUE);
		this.addSimilarityEntry(MembersPackage.Literals.MEMBER_CONTAINER__DEFAULT_MEMBERS, Boolean.TRUE);

		// Modifiers
		this.addSimilarityEntry(ModifiersPackage.Literals.ANNOTABLE_AND_MODIFIABLE__ANNOTATIONS_AND_MODIFIERS,
				Boolean.TRUE);
		this.addSimilarityEntry(ModifiersPackage.Literals.MODIFIABLE__MODIFIERS, Boolean.TRUE);

		// Modules
		this.addSimilarityEntry(ModulesPackage.Literals.MODULE_REFERENCE__TARGET, Boolean.TRUE);
		this.addSimilarityEntry(ModulesPackage.Literals.PROVIDES_MODULE_DIRECTIVE__SERVICE_PROVIDERS, Boolean.TRUE);
		this.addSimilarityEntry(ModulesPackage.Literals.REQUIRES_MODULE_DIRECTIVE__MODIFIER, Boolean.TRUE);
		this.addSimilarityEntry(ModulesPackage.Literals.ACCESS_PROVIDING_MODULE_DIRECTIVE__ACCESSABLE_PACKAGE,
				Boolean.TRUE);
		this.addSimilarityEntry(ModulesPackage.Literals.ACCESS_PROVIDING_MODULE_DIRECTIVE__MODULES, Boolean.TRUE);

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
		this.addSimilarityEntry(StatementsPackage.Literals.BLOCK_CONTAINER__BLOCK, Boolean.TRUE);
		this.addSimilarityEntry(StatementsPackage.Literals.STATEMENT_CONTAINER__STATEMENT, Boolean.TRUE);
		this.addSimilarityEntry(StatementListContainer.class, StatementsPackage.Literals.BLOCK__STATEMENTS, Boolean.TRUE);
		
		// Parameters
		this.addSimilarityEntry(ParametersPackage.Literals.CATCH_PARAMETER__TYPE_REFERENCES, Boolean.TRUE);
		this.addSimilarityEntry(ParametersPackage.Literals.RECEIVER_PARAMETER__THIS_REFERENCE, Boolean.TRUE);
		this.addSimilarityEntry(ParametersPackage.Literals.RECEIVER_PARAMETER__OUTER_TYPE_REFERENCE, Boolean.TRUE);
		this.addSimilarityEntry(ExplicitlyTypedLambdaParameters.class,
				ParametersPackage.Literals.PARAMETRIZABLE__PARAMETERS, Boolean.TRUE);
		this.addSimilarityEntry(ImplicitlyTypedLambdaParameters.class,
				ParametersPackage.Literals.PARAMETRIZABLE__PARAMETERS, Boolean.TRUE);
		this.addSimilarityEntry(SingleImplicitLambdaParameter.class,
				ParametersPackage.Literals.PARAMETRIZABLE__PARAMETERS, Boolean.TRUE);

		// References
		this.addSimilarityEntry(EnumConstant.class, ReferencesPackage.Literals.ARGUMENTABLE__ARGUMENTS, Boolean.TRUE);
		this.addSimilarityEntry(ReferencesPackage.Literals.PRIMITIVE_TYPE_REFERENCE__PRIMITIVE_TYPE, Boolean.TRUE);
		this.addSimilarityEntry(ReferencesPackage.Literals.SELF_REFERENCE__SELF, Boolean.TRUE);
		this.addSimilarityEntry(ReferencesPackage.Literals.TEXT_BLOCK_REFERENCE__VALUE, Boolean.TRUE);
		this.addSimilarityEntry(ReferencesPackage.Literals.ELEMENT_REFERENCE__CONTAINED_TARGET, Boolean.TRUE);
		this.addSimilarityEntry(ArrayInstantiation.class, ReferencesPackage.Literals.REFERENCE__NEXT, Boolean.TRUE);
		this.addSimilarityEntry(ArrayInstantiation.class, ReferencesPackage.Literals.REFERENCE__ARRAY_SELECTORS, Boolean.TRUE);
		this.addSimilarityEntry(NestedExpression.class, ReferencesPackage.Literals.REFERENCE__NEXT, Boolean.TRUE);
		this.addSimilarityEntry(NestedExpression.class, ReferencesPackage.Literals.REFERENCE__ARRAY_SELECTORS, Boolean.TRUE);
		this.addSimilarityEntry(Instantiation.class, ReferencesPackage.Literals.REFERENCE__NEXT, Boolean.TRUE);
		this.addSimilarityEntry(Instantiation.class, ReferencesPackage.Literals.REFERENCE__ARRAY_SELECTORS, Boolean.TRUE);
		this.addSimilarityEntry(MethodCall.class, ReferencesPackage.Literals.REFERENCE__ARRAY_SELECTORS, Boolean.TRUE);
		this.addSimilarityEntry(PrimitiveTypeReference.class, ReferencesPackage.Literals.REFERENCE__NEXT, Boolean.TRUE);
		this.addSimilarityEntry(PrimitiveTypeReference.class, ReferencesPackage.Literals.REFERENCE__ARRAY_SELECTORS, Boolean.TRUE);
		this.addSimilarityEntry(ReflectiveClassReference.class, ReferencesPackage.Literals.REFERENCE__NEXT, Boolean.TRUE);
		this.addSimilarityEntry(ReflectiveClassReference.class, ReferencesPackage.Literals.REFERENCE__ARRAY_SELECTORS, Boolean.TRUE);
		this.addSimilarityEntry(SelfReference.class, ReferencesPackage.Literals.REFERENCE__NEXT, Boolean.TRUE);
		this.addSimilarityEntry(SelfReference.class, ReferencesPackage.Literals.REFERENCE__ARRAY_SELECTORS, Boolean.TRUE);
		this.addSimilarityEntry(StringReference.class, ReferencesPackage.Literals.REFERENCE__NEXT, Boolean.TRUE);
		this.addSimilarityEntry(StringReference.class, ReferencesPackage.Literals.REFERENCE__ARRAY_SELECTORS, Boolean.TRUE);
		this.addSimilarityEntry(TextBlockReference.class, ReferencesPackage.Literals.REFERENCE__NEXT, Boolean.TRUE);
		this.addSimilarityEntry(TextBlockReference.class, ReferencesPackage.Literals.REFERENCE__ARRAY_SELECTORS, Boolean.TRUE);
		
		/*
		 * FIXME: There might be issues with TYPED_ELEMENT__TYPE_REFERENCE
		 */
		// Types
		this.addSimilarityEntry(TypesPackage.Literals.TYPED_ELEMENT_EXTENSION__ACTUAL_TARGETS, Boolean.TRUE);
		this.addSimilarityEntry(TypesPackage.Literals.TYPED_ELEMENT__TYPE_REFERENCE, Boolean.TRUE);
		this.addSimilarityEntry(InstanceOfExpression.class, TypesPackage.Literals.TYPED_ELEMENT__TYPE_REFERENCE,
				Boolean.FALSE);
		this.addSimilarityEntry(QualifiedTypeArgument.class, TypesPackage.Literals.TYPED_ELEMENT__TYPE_REFERENCE,
				Boolean.FALSE);
		this.addSimilarityEntry(NewConstructorCall.class, TypesPackage.Literals.TYPED_ELEMENT__TYPE_REFERENCE,
				Boolean.FALSE);
		this.addSimilarityEntry(NewConstructorCallWithInferredTypeArguments.class,
				TypesPackage.Literals.TYPED_ELEMENT__TYPE_REFERENCE, Boolean.FALSE);
		this.addSimilarityEntry(ProvidesModuleDirective.class, TypesPackage.Literals.TYPED_ELEMENT__TYPE_REFERENCE,
				Boolean.FALSE);
		this.addSimilarityEntry(UsesModuleDirective.class, TypesPackage.Literals.TYPED_ELEMENT__TYPE_REFERENCE,
				Boolean.FALSE);

		// Variables
		this.addSimilarityEntry(VariablesPackage.Literals.LOCAL_VARIABLE__ADDITIONAL_LOCAL_VARIABLES, Boolean.TRUE);
	}
}