package cipm.consistency.similarity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

import cipm.consistency.similarity.features.DerivedTargetFeature;
import cipm.consistency.similarity.features.OriginalTargetFeature;
import cipm.consistency.similarity.features.TargetFeature;
import cipm.consistency.similarity.features.TargetFeatureChain;

public class MetamodelResolver {
	private final MetamodelProvider provider;
	private static final String featureSeparator = "\\.";

	public MetamodelResolver(MetamodelProvider provider) {
		this.provider = provider;
	}

	public EClass resolveClass(String className) {
		return provider.getTargetMetamodelEClasses().stream().filter((t) -> className.equals(t.getName())).findFirst()
				.orElse(null);
	}

	public List<EStructuralFeature> resolveOriginalFeature(String featName) {
		return provider.getTargetMetamodelFeatures().stream().filter((f) -> featName.equals(f.getName()))
				.collect(Collectors.toList());
	}

	public EStructuralFeature resolveOriginalFeature(EClass eCls, String featName) {
//		var featInECls = eCls.getEAllStructuralFeatures().stream().filter((f) -> featName.equals(f.getName()))
//				.findFirst().orElse(null);
//
//		if (featInECls != null)
//			return featInECls;
//
//		provider.getTargetMetamodelEClasses().stream().filter((eClsInPac) -> eCls.isSuperTypeOf(eClsInPac))
//				.collect(Collectors.toList());
//
//		// Check the sub-EClasses too, since some EClasses serve as umbrella types for
//		// others (such as TypeReference)
//		var subEClss = provider.getTargetMetamodelEClasses().stream()
//				.filter((eClsInPac) -> eCls.isSuperTypeOf(eClsInPac)).collect(Collectors.toList());
//
//		for (var subECls : subEClss) {
//			for (var feat : subECls.getEStructuralFeatures()) {
//				if (feat.getName().equals(featName))
//					return feat;
//			}
//		}

		return eCls.getEAllStructuralFeatures().stream().filter((f) -> featName.equals(f.getName())).findFirst()
				.orElse(null);
	}

	public EStructuralFeature resolveOriginalFeature(String className, String featName) {
		return resolveOriginalFeature(resolveClass(className), featName);
	}

	public DerivedTargetFeature resolveDerivedFeature(String featName) {
		return provider.getTargetMetamodelDerivedFeatures().stream().filter((f) -> featName.equals(f.getFeatName()))
				.findFirst().orElse(null);
	}

	public TargetFeature getTargetFeature(EClass eCls, String featName) {
		var feat = resolveOriginalFeature(eCls, featName);

		if (feat != null) {
			return new OriginalTargetFeature(eCls, feat);
		} else {
			return resolveDerivedFeature(featName);
		}
	}

	public TargetFeature getTargetFeature(String className, String featName) {
		return getTargetFeature(resolveClass(className), featName);
	}

	public TargetFeatureChain getTargetFeatureChain(String serialisedTargetFeat) {
		var featChainComponents = serialisedTargetFeat.split(featureSeparator);

		var initialClsName = featChainComponents[0];
		EClass currentCls = resolveClass(initialClsName);

		var feats = new ArrayList<TargetFeature>();

		for (int i = 1; i < featChainComponents.length; i++) {
			var cmp = featChainComponents[i];
			var currentFeat = getTargetFeature(currentCls, cmp);
			if (!currentFeat.getFeatEClass().isSuperTypeOf(currentCls)) {
				throw new IllegalArgumentException(
						String.format("The class (%s) does not support the feature (%s) in %s", currentCls.getName(),
								currentFeat.getFeatName(), serialisedTargetFeat));
			}
			feats.add(currentFeat);
			currentCls = getEClassForClass(currentFeat.getFeatType());
		}

		return new TargetFeatureChain(feats);
	}

	private EClass getEClassForClass(Class<?> cls) {
		for (var eCls : provider.getTargetMetamodelEClasses()) {
			if (eCls.getInstanceClass().equals(cls))
				return eCls;
		}
		return null;
	}
}
