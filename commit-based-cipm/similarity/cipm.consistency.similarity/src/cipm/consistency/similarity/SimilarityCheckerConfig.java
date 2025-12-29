package cipm.consistency.similarity;

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
	
	public static boolean compare(EObject obj1, EObject obj2) {
		if (!SimilarityCheckingTemplateMethods.typesEqual(obj1, obj2))
			return false;

		var group = comparisonOps.getOrDefault(obj1.eClass(), null);
		return group != null ? group.compare(obj1, obj2) : true;
	}

//	public static boolean compare(EObject obj1, EObject obj2, TargetFeatureChain tfc) {
//		var vals1 = tfc.computeAllFeatureChainValues(obj1);
//		var vals2 = tfc.computeAllFeatureChainValues(obj2);
//
//		if (vals1.size() != vals2.size()) {
//			return false;
//		}
//
//		for (int i = 0; i < vals1.size(); i++) {
//			var val1 = vals1.get(i);
//			var val2 = vals2.get(i);
//			if (SimilarityCheckingTemplateMethods.compareValue(val1.getLastFeature().getTargetFeatureValue(),
//					val2.getLastFeature().getTargetFeatureValue()) != Boolean.TRUE) {
//				return false;
//			}
//		}
//
//		return true;
//	}

	private SimilarityCheckerConfig() {
		init();
	}

	private static void init() {
		initForAnnotations();
		initForClassifiers();
		initForCommons();
		initForContainers();
		initForExpressions();
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
		addTargetFeatureGroup(List.of(getChainWithDerivedTargetFeature(ClassifiersPackage.Literals.CONCRETE_CLASSIFIER,
				List.of(CommonsPackage.Literals.NAMED_ELEMENT__NAME,
						CommonsPackage.Literals.NAMESPACE_AWARE_ELEMENT__NAMESPACES),
				(cls) -> ((ConcreteClassifier) cls).getQualifiedName(), false, "qualifiedName")));
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

	/*
	 * TODO Enumerate relevant TargetFeatureChains. Note that the sub-chains might
	 * be irrelevant. Keep in mind that expanding all of them till getting a
	 * non-EObject value is not possible, due to recursive definitions (ex:
	 * TypeReferences, TypeParameters)
	 * 
	 * TODO Implement exemplary AbstractFeatureComparers. Start with non-EObject
	 * values (i.e. literals)
	 */
}
