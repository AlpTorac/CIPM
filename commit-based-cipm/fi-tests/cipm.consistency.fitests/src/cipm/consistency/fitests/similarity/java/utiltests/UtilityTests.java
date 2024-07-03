package cipm.consistency.fitests.similarity.java.utiltests;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.annotations.AnnotationsFactory;
import org.emftext.language.java.annotations.AnnotationsPackage;
import org.emftext.language.java.arrays.ArraysFactory;
import org.emftext.language.java.classifiers.ClassifiersFactory;
import org.emftext.language.java.commons.Commentable;
import org.emftext.language.java.commons.CommonsFactory;
import org.emftext.language.java.containers.ContainersFactory;
import org.emftext.language.java.expressions.ExpressionsFactory;
import org.emftext.language.java.generics.GenericsFactory;
import org.emftext.language.java.imports.ImportsFactory;
import org.emftext.language.java.instantiations.InstantiationsFactory;
import org.emftext.language.java.literals.LiteralsFactory;
import org.emftext.language.java.members.MembersFactory;
import org.emftext.language.java.modifiers.ModifiersFactory;
import org.emftext.language.java.modules.ModulesFactory;
import org.emftext.language.java.operators.OperatorsFactory;
import org.emftext.language.java.parameters.ParametersFactory;
import org.emftext.language.java.references.ReferencesFactory;
import org.emftext.language.java.statements.StatementsFactory;
import org.emftext.language.java.types.TypesFactory;
import org.emftext.language.java.variables.VariablesFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.AbstractSimilarityTest;

/**
 * A test class, whose tests can be used to make sure no initialiser interfaces,
 * concrete initialisers or initialiser tests are missing.
 * 
 * @author atora
 */
public class UtilityTests extends AbstractSimilarityTest {
	
	// FIXME: Find a better way to determine, if there are initialiser methods that can be used in tests. Maybe something like SimilarityValues.
	// TODO: Comment better, rename unintuitive parameters and methods.
	
	/**
	 * Points at the cimp.consistency.fitests.similarity.java package. Used
	 * by discovering methods in this class.
	 */
	private static final File root = new File("").getAbsoluteFile().getParentFile();
	/**
	 * The suffix used in the concrete implementation of {@link EObject} classes.
	 */
	private static final String implSuffix = "Impl";
	
	/**
	 * Pattern for testable initialiser method names (without whitespace):
	 * 
	 * preceding text +
	 * "default" +
	 * return type +
	 * "add" or "set" +
	 * rest of the method name (without "Assertion") +
	 * parameters +
	 * proceeding text
	 */
	private static final String testableMethodPattern = "(.*)default\\w+(add|set)\\w+(?<!Assertion)\\((.*)";
	
	@BeforeEach
	public void setUp() {
		this.setUpLogger();
	}

	@AfterEach
	public void tearDown() {
		return;
	}
	
	/**
	 * @return Names of all interfaces directly or indirectly
	 * implemented by cls.
	 */
	public Set<String> getClassNamesInHierarchy(Class<?> cls) {
		return Set.of(this.getClassesInHierarchy(cls).stream()
				.map((c)->c.getSimpleName()).toArray(String[]::new));
	}
	
	/**
	 * @return Names of all interfaces directly or indirectly
	 * implemented by classes in clss.
	 */
	public Set<String> getClassNamesInHierarchy(Collection<Class<?>> clss) {
		var result = new HashSet<String>();
		
		for (var cls : clss) {
			result.addAll(this.getClassNamesInHierarchy(cls));
		}
		
		return result;
	}
	
	/**
	 * @return All interfaces directly or indirectly
	 * implemented by cls.
	 */
	public Set<Class<?>> getClassesInHierarchy(Class<?> cls) {
		var result = new HashSet<Class<?>>();
		result.add(cls);
		
		var intfcs = cls.getInterfaces();
		for (var intfc : intfcs) {
			result.addAll(this.getClassesInHierarchy(intfc));
		}
		
		return result;
	}
	
	/**
	 * @return All interfaces directly or indirectly
	 * implemented by classes in clss.
	 */
	public Set<Class<?>> getClassesInHierarchy(Collection<Class<?>> clss) {
		var result = new HashSet<Class<?>>();
		
		for (var cls : clss) {
			result.addAll(this.getClassesInHierarchy(cls));
		}
		
		return result;
	}
	
	/**
	 * @return The given text without whitespaces (includes line breaks).
	 */
	public String removeWhitespaces(String text) {
		return text.replaceAll("\\n", "").replaceAll("\\r", "").replaceAll("\\s", "");
	}
	
	/**
	 * @return An instance of each concrete Java-Model-Element class.
	 */
	public Collection<EObject> getAllPossibleModelElements() {
		// TODO: Extract this method to another class (?)
		
		/*
		 * TODO: Try to use AnnotationsPackage.Literals.ANNOTABLE.getEPackage() and .subpackages()
		 * iterate over them and see if all types come out.
		 */
		
		return new ArrayList<EObject>() {{
			// Annotations
			add(AnnotationsFactory.eINSTANCE.createAnnotationAttributeSetting());
			add(AnnotationsFactory.eINSTANCE.createAnnotationInstance());
			add(AnnotationsFactory.eINSTANCE.createAnnotationParameterList());
			add(AnnotationsFactory.eINSTANCE.createSingleAnnotationParameter());
			// Arrays
			add(ArraysFactory.eINSTANCE.createArrayDimension());
			add(ArraysFactory.eINSTANCE.createArrayInitializer());
			add(ArraysFactory.eINSTANCE.createArrayInstantiationBySize());
			add(ArraysFactory.eINSTANCE.createArrayInstantiationByValuesTyped());
			add(ArraysFactory.eINSTANCE.createArrayInstantiationByValuesUntyped());
			add(ArraysFactory.eINSTANCE.createArraySelector());
			
			// Classifiers
			add(ClassifiersFactory.eINSTANCE.createAnnotation());
			add(ClassifiersFactory.eINSTANCE.createAnonymousClass());
			add(ClassifiersFactory.eINSTANCE.createClass());
			add(ClassifiersFactory.eINSTANCE.createEnumeration());
			add(ClassifiersFactory.eINSTANCE.createInterface());
			
			// Commons
			
			// Containers
			add(ContainersFactory.eINSTANCE.createCompilationUnit());
			add(ContainersFactory.eINSTANCE.createEmptyModel());
			add(ContainersFactory.eINSTANCE.createModule());
			add(ContainersFactory.eINSTANCE.createPackage());
			
			// Expressions
			add(ExpressionsFactory.eINSTANCE.createAdditiveExpression());
			add(ExpressionsFactory.eINSTANCE.createAndExpression());
			add(ExpressionsFactory.eINSTANCE.createArrayConstructorReferenceExpression());
			add(ExpressionsFactory.eINSTANCE.createAssignmentExpression());
			add(ExpressionsFactory.eINSTANCE.createCastExpression());
			add(ExpressionsFactory.eINSTANCE.createClassTypeConstructorReferenceExpression());
			add(ExpressionsFactory.eINSTANCE.createConditionalAndExpression());
			add(ExpressionsFactory.eINSTANCE.createConditionalExpression());
			add(ExpressionsFactory.eINSTANCE.createConditionalOrExpression());
			add(ExpressionsFactory.eINSTANCE.createEqualityExpression());
			add(ExpressionsFactory.eINSTANCE.createExclusiveOrExpression());
			add(ExpressionsFactory.eINSTANCE.createExplicitlyTypedLambdaParameters());
			add(ExpressionsFactory.eINSTANCE.createExpressionList());
			add(ExpressionsFactory.eINSTANCE.createImplicitlyTypedLambdaParameters());
			add(ExpressionsFactory.eINSTANCE.createInclusiveOrExpression());
			add(ExpressionsFactory.eINSTANCE.createInstanceOfExpression());
			add(ExpressionsFactory.eINSTANCE.createLambdaExpression());
			add(ExpressionsFactory.eINSTANCE.createMultiplicativeExpression());
			add(ExpressionsFactory.eINSTANCE.createNestedExpression());
			add(ExpressionsFactory.eINSTANCE.createPrefixUnaryModificationExpression());
			add(ExpressionsFactory.eINSTANCE.createPrimaryExpressionReferenceExpression());
			add(ExpressionsFactory.eINSTANCE.createRelationExpression());
			add(ExpressionsFactory.eINSTANCE.createShiftExpression());
			add(ExpressionsFactory.eINSTANCE.createSingleImplicitLambdaParameter());
			add(ExpressionsFactory.eINSTANCE.createSuffixUnaryModificationExpression());
			add(ExpressionsFactory.eINSTANCE.createUnaryExpression());
			
			// Generics
			add(GenericsFactory.eINSTANCE.createExtendsTypeArgument());
			add(GenericsFactory.eINSTANCE.createQualifiedTypeArgument());
			add(GenericsFactory.eINSTANCE.createSuperTypeArgument());
			add(GenericsFactory.eINSTANCE.createTypeParameter());
			add(GenericsFactory.eINSTANCE.createUnknownTypeArgument());
			
			// ImportsFactory
			add(ImportsFactory.eINSTANCE.createClassifierImport());
			add(ImportsFactory.eINSTANCE.createPackageImport());
			add(ImportsFactory.eINSTANCE.createStaticClassifierImport());
			add(ImportsFactory.eINSTANCE.createStaticMemberImport());
			
			// Instantiations
			add(InstantiationsFactory.eINSTANCE.createExplicitConstructorCall());
			add(InstantiationsFactory.eINSTANCE.createNewConstructorCall());
			add(InstantiationsFactory.eINSTANCE.createNewConstructorCallWithInferredTypeArguments());
			
			// Literals
			add(LiteralsFactory.eINSTANCE.createBinaryIntegerLiteral());
			add(LiteralsFactory.eINSTANCE.createBinaryLongLiteral());
			add(LiteralsFactory.eINSTANCE.createBooleanLiteral());
			add(LiteralsFactory.eINSTANCE.createCharacterLiteral());
			add(LiteralsFactory.eINSTANCE.createDecimalDoubleLiteral());
			add(LiteralsFactory.eINSTANCE.createDecimalFloatLiteral());
			add(LiteralsFactory.eINSTANCE.createDecimalIntegerLiteral());
			add(LiteralsFactory.eINSTANCE.createDecimalLongLiteral());
			add(LiteralsFactory.eINSTANCE.createHexDoubleLiteral());
			add(LiteralsFactory.eINSTANCE.createHexFloatLiteral());
			add(LiteralsFactory.eINSTANCE.createHexIntegerLiteral());
			add(LiteralsFactory.eINSTANCE.createHexLongLiteral());
			add(LiteralsFactory.eINSTANCE.createNullLiteral());
			add(LiteralsFactory.eINSTANCE.createOctalIntegerLiteral());
			add(LiteralsFactory.eINSTANCE.createOctalLongLiteral());
			add(LiteralsFactory.eINSTANCE.createSuper());
			add(LiteralsFactory.eINSTANCE.createThis());
			
			// Members
			add(MembersFactory.eINSTANCE.createAdditionalField());
			add(MembersFactory.eINSTANCE.createClassMethod());
			add(MembersFactory.eINSTANCE.createConstructor());
			add(MembersFactory.eINSTANCE.createEmptyMember());
			add(MembersFactory.eINSTANCE.createEnumConstant());
			add(MembersFactory.eINSTANCE.createField());
			add(MembersFactory.eINSTANCE.createInterfaceMethod());
			
			// Modifiers
			add(ModifiersFactory.eINSTANCE.createAbstract());
			add(ModifiersFactory.eINSTANCE.createDefault());
			add(ModifiersFactory.eINSTANCE.createFinal());
			add(ModifiersFactory.eINSTANCE.createNative());
			add(ModifiersFactory.eINSTANCE.createOpen());
			add(ModifiersFactory.eINSTANCE.createPrivate());
			add(ModifiersFactory.eINSTANCE.createProtected());
			add(ModifiersFactory.eINSTANCE.createPublic());
			add(ModifiersFactory.eINSTANCE.createStatic());
			add(ModifiersFactory.eINSTANCE.createStrictfp());
			add(ModifiersFactory.eINSTANCE.createSynchronized());
			add(ModifiersFactory.eINSTANCE.createTransient());
			add(ModifiersFactory.eINSTANCE.createTransitive());
			add(ModifiersFactory.eINSTANCE.createVolatile());
			
			// Modules
			add(ModulesFactory.eINSTANCE.createExportsModuleDirective());
			add(ModulesFactory.eINSTANCE.createModuleReference());
			add(ModulesFactory.eINSTANCE.createOpensModuleDirective());
			add(ModulesFactory.eINSTANCE.createProvidesModuleDirective());
			add(ModulesFactory.eINSTANCE.createRequiresModuleDirective());
			add(ModulesFactory.eINSTANCE.createUsesModuleDirective());
			
			// Operators
			add(OperatorsFactory.eINSTANCE.createAddition());
			add(OperatorsFactory.eINSTANCE.createAssignment());
			add(OperatorsFactory.eINSTANCE.createAssignmentAnd());
			add(OperatorsFactory.eINSTANCE.createAssignmentDivision());
			add(OperatorsFactory.eINSTANCE.createAssignmentExclusiveOr());
			add(OperatorsFactory.eINSTANCE.createAssignmentLeftShift());
			add(OperatorsFactory.eINSTANCE.createAssignmentMinus());
			add(OperatorsFactory.eINSTANCE.createAssignmentModulo());
			add(OperatorsFactory.eINSTANCE.createAssignmentMultiplication());
			add(OperatorsFactory.eINSTANCE.createAssignmentOr());
			add(OperatorsFactory.eINSTANCE.createAssignmentPlus());
			add(OperatorsFactory.eINSTANCE.createAssignmentRightShift());
			add(OperatorsFactory.eINSTANCE.createAssignmentUnsignedRightShift());
			add(OperatorsFactory.eINSTANCE.createComplement());
			add(OperatorsFactory.eINSTANCE.createDivision());
			add(OperatorsFactory.eINSTANCE.createEqual());
			add(OperatorsFactory.eINSTANCE.createGreaterThan());
			add(OperatorsFactory.eINSTANCE.createGreaterThanOrEqual());
			add(OperatorsFactory.eINSTANCE.createLeftShift());
			add(OperatorsFactory.eINSTANCE.createLessThan());
			add(OperatorsFactory.eINSTANCE.createLessThanOrEqual());
			add(OperatorsFactory.eINSTANCE.createMinusMinus());
			add(OperatorsFactory.eINSTANCE.createMultiplication());
			add(OperatorsFactory.eINSTANCE.createNegate());
			add(OperatorsFactory.eINSTANCE.createNotEqual());
			add(OperatorsFactory.eINSTANCE.createPlusPlus());
			add(OperatorsFactory.eINSTANCE.createRemainder());
			add(OperatorsFactory.eINSTANCE.createRightShift());
			add(OperatorsFactory.eINSTANCE.createSubtraction());
			add(OperatorsFactory.eINSTANCE.createUnsignedRightShift());
			
			// Parameters
			add(ParametersFactory.eINSTANCE.createCatchParameter());
			add(ParametersFactory.eINSTANCE.createOrdinaryParameter());
			add(ParametersFactory.eINSTANCE.createReceiverParameter());
			add(ParametersFactory.eINSTANCE.createVariableLengthParameter());
			
			// References
			add(ReferencesFactory.eINSTANCE.createIdentifierReference());
			add(ReferencesFactory.eINSTANCE.createMethodCall());
			add(ReferencesFactory.eINSTANCE.createPackageReference());
			add(ReferencesFactory.eINSTANCE.createPrimitiveTypeReference());
			add(ReferencesFactory.eINSTANCE.createReflectiveClassReference());
			add(ReferencesFactory.eINSTANCE.createSelfReference());
			add(ReferencesFactory.eINSTANCE.createStringReference());
			add(ReferencesFactory.eINSTANCE.createTextBlockReference());
			
			// Statements
			add(StatementsFactory.eINSTANCE.createAssert());
			add(StatementsFactory.eINSTANCE.createBlock());
			add(StatementsFactory.eINSTANCE.createBreak());
			add(StatementsFactory.eINSTANCE.createCatchBlock());
			add(StatementsFactory.eINSTANCE.createCondition());
			add(StatementsFactory.eINSTANCE.createContinue());
			add(StatementsFactory.eINSTANCE.createDefaultSwitchCase());
			add(StatementsFactory.eINSTANCE.createDefaultSwitchRule());
			add(StatementsFactory.eINSTANCE.createDoWhileLoop());
			add(StatementsFactory.eINSTANCE.createEmptyStatement());
			add(StatementsFactory.eINSTANCE.createExpressionStatement());
			add(StatementsFactory.eINSTANCE.createForEachLoop());
			add(StatementsFactory.eINSTANCE.createForLoop());
			add(StatementsFactory.eINSTANCE.createJumpLabel());
			add(StatementsFactory.eINSTANCE.createLocalVariableStatement());
			add(StatementsFactory.eINSTANCE.createNormalSwitchCase());
			add(StatementsFactory.eINSTANCE.createNormalSwitchRule());
			add(StatementsFactory.eINSTANCE.createReturn());
			add(StatementsFactory.eINSTANCE.createSwitch());
			add(StatementsFactory.eINSTANCE.createSynchronizedBlock());
			add(StatementsFactory.eINSTANCE.createThrow());
			add(StatementsFactory.eINSTANCE.createTryBlock());
			add(StatementsFactory.eINSTANCE.createWhileLoop());
			add(StatementsFactory.eINSTANCE.createYieldStatement());
			
			// Types
			add(TypesFactory.eINSTANCE.createBoolean());
			add(TypesFactory.eINSTANCE.createByte());
			add(TypesFactory.eINSTANCE.createChar());
			add(TypesFactory.eINSTANCE.createClassifierReference());
			add(TypesFactory.eINSTANCE.createDouble());
			add(TypesFactory.eINSTANCE.createFloat());
			add(TypesFactory.eINSTANCE.createInferableType());
			add(TypesFactory.eINSTANCE.createInt());
			add(TypesFactory.eINSTANCE.createLong());
			add(TypesFactory.eINSTANCE.createNamespaceClassifierReference());
			add(TypesFactory.eINSTANCE.createShort());
			add(TypesFactory.eINSTANCE.createVoid());
			
			// Variables
			add(VariablesFactory.eINSTANCE.createAdditionalLocalVariable());
			add(VariablesFactory.eINSTANCE.createLocalVariable());
		}};
	}
	
	/**
	 * @return Classes of everything returned by {@link #getAllPossibleModelElements()}.
	 */
	public Collection<Class<?>> getAllPossibleClasses() {
		return List.of(this.getAllPossibleModelElements().stream().map((obj) -> obj.getClass()).toArray(Class<?>[]::new));
	}
	
	/**
	 * @return A list of all files under the given path and its sub-directories.
	 */
	public Collection<File> getAllFiles(File currentPath) {
		var result = new ArrayList<File>();
		
		if (currentPath.isFile()) {
			result.add(currentPath);
		} else {
			var files = currentPath.listFiles();
			if (files != null) {
				for (var f : files) {
					result.addAll(this.getAllFiles(f));
				}
			}
		}
		
		return result;
	}
	
	/**
	 * @return Whether the given file's name (without extension)
	 * equals to the given fileName.
	 */
	public boolean fileNameEquals(File file, String fileName) {
		return file != null && file.getName().split("\\.")[0].equals(fileName);
	}
	
	/**
	 * @return Name of all interfaces that are implemented by at least
	 * one class from {@link #getAllPossibleClasses}.
	 */
	public Set<String> getClassNamesInFullHierarchy() {
		return this.getClassNamesInHierarchy(this.getAllPossibleClasses());
	}
	
	/**
	 * @return All interfaces that are implemented by at least
	 * one class from {@link #getAllPossibleClasses}.
	 */
	public Set<Class<?>> getClassesInFullHierarchy() {
		return this.getClassesInHierarchy(this.getAllPossibleClasses());
	}
	
	/**
	 * @return The name of the concrete initialiser corresponding to cls.
	 */
	public String getConcreteInitialiserName(Class<?> cls) {
		return cls.getSimpleName() + "Initialiser";
	}
	
	/**
	 * @return The name of the initialiser interface corresponding to cls.
	 */
	public String getInterfaceInitialiserName(Class<?> cls) {
		return "I" + cls.getSimpleName() + "Initialiser";
	}
	
	/**
	 * @return The name of the test corresponding to cls.
	 */
	public String getTestName(Class<?> cls) {
		return cls.getSimpleName()+"Test";
	}
	
	/**
	 * @return The classes from {@link #getClassesInFullHierarchy}, which
	 * should have a corresponding initialiser interface.
	 */
	public Collection<Class<?>> getAllInitialiserCandidates() {
		var fullHierarchy = this.getClassesInFullHierarchy();
		
		var intfcs = fullHierarchy.stream()
				.filter((c) -> Commentable.class.isAssignableFrom(c))
				.filter((c) -> !c.getSimpleName().endsWith(implSuffix))
				.toArray(Class<?>[]::new);
		
		return List.of(intfcs);
	}
	
	/**
	 * @return The classes from {@link #getClassesInFullHierarchy}, which
	 * should have a corresponding concrete initialiser.
	 */
	public Collection<Class<?>> getAllConcreteInitialiserCandidates() {
		var fullHierarchy = this.getClassesInFullHierarchy();
		
		var intfcs = fullHierarchy.stream()
				.filter((c) -> Commentable.class.isAssignableFrom(c))
				.filter((c) -> fullHierarchy.stream()
						.anyMatch((c2) -> c2.getSimpleName()
								.equals(c.getSimpleName()+implSuffix)))
				.toArray(Class<?>[]::new);
		
		return List.of(intfcs);
	}
	
	/**
	 * Prints the interfaces between everything from {@link #getAllPossibleModelElements()}
	 * and {@link Commentable}.
	 */
	@Disabled
	@Test
	public void printFullHierarchy() {
		var hSet = this.getClassNamesInHierarchy(this.getAllPossibleClasses());
		System.out.println(hSet.toString());
	}
	
	/**
	 * Checks if all interface initialisers (ISomethingInitialiser.java) files are
	 * implemented and present under {@link #root}.
	 */
	@Test
	public void testAllInitialiserInterfacesPresent() {
		var intfcs = this.getAllInitialiserCandidates();
		var matches = this.getClassesWithInitialiserInterface();
		
		this.getLogger().info(matches.size() + " initialiser interfaces out of "+intfcs.size()+" are present");
		
		if (matches.size() != intfcs.size()) {
			Assertions.fail("Initialisers missing for: " +
						intfcs.stream()
							.filter((e) -> !matches.contains(e))
							.map((e) -> e.getSimpleName())
							.reduce("", (s1, s2) -> s1 + ", " + s2)
					);
		}
	}
	
	/**
	 * @return All classes in the need of an initialiser that actually
	 * have a corresponding initialiser interface under {@link root}.
	 */
	public Collection<Class<?>> getClassesWithInitialiserInterface() {
		var allFiles = this.getAllFiles(root);
		
		return List.of(
				this.getAllInitialiserCandidates().stream()
				.filter((c) -> allFiles.stream()
						.anyMatch((f) -> this.fileNameEquals(f,
								this.getInterfaceInitialiserName(c))))
				.toArray(Class<?>[]::new)
				);
	}
	
	/**
	 * Checks if all concrete initialisers (SomethingInitialiser.java) files are
	 * implemented and present under {@link #root}.
	 */
	@Test
	public void testAllConcreteInitialisersPresent() {
		var intfcs = this.getAllConcreteInitialiserCandidates();
		var matches = this.getClassesWithConcreteInitialiser();
		
		this.getLogger().info(matches.size() + " concrete initialisers out of "+intfcs.size()+" are present");
		
		if (matches.size() != intfcs.size()) {
			Assertions.fail("Concrete initialisers missing for: " +
						intfcs.stream()
							.filter((e) -> !matches.contains(e))
							.map((e) -> e.getSimpleName())
							.reduce("", (s1, s2) -> s1 + ", " + s2)
					);
		}
	}
	
	/**
	 * @return All classes in the need of a concrete initialiser that actually
	 * have a corresponding concrete initialiser interface under {@link root}.
	 */
	public Collection<Class<?>> getClassesWithConcreteInitialiser() {
		var allFiles = this.getAllFiles(root);
		
		return List.of(
				this.getAllConcreteInitialiserCandidates().stream()
				.filter((c) -> allFiles.stream()
						.anyMatch((f) -> this.fileNameEquals(f,
								this.getConcreteInitialiserName(c))))
				.toArray(Class<?>[]::new)
				);
	}
	
	/**
	 * @return Whether the given initialiser file contains any methods
	 * that can be used in tests.
	 */
	public boolean containsTestableMethods(File f) {
		boolean containsTestableMethods = false;
		
		try {
			var content = Files.readString(f.toPath());
			content = this.removeWhitespaces(content);
			containsTestableMethods = content.matches(testableMethodPattern);
		} catch (IOException e) {
			return false;
		}
		
		return containsTestableMethods;
	}
	
	/**
	 * Checks if all classes in the need of an initialiser, which have their own
	 * methods that modify them (the ones they do not get from inheritance),
	 * have corresponding tests.
	 * 
	 * Only verifies, if there are corresponding test files. Does not check the
	 * unit tests they implement.
	 */
	@Test
	public void testAllInterfaceTestsPresent() {
		var allFiles = this.getAllFiles(root);
		var intfcs = this.getAllInitialiserCandidates();
		
		var matches = List.of(
				intfcs.stream()
				.filter((c) -> allFiles.stream()
						.anyMatch((f) -> {
							
							// Check if f is the corresponding initialiser file.
							if (!this.fileNameEquals(f, this.getInterfaceInitialiserName(c))) {
								return false;
							}
							
							return !this.containsTestableMethods(f) || allFiles.stream()
									.anyMatch((tf) -> fileNameEquals(tf, getTestName(c)));
						}))
				.toArray(Class<?>[]::new)
				);
		
		this.getLogger().info(matches.size()+" out of "+intfcs.size()+" interfaces are covered by tests");
		
		if (matches.size() != intfcs.size()) {
			var mismatches = List.of(intfcs.stream()
					.filter((e) -> !matches.contains(e))
					.toArray(Class<?>[]::new));
			
			Assertions.fail("Tests missing for: " +
						mismatches.stream()
							.map((e) -> e.getSimpleName())
							.reduce("", (s1, s2) -> s1 + ", " + s2)
							.substring(2)
					);
		}
	}
	
	/**
	 * Checks if {@link #testableMethodPattern} works as intended.
	 */
	@Test
	public void testPattern() {
		Assertions.assertTrue("defaulttypesetxyz(".matches(testableMethodPattern));
		Assertions.assertTrue("defaulttypeaddxyz(".matches(testableMethodPattern));
		Assertions.assertTrue("abcdefaulttypesetxyz(".matches(testableMethodPattern));
		Assertions.assertTrue("defaulttypeaddxyz(abc".matches(testableMethodPattern));
		Assertions.assertTrue("abcdefaulttypeaddxyz(cde".matches(testableMethodPattern));
		
		Assertions.assertFalse("defaulttypeaddxyzAssertion(".matches(testableMethodPattern));
		Assertions.assertFalse("defaulttypesetxyzAssertion(".matches(testableMethodPattern));
		Assertions.assertFalse("abcdefaulttypeaddxyzAssertion(".matches(testableMethodPattern));
		Assertions.assertFalse("defaulttypesetxyzAssertion(abc".matches(testableMethodPattern));
		Assertions.assertFalse("abcdefaulttypesetxyzAssertion(cde".matches(testableMethodPattern));
	}
}
