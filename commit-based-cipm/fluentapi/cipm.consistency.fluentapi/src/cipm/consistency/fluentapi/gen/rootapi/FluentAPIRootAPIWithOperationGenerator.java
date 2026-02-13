package cipm.consistency.fluentapi.gen.rootapi;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EOperation;

import cipm.consistency.fluentapi.gen.FluentAPIGeneralParameterGenerator;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationContext;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
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

		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithFeatMethodName(), context.getFluentAPIECls());
		FluentAPIGenerationUtil.addBody(op, String.format(featureValModificationMethodBodyTemplate,
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithFeatMethodName()));
		FluentAPIGenerationUtil.addEParameters(op, eobjParam, featParam, featValParam);
		FluentAPIGenerationUtil.addDocumentation(op,
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithFeatMethodSummary());
		return op;
	}

	private EOperation getWithoutFeatOp(FluentAPIGenerationContext context) {
		var eobjParam = FluentAPIGeneralParameterGenerator.getEObjectParam();
		var featParam = FluentAPIGeneralParameterGenerator.getFeatParam();

		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithoutFeatMethodName(), context.getFluentAPIECls());
		FluentAPIGenerationUtil.addBody(op, String.format(featureValCleaningMethodBodyTemplate,
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithoutFeatMethodName()));
		FluentAPIGenerationUtil.addEParameters(op, eobjParam, featParam);
		FluentAPIGenerationUtil.addDocumentation(op,
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithoutFeatMethodSummary());
		return op;
	}

	private EOperation getWithAddedFeatOp(FluentAPIGenerationContext context) {
		var eobjParam = FluentAPIGeneralParameterGenerator.getEObjectParam();
		var featParam = FluentAPIGeneralParameterGenerator.getFeatParam();
		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithAddedFeatMethodName(),
				context.getFluentAPIECls());
		FluentAPIGenerationUtil.addBody(op, String.format(featureValModificationMethodBodyTemplate,
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithAddedFeatMethodName()));
		FluentAPIGenerationUtil.addEParameters(op, eobjParam, featParam, FluentAPIGeneralParameterGenerator.getFeatValParam());
		FluentAPIGenerationUtil.addDocumentation(op,
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithAddedFeatMethodSummary());
		return op;
	}

	private EOperation getWithRemovedFeatOp(FluentAPIGenerationContext context) {
		var eobjParam = FluentAPIGeneralParameterGenerator.getEObjectParam();
		var featParam = FluentAPIGeneralParameterGenerator.getFeatParam();
		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithRemovedFeatMethodName(),
				context.getFluentAPIECls());
		FluentAPIGenerationUtil.addBody(op, String.format(featureValModificationMethodBodyTemplate,
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithRemovedFeatMethodName()));
		FluentAPIGenerationUtil.addEParameters(op, eobjParam, featParam, FluentAPIGeneralParameterGenerator.getFeatValParam());
		FluentAPIGenerationUtil.addDocumentation(op,
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithRemovedFeatMethodSummary());
		return op;
	}

	private EOperation getCleanFeatOp(FluentAPIGenerationContext context) {
		var eobjParam = FluentAPIGeneralParameterGenerator.getEObjectParam();
		var featParam = FluentAPIGeneralParameterGenerator.getFeatParam();
		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXCleanFeatMethodName(), context.getFluentAPIECls());
		FluentAPIGenerationUtil.addBody(op, String.format(featureValCleaningMethodBodyTemplate,
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXCleanFeatMethodName()));
		FluentAPIGenerationUtil.addEParameters(op, eobjParam, featParam);
		FluentAPIGenerationUtil.addDocumentation(op,
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXCleanFeatMethodSummary());
		return op;
	}
}
