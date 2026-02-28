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
					+ FluentAPIGeneralParameterGenerator.getFluentAPIEObjectParameterName() + ", "
					+ FluentAPIGeneralParameterGenerator.getFluentAPIFeatureParameterName() + ", "
					+ FluentAPIGeneralParameterGenerator.getFluentAPIFeatureValueParameterName() + ")",
			"return this");

	private static final String featureValCleaningMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			// %s: Corresponding method's name in FluentEObjectAPIMethods
			FluentEObjectAPIMethods.class.getName() + ".%s(this, "
					+ FluentAPIGeneralParameterGenerator.getFluentAPIEObjectParameterName() + ", "
					+ FluentAPIGeneralParameterGenerator.getFluentAPIFeatureParameterName() + ")",
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

		var op = FluentAPIGenerationUtil.generateEOperation(ModelConstants.RootAPI.WithFeat.NAME.get(),
				context.getFluentAPIECls());
		FluentAPIGenerationUtil.addBody(op,
				String.format(featureValModificationMethodBodyTemplate, ModelConstants.RootAPI.WithFeat.NAME.get()));
		FluentAPIGenerationUtil.addEParameters(op, eobjParam, featParam, featValParam);
		FluentAPIGenerationUtil.addDocumentation(op, ModelConstants.RootAPI.WithFeat.SUMMARY.get());
		return op;
	}

	private EOperation getWithoutFeatOp(FluentAPIGenerationContext context) {
		var eobjParam = FluentAPIGeneralParameterGenerator.getEObjectParam();
		var featParam = FluentAPIGeneralParameterGenerator.getFeatParam();

		var op = FluentAPIGenerationUtil.generateEOperation(ModelConstants.RootAPI.WithoutFeat.NAME.get(),
				context.getFluentAPIECls());
		FluentAPIGenerationUtil.addBody(op,
				String.format(featureValCleaningMethodBodyTemplate, ModelConstants.RootAPI.WithoutFeat.NAME.get()));
		FluentAPIGenerationUtil.addEParameters(op, eobjParam, featParam);
		FluentAPIGenerationUtil.addDocumentation(op, ModelConstants.RootAPI.WithoutFeat.SUMMARY.get());
		return op;
	}

	private EOperation getWithAddedFeatOp(FluentAPIGenerationContext context) {
		var eobjParam = FluentAPIGeneralParameterGenerator.getEObjectParam();
		var featParam = FluentAPIGeneralParameterGenerator.getFeatParam();
		var op = FluentAPIGenerationUtil.generateEOperation(ModelConstants.RootAPI.WithAddedFeat.NAME.get(),
				context.getFluentAPIECls());
		FluentAPIGenerationUtil.addBody(op, String.format(featureValModificationMethodBodyTemplate,
				ModelConstants.RootAPI.WithAddedFeat.NAME.get()));
		FluentAPIGenerationUtil.addEParameters(op, eobjParam, featParam,
				FluentAPIGeneralParameterGenerator.getFeatValParam());
		FluentAPIGenerationUtil.addDocumentation(op, ModelConstants.RootAPI.WithAddedFeat.SUMMARY.get());
		return op;
	}

	private EOperation getWithRemovedFeatOp(FluentAPIGenerationContext context) {
		var eobjParam = FluentAPIGeneralParameterGenerator.getEObjectParam();
		var featParam = FluentAPIGeneralParameterGenerator.getFeatParam();
		var op = FluentAPIGenerationUtil.generateEOperation(ModelConstants.RootAPI.WithRemovedFeat.NAME.get(),
				context.getFluentAPIECls());
		FluentAPIGenerationUtil.addBody(op, String.format(featureValModificationMethodBodyTemplate,
				ModelConstants.RootAPI.WithRemovedFeat.NAME.get()));
		FluentAPIGenerationUtil.addEParameters(op, eobjParam, featParam,
				FluentAPIGeneralParameterGenerator.getFeatValParam());
		FluentAPIGenerationUtil.addDocumentation(op, ModelConstants.RootAPI.WithRemovedFeat.SUMMARY.get());
		return op;
	}

	private EOperation getCleanFeatOp(FluentAPIGenerationContext context) {
		var eobjParam = FluentAPIGeneralParameterGenerator.getEObjectParam();
		var featParam = FluentAPIGeneralParameterGenerator.getFeatParam();
		var op = FluentAPIGenerationUtil.generateEOperation(ModelConstants.RootAPI.CleanFeat.NAME.get(),
				context.getFluentAPIECls());
		FluentAPIGenerationUtil.addBody(op,
				String.format(featureValCleaningMethodBodyTemplate, ModelConstants.RootAPI.CleanFeat.NAME.get()));
		FluentAPIGenerationUtil.addEParameters(op, eobjParam, featParam);
		FluentAPIGenerationUtil.addDocumentation(op, ModelConstants.RootAPI.CleanFeat.SUMMARY.get());
		return op;
	}
}
