package cipm.consistency.fluentapi.gen.metamodels.java;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClassifier;
import org.emftext.language.java.classifiers.ClassifiersPackage;
import org.emftext.language.java.types.TypesPackage;

import cipm.consistency.fluentapi.gen.FluentAPIMethodParameterOverload;
import cipm.consistency.fluentapi.gen.FluentAPITargetMetamodelGenerationSettings;

public class FluentAPIJavaMetamodelGenerationSettings extends FluentAPITargetMetamodelGenerationSettings {
	@Override
	public List<FluentAPIMethodParameterOverload> getMetamodelSpecificParameterOverloads(EClassifier eClassifier) {
		var pairs = new ArrayList<FluentAPIMethodParameterOverload>();

		if (eClassifier.equals(TypesPackage.Literals.TYPE_REFERENCE)) {
			pairs.add(new FluentAPIMethodParameterOverload(ClassifiersPackage.Literals.CLASSIFIER,
					// TODO Use constants for this part
					"this.toAPI().newClassifierReference().withTarget(%s).createNow()"));
		}

		return pairs;
	}
}
