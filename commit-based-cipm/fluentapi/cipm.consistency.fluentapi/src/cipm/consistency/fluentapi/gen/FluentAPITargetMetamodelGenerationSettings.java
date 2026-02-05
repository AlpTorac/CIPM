package cipm.consistency.fluentapi.gen;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.net4j.util.collection.Pair;

public class FluentAPITargetMetamodelGenerationSettings {
	/**
	 * Yields the convenience overloads for the withXFeat method, which are
	 * independent of the concrete metamodel.
	 * 
	 * @return List of (Parameter type, new value expression) pairs for withXFeat
	 *         overloads for cases, where feat meets certain criteria.
	 */
	public static List<Pair<EClassifier, String>> getGlobalWithXFeatMethodOverrides(EStructuralFeature feat) {
		var pairs = new ArrayList<Pair<EClassifier, String>>();
		if (feat.getEType().equals(EcorePackage.Literals.EBIG_INTEGER)) {
			pairs.add(new Pair<>(EcorePackage.Literals.ELONG, "java.math.BigInteger.valueOf(%s)"));
			pairs.add(new Pair<>(EcorePackage.Literals.EINT, "java.math.BigInteger.valueOf(%s)"));
		}
		return pairs;
	}

	/**
	 * Override this method, if necessary.
	 * 
	 * @return List of (Parameter type, new value expression) pairs for withXFeat
	 *         overloads for metamodel specific cases, where feat meets certain
	 *         criteria (see concrete implementors for more details)
	 */
	public List<Pair<EClassifier, String>> getMetamodelSpecificWithXFeatMethodOverrides(EStructuralFeature feat) {
		return new ArrayList<>();
	}
}
