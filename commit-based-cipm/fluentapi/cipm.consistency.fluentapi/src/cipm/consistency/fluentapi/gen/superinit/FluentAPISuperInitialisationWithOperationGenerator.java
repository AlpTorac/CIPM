package cipm.consistency.fluentapi.gen.superinit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EOperation;

import cipm.consistency.fluentapi.gen.FluentAPIGeneralParameterGenerator;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationContext;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.IFluentAPIMethodGenerator;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;
import cipm.consistency.fluentapi.gen.rootapi.FluentAPIRootAPIConstants;

public class FluentAPISuperInitialisationWithOperationGenerator implements IFluentAPIMethodGenerator {
	/**
	 * The template for with/without methods that delegate to the api instance,
	 * which take a feature value parameter.
	 */
	private static final String xWithFeatValParameterDelegateMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			// %s: api.with method name
			"this." + FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationToAPIMethodName()
					+ "().%s(this.get"
					+ FluentAPISuperInitialisationConstants
							.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName()
					+ "(), " + FluentAPIGeneralParameterGenerator.getFluentAPIFeatureParameterName() + ", "
					+ FluentAPIGeneralParameterGenerator.getFluentAPIFeatureValueParameterName() + ")",
			"return this");

	/**
	 * The template for with/without methods that delegate to the api instance,
	 * which do not take a feature value parameter.
	 */
	private static final String xWithoutFeatValParameterDelegateMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			// %s: api.with method name
			"this." + FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationToAPIMethodName()
					+ "().%s(this.get"
					+ FluentAPISuperInitialisationConstants
							.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName()
					+ "(), " + FluentAPIGeneralParameterGenerator.getFluentAPIFeatureParameterName() + ")",
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
		var featParam = FluentAPIGeneralParameterGenerator.getFeatParam();
		var featValParam = FluentAPIGeneralParameterGenerator.getFeatValParam();

		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithFeatMethodName(), context.getInitSuperECls());
		FluentAPIGenerationUtil.addBody(op, String.format(xWithFeatValParameterDelegateMethodBodyTemplate,
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithFeatMethodName()));
		FluentAPIGenerationUtil.addEParameters(op, featParam, featValParam);
		FluentAPIGenerationUtil.addDocumentation(op,
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithFeatMethodSummary());
		return op;
	}

	private EOperation getWithoutFeatOp(FluentAPIGenerationContext context) {
		var featParam = FluentAPIGeneralParameterGenerator.getFeatParam();

		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithoutFeatMethodName(), context.getInitSuperECls());
		FluentAPIGenerationUtil.addBody(op, String.format(xWithoutFeatValParameterDelegateMethodBodyTemplate,
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithoutFeatMethodName()));
		FluentAPIGenerationUtil.addEParameters(op, featParam);
		FluentAPIGenerationUtil.addDocumentation(op,
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithoutFeatMethodSummary());
		return op;
	}

	private EOperation getWithAddedFeatOp(FluentAPIGenerationContext context) {
		var featParam = FluentAPIGeneralParameterGenerator.getFeatParam();
		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithAddedFeatMethodName(), context.getInitSuperECls());
		FluentAPIGenerationUtil.addBody(op, String.format(xWithFeatValParameterDelegateMethodBodyTemplate,
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithAddedFeatMethodName()));
		FluentAPIGenerationUtil.addEParameters(op, featParam, FluentAPIGeneralParameterGenerator.getFeatValParam());
		FluentAPIGenerationUtil.addDocumentation(op,
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithAddedFeatMethodSummary());
		return op;
	}

	private EOperation getWithRemovedFeatOp(FluentAPIGenerationContext context) {
		var featParam = FluentAPIGeneralParameterGenerator.getFeatParam();
		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithRemovedFeatMethodName(), context.getInitSuperECls());
		FluentAPIGenerationUtil.addBody(op, String.format(xWithFeatValParameterDelegateMethodBodyTemplate,
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithRemovedFeatMethodName()));
		FluentAPIGenerationUtil.addEParameters(op, featParam, FluentAPIGeneralParameterGenerator.getFeatValParam());
		FluentAPIGenerationUtil.addDocumentation(op,
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithRemovedFeatMethodSummary());
		return op;
	}

	private EOperation getCleanFeatOp(FluentAPIGenerationContext context) {
		var featParam = FluentAPIGeneralParameterGenerator.getFeatParam();
		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXCleanFeatMethodName(), context.getInitSuperECls());
		FluentAPIGenerationUtil.addBody(op, String.format(xWithoutFeatValParameterDelegateMethodBodyTemplate,
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXCleanFeatMethodName()));
		FluentAPIGenerationUtil.addEParameters(op, featParam);
		FluentAPIGenerationUtil.addDocumentation(op,
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXCleanFeatMethodSummary());
		return op;
	}

	@Override
	public Map<String, String> getMethodNamesToDescriptions() {
		return Map.of(FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithFeatMethodName(),
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithFeatMethodSummary(),

				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithoutFeatMethodName(),
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithoutFeatMethodSummary(),

				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithAddedFeatMethodName(),
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithAddedFeatMethodSummary(),

				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithRemovedFeatMethodName(),
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithRemovedFeatMethodSummary(),

				FluentAPIRootAPIConstants.getFluentAPIRootAPIXCleanFeatMethodName(),
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXCleanFeatMethodSummary());
	}
}
