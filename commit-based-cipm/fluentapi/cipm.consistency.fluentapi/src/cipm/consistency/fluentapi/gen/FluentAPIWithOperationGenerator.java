package cipm.consistency.fluentapi.gen;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.emftext.language.java.commons.Commentable;

public class FluentAPIWithOperationGenerator {
	private static final String genModelURL = "http://www.eclipse.org/emf/2002/GenModel";

	private List<EStructuralFeature> getAllEligibleFeats(EClass elemToInit) {
		var concreteCls = elemToInit.getEPackage().getEFactoryInstance().create(elemToInit).eClass();
		var allFeats = concreteCls.getEAllStructuralFeatures();
		return allFeats.stream()
				// TODO Add a filter to exclude certain features without hard-coding
				.filter((attr) -> !attr.getEContainingClass().getInstanceClass().isAssignableFrom(Commentable.class))
				// Enable if only features with EObject values are to be considered
//				.filter((attr) -> EObject.class.isAssignableFrom(attr.getEType().getInstanceClass()))
				.filter((attr) -> attr.isChangeable()).collect(Collectors.toCollection(ArrayList::new));
	}

	public List<EOperation> getAllWithOperationsFor(EClass elemToInit) {
		var feats = this.getAllEligibleFeats(elemToInit);

		/*
		 * 
		 * TODO Generate these operations
		 * 
		 * + withX_feat(param) : This + withX_featOfContainer() : This (uses the value
		 * of the same feature from container, only if either EAttribute or
		 * non-containment & non-container EReference)
		 * 
		 * + withoutX_feat() : This
		 * 
		 * + withAddedX_feat(param...) : This + withRemovedX_feat(param...) : This +
		 * withExactX_feat(param...) : This
		 * 
		 * + withAddedX_feat(paramList) : This + withRemovedX_feat(paramList) : This +
		 * withExactX_feat(paramList) : This
		 */
		return List.of();
	}

	public EOperation getWithOperationsFor(EStructuralFeature feat, EClass elemToInit) {
		var op = EcoreFactory.eINSTANCE.createEOperation();
		var elemInstanceName = elemToInit.getInstanceClass().getSimpleName();
		var featName = feat.getName();
		op.setName(String.format("with%s_%s", elemInstanceName, featName));

		// Add obj param to operation, which is the element to initialise
		var objParam = EcoreFactory.eINSTANCE.createEParameter();
		var objParamName = "objToInit";
		objParam.setEType(elemToInit);
		objParam.setName(objParamName);
		objParam.setLowerBound(1);
		objParam.setUpperBound(1);
		op.getEParameters().add(objParam);

		// Add feature param to operation, which will be the new value of obj.feat
		var featValParam = EcoreFactory.eINSTANCE.createEParameter();
		var featValParamName = "";
		featValParam.setEType(feat.getEType());
		if (!feat.isMany()) {
			featValParamName = "newFeatVal";
		} else {
			featValParamName = "addToFeatVal";
		}
		featValParam.setName(featValParamName);
		featValParam.setLowerBound(1);
		featValParam.setUpperBound(1);
		op.getEParameters().add(featValParam);

		// Add the method body
		var anno = EcoreFactory.eINSTANCE.createEAnnotation();
		anno.setSource(genModelURL);
		var bodyKey = "body";
		var bodyValue = getWithOperationBodyFor(objParamName, featValParamName, feat);

		anno.getDetails().put(bodyKey, bodyValue);

		op.getEAnnotations().add(anno);
		return op;
	}

	public String getWithOperationBodyFor(String objParamName, String featureValParamName, EStructuralFeature feat) {
		/*
		 * TODO Add hooks to creation methods for validation and assertions
		 */

		if (!feat.isMany()) {
			return String.format("%s.eSet(%s.eClass().getEStructuralFeature(\"%s\"), %s);", objParamName, objParamName,
					feat.getName(), featureValParamName);
		} else {
			return String.format(
					"var val = %s.eGet(%s.eClass().getEStructuralFeature(\"%s\"));" + System.lineSeparator()
							+ "((EList) val).add(%s);",
					objParamName, objParamName, feat.getName(), featureValParamName);
		}
	}
}
