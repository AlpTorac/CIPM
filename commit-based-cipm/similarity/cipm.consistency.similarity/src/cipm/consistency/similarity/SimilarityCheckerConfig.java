package cipm.consistency.similarity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.emftext.language.java.annotations.AnnotationsPackage;
import org.emftext.language.java.classifiers.ClassifiersPackage;
import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.commons.CommonsPackage;
import org.emftext.language.java.containers.CompilationUnit;
import org.emftext.language.java.containers.ContainersPackage;
import org.emftext.language.java.expressions.ExpressionsPackage;
import org.emftext.language.java.statements.Statement;
import org.emftext.language.java.statements.StatementListContainer;
import org.emftext.language.java.statements.StatementsPackage;

import cipm.consistency.similarity.features.DerivedTargetFeature;
import cipm.consistency.similarity.features.OriginalTargetFeature;
import cipm.consistency.similarity.features.TargetFeature;
import cipm.consistency.similarity.features.TargetFeatureChain;
import cipm.consistency.similarity.features.TargetFeatureGroup;
import cipm.consistency.similarity.templates.SimilarityCheckingTemplateMethods;

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

	private static TargetFeatureChain getChainWithDerivedTargetFeature(EClass initialECls,
			List<EStructuralFeature> derivedFeatureComponents,
			Function<EObject, Object> derivedFeatureComputationAlgorithm, boolean isMany, String derivedFeatureName) {
		return new TargetFeatureChain(List.of(new DerivedTargetFeature(initialECls, derivedFeatureComponents,
				derivedFeatureComputationAlgorithm, isMany, derivedFeatureName)));
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

	private static Boolean compare(EObject obj1, EObject obj2, TargetFeatureGroup tfg, List<EObject> comparedObj1,
			List<EObject> comparedObj2) {
		for (int i = 0; i < comparedObj1.size(); i++) {
			if (comparedObj1.get(i) == obj1 && comparedObj2.get(i) == obj2
					|| comparedObj1.get(i) == obj2 && comparedObj2.get(i) == obj1)
				return true;
		}

		for (var tfc : tfg.getTargetFeatureChains()) {
			var vals1 = tfc.computeAllFeatureChainValues(obj1);
			var vals2 = tfc.computeAllFeatureChainValues(obj2);

			if (vals1.size() != vals2.size()) {
				return false;
			}

			for (int i = 0; i < vals1.size(); i++) {
				var val1 = vals1.get(i);
				var val2 = vals2.get(i);

				var lastFeatVal1 = val1.getLastFeatureResult().getTargetFeatureValue();
				var lastFeatVal2 = val2.getLastFeatureResult().getTargetFeatureValue();

				if (!SimilarityCheckingTemplateMethods.isSimilarityPossible(lastFeatVal1, lastFeatVal2))
					return false;

				if (!SimilarityCheckingTemplateMethods.typesEqual(lastFeatVal1, lastFeatVal2))
					return false;

				if ((lastFeatVal1 != null && lastFeatVal2 != null
						&& EObject.class.isAssignableFrom(lastFeatVal1.getClass()))) {
					var subComparisonGroup = comparisonOps.getOrDefault(((EObject) lastFeatVal1).eClass(), null);
					var subComparisonResult = subComparisonGroup != null
							? compare((EObject) lastFeatVal1, (EObject) lastFeatVal2, subComparisonGroup, comparedObj1,
									comparedObj2)
							: true;
					if (subComparisonResult != Boolean.TRUE) {
						return subComparisonResult;
					}
				} else {
					var comparisonResult = SimilarityCheckingTemplateMethods.compareValue(lastFeatVal1, lastFeatVal2);
					if (comparisonResult != Boolean.TRUE) {
						return comparisonResult;
					}
				}

				comparedObj1.add(obj1);
				comparedObj2.add(obj2);
			}

		}
		return true;
	}

	public static boolean compare(EObject obj1, EObject obj2) {
		if (!SimilarityCheckingTemplateMethods.typesEqual(obj1, obj2))
			return false;

		var group = comparisonOps.getOrDefault(obj1.eClass(), null);
		return group != null ? compare(obj1, obj2, group, new ArrayList<>(), new ArrayList<>()) : true;
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
			addTargetFeatureGroup(List.of(getChainWithDerivedTargetFeature(eCls,
					List.of(CommonsPackage.Literals.NAMED_ELEMENT__NAME,
							CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES),
					(cls) -> ((ConcreteClassifier) cls).getQualifiedName(), false, "qualifiedName")));
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
				getChainWithDerivedTargetFeature(ContainersPackage.Literals.COMPILATION_UNIT,
						List.of(CommonsPackage.Literals.NAMED_ELEMENT__NAME), (cu) -> ((CompilationUnit) cu).getName(),
						false, "normalisedName"),
				getChainWithDerivedTargetFeature(ContainersPackage.Literals.COMPILATION_UNIT,
						List.of(CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES),
						(cu) -> ((CompilationUnit) cu).getNamespacesAsString(), false, "normalisedNamespaces")));

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
	}

	private static Statement getPred(Statement st) {
		if (st.eContainer() == null)
			return null;

		var con = ((StatementListContainer) st.eContainer());
		var stIdx = con.getStatements().indexOf(st);
		return stIdx > 0 ? con.getStatements().get(stIdx - 1) : null;
	}

	private static Statement getSucc(Statement st) {
		if (st.eContainer() == null)
			return null;

		var con = ((StatementListContainer) st.eContainer());
		var stIdx = con.getStatements().indexOf(st);
		return stIdx + 1 < con.getStatements().size() ? con.getStatements().get(stIdx + 1) : null;
	}

	private static void initForStatements() {
		addTargetFeatureGroup(List.of(
				getChainWithOriginalTargetFeature(StatementsPackage.Literals.EXPRESSION_STATEMENT,
						StatementsPackage.Literals.EXPRESSION_STATEMENT__EXPRESSION),
				getChainWithDerivedTargetFeature(StatementsPackage.Literals.EXPRESSION_STATEMENT, null,
						(est) -> getPred((Statement) est), false, "predecessor"),
				getChainWithDerivedTargetFeature(StatementsPackage.Literals.EXPRESSION_STATEMENT, null,
						(est) -> getSucc((Statement) est), false, "successor")));
	}

	/*
	 * TODO Enumerate relevant TargetFeatureChains. Note that the sub-chains might
	 * be irrelevant. Keep in mind that expanding all of them till getting a
	 * non-EObject value is not possible, due to recursive definitions (ex:
	 * TypeReferences, TypeParameters)
	 */
}
