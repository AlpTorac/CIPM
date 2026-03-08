package cipm.consistency.fluentapi.gen.rootapi;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EOperation;

import cipm.consistency.fluentapi.gen.FluentAPIGeneralParameterGenerator;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationContext;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.ModelConstants;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;
import cipm.consistency.fluentapi.gen.methods.FluentEObjectAPIMethods;

public class FluentAPIRootAPIWithOperationGenerator {
	// TODO Add documentation

	private static final String featureValModificationMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			// %s: Corresponding method's name in FluentEObjectAPIMethods
			FluentEObjectAPIMethods.class.getName() + ".%s(this, "
					+ ModelConstants.GeneralParameters.USED_EOBJECT_PARAMETER_NAME.get() + ", "
					+ ModelConstants.GeneralParameters.MODIFIED_FEATURE_PARAMETER_NAME.get() + ", "
					+ ModelConstants.GeneralParameters.FEATURE_VALUE_PARAMETER_NAME.get() + ")",
			"return this");

	private static final String featureValCleaningMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			// %s: Corresponding method's name in FluentEObjectAPIMethods
			FluentEObjectAPIMethods.class.getName() + ".%s(this, "
					+ ModelConstants.GeneralParameters.USED_EOBJECT_PARAMETER_NAME.get() + ", "
					+ ModelConstants.GeneralParameters.MODIFIED_FEATURE_PARAMETER_NAME.get() + ")",
			"return this");

	public List<EOperation> getAllAPITopLevelWithOperations(FluentAPIGenerationContext context) {
		var ops = new ArrayList<EOperation>();

		ops.add(getWithFeatOp(context));
		ops.add(getWithoutFeatOp(context));
		ops.add(getWithAddedFeatOp(context));
		ops.add(getWithRemovedFeatOp(context));
		ops.add(getCleanFeatOp(context));

		return ops;
	}

	private EOperation getWithFeatOp(FluentAPIGenerationContext context) {
		var eobjParam = FluentAPIGeneralParameterGenerator.getEObjectParam();
		var featParam = FluentAPIGeneralParameterGenerator.getFeatParam();
		var featValParam = FluentAPIGeneralParameterGenerator.getFeatValParam();

		var op = FluentAPIGenerationUtil.generateEOperation(ModelConstants.FluentAPI.WithFeat.NAME.get(),
				context.getFluentAPIECls());
		FluentAPIGenerationUtil.addBody(op,
				String.format(featureValModificationMethodBodyTemplate, ModelConstants.FluentAPI.WithFeat.NAME.get()));
		FluentAPIGenerationUtil.addEParameters(op, eobjParam, featParam, featValParam);
		FluentAPIGenerationUtil.addDocumentation(op, ModelConstants.FluentAPI.WithFeat.SUMMARY.get());
		return op;
	}

	private EOperation getWithoutFeatOp(FluentAPIGenerationContext context) {
		var eobjParam = FluentAPIGeneralParameterGenerator.getEObjectParam();
		var featParam = FluentAPIGeneralParameterGenerator.getFeatParam();

		var op = FluentAPIGenerationUtil.generateEOperation(ModelConstants.FluentAPI.WithoutFeat.NAME.get(),
				context.getFluentAPIECls());
		FluentAPIGenerationUtil.addBody(op,
				String.format(featureValCleaningMethodBodyTemplate, ModelConstants.FluentAPI.WithoutFeat.NAME.get()));
		FluentAPIGenerationUtil.addEParameters(op, eobjParam, featParam);
		FluentAPIGenerationUtil.addDocumentation(op, ModelConstants.FluentAPI.WithoutFeat.SUMMARY.get());
		return op;
	}

	private EOperation getWithAddedFeatOp(FluentAPIGenerationContext context) {
		var eobjParam = FluentAPIGeneralParameterGenerator.getEObjectParam();
		var featParam = FluentAPIGeneralParameterGenerator.getFeatParam();
		var op = FluentAPIGenerationUtil.generateEOperation(ModelConstants.FluentAPI.WithAddedFeat.NAME.get(),
				context.getFluentAPIECls());
		FluentAPIGenerationUtil.addBody(op, String.format(featureValModificationMethodBodyTemplate,
				ModelConstants.FluentAPI.WithAddedFeat.NAME.get()));
		FluentAPIGenerationUtil.addEParameters(op, eobjParam, featParam,
				FluentAPIGeneralParameterGenerator.getFeatValParam());
		FluentAPIGenerationUtil.addDocumentation(op, ModelConstants.FluentAPI.WithAddedFeat.SUMMARY.get());
		return op;
	}

	private EOperation getWithRemovedFeatOp(FluentAPIGenerationContext context) {
		var eobjParam = FluentAPIGeneralParameterGenerator.getEObjectParam();
		var featParam = FluentAPIGeneralParameterGenerator.getFeatParam();
		var op = FluentAPIGenerationUtil.generateEOperation(ModelConstants.FluentAPI.WithRemovedFeat.NAME.get(),
				context.getFluentAPIECls());
		FluentAPIGenerationUtil.addBody(op, String.format(featureValModificationMethodBodyTemplate,
				ModelConstants.FluentAPI.WithRemovedFeat.NAME.get()));
		FluentAPIGenerationUtil.addEParameters(op, eobjParam, featParam,
				FluentAPIGeneralParameterGenerator.getFeatValParam());
		FluentAPIGenerationUtil.addDocumentation(op, ModelConstants.FluentAPI.WithRemovedFeat.SUMMARY.get());
		return op;
	}

	private EOperation getCleanFeatOp(FluentAPIGenerationContext context) {
		var eobjParam = FluentAPIGeneralParameterGenerator.getEObjectParam();
		var featParam = FluentAPIGeneralParameterGenerator.getFeatParam();
		var op = FluentAPIGenerationUtil.generateEOperation(ModelConstants.FluentAPI.CleanFeat.NAME.get(),
				context.getFluentAPIECls());
		FluentAPIGenerationUtil.addBody(op,
				String.format(featureValCleaningMethodBodyTemplate, ModelConstants.FluentAPI.CleanFeat.NAME.get()));
		FluentAPIGenerationUtil.addEParameters(op, eobjParam, featParam);
		FluentAPIGenerationUtil.addDocumentation(op, ModelConstants.FluentAPI.CleanFeat.SUMMARY.get());
		return op;
	}
}
