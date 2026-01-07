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
		if (eCls == null)
			return null;
		return eCls.getEAllStructuralFeatures().stream().filter((f) -> featName.equals(f.getName())).findFirst()
				.orElse(null);
	}

	public EStructuralFeature resolveOriginalFeature(String className, String featName) {
		return resolveOriginalFeature(resolveClass(className), featName);
	}

	public DerivedTargetFeature resolveDerivedFeature(EClass eCls, String featName) {
		if (eCls == null)
			return null;
		return provider.getTargetMetamodelDerivedFeatures().stream()
				.filter((f) -> f.getFeatEClass().isSuperTypeOf(eCls) && featName.equals(f.getFeatName())).findFirst()
				.orElse(null);
	}

	public DerivedTargetFeature resolveDerivedFeature(String className, String featName) {
		return resolveDerivedFeature(resolveClass(className), featName);
	}

	public TargetFeature getTargetFeature(EClass eCls, String featName) {
		var feat = resolveOriginalFeature(eCls, featName);

		if (feat != null) {
			if (!eCls.getEAllStructuralFeatures().contains(feat))
				throw new IllegalArgumentException(String.format(
						"The class (%s) does not support the original feature (%s)", eCls.getName(), feat.getName()));
			return new OriginalTargetFeature(eCls, feat);
		} else {
			var derivedFeat = resolveDerivedFeature(eCls, featName);
			if (derivedFeat == null)
				throw new IllegalArgumentException(String.format(
						"No derived feature with name (%s) found for the class (%s)", featName, eCls.getName()));
			return derivedFeat;
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
			if (currentCls == null)
				throw new IllegalStateException(String.format("Could not find an EClass for feature (%s) in %s",
						currentFeat.getFeatName(), serialisedTargetFeat));
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
