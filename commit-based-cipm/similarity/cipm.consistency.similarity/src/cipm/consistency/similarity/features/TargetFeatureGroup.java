package cipm.consistency.similarity.features;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import cipm.consistency.similarity.templates.SimilarityCheckingTemplateMethods;

public class TargetFeatureGroup {
	private final List<TargetFeatureChain> targetFeatChains;

	public TargetFeatureGroup(List<TargetFeatureChain> targetFeatChains) {
		this.targetFeatChains = List.copyOf(targetFeatChains);
	}

	public List<TargetFeatureChain> getTargetFeatureChains() {
		return List.copyOf(targetFeatChains);
	}

	public boolean hasTargetFeature(TargetFeature tf) {
		return this.targetFeatChains.stream().anyMatch((tfc) -> tfc.hasTargetFeature(tf));
	}

	public boolean isFeatureRelevant(EStructuralFeature feat) {
		return targetFeatChains.stream().anyMatch((tfc) -> tfc.isFeatureRelevant(feat));
	}

	public boolean isFeatureRelevant(EClass eCls, EStructuralFeature feat) {
		return targetFeatChains.stream().anyMatch((tfc) -> tfc.isFeatureRelevant(eCls, feat));
	}

	public boolean isDerivedFeatureRelevant(EClass eCls, String derivedFeatName) {
		return targetFeatChains.stream().anyMatch((tfc) -> tfc.isDerivedFeatureRelevant(eCls, derivedFeatName));
	}

	/*
	 * TODO Extract compare operation to its own type "ComparisonResult"
	 * 
	 * Enables logging similarity checking
	 */

//	public SimilarityCheckerComparison compare(EObject obj1, EObject obj2, TargetFeatureChain tfc) {
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
//			var comparisonResult = SimilarityCheckingTemplateMethods.compareValue(
//					val1.getLastFeatureResult().getTargetFeatureValue(),
//					val2.getLastFeatureResult().getTargetFeatureValue());
//			if (comparisonResult != Boolean.TRUE) {
//				return comparisonResult;
//			}
//		}
//		return true;
//	}
//
//	public Boolean compare(EObject obj1, EObject obj2) {
//		for (var tfc : targetFeatChains) {
//			var vals1 = tfc.computeAllFeatureChainValues(obj1);
//			var vals2 = tfc.computeAllFeatureChainValues(obj2);
//
//			if (vals1.size() != vals2.size()) {
//				return false;
//			}
//
//			for (int i = 0; i < vals1.size(); i++) {
//				var val1 = vals1.get(i);
//				var val2 = vals2.get(i);
//				var comparisonResult = SimilarityCheckingTemplateMethods.compareValue(
//						val1.getLastFeatureResult().getTargetFeatureValue(),
//						val2.getLastFeatureResult().getTargetFeatureValue());
//				if (comparisonResult != Boolean.TRUE) {
//					return comparisonResult;
//				}
//			}
//
//		}
//		return true;
//	}
}
