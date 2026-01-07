package cipm.consistency.similarity;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.emftext.language.java.annotations.AnnotationsPackage;
import org.emftext.language.java.classifiers.ClassifiersPackage;
import org.emftext.language.java.commons.CommonsPackage;
import org.emftext.language.java.containers.ContainersPackage;
import org.emftext.language.java.expressions.ExpressionsPackage;
import org.emftext.language.java.statements.StatementsPackage;

import cipm.consistency.similarity.features.DerivedTargetFeature;
import cipm.consistency.similarity.features.OriginalTargetFeature;
import cipm.consistency.similarity.features.TargetFeature;
import cipm.consistency.similarity.features.TargetFeatureChain;
import cipm.consistency.similarity.features.TargetFeatureGroup;

public final class SimilarityCheckerConfig {

	private static final Map<EClass, TargetFeatureGroup> comparisonOps = new LinkedHashMap<>();

	static {
		init();
	}

	private static void addTargetFeatureGroup(List<TargetFeatureChain> tfcs) {
		comparisonOps.put(tfcs.get(0).getFirstFeature().getFeatEClass(), new TargetFeatureGroup(tfcs));
	}

	private static TargetFeatureChain getChainWithOriginalTargetFeature(EClass initialECls, EStructuralFeature feat) {
		return new TargetFeatureChain(List.of(new OriginalTargetFeature(initialECls, feat)));
	}

	private static TargetFeatureChain getChainWithDerivedTargetFeature(EClass initialECls, Class<?> featType,
			List<EStructuralFeature> derivedFeatureComponents, String derivedFeatureName) {
		return new TargetFeatureChain(
				List.of(new DerivedTargetFeature(initialECls, featType, derivedFeatureComponents, derivedFeatureName)));
	}

	public static boolean isTypeRelevant(EClass eCls) {
		return comparisonOps.keySet().stream().anyMatch((t) -> t.isSuperTypeOf(eCls));
	}

	public static boolean isDerivedFeatureRelevant(EClass eCls, String derivedFeatName) {
		if (!comparisonOps.containsKey(eCls))
			return false;

		return comparisonOps.get(eCls).isDerivedFeatureRelevant(eCls, derivedFeatName);
	}

	public static boolean isFeatureRelevant(EClass eCls, EStructuralFeature feat) {
		if (!comparisonOps.containsKey(eCls))
			return false;

		return comparisonOps.get(eCls).isFeatureRelevant(eCls, feat);
	}

	public static boolean isFeatureRelevant(EClass eCls, TargetFeature targetFeat) {
		if (!comparisonOps.containsKey(eCls))
			return false;

		return comparisonOps.get(eCls).hasTargetFeature(targetFeat);
	}

	private SimilarityCheckerConfig() {
		init();
	}

	private static void init() {
		initForAnnotations();
		initForClassifiers();
		initForCommons();
		initForContainers();
		initForExpressions();
		initForStatements();
	}

	// AnnotationsSimilaritySwitch
	private static void initForAnnotations() {
		// caseAnnotationInstance
		addTargetFeatureGroup(List.of(
				getChainWithOriginalTargetFeature(AnnotationsPackage.Literals.ANNOTATION_INSTANCE,
						AnnotationsPackage.Literals.ANNOTATION_INSTANCE__ANNOTATION),
				getChainWithOriginalTargetFeature(AnnotationsPackage.Literals.ANNOTATION_INSTANCE,
						CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES)));

		// caseAnnotationAttributeSetting
		addTargetFeatureGroup(
				List.of(getChainWithOriginalTargetFeature(AnnotationsPackage.Literals.ANNOTATION_ATTRIBUTE_SETTING,
						AnnotationsPackage.Literals.ANNOTATION_ATTRIBUTE_SETTING__ATTRIBUTE)));
	}

	// ClassifiersSimilaritySwitch
	private static void initForClassifiers() {
		// caseConcreteClassifier

		// TODO Expand overarching cases
		for (var eCls : List.of(ClassifiersPackage.Literals.CONCRETE_CLASSIFIER, ClassifiersPackage.Literals.CLASS,
				ClassifiersPackage.Literals.ENUMERATION, ClassifiersPackage.Literals.ANNOTATION,
				ClassifiersPackage.Literals.INTERFACE)) {
			addTargetFeatureGroup(List.of(getChainWithDerivedTargetFeature(eCls, String.class,
					List.of(CommonsPackage.Literals.NAMED_ELEMENT__NAME,
							CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES),
					"qualifiedName")));
		}
	}

	// CommonsSimilaritySwitch (CURRENTLY UNUSED)
	private static void initForCommons() {
		// caseNamedElement (CURRENTLY UNUSED)
		addTargetFeatureGroup(List.of(getChainWithOriginalTargetFeature(CommonsPackage.Literals.NAMED_ELEMENT,
				CommonsPackage.Literals.NAMED_ELEMENT__NAME)));
	}

	// ContainersSimilaritySwitch
	private static void initForContainers() {
		// caseCompilationUnit
		addTargetFeatureGroup(List.of(
				getChainWithDerivedTargetFeature(ContainersPackage.Literals.COMPILATION_UNIT, String.class,
						List.of(CommonsPackage.Literals.NAMED_ELEMENT__NAME), "normalisedName"),
				getChainWithDerivedTargetFeature(ContainersPackage.Literals.COMPILATION_UNIT, String.class,
						List.of(CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES), "normalisedNamespaces")));

		// casePackage
		addTargetFeatureGroup(List.of(getChainWithOriginalTargetFeature(ContainersPackage.Literals.PACKAGE,
				CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES)));

		// caseModule
		addTargetFeatureGroup(List.of(getChainWithOriginalTargetFeature(ContainersPackage.Literals.MODULE,
				CommonsPackage.Literals.NAMED_ELEMENT__NAME)));
	}

	private static void initForExpressions() {
		// caseAssignmentExpression
		addTargetFeatureGroup(List.of(
				getChainWithOriginalTargetFeature(ExpressionsPackage.Literals.ASSIGNMENT_EXPRESSION,
						ExpressionsPackage.Literals.ASSIGNMENT_EXPRESSION__CHILD),
				getChainWithOriginalTargetFeature(ExpressionsPackage.Literals.ASSIGNMENT_EXPRESSION,
						ExpressionsPackage.Literals.ASSIGNMENT_EXPRESSION__ASSIGNMENT_OPERATOR),
				getChainWithOriginalTargetFeature(ExpressionsPackage.Literals.ASSIGNMENT_EXPRESSION,
						ExpressionsPackage.Literals.ASSIGNMENT_EXPRESSION__VALUE)));

		// caseEqualityExpression
		addTargetFeatureGroup(List.of(
				getChainWithOriginalTargetFeature(ExpressionsPackage.Literals.EQUALITY_EXPRESSION,
						ExpressionsPackage.Literals.EQUALITY_EXPRESSION__EQUALITY_OPERATORS),
				getChainWithOriginalTargetFeature(ExpressionsPackage.Literals.EQUALITY_EXPRESSION,
						ExpressionsPackage.Literals.EQUALITY_EXPRESSION__CHILDREN)));

		// caseRelationExpression
		addTargetFeatureGroup(List.of(
				getChainWithOriginalTargetFeature(ExpressionsPackage.Literals.RELATION_EXPRESSION,
						ExpressionsPackage.Literals.RELATION_EXPRESSION__RELATION_OPERATORS),
				getChainWithOriginalTargetFeature(ExpressionsPackage.Literals.RELATION_EXPRESSION,
						ExpressionsPackage.Literals.RELATION_EXPRESSION__CHILDREN)));

		// TODO continue ...

	}

	private static void initForStatements() {
		addTargetFeatureGroup(List.of(
				getChainWithOriginalTargetFeature(StatementsPackage.Literals.EXPRESSION_STATEMENT,
						StatementsPackage.Literals.EXPRESSION_STATEMENT__EXPRESSION),
				getChainWithDerivedTargetFeature(StatementsPackage.Literals.EXPRESSION_STATEMENT,
						org.emftext.language.java.statements.Statement.class, null, "predecessor"),
				getChainWithDerivedTargetFeature(StatementsPackage.Literals.EXPRESSION_STATEMENT,
						org.emftext.language.java.statements.Statement.class, null, "successor")));
	}

	/*
	 * TODO Enumerate relevant TargetFeatureChains. Note that the sub-chains might
	 * be irrelevant. Keep in mind that expanding all of them till getting a
	 * non-EObject value is not possible, due to recursive definitions (ex:
	 * TypeReferences, TypeParameters)
	 */
}
