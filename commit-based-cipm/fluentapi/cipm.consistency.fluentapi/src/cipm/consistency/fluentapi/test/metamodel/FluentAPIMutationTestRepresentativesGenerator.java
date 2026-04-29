package cipm.consistency.fluentapi.test.metamodel;

import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;

import org.junit.jupiter.api.Assertions;

public class FluentAPIMutationTestRepresentativesGenerator {
	/**
	 * @return A minimal yet representative set of target metamodel's concrete
	 *         EClasses. Currently 1 EClass with no modifiable features, 1 EClass
	 *         with multiple modifiable features, 1 EClass with at least one
	 *         non-many-valued feature, 1 EClass with at least one many-valued
	 *         feature.
	 */
	public Set<EClass> getRepresentativeTargetMetamodelConcreteEClasses_BasedOnModifiability() {
		var allConcreteEClss = FluentAPIGenerationTestSettings.getMetamodelProvider()
				.getAllConcreteEClassedInOriginalMetamodel();
		var eClssToMutate = new LinkedHashSet<EClass>();

		// EClass without modifiable features
		allConcreteEClss.stream().filter((eCls) -> !eClssToMutate.contains(eCls)).filter(
				(eCls) -> FluentAPIGenerationTestSettings.getFeatureFilter().getModifiableFeatureCount(eCls) == 0)
				.limit(1).forEach(eClssToMutate::add);

		// EClass with multiple modifiable features
		allConcreteEClss.stream().filter((eCls) -> !eClssToMutate.contains(eCls)).filter(
				(eCls) -> FluentAPIGenerationTestSettings.getFeatureFilter().getModifiableFeatureCount(eCls) > 1)
				.limit(1).forEach(eClssToMutate::add);

		// EClass with at least one single-valued modifiable feature
		allConcreteEClss.stream().filter((eCls) -> !eClssToMutate.contains(eCls))
				.filter((eCls) -> FluentAPIGenerationTestSettings.getFeatureFilter().getModifiableFeatures(eCls)
						.stream().filter((f) -> !f.isMany()).count() > 0)
				.limit(1).forEach(eClssToMutate::add);

		// EClass with at least one many-valued modifiable feature
		allConcreteEClss.stream().filter((eCls) -> !eClssToMutate.contains(eCls))
				.filter((eCls) -> FluentAPIGenerationTestSettings.getFeatureFilter().getModifiableFeatures(eCls)
						.stream().filter((f) -> f.isMany()).count() > 0)
				.limit(1).forEach(eClssToMutate::add);

		Assertions.assertEquals(4, eClssToMutate.size(), "Not all supported EClasses are represented");

		return eClssToMutate;
	}
}
