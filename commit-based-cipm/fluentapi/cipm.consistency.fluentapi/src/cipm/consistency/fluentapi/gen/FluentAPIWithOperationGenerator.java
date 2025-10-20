package cipm.consistency.fluentapi.gen;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EStructuralFeature;

import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;

public class FluentAPIWithOperationGenerator {
	private static final String genModelURL = "http://www.eclipse.org/emf/2002/GenModel";

	private static final String withXFeatNameTemplate = "with%s";
//	private static final String withXFeatOfContainerNameTemplate = "with%sOfContainer";
	private static final String withoutXFeatNameTemplate = "without%s";
	private static final String withAddedXFeatNameTemplate = "withAdded%s";
	private static final String withRemovedXFeatNameTemplate = "withRemoved%s";
//	private static final String withExactXFeatNameTemplate = "withExact%s";

	private static final String objToInitParamName = "objToInit";
	private static final String newFeatValParamName = "newFeatVal";

	private static final String addedFeatValParamName = "featValToAdd";
	private static final String removedFeatValParamName = "featValToRemove";

	private static final String withXFeatMethodBodyTemplate = FluentAPIMethodsUtil
			.callMethodAndReturnThis(FluentAPIMethodsUtil.getESetStatement("%s", "%s", "%s"));

	private static final String withoutXFeatMethodBodyTemplate = FluentAPIMethodsUtil
			.callMethodAndReturnThis(FluentAPIMethodsUtil.getEUnsetStatement("%s", "%s"));

	private static final String withAddedXFeatMethodBodyTemplate = FluentAPIMethodsUtil.callMethodAndReturnThis(
			FluentAPIMethodsUtil.addToEList(FluentAPIMethodsUtil.getEGetAsEList("%s", "%s"), "%s"));

	private static final String withRemovedXFeatMethodBodyTemplate = FluentAPIMethodsUtil.callMethodAndReturnThis(
			FluentAPIMethodsUtil.removeFromEList(FluentAPIMethodsUtil.getEGetAsEList("%s", "%s"), "%s"));

	private List<EStructuralFeature> getAllEligibleFeats(EClass elemToInit,
			FluentAPITargetMetamodelFeatureFilter featFilter) {
		return elemToInit.getEAllStructuralFeatures().stream()
				.filter((feat) -> featFilter.isFeatureEligible(elemToInit, feat))
				.collect(Collectors.toCollection(ArrayList::new));
	}

	public List<EOperation> generateAllWithOperationsFor(EClass initECls, EClass elemToInit,
			FluentAPITargetMetamodelFeatureFilter featFilter) {
		var feats = this.getAllEligibleFeats(elemToInit, featFilter);
		var ops = new ArrayList<EOperation>();

		for (var feat : feats) {
			if (!feat.isMany()) {
				ops.add(this.generateWithXFeat(initECls, elemToInit, feat));
				ops.add(this.generateWithoutXFeat(initECls, elemToInit, feat));
			} else {
				ops.add(this.generateWithAddedXFeat(initECls, elemToInit, feat));
				ops.add(this.generateWithRemovedXFeat(initECls, elemToInit, feat));
			}

			// TODO Add withX_featOfContainer() : This
			// TODO Add withExactX_feat(paramList) : This
		}
		return ops;
	}

	public EOperation generateWithXFeat(EClass initECls, EClass elemToInit, EStructuralFeature feat) {
		var objParam = getObjToInitParam(elemToInit);
		var newFeatValParam = getNewFeatValParam(feat);

		return FluentAPIGenerationUtil.generateEOperationWithBody(
				String.format(withXFeatNameTemplate, StringUtils.capitalize(feat.getName())), genModelURL, initECls,
				String.format(withXFeatMethodBodyTemplate, objParam.getName(), objParam.getName(), feat.getName(),
						newFeatValParam.getName()),
				objParam, newFeatValParam);
	}

	public EOperation generateWithoutXFeat(EClass initECls, EClass elemToInit, EStructuralFeature feat) {
		var objParam = getObjToInitParam(elemToInit);

		return FluentAPIGenerationUtil.generateEOperationWithBody(
				String.format(withoutXFeatNameTemplate, StringUtils.capitalize(feat.getName())), genModelURL, initECls,
				String.format(withoutXFeatMethodBodyTemplate, objParam.getName(), objParam.getName(), feat.getName()),
				objParam);
	}

	public EOperation generateWithAddedXFeat(EClass initECls, EClass elemToInit, EStructuralFeature feat) {
		var objParam = getObjToInitParam(elemToInit);
		var addedFeatValParam = getAddedFeatValParam(feat);

		return FluentAPIGenerationUtil
				.generateEOperationWithBody(
						String.format(withAddedXFeatNameTemplate, StringUtils.capitalize(feat.getName())), genModelURL,
						initECls, String.format(withAddedXFeatMethodBodyTemplate, objParam.getName(),
								objParam.getName(), feat.getName(), addedFeatValParam.getName()),
						objParam, addedFeatValParam);
	}

	public EOperation generateWithRemovedXFeat(EClass initECls, EClass elemToInit, EStructuralFeature feat) {
		var objParam = getObjToInitParam(elemToInit);
		var removedFeatValParam = getRemovedFeatValParam(feat);

		return FluentAPIGenerationUtil
				.generateEOperationWithBody(
						String.format(withRemovedXFeatNameTemplate, StringUtils.capitalize(feat.getName())),
						genModelURL, initECls, String.format(withRemovedXFeatMethodBodyTemplate, objParam.getName(),
								objParam.getName(), feat.getName(), removedFeatValParam.getName()),
						objParam, removedFeatValParam);
	}

	public EParameter getObjToInitParam(EClass elemToInit) {
		return FluentAPIGenerationUtil.generateSingleValuedEParameter(objToInitParamName, elemToInit);
	}

	public EParameter getNewFeatValParam(EStructuralFeature feat) {
		return FluentAPIGenerationUtil.generateSingleValuedEParameter(newFeatValParamName, feat.getEType());
	}

	public EParameter getAddedFeatValParam(EStructuralFeature feat) {
		return FluentAPIGenerationUtil.generateSingleValuedEParameter(addedFeatValParamName, feat.getEType());
	}

	public EParameter getRemovedFeatValParam(EStructuralFeature feat) {
		return FluentAPIGenerationUtil.generateSingleValuedEParameter(removedFeatValParamName, feat.getEType());
	}

	public String getWithXFeatOperationBodyFor(String objParamName, String featureValParamName,
			EStructuralFeature feat) {
		/*
		 * TODO Add hooks to creation methods for validation and assertions
		 */

		if (!feat.isMany()) {

		} else {
			return String.format(
					"var val = %s.eGet(%s.eClass().getEStructuralFeature(\"%s\"));" + System.lineSeparator()
							+ "((EList) val).add(%s);",
					objParamName, objParamName, feat.getName(), featureValParamName);
		}
		return String.format("%s.eSet(%s.eClass().getEStructuralFeature(\"%s\"), %s);", objParamName, objParamName,
				feat.getName(), featureValParamName);
	}
}
