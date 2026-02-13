package cipm.consistency.fluentapi.gen.init;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EStructuralFeature;

import cipm.consistency.fluentapi.gen.FluentAPIConstants;
import cipm.consistency.fluentapi.gen.FluentAPIDocumentationUtil;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationContext;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
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
	private static final String addedFeatValParamDocumentationTemplate = "Value(s) for the feature %s, which will be added to its current values in the initialised object this.get"
			+ FluentAPISuperInitialisationConstants
					.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName()
			+ "().";

	// %s: Feature name
	private static final String removedFeatValParamDocumentationTemplate = "Value(s) for the feature %s, which will be removed from its current values in the initialised object this.get"
			+ FluentAPISuperInitialisationConstants
					.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName()
			+ "().";

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

	private static final String withExactXFeatMethodBodyTemplate = FluentAPIMethodsUtil
			.joinLOC("var list = (org.eclipse.emf.common.util.EList) this.get"
					+ FluentAPISuperInitialisationConstants
							.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName()
					+ "().eGet(this.get" + FluentAPISuperInitialisationConstants
							.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName()
					// %s: Feature name
					+ "().eClass().getEStructuralFeature(\"%s\"))", "list.clear()",
					// %s: New feat values expression
					"list.addAll(%s)", "return this");

	private List<EStructuralFeature> getAllEligibleFeats(FluentAPIGenerationContext context, EClass elemToInit) {
		return elemToInit.getEAllStructuralFeatures().stream()
				.filter((feat) -> context.getTargetMetamodelFeatureFilter().isFeatureEligible(elemToInit, feat))
				.collect(Collectors.toCollection(ArrayList::new));
	}

	public List<EOperation> generateAllWithOperationsFor(FluentAPIGenerationContext context, EClass initECls,
			EClass elemToInit) {
		var feats = this.getAllEligibleFeats(context, elemToInit);
		var ops = new ArrayList<EOperation>();

		for (var feat : feats) {
			if (!feat.isMany()) {
				ops.addAll(this.generateWithXFeat(context, initECls, elemToInit, feat));
				ops.add(this.generateWithoutXFeat(initECls, elemToInit, feat));
			} else {
				ops.add(this.generateWithAddedXFeat(context, initECls, elemToInit, feat));
				ops.add(this.generateWithRemovedXFeat(context, initECls, elemToInit, feat));
				ops.addAll(this.generateWithExactXFeat(context, initECls, elemToInit, feat));
			}
		}
		return ops;
	}

	private List<EOperation> generateWithXFeat(FluentAPIGenerationContext context, EClass initECls, EClass elemToInit,
			EStructuralFeature feat) {
		var ops = new ArrayList<EOperation>();

		BiFunction<EParameter, String, EOperation> opGenerator = (featValParam, featValParamPlugin) -> {
			var op = FluentAPIGenerationUtil.generateEOperation(
					FluentAPIInitialisationConstants.getFluentAPIInitialisationWithXFeatNameForType(feat), initECls);
			FluentAPIGenerationUtil.addBody(op,
					String.format(withXFeatMethodBodyTemplate, feat.getName(), featValParamPlugin));
			FluentAPIGenerationUtil.addDocumentation(op, String.format(withXFeatDocumentationTemplate, feat.getName()));
			FluentAPIGenerationUtil.addEParameters(op, featValParam);
			return op;
		};

		var originalOpNewFeatValParam = getNewFeatValParam(feat);
		var originalOp = opGenerator.apply(originalOpNewFeatValParam, originalOpNewFeatValParam.getName());
		ops.add(originalOp);

		return ops;
	}

	private EOperation generateWithoutXFeat(EClass initECls, EClass elemToInit, EStructuralFeature feat) {
		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIInitialisationConstants.getFluentAPIInitialisationWithoutXFeatNameForType(feat), initECls);
		FluentAPIGenerationUtil.addBody(op, String.format(withoutXFeatMethodBodyTemplate, feat.getName()));
		FluentAPIGenerationUtil.addDocumentation(op, String.format(withoutXFeatDocumentationTemplate, feat.getName()));
		return op;
	}

	private EOperation generateWithAddedXFeat(FluentAPIGenerationContext context, EClass initECls, EClass elemToInit,
			EStructuralFeature feat) {
		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIInitialisationConstants.getFluentAPIInitialisationWithAddedXFeatNameForType(feat), initECls);

		FluentAPIGenerationUtil.addBody(op, String.format(withAddedXFeatMethodBodyTemplate, feat.getName()));
		FluentAPIGenerationUtil.addDocumentation(op,
				String.format(withAddedXFeatDocumentationTemplate, feat.getName()));
		FluentAPIGenerationUtil.addEParameters(op, getAddedFeatValParam(feat));
		return op;
	}

	private EOperation generateWithRemovedXFeat(FluentAPIGenerationContext context, EClass initECls, EClass elemToInit,
			EStructuralFeature feat) {
		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIInitialisationConstants.getFluentAPIInitialisationWithRemovedXFeatNameForType(feat), initECls);
		FluentAPIGenerationUtil.addBody(op, String.format(withRemovedXFeatMethodBodyTemplate, feat.getName()));
		FluentAPIGenerationUtil.addDocumentation(op,
				String.format(withRemovedXFeatDocumentationTemplate, feat.getName()));
		FluentAPIGenerationUtil.addEParameters(op, getRemovedFeatValParam(feat));
		return op;
	}

	private List<EOperation> generateWithExactXFeat(FluentAPIGenerationContext context, EClass initECls,
			EClass elemToInit, EStructuralFeature feat) {
		var opList = new ArrayList<EOperation>();
		BiFunction<EParameter, String, EOperation> opGenerator = (featValParam, methodBody) -> {
			var op = FluentAPIGenerationUtil.generateEOperation(
					FluentAPIInitialisationConstants.getFluentAPIInitialisationWithExactXFeatNameForType(feat),
					initECls);
			FluentAPIGenerationUtil.addBody(op, methodBody);
			FluentAPIGenerationUtil.addDocumentation(op,
					String.format(withExactXFeatDocumentationTemplate, feat.getName(), feat.getName()));
			FluentAPIGenerationUtil.addEParameters(op, featValParam);
			return op;
		};

		var formattedIterableMethodBody = String.format(withExactXFeatMethodBodyTemplate, feat.getName(),
				FluentAPIInitialisationConstants.getFluentAPIInitialisationWithMethodExactFeatValParamName());

		var colExactFeatValParam = getExactFeatColValParam(context, feat);
		var colOp = opGenerator.apply(colExactFeatValParam, formattedIterableMethodBody);
		opList.add(colOp);

		var arrayExactFeatValParam = getExactArrayFeatValParam(context, feat);
		var arrayOp = opGenerator.apply(arrayExactFeatValParam,
				String.format(withExactXFeatMethodBodyTemplate, feat.getName(), "java.util.List.of("
						+ FluentAPIInitialisationConstants.getFluentAPIInitialisationWithMethodExactFeatValParamName()
						+ ")"));
		opList.add(arrayOp);

		return opList;
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
				String.format(addedFeatValParamDocumentationTemplate, feat.getName()));
		return param;
	}

	private EParameter getRemovedFeatValParam(EStructuralFeature feat) {
		var param = FluentAPIGenerationUtil.generateSingleValuedEParameter(
				FluentAPIInitialisationConstants.getFluentAPIInitialisationWithMethodRemovedFeatValParamName(),
				feat.getEType());
		FluentAPIGenerationUtil.addDocumentation(param,
				String.format(removedFeatValParamDocumentationTemplate, feat.getName()));
		return param;
	}

	private EParameter getExactFeatColValParam(FluentAPIGenerationContext context, EStructuralFeature feat) {
		var param = FluentAPIGenerationUtil.generateSingleValuedEParameter(
				FluentAPIInitialisationConstants.getFluentAPIInitialisationWithMethodExactFeatValParamName(),
				FluentAPIGenerationUtil.generateCollectionTypeParameter(context, feat.getEType()));

		FluentAPIGenerationUtil.addDocumentation(param,
				String.format(exactFeatValParamDocumentationTemplate, feat.getName()));
		return param;
	}

	private EParameter getExactArrayFeatValParam(FluentAPIGenerationContext context, EStructuralFeature feat) {
		var param = FluentAPIGenerationUtil.generateArrayValuedEParameter(context,
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
