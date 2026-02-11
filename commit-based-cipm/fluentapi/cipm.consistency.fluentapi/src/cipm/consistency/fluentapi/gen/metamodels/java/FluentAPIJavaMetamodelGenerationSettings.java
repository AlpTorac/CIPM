package cipm.consistency.fluentapi.gen.metamodels.java;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EOperation;
import org.emftext.language.java.classifiers.ClassifiersPackage;
import org.emftext.language.java.types.TypesPackage;

import cipm.consistency.fluentapi.gen.FluentAPIMethodOverload;
import cipm.consistency.fluentapi.gen.FluentAPIMethodParameterOverload;
import cipm.consistency.fluentapi.gen.FluentAPITargetMetamodelGenerationSettings;

public class FluentAPIJavaMetamodelGenerationSettings extends FluentAPITargetMetamodelGenerationSettings {
	private static final String typeReferenceParameterOverrideTemplate = "this.toAPI().newClassifierReference().withTarget(%s).createNow()";

	@Override
	public List<FluentAPIMethodOverload> getMetamodelSpecificParameterOverloads(EOperation op) {
		var pairs = new ArrayList<FluentAPIMethodOverload>();

		if (op.getEParameters().stream()
				.anyMatch((p) -> p.getEType() != null && p.getEType().equals(TypesPackage.Literals.TYPE_REFERENCE))) {
			var overload = new FluentAPIMethodParameterOverload();

			var paramExprs = new ArrayList<String>();
			var paramTypes = new ArrayList<EClassifier>();

			for (int i = 0; i < op.getEParameters().size(); i++) {
				var currentParam = op.getEParameters().get(i);
				if (currentParam.getEType().equals(TypesPackage.Literals.TYPE_REFERENCE)) {
					paramExprs.add(String.format(typeReferenceParameterOverrideTemplate, currentParam.getName()));
					paramTypes.add(ClassifiersPackage.Literals.CLASSIFIER);
				} else {
					paramExprs.add(currentParam.getName());
					paramTypes.add(currentParam.getEType());
				}
			}

			overload.setAdaptedParameterExpressions(paramExprs);
			overload.setNewParamTypes(paramTypes);
			pairs.add(overload);
		}

		return pairs;
	}
}
