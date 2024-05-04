package cipm.consistency.fitests.similarity.java.params;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import cipm.consistency.fitests.similarity.java.initialiser.EObjectInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.annotations.AnnotationAttributeSettingInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.annotations.AnnotationInstanceInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.annotations.AnnotationParameterListInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.annotations.SingleAnnotationParameterInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.arrays.ArrayDimensionInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.arrays.ArrayInitializerInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.arrays.ArrayInstantiationBySizeInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.arrays.ArrayInstantiationByValuesTypedInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.arrays.ArrayInstantiationByValuesUntypedInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.arrays.ArraySelectorInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.classifiers.AnnotationInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.classifiers.AnonymousClassInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.classifiers.ClassInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.classifiers.EnumerationInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.classifiers.InterfaceInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.containers.CompilationUnitInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.containers.EmptyModelInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.containers.ModuleInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.containers.PackageInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.expressions.AdditiveExpressionInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.expressions.AndExpressionInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.expressions.ArrayConstructorReferenceExpressionInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.expressions.AssignmentExpressionInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.expressions.CastExpressionInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.expressions.ClassTypeConstructorReferenceExpressionInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.expressions.ConditionalAndExpressionInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.expressions.ConditionalExpressionInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.expressions.ConditionalOrExpressionInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.expressions.EqualityExpressionInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.expressions.ExclusiveOrExpressionInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.expressions.ExplicitlyTypedLambdaParametersInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.expressions.ExpressionListInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.expressions.MultiplicativeExpressionInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.expressions.NestedExpressionInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.expressions.PrefixUnaryModificationExpressionInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.expressions.PrimaryExpressionReferenceExpressionInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.expressions.RelationExpressionInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.expressions.ShiftExpressionInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.expressions.SingleImplicitLambdaParameterInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.expressions.SuffixUnaryModificationExpressionInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.expressions.UnaryExpressionInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.generics.ExtendsTypeArgumentInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.generics.QualifiedTypeArgumentInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.generics.SuperTypeArgumentInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.generics.TypeParameterInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.generics.UnknownTypeArgumentInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.imports.ClassifierImportInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.imports.PackageImportInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.imports.StaticClassifierImportInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.imports.StaticMemberImportInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.instantiations.ExplicitConstructorCallInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.instantiations.NewConstructorCallInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.instantiations.NewConstructorCallWithInferredTypeArgumentsInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.literals.BinaryIntegerLiteralInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.literals.BinaryLongLiteralInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.literals.BooleanLiteralInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.literals.CharacterLiteralInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.literals.DecimalDoubleLiteralInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.literals.DecimalFloatLiteralInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.literals.DecimalIntegerLiteralInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.literals.DecimalLongLiteralInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.literals.HexDoubleLiteralInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.literals.HexFloatLiteralInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.literals.HexIntegerLiteralInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.literals.HexLongLiteralInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.literals.NullLiteralInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.literals.OctalIntegerLiteralInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.literals.OctalLongLiteralInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.literals.SuperInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.literals.ThisInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.members.AdditionalFieldInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.members.ClassMethodInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.members.ConstructorInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.members.EmptyMemberInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.members.EnumConstantInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.members.FieldInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.members.InterfaceMethodInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.modifiers.AbstractInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.modifiers.DefaultInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.modifiers.FinalInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.modifiers.NativeInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.modifiers.OpenInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.modifiers.PrivateInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.modifiers.ProtectedInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.modifiers.PublicInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.modifiers.StaticInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.modifiers.StrictfpInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.modifiers.SynchronizedInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.modifiers.TransientInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.modifiers.TransitiveInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.modifiers.VolatileInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.modules.ExportsModuleDirectiveInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.modules.ModuleReferenceInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.modules.OpensModuleDirectiveInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.modules.ProvidesModuleDirectiveInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.modules.RequiresModuleDirectiveInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.modules.UsesModuleDirectiveInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.operators.AdditionInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.operators.AssignmentAndInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.operators.AssignmentDivisionInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.operators.AssignmentExclusiveOrInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.operators.AssignmentInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.operators.AssignmentLeftShiftInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.operators.AssignmentMinusInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.operators.AssignmentModuloInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.operators.AssignmentMultiplicationInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.operators.AssignmentOrInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.operators.AssignmentPlusInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.operators.AssignmentRightShiftInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.operators.AssignmentUnsignedRightShiftInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.operators.ComplementInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.operators.DivisionInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.operators.EqualInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.operators.GreaterThanInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.operators.GreaterThanOrEqualInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.operators.LeftShiftInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.operators.LessThanInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.operators.LessThanOrEqualInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.operators.MinusMinusInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.operators.MultiplicationInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.operators.NegateInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.operators.NotEqualInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.operators.PlusPlusInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.operators.RemainderInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.operators.RightShiftInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.operators.SubtractionInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.operators.UnsignedRightShiftInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.parameters.CatchParameterInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.parameters.OrdinaryParameterInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.parameters.ReceiverParameterInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.parameters.VariableLengthParameterInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.references.IdentifierReferenceInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.references.MethodCallInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.references.PackageReferenceInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.references.PrimitiveTypeReferenceInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.references.ReflectiveClassReferenceInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.references.SelfReferenceInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.references.StringReferenceInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.references.TextBlockReferenceInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.statements.AssertInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.statements.BlockInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.statements.BreakInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.statements.CatchBlockInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.statements.ConditionInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.statements.ContinueInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.statements.DefaultSwitchCaseInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.statements.DefaultSwitchRuleInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.statements.DoWhileLoopInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.statements.EmptyStatementInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.statements.ExpressionStatementInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.statements.ForEachLoopInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.statements.ForLoopInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.statements.JumpLabelInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.statements.LocalVariableStatementInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.statements.NormalSwitchCaseInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.statements.NormalSwitchRuleInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.statements.ReturnInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.statements.SwitchInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.statements.SynchronizedBlockInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.statements.ThrowInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.statements.TryBlockInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.statements.WhileLoopInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.statements.YieldStatementInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.types.ClassifierReferenceInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.types.InferableTypeInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.types.NamespaceClassifierReferenceInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.variables.AdditionalLocalVariableInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.variables.LocalVariableInitialiser;

public class InitialiserParameters {
	/*
	 * TODO: Include whether certain differing aspects are supposed
	 * to cause the respective EObject instances to be considered
	 * different.
	 */
	
	@SuppressWarnings("serial")
	public List<EObjectInitialiser> getAllInitialisers() {
		return new ArrayList<EObjectInitialiser>() {{
			// Annotations
			add(new AnnotationAttributeSettingInitialiser());
			add(new AnnotationInstanceInitialiser());
			add(new AnnotationParameterListInitialiser());
			add(new SingleAnnotationParameterInitialiser());
			
			// Arrays
			add(new ArrayDimensionInitialiser());
			add(new ArrayInitializerInitialiser());
			add(new ArrayInstantiationBySizeInitialiser());
			add(new ArrayInstantiationByValuesTypedInitialiser());
			add(new ArrayInstantiationByValuesUntypedInitialiser());
			add(new ArraySelectorInitialiser());
			
			// Classifiers
			add(new AnnotationInitialiser());
			add(new AnonymousClassInitialiser());
			add(new ClassInitialiser());
			add(new EnumerationInitialiser());
			add(new InterfaceInitialiser());
			
			// Containers
			add(new CompilationUnitInitialiser());
			add(new EmptyModelInitialiser());
			add(new ModuleInitialiser());
			add(new PackageInitialiser());
			
			// Expressions
			add(new AdditiveExpressionInitialiser());
			add(new AndExpressionInitialiser());
			add(new ArrayConstructorReferenceExpressionInitialiser());
			add(new AssignmentExpressionInitialiser());
			add(new CastExpressionInitialiser());
			add(new ClassTypeConstructorReferenceExpressionInitialiser());
			add(new ConditionalAndExpressionInitialiser());
			add(new ConditionalExpressionInitialiser());
			add(new ConditionalOrExpressionInitialiser());
			add(new EqualityExpressionInitialiser());
			add(new ExclusiveOrExpressionInitialiser());
			add(new ExplicitlyTypedLambdaParametersInitialiser());
			add(new ExpressionListInitialiser());
			add(new MultiplicativeExpressionInitialiser());
			add(new NestedExpressionInitialiser());
			add(new PrefixUnaryModificationExpressionInitialiser());
			add(new PrimaryExpressionReferenceExpressionInitialiser());
			add(new RelationExpressionInitialiser());
			add(new ShiftExpressionInitialiser());
			add(new SingleImplicitLambdaParameterInitialiser());
			add(new SuffixUnaryModificationExpressionInitialiser());
			add(new UnaryExpressionInitialiser());
			
			// Generics
			add(new ExtendsTypeArgumentInitialiser());
			add(new QualifiedTypeArgumentInitialiser());
			add(new SuperTypeArgumentInitialiser());
			add(new TypeParameterInitialiser());
			add(new UnknownTypeArgumentInitialiser());
			
			// Imports
			add(new ClassifierImportInitialiser());
			add(new PackageImportInitialiser());
			add(new StaticClassifierImportInitialiser());
			add(new StaticMemberImportInitialiser());
			
			// Instantiations
			add(new ExplicitConstructorCallInitialiser());
			add(new NewConstructorCallInitialiser());
			add(new NewConstructorCallWithInferredTypeArgumentsInitialiser());
			
			// Literals
			add(new BinaryIntegerLiteralInitialiser());
			add(new BinaryLongLiteralInitialiser());
			add(new BooleanLiteralInitialiser());
			add(new CharacterLiteralInitialiser());
			add(new DecimalDoubleLiteralInitialiser());
			add(new DecimalFloatLiteralInitialiser());
			add(new DecimalIntegerLiteralInitialiser());
			add(new DecimalLongLiteralInitialiser());
			add(new HexDoubleLiteralInitialiser());
			add(new HexFloatLiteralInitialiser());
			add(new HexIntegerLiteralInitialiser());
			add(new HexLongLiteralInitialiser());
			add(new NullLiteralInitialiser());
			add(new OctalIntegerLiteralInitialiser());
			add(new OctalLongLiteralInitialiser());
			add(new SuperInitialiser());
			add(new ThisInitialiser());
			
			// Members
			add(new AdditionalFieldInitialiser());
			add(new ClassMethodInitialiser());
			add(new ConstructorInitialiser());
			add(new EmptyMemberInitialiser());
			add(new EnumConstantInitialiser());
			add(new FieldInitialiser());
			add(new InterfaceMethodInitialiser());
			
			// Modifiers
			add(new AbstractInitialiser());
			add(new DefaultInitialiser());
			add(new FinalInitialiser());
			add(new NativeInitialiser());
			add(new OpenInitialiser());
			add(new PrivateInitialiser());
			add(new ProtectedInitialiser());
			add(new PublicInitialiser());
			add(new StaticInitialiser());
			add(new StrictfpInitialiser());
			add(new SynchronizedInitialiser());
			add(new TransientInitialiser());
			add(new TransitiveInitialiser());
			add(new VolatileInitialiser());
			
			// Modules
			add(new ExportsModuleDirectiveInitialiser());
			add(new ModuleReferenceInitialiser());
			add(new OpensModuleDirectiveInitialiser());
			add(new ProvidesModuleDirectiveInitialiser());
			add(new RequiresModuleDirectiveInitialiser());
			add(new UsesModuleDirectiveInitialiser());
			
			// Operators
			add(new AdditionInitialiser());
			add(new AssignmentAndInitialiser());
			add(new AssignmentDivisionInitialiser());
			add(new AssignmentExclusiveOrInitialiser());
			add(new AssignmentInitialiser());
			add(new AssignmentLeftShiftInitialiser());
			add(new AssignmentMinusInitialiser());
			add(new AssignmentModuloInitialiser());
			add(new AssignmentMultiplicationInitialiser());
			add(new AssignmentOrInitialiser());
			add(new AssignmentPlusInitialiser());
			add(new AssignmentRightShiftInitialiser());
			add(new AssignmentUnsignedRightShiftInitialiser());
			add(new ComplementInitialiser());
			add(new DivisionInitialiser());
			add(new EqualInitialiser());
			add(new GreaterThanInitialiser());
			add(new GreaterThanOrEqualInitialiser());
			add(new LeftShiftInitialiser());
			add(new LessThanInitialiser());
			add(new LessThanOrEqualInitialiser());
			add(new MinusMinusInitialiser());
			add(new MultiplicationInitialiser());
			add(new NegateInitialiser());
			add(new NotEqualInitialiser());
			add(new PlusPlusInitialiser());
			add(new RemainderInitialiser());
			add(new RightShiftInitialiser());
			add(new SubtractionInitialiser());
			add(new UnsignedRightShiftInitialiser());
			
			// Parameters
			add(new CatchParameterInitialiser());
			add(new OrdinaryParameterInitialiser());
			add(new ReceiverParameterInitialiser());
			add(new VariableLengthParameterInitialiser());
			
			// Reference
			add(new IdentifierReferenceInitialiser());
			add(new MethodCallInitialiser());
			add(new PackageReferenceInitialiser());
			add(new PrimitiveTypeReferenceInitialiser());
			add(new ReflectiveClassReferenceInitialiser());
			add(new SelfReferenceInitialiser());
			add(new StringReferenceInitialiser());
			add(new TextBlockReferenceInitialiser());
			
			// Statements
			add(new AssertInitialiser());
			add(new BlockInitialiser());
			add(new BreakInitialiser());
			add(new CatchBlockInitialiser());
			add(new ConditionInitialiser());
			add(new ContinueInitialiser());
			add(new DefaultSwitchCaseInitialiser());
			add(new DefaultSwitchRuleInitialiser());
			add(new DoWhileLoopInitialiser());
			add(new EmptyStatementInitialiser());
			add(new ExpressionStatementInitialiser());
			add(new ForEachLoopInitialiser());
			add(new ForLoopInitialiser());
			add(new JumpLabelInitialiser());
			add(new LocalVariableStatementInitialiser());
			add(new NormalSwitchCaseInitialiser());
			add(new NormalSwitchRuleInitialiser());
			add(new ReturnInitialiser());
			add(new SwitchInitialiser());
			add(new SynchronizedBlockInitialiser());
			add(new ThrowInitialiser());
			add(new TryBlockInitialiser());
			add(new WhileLoopInitialiser());
			add(new YieldStatementInitialiser());
			
			// Types
			add(new ClassifierReferenceInitialiser());
			add(new InferableTypeInitialiser());
			add(new NamespaceClassifierReferenceInitialiser());
			
			// Variables
			add(new AdditionalLocalVariableInitialiser());
			add(new LocalVariableInitialiser());
		}};
	}
	
	public List<EObjectInitialiser> getInitialisersBy(Predicate<EObjectInitialiser> pred) {
		var result = new ArrayList<EObjectInitialiser>();
		this.getAllInitialisers().stream().filter(pred).forEach((i) -> result.add(i));
		return result;
	}
	
	public List<EObjectInitialiser> getInitialisersBySuper(Stream<Class<? extends EObjectInitialiser>> clss) {
		return this.getInitialisersBy(
					(i) -> clss.anyMatch((cls) -> cls.isInstance(i))
				);
	}
	
	public EObjectInitialiser getInitialiserByClass(Stream<Class<? extends EObjectInitialiser>> clss) {
		return this.getAllInitialisers().stream()
				.filter((i) -> clss.anyMatch((cls) -> i.getClass().equals(cls)))
				.findFirst()
				.orElseGet(null);
	}
	
	public List<EObjectInitialiser> getInitialisersBySuper(Class<? extends EObjectInitialiser> cls) {
		return this.getInitialisersBy(
				(i) -> cls.isInstance(i)
				);
	}
	
	public EObjectInitialiser getInitialiserByClass(Class<? extends EObjectInitialiser> cls) {
		return this.getAllInitialisers().stream()
				.filter((i) -> i.getClass().equals(cls))
				.findFirst()
				.orElseGet(null);
	}
}
