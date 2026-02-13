package cipm.consistency.fluentapi.gen.rootapi;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;

import cipm.consistency.fluentapi.gen.FluentAPIGeneralParameterGenerator;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationContext;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;
import cipm.consistency.fluentapi.gen.methods.FluentEObjectAPIMethods;

public class FluentAPIRootAPIWithOperationGenerator {
	// TODO Add documentation

	private static final String xWithFeatValParameterMethodBody = FluentAPIMethodsUtil.joinLOC(
			// %s: Corresponding method's name in FluentEObjectAPIMethods
			FluentEObjectAPIMethods.class.getName() + ".%s(this, "
					+ FluentAPIGeneralParameterGenerator.getFluentAPIEObjectParameterName() + ", "
					+ FluentAPIGeneralParameterGenerator.getFluentAPIFeatureParameterName() + ", "
					+ FluentAPIGeneralParameterGenerator.getFluentAPIFeatureValueParameterName() + ")",
			"return this");

	private static final String xWithoutFeatValParameterMethodBody = FluentAPIMethodsUtil.joinLOC(
			// %s: Corresponding method's name in FluentEObjectAPIMethods
			FluentEObjectAPIMethods.class.getName() + ".%s(this, "
					+ FluentAPIGeneralParameterGenerator.getFluentAPIEObjectParameterName() + ", "
					+ FluentAPIGeneralParameterGenerator.getFluentAPIFeatureParameterName() + ")",
			"return this");

	public List<EOperation> getAllAPITopLevelWithOperations(FluentAPIGenerationContext context) {
		var ops = new ArrayList<EOperation>();

		ops.add(getWithFeatOp(context));
		ops.add(getWithoutFeatOp(context));
		ops.addAll(getWithAddedFeatOp(context));
		ops.addAll(getWithRemovedFeatOp(context));
		ops.addAll(getWithExactFeatOp(context));

		return ops;
	}

	private EOperation getWithFeatOp(FluentAPIGenerationContext context) {
		var eobjParam = FluentAPIGeneralParameterGenerator.getEObjectParam();
		var featParam = FluentAPIGeneralParameterGenerator.getFeatParam();
		var featValParam = FluentAPIGeneralParameterGenerator.getFeatValParam();

		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithFeatMethodName(), context.getFluentAPIECls());
		FluentAPIGenerationUtil.addBody(op, String.format(xWithFeatValParameterMethodBody, "xWithFeat"));
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
		FluentAPIGenerationUtil.addBody(op, String.format(xWithoutFeatValParameterMethodBody, "xWithoutFeat"));
		FluentAPIGenerationUtil.addEParameters(op, eobjParam, featParam);
		FluentAPIGenerationUtil.addDocumentation(op,
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithoutFeatMethodSummary());
		return op;
	}

	private List<EOperation> getWithAddedFeatOp(FluentAPIGenerationContext context) {
		var ops = new ArrayList<EOperation>();
		Function<EParameter, EOperation> opGen = (featValParam) -> {
			var eobjParam = FluentAPIGeneralParameterGenerator.getEObjectParam();
			var featParam = FluentAPIGeneralParameterGenerator.getFeatParam();
			var op = FluentAPIGenerationUtil.generateEOperation(
					FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithAddedFeatMethodName(),
					context.getFluentAPIECls());
			FluentAPIGenerationUtil.addBody(op, String.format(xWithFeatValParameterMethodBody, "xWithAddedFeat"));
			FluentAPIGenerationUtil.addEParameters(op, eobjParam, featParam, featValParam);
			FluentAPIGenerationUtil.addDocumentation(op,
					FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithAddedFeatMethodSummary());
			return op;
		};

		ops.add(opGen.apply(FluentAPIGeneralParameterGenerator.getFeatValParam()));
		ops.add(opGen.apply(FluentAPIGeneralParameterGenerator.getFeatValArrayParam(context)));
		ops.add(opGen.apply(FluentAPIGeneralParameterGenerator.getFeatValColParam(context)));

		return ops;
	}

	private List<EOperation> getWithRemovedFeatOp(FluentAPIGenerationContext context) {
		var ops = new ArrayList<EOperation>();
		Function<EParameter, EOperation> opGen = (featValParam) -> {
			var eobjParam = FluentAPIGeneralParameterGenerator.getEObjectParam();
			var featParam = FluentAPIGeneralParameterGenerator.getFeatParam();
			var op = FluentAPIGenerationUtil.generateEOperation(
					FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithRemovedFeatMethodName(),
					context.getFluentAPIECls());
			FluentAPIGenerationUtil.addBody(op, String.format(xWithFeatValParameterMethodBody, "xWithRemovedFeat"));
			FluentAPIGenerationUtil.addEParameters(op, eobjParam, featParam, featValParam);
			FluentAPIGenerationUtil.addDocumentation(op,
					FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithRemovedFeatMethodSummary());
			return op;
		};

		ops.add(opGen.apply(FluentAPIGeneralParameterGenerator.getFeatValParam()));
		ops.add(opGen.apply(FluentAPIGeneralParameterGenerator.getFeatValArrayParam(context)));
		ops.add(opGen.apply(FluentAPIGeneralParameterGenerator.getFeatValColParam(context)));

		return ops;
	}

	private List<EOperation> getWithExactFeatOp(FluentAPIGenerationContext context) {
		var ops = new ArrayList<EOperation>();
		Function<EParameter, EOperation> opGen = (featValParam) -> {
			var eobjParam = FluentAPIGeneralParameterGenerator.getEObjectParam();
			var featParam = FluentAPIGeneralParameterGenerator.getFeatParam();
			var op = FluentAPIGenerationUtil.generateEOperation(
					FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithExactFeatMethodName(),
					context.getFluentAPIECls());
			FluentAPIGenerationUtil.addBody(op, String.format(xWithFeatValParameterMethodBody, "xWithExactFeat"));
			FluentAPIGenerationUtil.addEParameters(op, eobjParam, featParam, featValParam);
			FluentAPIGenerationUtil.addDocumentation(op,
					FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithExactFeatMethodSummary());
			return op;
		};

		ops.add(opGen.apply(FluentAPIGeneralParameterGenerator.getFeatValParam()));
		ops.add(opGen.apply(FluentAPIGeneralParameterGenerator.getFeatValArrayParam(context)));
		ops.add(opGen.apply(FluentAPIGeneralParameterGenerator.getFeatValColParam(context)));
		return ops;
	}
}
