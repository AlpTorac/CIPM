package cipm.consistency.fluentapi.gen.init;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;

import cipm.consistency.fluentapi.gen.FluentAPIConstants;
import cipm.consistency.fluentapi.gen.FluentAPIDocumentationUtil;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.FluentAPITargetMetamodelFeatureFilter;
import cipm.consistency.fluentapi.gen.FluentAPITargetMetamodelPackageProvider;
import cipm.consistency.fluentapi.gen.IFluentAPIMethodGenerator;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;
import cipm.consistency.fluentapi.gen.rootapi.FluentAPIRootAPIConstants;
import cipm.consistency.fluentapi.gen.superinit.FluentAPISuperInitialisationConstants;

public class FluentAPIInitialisationWithOperationGenerator implements IFluentAPIMethodGenerator {
	//
	// Method names
	//

	// %s: Feature name
	private static final String withXFeatDocumentationTemplate = FluentAPIDocumentationUtil
			.appendSummaryToStart(FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithFeatMethodSummary())
			+ "Sets the value of the feature %s in this.get" + FluentAPISuperInitialisationConstants
					.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName()
			+ "() to the given value.";

	// %s: Feature name
	private static final String withXFeatOfContainerDocumentationTemplate = FluentAPIDocumentationUtil
			.appendSummaryToStart(FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithFeatOfContainerMethodSummary())
			+ "Sets the value of the feature %s in this.get"
			+ FluentAPISuperInitialisationConstants
					.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName()
			+ "() to the value of the same feature in this.get"
			+ FluentAPISuperInitialisationConstants
					.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName()
			+ "().eContainer(), i.e. the container of this.get"
			+ FluentAPISuperInitialisationConstants
					.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName()
			+ "(). Assumes this.get"
			+ FluentAPISuperInitialisationConstants
					.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName()
			+ "() to be contained in an elligible container.";

	// %s: Feature name
	private static final String withoutXFeatDocumentationTemplate = FluentAPIDocumentationUtil
			.appendSummaryToStart(FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithoutFeatMethodSummary())
			+ "Unsets the value of the feature %s in this.get"
			+ FluentAPISuperInitialisationConstants
					.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName()
			+ "(), which sets its value to null.";

	// %s: Feature name
	private static final String withAddedXFeatDocumentationTemplate = FluentAPIDocumentationUtil
			.appendSummaryToStart(FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithAddedFeatMethodSummary())
			+ "Adds the given values to the current values of the feature %s in this.get"
			+ FluentAPISuperInitialisationConstants
					.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName()
			+ "().";

	// %s: Feature name
	private static final String withRemovedXFeatDocumentationTemplate = FluentAPIDocumentationUtil
			.appendSummaryToStart(FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithRemovedFeatMethodSummary())
			+ "Removes the given values from the current values of the feature %s in this.get"
			+ FluentAPISuperInitialisationConstants
					.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName()
			+ "().";

	// %s: Feature name
	private static final String withExactXFeatDocumentationTemplate = FluentAPIDocumentationUtil
			.appendSummaryToStart(FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithExactFeatMethodSummary())
			+ "Sets the value of the (many-valued) feature %s in this.get"
			+ FluentAPISuperInitialisationConstants
					.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName()
			+ "() to the given value. Doing so sets the value of the (many-valued) feature to exactly the given values.";

	//
	// Parameters
	//

	// %s: Feature name
	private static final String newFeatValParamDocumentationTemplate = "The new value of the feature %s, which will replace its current value in the initialised object this.get"
			+ FluentAPISuperInitialisationConstants
					.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName()
			+ "()";

	// %s: Feature name
	private static final String addedFeatValParamDocumentationTemplate = "for the feature %s, which will be added to its current values in the initialised object this.get"
			+ FluentAPISuperInitialisationConstants
					.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName()
			+ "().";
	private static final String addedSingleFeatValParamDocumentationTemplate = "Value "
			+ addedFeatValParamDocumentationTemplate;
	private static final String addedListFeatValParamDocumentationTemplate = "Values "
			+ addedFeatValParamDocumentationTemplate;

	// %s: Feature name
	private static final String removedFeatValParamDocumentationTemplate = "for the feature %s, which will be removed from its current values in the initialised object this.get"
			+ FluentAPISuperInitialisationConstants
					.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName()
			+ "().";
	private static final String removedSingleFeatValParamDocumentationTemplate = "Value "
			+ removedFeatValParamDocumentationTemplate;
	private static final String removedListFeatValParamDocumentationTemplate = "Values "
			+ removedFeatValParamDocumentationTemplate;

	// %s: Feature name
	private static final String exactFeatValParamDocumentationTemplate = "Values for the feature %s, which will replace its current value in the initialised object this.get"
			+ FluentAPISuperInitialisationConstants
					.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName()
			+ "(). Afterward the value of the feature will be exactly the given values.";

	//
	// Method bodies
	//

	private static final String withXFeatMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC("this.get"
			+ FluentAPISuperInitialisationConstants
					.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName()
			+ "().eSet(this.get" + FluentAPISuperInitialisationConstants
					.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName()
			// %s: Feature name
			// %s: Feature value parameter (although the parameter name is known, there are
			// overloading methods process the parameter)
			+ "().eClass().getEStructuralFeature(\"%s\"), %s)", "return this");

	private static final String withoutXFeatMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC("this.get"
			+ FluentAPISuperInitialisationConstants
					.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName()
			+ "().eUnset(this.get" + FluentAPISuperInitialisationConstants
					.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName()
			// %s: Feature name
			+ "().eClass().getEStructuralFeature(\"%s\"))", "return this");

	private static final String withAddedXFeatMethodBodyTemplate = FluentAPIMethodsUtil
			.joinLOC("((org.eclipse.emf.common.util.EList) this.get"
					+ FluentAPISuperInitialisationConstants
							.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName()
					+ "().eGet(this.get" + FluentAPISuperInitialisationConstants
							.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName()
					// %s: Feature name
					+ "().eClass().getEStructuralFeature(\"%s\"))).add("
					+ FluentAPIInitialisationConstants.getFluentAPIInitialisationWithMethodAddedFeatValParamName()
					+ ")", "return this");

	private static final String withRemovedXFeatMethodBodyTemplate = FluentAPIMethodsUtil
			.joinLOC("((org.eclipse.emf.common.util.EList) this.get"
					+ FluentAPISuperInitialisationConstants
							.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName()
					+ "().eGet(this.get" + FluentAPISuperInitialisationConstants
							.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName()
					// %s: Feature name
					+ "().eClass().getEStructuralFeature(\"%s\"))).remove("
					+ FluentAPIInitialisationConstants.getFluentAPIInitialisationWithMethodRemovedFeatValParamName()
					+ ")", "return this");

	private static final String withAddedXListFeatMethodBodyTemplate = FluentAPIMethodsUtil
			.joinLOC("((org.eclipse.emf.common.util.EList) this.get"
					+ FluentAPISuperInitialisationConstants
							.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName()
					+ "().eGet(this.get" + FluentAPISuperInitialisationConstants
							.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName()
					// %s: Feature name
					+ "().eClass().getEStructuralFeature(\"%s\"))).addAll("
					+ FluentAPIInitialisationConstants.getFluentAPIInitialisationWithMethodAddedFeatValParamName()
					+ ")", "return this");

	private static final String withAddedXArrayFeatMethodBodyTemplate = FluentAPIMethodsUtil
			.joinLOC("((org.eclipse.emf.common.util.EList) this.get"
					+ FluentAPISuperInitialisationConstants
							.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName()
					+ "().eGet(this.get" + FluentAPISuperInitialisationConstants
							.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName()
					// %s: Feature name
					+ "().eClass().getEStructuralFeature(\"%s\"))).addAll(java.util.List.of("
					+ FluentAPIInitialisationConstants.getFluentAPIInitialisationWithMethodAddedFeatValParamName()
					+ "))", "return this");

	private static final String withRemovedXListFeatMethodBodyTemplate = FluentAPIMethodsUtil
			.joinLOC("((org.eclipse.emf.common.util.EList) this.get"
					+ FluentAPISuperInitialisationConstants
							.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName()
					+ "().eGet(this.get" + FluentAPISuperInitialisationConstants
							.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName()
					// %s: Feature name
					+ "().eClass().getEStructuralFeature(\"%s\"))).removeAll("
					+ FluentAPIInitialisationConstants.getFluentAPIInitialisationWithMethodRemovedFeatValParamName()
					+ ")", "return this");

	private static final String withRemovedXArrayFeatMethodBodyTemplate = FluentAPIMethodsUtil
			.joinLOC("((org.eclipse.emf.common.util.EList) this.get"
					+ FluentAPISuperInitialisationConstants
							.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName()
					+ "().eGet(this.get" + FluentAPISuperInitialisationConstants
							.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName()
					// %s: Feature name
					+ "().eClass().getEStructuralFeature(\"%s\"))).removeAll(java.util.List.of("
					+ FluentAPIInitialisationConstants.getFluentAPIInitialisationWithMethodRemovedFeatValParamName()
					+ "))", "return this");

	private static final String withExactXFeatMethodBodyTemplate = FluentAPIMethodsUtil
			.joinLOC("var list = (org.eclipse.emf.common.util.EList) this.get"
					+ FluentAPISuperInitialisationConstants
							.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName()
					+ "().eGet(this.get" + FluentAPISuperInitialisationConstants
							.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName()
					// %s: Feature name
					+ "().eClass().getEStructuralFeature(\"%s\"))", "list.clear()",
					"list.addAll(" + FluentAPIInitialisationConstants
							.getFluentAPIInitialisationWithMethodExactFeatValParamName() + ")",
					"return this");

	private static final String withExactXArrayFeatMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			// %s: Feature name
			"var list = (org.eclipse.emf.common.util.EList) this.get"
					+ FluentAPISuperInitialisationConstants
							.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName()
					+ "().eGet(this.get"
					+ FluentAPISuperInitialisationConstants
							.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName()
					+ "().eClass().getEStructuralFeature(\"%s\"))",
			//
			"list.clear()",
			"list.addAll(java.util.List.of("
					+ FluentAPIInitialisationConstants.getFluentAPIInitialisationWithMethodExactFeatValParamName()
					+ "))",
			//
			"return this");

	private static final String withXFeatOfContainerMethodBodyForManyValuedFeatTemplate = FluentAPIMethodsUtil.joinLOC(
			"var cElem = this.get" + FluentAPISuperInitialisationConstants
					.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName() + "()",
			"org.eclipse.emf.common.util.EList featVal = new org.eclipse.emf.common.util.BasicEList<>()",
			// %s: Feature name
			"if (cElem.eContainer() != null) featVal = (org.eclipse.emf.common.util.EList) cElem.eContainer().eGet(cElem.eClass().getEStructuralFeature(\"%s\"))",
			// %s: Feature name (capitalised)
			"withExact%s(featVal)",
			//
			"return this");

	private static final String withXFeatOfContainerMethodBodyForSingleValuedFeatTemplate = FluentAPIMethodsUtil
			.joinLOC(
					"var cElem = this.get" + FluentAPISuperInitialisationConstants
							.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName() + "()",
					"Object featVal = null",
					// %s: Feature name
					"if (cElem.eContainer() != null) featVal = cElem.eContainer().eGet(cElem.eClass().getEStructuralFeature(\"%s\"))",
					// %s: Feature name (capitalised)
					// %s: Feature value type
					"if (featVal != null) with%s((%s) featVal)",
					// %s: Feature name (capitalised)
					"if (featVal == null) without%s()",
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

				if (featFilter.canShareFeatureWithContainer(eClassProvider, elemToInit, feat)) {
					// Do not use eContainer(), use eGet(featName) instead
					ops.add(this.generateWithXFeatOfContainerForSingleValued(initECls, elemToInit, feat));
				}

			} else {
				ops.add(this.generateWithAddedXFeat(initECls, elemToInit, feat));
				ops.addAll(this.generateWithAddedXListFeat(initECls, elemToInit, feat));
				ops.add(this.generateWithRemovedXFeat(initECls, elemToInit, feat));
				ops.addAll(this.generateWithRemovedXListFeat(initECls, elemToInit, feat));
				ops.addAll(this.generateWithExactXFeat(initECls, elemToInit, feat));

				if (featFilter.canShareFeatureWithContainer(eClassProvider, elemToInit, feat)) {
					ops.add(this.generateWithXFeatOfContainerForManyValued(initECls, elemToInit, feat));
				}
			}
		}
		return ops;
	}

	private List<EOperation> generateWithXFeat(EClass initECls, EClass elemToInit, EStructuralFeature feat) {
		var ops = new ArrayList<EOperation>();
		var originalOpNewFeatValParam = getNewFeatValParam(feat);

		var originalOp = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIInitialisationConstants.getFluentAPIInitialisationWithXFeatNameForType(feat), initECls);
		FluentAPIGenerationUtil.addBody(originalOp,
				String.format(withXFeatMethodBodyTemplate, feat.getName(), originalOpNewFeatValParam.getName()));
		FluentAPIGenerationUtil.addDocumentation(originalOp,
				String.format(withXFeatDocumentationTemplate, feat.getName()));
		FluentAPIGenerationUtil.addEParameters(originalOp, originalOpNewFeatValParam);
		ops.add(originalOp);

		if (originalOpNewFeatValParam.getEType().equals(EcorePackage.Literals.EBIG_INTEGER)) {
			var longOpNewFeatValParam = getNewFeatValParam(feat);
			longOpNewFeatValParam.setEType(EcorePackage.Literals.ELONG);
			var longOp = FluentAPIGenerationUtil.generateEOperation(
					FluentAPIInitialisationConstants.getFluentAPIInitialisationWithXFeatNameForType(feat), initECls);
			FluentAPIGenerationUtil.addBody(longOp, String.format(withXFeatMethodBodyTemplate, feat.getName(),
					String.format("java.math.BigInteger.valueOf(%s)", longOpNewFeatValParam.getName())));
			FluentAPIGenerationUtil.addDocumentation(longOp,
					String.format(withXFeatDocumentationTemplate, feat.getName()));
			FluentAPIGenerationUtil.addEParameters(longOp, longOpNewFeatValParam);
			ops.add(longOp);

			var intOpNewFeatValParam = getNewFeatValParam(feat);
			intOpNewFeatValParam.setEType(EcorePackage.Literals.EINT);
			var intOp = FluentAPIGenerationUtil.generateEOperation(
					FluentAPIInitialisationConstants.getFluentAPIInitialisationWithXFeatNameForType(feat), initECls);
			FluentAPIGenerationUtil.addBody(intOp, String.format(withXFeatMethodBodyTemplate, feat.getName(),
					String.format("java.math.BigInteger.valueOf(%s)", intOpNewFeatValParam.getName())));
			FluentAPIGenerationUtil.addDocumentation(intOp,
					String.format(withXFeatDocumentationTemplate, feat.getName()));
			FluentAPIGenerationUtil.addEParameters(intOp, intOpNewFeatValParam);
			ops.add(intOp);
		}

		return ops;
	}

	private EOperation generateWithoutXFeat(EClass initECls, EClass elemToInit, EStructuralFeature feat) {
		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIInitialisationConstants.getFluentAPIInitialisationWithoutXFeatNameForType(feat), initECls);
		FluentAPIGenerationUtil.addBody(op, String.format(withoutXFeatMethodBodyTemplate, feat.getName()));
		FluentAPIGenerationUtil.addDocumentation(op, String.format(withoutXFeatDocumentationTemplate, feat.getName()));
		return op;
	}

	private EOperation generateWithAddedXFeat(EClass initECls, EClass elemToInit, EStructuralFeature feat) {
		var addedFeatValParam = getAddedFeatValParam(feat);
		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIInitialisationConstants.getFluentAPIInitialisationWithAddedXFeatNameForType(feat), initECls);

		FluentAPIGenerationUtil.addBody(op,
				String.format(withAddedXFeatMethodBodyTemplate, feat.getName(), addedFeatValParam.getName()));
		FluentAPIGenerationUtil.addDocumentation(op,
				String.format(withAddedXFeatDocumentationTemplate, feat.getName()));
		FluentAPIGenerationUtil.addEParameters(op, addedFeatValParam);
		return op;
	}

	private EOperation generateWithRemovedXFeat(EClass initECls, EClass elemToInit, EStructuralFeature feat) {
		var removedFeatValParam = getRemovedFeatValParam(feat);
		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIInitialisationConstants.getFluentAPIInitialisationWithRemovedXFeatNameForType(feat), initECls);

		FluentAPIGenerationUtil.addBody(op,
				String.format(withRemovedXFeatMethodBodyTemplate, feat.getName(), removedFeatValParam.getName()));
		FluentAPIGenerationUtil.addDocumentation(op,
				String.format(withRemovedXFeatDocumentationTemplate, feat.getName()));
		FluentAPIGenerationUtil.addEParameters(op, removedFeatValParam);
		return op;
	}

	private List<EOperation> generateWithAddedXListFeat(EClass initECls, EClass elemToInit, EStructuralFeature feat) {
		var opList = new ArrayList<EOperation>();

		var eListAddedFeatValParam = getAddedListFeatValParam(feat);
		var listOp = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIInitialisationConstants.getFluentAPIInitialisationWithAddedXFeatNameForType(feat), initECls);

		FluentAPIGenerationUtil.addBody(listOp, String.format(withAddedXListFeatMethodBodyTemplate, feat.getName(),
				eListAddedFeatValParam.getName(), eListAddedFeatValParam.getName()));
		FluentAPIGenerationUtil.addDocumentation(listOp,
				String.format(withAddedXFeatDocumentationTemplate, feat.getName()));
		FluentAPIGenerationUtil.addEParameters(listOp, eListAddedFeatValParam);
		opList.add(listOp);

		var arrayAddedFeatValParam = getAddedArrayFeatValParam(feat);
		var arrayOp = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIInitialisationConstants.getFluentAPIInitialisationWithAddedXFeatNameForType(feat), initECls);
		FluentAPIGenerationUtil.addBody(arrayOp, String.format(withAddedXArrayFeatMethodBodyTemplate, feat.getName(),
				arrayAddedFeatValParam.getName(), arrayAddedFeatValParam.getName()));
		FluentAPIGenerationUtil.addDocumentation(arrayOp,
				String.format(withAddedXFeatDocumentationTemplate, feat.getName()));
		FluentAPIGenerationUtil.addEParameters(arrayOp, arrayAddedFeatValParam);
		opList.add(arrayOp);

		return opList;
	}

	private List<EOperation> generateWithRemovedXListFeat(EClass initECls, EClass elemToInit, EStructuralFeature feat) {
		var opList = new ArrayList<EOperation>();

		var eListRemovedFeatValParam = getRemovedListFeatValParam(feat);
		var listOp = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIInitialisationConstants.getFluentAPIInitialisationWithRemovedXFeatNameForType(feat), initECls);
		FluentAPIGenerationUtil.addBody(listOp, String.format(withRemovedXListFeatMethodBodyTemplate, feat.getName(),
				eListRemovedFeatValParam.getName(), eListRemovedFeatValParam.getName()));
		FluentAPIGenerationUtil.addDocumentation(listOp,
				String.format(withRemovedXFeatDocumentationTemplate, feat.getName()));
		FluentAPIGenerationUtil.addEParameters(listOp, eListRemovedFeatValParam);
		opList.add(listOp);

		var arrayRemovedFeatValParam = getRemovedArrayFeatValParam(feat);
		var arrayOp = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIInitialisationConstants.getFluentAPIInitialisationWithRemovedXFeatNameForType(feat), initECls);
		FluentAPIGenerationUtil.addBody(arrayOp, String.format(withRemovedXArrayFeatMethodBodyTemplate, feat.getName(),
				arrayRemovedFeatValParam.getName(), arrayRemovedFeatValParam.getName()));
		FluentAPIGenerationUtil.addDocumentation(arrayOp,
				String.format(withRemovedXFeatDocumentationTemplate, feat.getName()));
		FluentAPIGenerationUtil.addEParameters(arrayOp, arrayRemovedFeatValParam);
		opList.add(arrayOp);

		return opList;
	}

	private List<EOperation> generateWithExactXFeat(EClass initECls, EClass elemToInit, EStructuralFeature feat) {
		var opList = new ArrayList<EOperation>();

		var eListExactFeatValParam = getExactFeatValParam(feat);
		var listOp = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIInitialisationConstants.getFluentAPIInitialisationWithExactXFeatNameForType(feat), initECls);
		opList.add(listOp);
		FluentAPIGenerationUtil.addBody(listOp,
				String.format(withExactXFeatMethodBodyTemplate, feat.getName(), eListExactFeatValParam.getName()));
		FluentAPIGenerationUtil.addDocumentation(listOp,
				String.format(withExactXFeatDocumentationTemplate, feat.getName(), feat.getName()));
		FluentAPIGenerationUtil.addEParameters(listOp, eListExactFeatValParam);

		var arrayExactFeatValParam = getExactArrayFeatValParam(feat);
		var arrayOp = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIInitialisationConstants.getFluentAPIInitialisationWithExactXFeatNameForType(feat), initECls);
		FluentAPIGenerationUtil.addBody(arrayOp,
				String.format(withExactXArrayFeatMethodBodyTemplate, feat.getName(), arrayExactFeatValParam.getName()));
		FluentAPIGenerationUtil.addDocumentation(arrayOp,
				String.format(withExactXFeatDocumentationTemplate, feat.getName(), feat.getName()));
		FluentAPIGenerationUtil.addEParameters(arrayOp, arrayExactFeatValParam);
		opList.add(arrayOp);

		return opList;
	}

	private EOperation generateWithXFeatOfContainerForManyValued(EClass initECls, EClass elemToInit,
			EStructuralFeature feat) {
		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIInitialisationConstants.getFluentAPIInitialisationWithXFeatOfContainerNameForType(feat),
				initECls);
		FluentAPIGenerationUtil.addBody(op, String.format(withXFeatOfContainerMethodBodyForManyValuedFeatTemplate,
				feat.getName(), StringUtils.capitalize(feat.getName())));
		FluentAPIGenerationUtil.addDocumentation(op,
				String.format(withXFeatOfContainerDocumentationTemplate, feat.getName()));
		return op;
	}

	private EOperation generateWithXFeatOfContainerForSingleValued(EClass initECls, EClass elemToInit,
			EStructuralFeature feat) {
		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIInitialisationConstants.getFluentAPIInitialisationWithXFeatOfContainerNameForType(feat),
				initECls);
		FluentAPIGenerationUtil.addBody(op,
				String.format(withXFeatOfContainerMethodBodyForSingleValuedFeatTemplate, feat.getName(),
						StringUtils.capitalize(feat.getName()), feat.getEType().getInstanceClass().getName(),
						StringUtils.capitalize(feat.getName())));
		FluentAPIGenerationUtil.addDocumentation(op,
				String.format(withXFeatOfContainerDocumentationTemplate, feat.getName()));
		return op;
	}

	private EParameter getNewFeatValParam(EStructuralFeature feat) {
		var param = FluentAPIGenerationUtil.generateSingleValuedEParameter(
				FluentAPIInitialisationConstants.getFluentAPIInitialisationWithMethodNewFeatValParamName(),
				feat.getEType());
		FluentAPIGenerationUtil.addDocumentation(param,
				String.format(newFeatValParamDocumentationTemplate, feat.getName()));
		return param;
	}

	private EParameter getAddedFeatValParam(EStructuralFeature feat) {
		var param = FluentAPIGenerationUtil.generateSingleValuedEParameter(
				FluentAPIInitialisationConstants.getFluentAPIInitialisationWithMethodAddedFeatValParamName(),
				feat.getEType());
		FluentAPIGenerationUtil.addDocumentation(param,
				String.format(addedSingleFeatValParamDocumentationTemplate, feat.getName()));
		return param;
	}

	private EParameter getRemovedFeatValParam(EStructuralFeature feat) {
		var param = FluentAPIGenerationUtil.generateSingleValuedEParameter(
				FluentAPIInitialisationConstants.getFluentAPIInitialisationWithMethodRemovedFeatValParamName(),
				feat.getEType());
		FluentAPIGenerationUtil.addDocumentation(param,
				String.format(removedSingleFeatValParamDocumentationTemplate, feat.getName()));
		return param;
	}

	private EParameter getAddedListFeatValParam(EStructuralFeature feat) {
		var param = FluentAPIGenerationUtil.generateManyValuedEParameter(
				FluentAPIInitialisationConstants.getFluentAPIInitialisationWithMethodAddedFeatValParamName(),
				feat.getEType());
		FluentAPIGenerationUtil.addDocumentation(param,
				String.format(addedListFeatValParamDocumentationTemplate, feat.getName()));
		return param;
	}

	private EParameter getAddedArrayFeatValParam(EStructuralFeature feat) {
		var param = FluentAPIGenerationUtil.generateArrayValuedEParameter(
				FluentAPIInitialisationConstants.getFluentAPIInitialisationWithMethodAddedFeatValParamName(),
				feat.getEType());
		FluentAPIGenerationUtil.addDocumentation(param,
				String.format(addedListFeatValParamDocumentationTemplate, feat.getName()));
		return param;
	}

	private EParameter getRemovedListFeatValParam(EStructuralFeature feat) {
		var param = FluentAPIGenerationUtil.generateManyValuedEParameter(
				FluentAPIInitialisationConstants.getFluentAPIInitialisationWithMethodRemovedFeatValParamName(),
				feat.getEType());

		FluentAPIGenerationUtil.addDocumentation(param,
				String.format(removedListFeatValParamDocumentationTemplate, feat.getName()));
		return param;
	}

	private EParameter getRemovedArrayFeatValParam(EStructuralFeature feat) {
		var param = FluentAPIGenerationUtil.generateArrayValuedEParameter(
				FluentAPIInitialisationConstants.getFluentAPIInitialisationWithMethodRemovedFeatValParamName(),
				feat.getEType());
		FluentAPIGenerationUtil.addDocumentation(param,
				String.format(removedListFeatValParamDocumentationTemplate, feat.getName()));
		return param;
	}

	private EParameter getExactFeatValParam(EStructuralFeature feat) {
		var param = FluentAPIGenerationUtil.generateManyValuedEParameter(
				FluentAPIInitialisationConstants.getFluentAPIInitialisationWithMethodExactFeatValParamName(),
				feat.getEType());

		FluentAPIGenerationUtil.addDocumentation(param,
				String.format(exactFeatValParamDocumentationTemplate, feat.getName()));
		return param;
	}

	private EParameter getExactArrayFeatValParam(EStructuralFeature feat) {
		var param = FluentAPIGenerationUtil.generateArrayValuedEParameter(
				FluentAPIInitialisationConstants.getFluentAPIInitialisationWithMethodExactFeatValParamName(),
				feat.getEType());

		FluentAPIGenerationUtil.addDocumentation(param,
				String.format(exactFeatValParamDocumentationTemplate, feat.getName()));
		return param;
	}

	@Override
	public Map<String, String> getMethodNamesToDescriptions() {
		return Map.of(
				String.format(FluentAPIInitialisationConstants.getFluentAPIInitialisationWithXFeatNameTemplate(),
						FluentAPIConstants.getDocumentationPlaceholder()),
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithFeatMethodSummary(),

				String.format(
						FluentAPIInitialisationConstants.getFluentAPIInitialisationWithXFeatOfContainerNameTemplate(),
						FluentAPIConstants.getDocumentationPlaceholder()),
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithFeatOfContainerMethodSummary(),

				String.format(FluentAPIInitialisationConstants.getFluentAPIInitialisationWithoutXFeatNameTemplate(),
						FluentAPIConstants.getDocumentationPlaceholder()),
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithoutFeatMethodSummary(),

				String.format(FluentAPIInitialisationConstants.getFluentAPIInitialisationWithAddedXFeatNameTemplate(),
						FluentAPIConstants.getDocumentationPlaceholder()),
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithAddedFeatMethodSummary(),

				String.format(FluentAPIInitialisationConstants.getFluentAPIInitialisationWithRemovedXFeatNameTemplate(),
						FluentAPIConstants.getDocumentationPlaceholder()),
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithRemovedFeatMethodSummary(),

				String.format(FluentAPIInitialisationConstants.getFluentAPIInitialisationWithExactXFeatNameTemplate(),
						FluentAPIConstants.getDocumentationPlaceholder()),
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithExactFeatMethodSummary());
	}
}
