package cipm.consistency.fluentapi.gen.init;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.FluentAPITargetMetamodelFeatureFilter;
import cipm.consistency.fluentapi.gen.FluentAPITargetMetamodelPackageProvider;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;

public class FluentAPIWithOperationGenerator {
	//
	// Method names
	//

	// %s: Feature name
	private static final String withXFeatNameTemplate = "with%s";
	private static final String withXFeatDocumentationTemplate = "Sets the value of the feature %s in this.getCurrentElement() to the given value.";

	// %s: Feature name
	private static final String withXFeatOfContainerNameTemplate = "with%sOfContainer";
	private static final String withXFeatOfContainerDocumentationTemplate = "Sets the value of the feature %s in this.getCurrentElement() to the value of the same feature in this.getCurrentElement().eContainer(), i.e. the container of this.getCurrentElement(). Assumes this.getCurrentElement() to be contained in an elligible container.";

	// %s: Feature name
	private static final String withoutXFeatNameTemplate = "without%s";
	private static final String withoutXFeatDocumentationTemplate = "Unsets the value of the feature %s in this.getCurrentElement(), which sets its value to null.";

	// %s: Feature name
	private static final String withAddedXFeatNameTemplate = "withAdded%s";
	private static final String withAddedXFeatDocumentationTemplate = "Adds the given values to the current values of the feature %s in this.getCurrentElement().";

	// %s: Feature name
	private static final String withRemovedXFeatNameTemplate = "withRemoved%s";
	private static final String withRemovedXFeatDocumentationTemplate = "Removes the given values from the current values of the feature %s in this.getCurrentElement().";

	// %s: Feature name
	// %s: Feature name
	private static final String withExactXFeatNameTemplate = "withExact%s";
	private static final String withExactXFeatDocumentationTemplate = "Sets the value of the (many-valued) feature %s in this.getCurrentElement() to the given value. This method is the counterpart of withX(...) methods for many-valued features.";

	//
	// Parameters
	//

	private static final String newFeatValParamName = "newFeatVal";
	// %s: Feature name
	private static final String newFeatValParamDocumentationTemplate = "The new value of the feature %s, which will replace its current value in the initialised object this.getCurrentElement()";

	private static final String addedFeatValParamName = "featValToAdd";
	// %s: Feature name
	private static final String addedFeatValParamDocumentationTemplate = "for the feature %s, which will be added to its current values in the initialised object this.getCurrentElement().";
	private static final String addedSingleFeatValParamDocumentationTemplate = "Value "
			+ addedFeatValParamDocumentationTemplate;
	private static final String addedListFeatValParamDocumentationTemplate = "Values "
			+ addedFeatValParamDocumentationTemplate;

	private static final String removedFeatValParamName = "featValToRemove";
	// %s: Feature name
	private static final String removedFeatValParamDocumentationTemplate = "for the feature %s, which will be removed from its current values in the initialised object this.getCurrentElement().";
	private static final String removedSingleFeatValParamDocumentationTemplate = "Value "
			+ removedFeatValParamDocumentationTemplate;
	private static final String removedListFeatValParamDocumentationTemplate = "Values "
			+ removedFeatValParamDocumentationTemplate;

	private static final String exactFeatValParamName = "exactFeatVals";
	// %s: Feature name
	private static final String exactFeatValParamDocumentationTemplate = "Values for the feature %s, which will replace its current value in the initialised object this.getCurrentElement(). Afterward the value of the feature will be exactly the given values.";

	//
	// Method bodies
	//

	private static final String withXFeatMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			// %s: Feature name
			// %s: Feature value
			"this.getCurrentElement().eSet(this.getCurrentElement().eClass().getEStructuralFeature(\"%s\"), %s)",
			//
			"return this");

	private static final String withoutXFeatMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			// %s: Feature name
			"this.getCurrentElement().eUnset(this.getCurrentElement().eClass().getEStructuralFeature(\"%s\"))",
			//
			"return this");

	private static final String withAddedXFeatMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			// %s: Feature name
			// %s: Feature value
			"((org.eclipse.emf.common.util.EList) this.getCurrentElement().eGet(this.getCurrentElement().eClass().getEStructuralFeature(\"%s\"))).add(%s)",
			//
			"return this");

	private static final String withRemovedXFeatMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			// %s: Feature name
			// %s: Feature value
			"((org.eclipse.emf.common.util.EList) this.getCurrentElement().eGet(this.getCurrentElement().eClass().getEStructuralFeature(\"%s\"))).remove(%s)",
			//
			"return this");

	private static final String withAddedXListFeatMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			// %s: Feature name
			// %s: Feature value
			"((org.eclipse.emf.common.util.EList) this.getCurrentElement().eGet(this.getCurrentElement().eClass().getEStructuralFeature(\"%s\"))).addAll(%s)",
			//
			"return this");

	private static final String withAddedXArrayFeatMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			// %s: Feature name
			// %s: Feature value
			"((org.eclipse.emf.common.util.EList) this.getCurrentElement().eGet(this.getCurrentElement().eClass().getEStructuralFeature(\"%s\"))).addAll(java.util.List.of(%s))",
			//
			"return this");

	private static final String withRemovedXListFeatMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			// %s: Feature name
			// %s: Feature value
			"((org.eclipse.emf.common.util.EList) this.getCurrentElement().eGet(this.getCurrentElement().eClass().getEStructuralFeature(\"%s\"))).removeAll(%s)",
			//
			"return this");

	private static final String withRemovedXArrayFeatMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			// %s: Feature name
			// %s: Feature value
			"((org.eclipse.emf.common.util.EList) this.getCurrentElement().eGet(this.getCurrentElement().eClass().getEStructuralFeature(\"%s\"))).removeAll(java.util.List.of(%s))",
			//
			"return this");

	private static final String withExactXFeatMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			// %s: Feature name
			"var list = (org.eclipse.emf.common.util.EList) this.getCurrentElement().eGet(this.getCurrentElement().eClass().getEStructuralFeature(\"%s\"))",
			//
			"list.clear()",
			// %s: Feature value
			"list.addAll(%s)",
			//
			"return this");

	private static final String withExactXArrayFeatMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			// %s: Feature name
			"var list = (org.eclipse.emf.common.util.EList) this.getCurrentElement().eGet(this.getCurrentElement().eClass().getEStructuralFeature(\"%s\"))",
			//
			"list.clear()",
			// %s: Feature value
			"list.addAll(java.util.List.of(%s))",
			//
			"return this");

	private static final String withXFeatOfContainerMethodBodyForManyValuedFeatTemplate = FluentAPIMethodsUtil.joinLOC(
			// %s: Feature name
			// %s: Feature name
			"withExact%s((org.eclipse.emf.common.util.EList) this.getCurrentElement().eContainer().eGet(this.getCurrentElement().eClass().getEStructuralFeature(\"%s\")))",
			//
			"return this");

	private static final String withXFeatOfContainerMethodBodyForSingleValuedFeatTemplate = FluentAPIMethodsUtil
			.joinLOC(
					// %s: Feature name
					// %s: Feature value type
					// %s: Feature name
					"with%s((%s) this.getCurrentElement().eContainer().eGet(this.getCurrentElement().eClass().getEStructuralFeature(\"%s\")))",
					//
					"return this");

	private List<EStructuralFeature> getAllEligibleFeats(EClass elemToInit,
			FluentAPITargetMetamodelFeatureFilter featFilter) {
		return elemToInit.getEAllStructuralFeatures().stream()
				.filter((feat) -> featFilter.isFeatureEligible(elemToInit, feat))
				.collect(Collectors.toCollection(ArrayList::new));
	}

	public List<EOperation> generateAllWithOperationsFor(EClass initECls, EClass elemToInit,
			FluentAPITargetMetamodelPackageProvider eClassProvider, FluentAPITargetMetamodelFeatureFilter featFilter) {
		var feats = this.getAllEligibleFeats(elemToInit, featFilter);
		var ops = new ArrayList<EOperation>();

		for (var feat : feats) {
			if (!feat.isMany()) {
				ops.addAll(this.generateWithXFeat(initECls, elemToInit, feat));
				ops.add(this.generateWithoutXFeat(initECls, elemToInit, feat));

				if (isEligibleForXOfContainer(elemToInit, feat, eClassProvider)) {
					// TODO Do not use eContainer(), use eGet(featName) instead
					ops.add(this.generateWithXFeatOfContainerForSingleValued(initECls, elemToInit, feat));
				}

			} else {
				ops.add(this.generateWithAddedXFeat(initECls, elemToInit, feat));
				ops.addAll(this.generateWithAddedXListFeat(initECls, elemToInit, feat));
				ops.add(this.generateWithRemovedXFeat(initECls, elemToInit, feat));
				ops.addAll(this.generateWithRemovedXListFeat(initECls, elemToInit, feat));
				ops.addAll(this.generateWithExactXFeat(initECls, elemToInit, feat));

				if (isEligibleForXOfContainer(elemToInit, feat, eClassProvider)) {
					// TODO Check for container eligibility and set the "EOpposites" as well
					// EOpposites are not always clear, especially it is a 1 to many EReference list
					// Use the list of EReferences you find with isEligibleForXOfContainer and
					// set the bidirectional reference: pac.withModule(mod) THEN mod.withPackage()
					//
					// TODO Do not use eContainer(), use eGet(featName) instead
					ops.add(this.generateWithXFeatOfContainerForManyValued(initECls, elemToInit, feat));
				}
			}
		}
		return ops;
	}

	private boolean isEligibleForXOfContainer(EClass elemToInit, EStructuralFeature feat,
			FluentAPITargetMetamodelPackageProvider eClassProvider) {
		// Containment EReferences are not eligible here, because their contents would
		// get shifted while calling the withXFeatOfContainer method
		if (feat instanceof EReference && ((EReference) feat).isContainment())
			return false;

		// Ensure that elemToInit instances have the change of having a container
		// that supports feat
		var allEClasses = eClassProvider.getAllTargetMetamodelConcreteEClasses();
		return allEClasses.stream().anyMatch((eCls) -> eCls.getEAllReferences().stream().anyMatch((ref) -> {
			var refType = ref.getEType();
			return isContainmentReferenceFor(elemToInit, ref)
					&& ((EClass) refType).getEAllStructuralFeatures().contains(feat);
		}));
	}

	private boolean isContainmentReferenceFor(EClass elemToInit, EStructuralFeature potentialContainmentFeat) {
		var refType = potentialContainmentFeat.getEType();
		var refTypeCls = refType.getInstanceClass();
		return refType instanceof EClass && refTypeCls.isAssignableFrom(elemToInit.getInstanceClass());
	}

	private List<EOperation> generateWithXFeat(EClass initECls, EClass elemToInit, EStructuralFeature feat) {
		var ops = new ArrayList<EOperation>();
		var originalOpNewFeatValParam = getNewFeatValParam(feat);

		var originalOp = FluentAPIGenerationUtil.generateEOperationWithBodyAndDocumentation(
				String.format(withXFeatNameTemplate, StringUtils.capitalize(feat.getName())), initECls,
				String.format(withXFeatMethodBodyTemplate, feat.getName(), originalOpNewFeatValParam.getName()),
				String.format(withXFeatDocumentationTemplate, feat.getName()), originalOpNewFeatValParam);
		ops.add(originalOp);

		if (originalOpNewFeatValParam.getEType().equals(EcorePackage.Literals.EBIG_INTEGER)) {
			var longOpNewFeatValParam = getNewFeatValParam(feat);
			longOpNewFeatValParam.setEType(EcorePackage.Literals.ELONG);
			var longOp = FluentAPIGenerationUtil.generateEOperationWithBodyAndDocumentation(
					String.format(withXFeatNameTemplate, StringUtils.capitalize(feat.getName())), initECls,
					String.format(withXFeatMethodBodyTemplate, feat.getName(),
							String.format("java.math.BigInteger.valueOf(%s)", longOpNewFeatValParam.getName())),
					String.format(withXFeatDocumentationTemplate, feat.getName()), longOpNewFeatValParam);
			ops.add(longOp);

			var intOpNewFeatValParam = getNewFeatValParam(feat);
			intOpNewFeatValParam.setEType(EcorePackage.Literals.EINT);
			var intOp = FluentAPIGenerationUtil.generateEOperationWithBodyAndDocumentation(
					String.format(withXFeatNameTemplate, StringUtils.capitalize(feat.getName())), initECls,
					String.format(withXFeatMethodBodyTemplate, feat.getName(),
							String.format("java.math.BigInteger.valueOf(%s)", intOpNewFeatValParam.getName())),
					String.format(withXFeatDocumentationTemplate, feat.getName()), intOpNewFeatValParam);
			ops.add(intOp);
		}

		return ops;
	}

	private EOperation generateWithoutXFeat(EClass initECls, EClass elemToInit, EStructuralFeature feat) {
		return FluentAPIGenerationUtil.generateEOperationWithBodyAndDocumentation(
				String.format(withoutXFeatNameTemplate, StringUtils.capitalize(feat.getName())), initECls,
				String.format(withoutXFeatMethodBodyTemplate, feat.getName()),
				String.format(withoutXFeatDocumentationTemplate, feat.getName()));
	}

	private EOperation generateWithAddedXFeat(EClass initECls, EClass elemToInit, EStructuralFeature feat) {
		var addedFeatValParam = getAddedFeatValParam(feat);

		return FluentAPIGenerationUtil.generateEOperationWithBodyAndDocumentation(
				String.format(withAddedXFeatNameTemplate, StringUtils.capitalize(feat.getName())), initECls,
				String.format(withAddedXFeatMethodBodyTemplate, feat.getName(), addedFeatValParam.getName()),
				String.format(withAddedXFeatDocumentationTemplate, feat.getName()), addedFeatValParam);
	}

	private EOperation generateWithRemovedXFeat(EClass initECls, EClass elemToInit, EStructuralFeature feat) {
		var removedFeatValParam = getRemovedFeatValParam(feat);

		return FluentAPIGenerationUtil.generateEOperationWithBodyAndDocumentation(
				String.format(withRemovedXFeatNameTemplate, StringUtils.capitalize(feat.getName())), initECls,
				String.format(withRemovedXFeatMethodBodyTemplate, feat.getName(), removedFeatValParam.getName()),
				String.format(withRemovedXFeatDocumentationTemplate, feat.getName()), removedFeatValParam);
	}

	private List<EOperation> generateWithAddedXListFeat(EClass initECls, EClass elemToInit, EStructuralFeature feat) {
		var opList = new ArrayList<EOperation>();

		var eListAddedFeatValParam = getAddedListFeatValParam(feat);
		opList.add(FluentAPIGenerationUtil.generateEOperationWithBodyAndDocumentation(
				String.format(withAddedXFeatNameTemplate, StringUtils.capitalize(feat.getName())), initECls,
				String.format(withAddedXListFeatMethodBodyTemplate, feat.getName(), eListAddedFeatValParam.getName(),
						eListAddedFeatValParam.getName()),
				String.format(withAddedXFeatDocumentationTemplate, feat.getName()), eListAddedFeatValParam));

		var arrayAddedFeatValParam = getAddedArrayFeatValParam(feat);
		opList.add(FluentAPIGenerationUtil.generateEOperationWithBodyAndDocumentation(
				String.format(withAddedXFeatNameTemplate, StringUtils.capitalize(feat.getName())), initECls,
				String.format(withAddedXArrayFeatMethodBodyTemplate, feat.getName(), arrayAddedFeatValParam.getName(),
						arrayAddedFeatValParam.getName()),
				String.format(withAddedXFeatDocumentationTemplate, feat.getName()), arrayAddedFeatValParam));

		return opList;
	}

	private List<EOperation> generateWithRemovedXListFeat(EClass initECls, EClass elemToInit, EStructuralFeature feat) {
		var opList = new ArrayList<EOperation>();

		var eListRemovedFeatValParam = getRemovedListFeatValParam(feat);
		opList.add(FluentAPIGenerationUtil.generateEOperationWithBodyAndDocumentation(
				String.format(withRemovedXFeatNameTemplate, StringUtils.capitalize(feat.getName())), initECls,
				String.format(withRemovedXListFeatMethodBodyTemplate, feat.getName(),
						eListRemovedFeatValParam.getName(), eListRemovedFeatValParam.getName()),
				String.format(withRemovedXFeatDocumentationTemplate, feat.getName()), eListRemovedFeatValParam));

		var arrayRemovedFeatValParam = getRemovedArrayFeatValParam(feat);
		opList.add(FluentAPIGenerationUtil.generateEOperationWithBodyAndDocumentation(
				String.format(withRemovedXFeatNameTemplate, StringUtils.capitalize(feat.getName())), initECls,
				String.format(withRemovedXArrayFeatMethodBodyTemplate, feat.getName(),
						arrayRemovedFeatValParam.getName(), arrayRemovedFeatValParam.getName()),
				String.format(withRemovedXFeatDocumentationTemplate, feat.getName()), arrayRemovedFeatValParam));

		return opList;
	}

	private List<EOperation> generateWithExactXFeat(EClass initECls, EClass elemToInit, EStructuralFeature feat) {
		var opList = new ArrayList<EOperation>();

		var eListExactFeatValParam = getExactFeatValParam(feat);
		opList.add(FluentAPIGenerationUtil.generateEOperationWithBodyAndDocumentation(
				String.format(withExactXFeatNameTemplate, StringUtils.capitalize(feat.getName())), initECls,
				String.format(withExactXFeatMethodBodyTemplate, feat.getName(), eListExactFeatValParam.getName()),
				String.format(withExactXFeatDocumentationTemplate, feat.getName(), feat.getName()),
				eListExactFeatValParam));

		var arrayExactFeatValParam = getExactArrayFeatValParam(feat);
		opList.add(FluentAPIGenerationUtil.generateEOperationWithBodyAndDocumentation(
				String.format(withExactXFeatNameTemplate, StringUtils.capitalize(feat.getName())), initECls,
				String.format(withExactXArrayFeatMethodBodyTemplate, feat.getName(), arrayExactFeatValParam.getName()),
				String.format(withExactXFeatDocumentationTemplate, feat.getName(), feat.getName()),
				arrayExactFeatValParam));

		return opList;
	}

	private EOperation generateWithXFeatOfContainerForManyValued(EClass initECls, EClass elemToInit,
			EStructuralFeature feat) {
		var featNameCapitalised = StringUtils.capitalize(feat.getName());

		return FluentAPIGenerationUtil
				.generateEOperationWithBodyAndDocumentation(
						String.format(withXFeatOfContainerNameTemplate, featNameCapitalised), initECls,
						String.format(withXFeatOfContainerMethodBodyForManyValuedFeatTemplate, featNameCapitalised,
								feat.getName()),
						String.format(withXFeatOfContainerDocumentationTemplate, feat.getName()));
	}

	private EOperation generateWithXFeatOfContainerForSingleValued(EClass initECls, EClass elemToInit,
			EStructuralFeature feat) {
		var featNameCapitalised = StringUtils.capitalize(feat.getName());

		return FluentAPIGenerationUtil.generateEOperationWithBodyAndDocumentation(
				String.format(withXFeatOfContainerNameTemplate, featNameCapitalised), initECls,
				String.format(withXFeatOfContainerMethodBodyForSingleValuedFeatTemplate, featNameCapitalised,
						feat.getEType().getInstanceClass().getName(), feat.getName()),
				String.format(withXFeatOfContainerDocumentationTemplate, feat.getName()));
	}

	private EParameter getNewFeatValParam(EStructuralFeature feat) {
		return FluentAPIGenerationUtil.generateSingleValuedEParameterWithDocumentation(newFeatValParamName,
				feat.getEType(), String.format(newFeatValParamDocumentationTemplate, feat.getName()));
	}

	private EParameter getAddedFeatValParam(EStructuralFeature feat) {
		return FluentAPIGenerationUtil.generateSingleValuedEParameterWithDocumentation(addedFeatValParamName,
				feat.getEType(), String.format(addedSingleFeatValParamDocumentationTemplate, feat.getName()));
	}

	private EParameter getRemovedFeatValParam(EStructuralFeature feat) {
		return FluentAPIGenerationUtil.generateSingleValuedEParameterWithDocumentation(removedFeatValParamName,
				feat.getEType(), String.format(removedSingleFeatValParamDocumentationTemplate, feat.getName()));
	}

	private EParameter getAddedListFeatValParam(EStructuralFeature feat) {
		return FluentAPIGenerationUtil.generateManyValuedEParameterWithDocumentation(addedFeatValParamName,
				feat.getEType(), String.format(addedListFeatValParamDocumentationTemplate, feat.getName()));
	}

	private EParameter getAddedArrayFeatValParam(EStructuralFeature feat) {
		return FluentAPIGenerationUtil.generateArrayValuedEParameterWithDocumentation(addedFeatValParamName,
				feat.getEType(), String.format(addedListFeatValParamDocumentationTemplate, feat.getName()));
	}

	private EParameter getRemovedListFeatValParam(EStructuralFeature feat) {
		return FluentAPIGenerationUtil.generateManyValuedEParameterWithDocumentation(removedFeatValParamName,
				feat.getEType(), String.format(removedListFeatValParamDocumentationTemplate, feat.getName()));
	}

	private EParameter getRemovedArrayFeatValParam(EStructuralFeature feat) {
		return FluentAPIGenerationUtil.generateArrayValuedEParameterWithDocumentation(removedFeatValParamName,
				feat.getEType(), String.format(removedListFeatValParamDocumentationTemplate, feat.getName()));
	}

	private EParameter getExactFeatValParam(EStructuralFeature feat) {
		return FluentAPIGenerationUtil.generateManyValuedEParameterWithDocumentation(exactFeatValParamName,
				feat.getEType(), String.format(exactFeatValParamDocumentationTemplate, feat.getName()));
	}

	private EParameter getExactArrayFeatValParam(EStructuralFeature feat) {
		return FluentAPIGenerationUtil.generateArrayValuedEParameterWithDocumentation(exactFeatValParamName,
				feat.getEType(), String.format(exactFeatValParamDocumentationTemplate, feat.getName()));
	}

	public static String getWithxfeatnametemplate() {
		return withXFeatNameTemplate;
	}

	public static String getWithxfeatofcontainernametemplate() {
		return withXFeatOfContainerNameTemplate;
	}

	public static String getWithoutxfeatnametemplate() {
		return withoutXFeatNameTemplate;
	}

	public static String getWithaddedxfeatnametemplate() {
		return withAddedXFeatNameTemplate;
	}

	public static String getWithremovedxfeatnametemplate() {
		return withRemovedXFeatNameTemplate;
	}

	public static String getWithexactxfeatnametemplate() {
		return withExactXFeatNameTemplate;
	}

	public static String getNewfeatvalparamname() {
		return newFeatValParamName;
	}

	public static String getAddedfeatvalparamname() {
		return addedFeatValParamName;
	}

	public static String getRemovedfeatvalparamname() {
		return removedFeatValParamName;
	}

	public static String getExactfeatvalparamname() {
		return exactFeatValParamName;
	}

	public static String getWithxfeatmethodbodytemplate() {
		return withXFeatMethodBodyTemplate;
	}

	public static String getWithoutxfeatmethodbodytemplate() {
		return withoutXFeatMethodBodyTemplate;
	}

	public static String getWithaddedxfeatmethodbodytemplate() {
		return withAddedXFeatMethodBodyTemplate;
	}

	public static String getWithremovedxfeatmethodbodytemplate() {
		return withRemovedXFeatMethodBodyTemplate;
	}

	public static String getWithaddedxlistfeatmethodbodytemplate() {
		return withAddedXListFeatMethodBodyTemplate;
	}

	public static String getWithremovedxlistfeatmethodbodytemplate() {
		return withRemovedXListFeatMethodBodyTemplate;
	}

	public static String getWithexactxfeatmethodbodytemplate() {
		return withExactXFeatMethodBodyTemplate;
	}

	public static String getWithxfeatofcontainermethodbodyformanyvaluedfeattemplate() {
		return withXFeatOfContainerMethodBodyForManyValuedFeatTemplate;
	}

	public static String getWithxfeatofcontainermethodbodyforsinglevaluedfeattemplate() {
		return withXFeatOfContainerMethodBodyForSingleValuedFeatTemplate;
	}

}
