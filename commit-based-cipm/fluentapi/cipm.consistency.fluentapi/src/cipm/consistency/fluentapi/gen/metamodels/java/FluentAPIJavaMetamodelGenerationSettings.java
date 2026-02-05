package cipm.consistency.fluentapi.gen.metamodels.java;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.net4j.util.collection.Pair;
import org.emftext.language.java.classifiers.ClassifiersPackage;
import org.emftext.language.java.types.TypesPackage;

import cipm.consistency.fluentapi.gen.FluentAPITargetMetamodelGenerationSettings;

public class FluentAPIJavaMetamodelGenerationSettings extends FluentAPITargetMetamodelGenerationSettings {
	@Override
	public List<Pair<EClassifier, String>> getMetamodelSpecificWithXFeatMethodOverrides(EStructuralFeature feat) {
		var pairs = new ArrayList<Pair<EClassifier, String>>();
		
		if (feat.getEType().equals(TypesPackage.Literals.TYPE_REFERENCE)) {
			pairs.add(new Pair<>(ClassifiersPackage.Literals.CLASSIFIER,
					// TODO Use constants for this part
					"this.toAPI().newClassifierReference().withTarget(%s).createNow()"));
		}
		
		return pairs;
	}
}
