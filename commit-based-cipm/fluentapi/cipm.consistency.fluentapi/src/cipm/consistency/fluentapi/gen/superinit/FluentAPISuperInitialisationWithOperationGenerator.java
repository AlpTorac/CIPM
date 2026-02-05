package cipm.consistency.fluentapi.gen.superinit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EcorePackage;

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
					+ "(), " + FluentAPIRootAPIConstants.getFluentAPIRootAPIWithMethodFeatureParameterName() + ", "
					+ FluentAPIRootAPIConstants.getFluentAPIRootAPIWithMethodFeatureValueParameterName() + ")",
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
					+ "(), " + FluentAPIRootAPIConstants.getFluentAPIRootAPIWithMethodFeatureParameterName() + ")",
			"return this");

	public List<EOperation> getAllAPITopLevelWithOperations(FluentAPIGenerationContext context) {
		var ops = new ArrayList<EOperation>();

		ops.add(getWithFeatOp(context));
		ops.add(getWithoutFeatOp(context));
		ops.addAll(getWithAddedFeatOp(context));
		ops.addAll(getWithRemovedFeatOp(context));
		ops.addAll(getWithExactFeatOp(context));
		ops.add(getWithFeatOfContainerOp(context));

		return ops;
	}

	private EOperation getWithFeatOp(FluentAPIGenerationContext context) {
		var featParam = getFeatParam();
		var featValParam = getFeatValParam();

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
		var featParam = getFeatParam();

		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithoutFeatMethodName(), context.getInitSuperECls());
		FluentAPIGenerationUtil.addBody(op, String.format(xWithoutFeatValParameterDelegateMethodBodyTemplate,
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithoutFeatMethodName()));
		FluentAPIGenerationUtil.addEParameters(op, featParam);
		FluentAPIGenerationUtil.addDocumentation(op,
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithoutFeatMethodSummary());
		return op;
	}

	private List<EOperation> getWithAddedFeatOp(FluentAPIGenerationContext context) {
		var ops = new ArrayList<EOperation>();
		Function<EParameter, EOperation> opGen = (featValParam) -> {
			var featParam = getFeatParam();
			var op = FluentAPIGenerationUtil.generateEOperation(
					FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithAddedFeatMethodName(),
					context.getInitSuperECls());
			FluentAPIGenerationUtil.addBody(op, String.format(xWithFeatValParameterDelegateMethodBodyTemplate,
					FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithAddedFeatMethodName()));
			FluentAPIGenerationUtil.addEParameters(op, featParam, featValParam);
			FluentAPIGenerationUtil.addDocumentation(op,
					FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithAddedFeatMethodSummary());
			return op;
		};

		ops.add(opGen.apply(getFeatValParam()));
		ops.add(opGen.apply(getFeatValArrayParam(context)));
		ops.add(opGen.apply(getFeatValColParam(context)));

		return ops;
	}

	private List<EOperation> getWithRemovedFeatOp(FluentAPIGenerationContext context) {
		var ops = new ArrayList<EOperation>();
		Function<EParameter, EOperation> opGen = (featValParam) -> {
			var featParam = getFeatParam();
			var op = FluentAPIGenerationUtil.generateEOperation(
					FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithRemovedFeatMethodName(),
					context.getInitSuperECls());
			FluentAPIGenerationUtil.addBody(op, String.format(xWithFeatValParameterDelegateMethodBodyTemplate,
					FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithRemovedFeatMethodName()));
			FluentAPIGenerationUtil.addEParameters(op, featParam, featValParam);
			FluentAPIGenerationUtil.addDocumentation(op,
					FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithRemovedFeatMethodSummary());
			return op;
		};

		ops.add(opGen.apply(getFeatValParam()));
		ops.add(opGen.apply(getFeatValArrayParam(context)));
		ops.add(opGen.apply(getFeatValColParam(context)));

		return ops;
	}

	private List<EOperation> getWithExactFeatOp(FluentAPIGenerationContext context) {
		var ops = new ArrayList<EOperation>();
		Function<EParameter, EOperation> opGen = (featValParam) -> {
			var featParam = getFeatParam();
			var op = FluentAPIGenerationUtil.generateEOperation(
					FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithExactFeatMethodName(),
					context.getInitSuperECls());
			FluentAPIGenerationUtil.addBody(op, String.format(xWithFeatValParameterDelegateMethodBodyTemplate,
					FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithExactFeatMethodName()));
			FluentAPIGenerationUtil.addEParameters(op, featParam, featValParam);
			FluentAPIGenerationUtil.addDocumentation(op,
					FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithExactFeatMethodSummary());
			return op;
		};

		ops.add(opGen.apply(getFeatValParam()));
		ops.add(opGen.apply(getFeatValArrayParam(context)));
		ops.add(opGen.apply(getFeatValColParam(context)));
		return ops;
	}

	private EOperation getWithFeatOfContainerOp(FluentAPIGenerationContext context) {
		var featParam = getFeatParam();

		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithFeatOfContainerMethodName(),
				context.getInitSuperECls());
		FluentAPIGenerationUtil.addBody(op, String.format(xWithoutFeatValParameterDelegateMethodBodyTemplate,
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithFeatOfContainerMethodName()));
		FluentAPIGenerationUtil.addEParameters(op, featParam);
		FluentAPIGenerationUtil.addDocumentation(op,
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithFeatOfContainerMethodSummary());
		return op;
	}

	private EParameter getFeatParam() {
		var param = FluentAPIGenerationUtil.generateSingleValuedEParameter(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIWithMethodFeatureParameterName(),
				EcorePackage.Literals.ESTRUCTURAL_FEATURE);
		FluentAPIGenerationUtil.addDocumentation(param,
				FluentAPIRootAPIConstants.getFluentAPIRootAPIWithMethodFeatureParameterDocumentation());
		return param;
	}

	private EParameter getFeatValParam() {
		var param = FluentAPIGenerationUtil.generateSingleValuedEParameter(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIWithMethodFeatureValueParameterName(),
				EcorePackage.Literals.EJAVA_OBJECT);
		FluentAPIGenerationUtil.addDocumentation(param,
				FluentAPIRootAPIConstants.getFluentAPIRootAPIWithMethodFeatureValueParameterDocumentation());
		return param;
	}

	private EParameter getFeatValArrayParam(FluentAPIGenerationContext context) {
		var param = FluentAPIGenerationUtil.generateArrayValuedEParameter(context,
				FluentAPIRootAPIConstants.getFluentAPIRootAPIWithMethodFeatureValueParameterName(),
				EcorePackage.Literals.EJAVA_OBJECT);
		FluentAPIGenerationUtil.addDocumentation(param,
				FluentAPIRootAPIConstants.getFluentAPIRootAPIWithMethodFeatureValueParameterDocumentation());
		return param;
	}

	private EParameter getFeatValColParam(FluentAPIGenerationContext context) {
		var pureColType = FluentAPIGenerationUtil.createOrGetEDataType(context, Collection.class, 1);
		var colGenTypeArgument = FluentAPIGenerationUtil.generateEGenericTypeWithBounds(null, null);
		var colGenType = FluentAPIGenerationUtil.generateEGenericTypeWithClassifier(pureColType);
		FluentAPIGenerationUtil.addTypeArgument(colGenType, colGenTypeArgument);

		var param = FluentAPIGenerationUtil.generateSingleValuedEParameter(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIWithMethodFeatureValueParameterName(), colGenType);
		return param;
	}

	@Override
	public Map<String, String> getMethodNamesToDescriptions() {
		return Map.of(FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithFeatMethodName(),
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithFeatMethodSummary(),

				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithoutFeatMethodName(),
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithoutFeatMethodSummary(),

				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithFeatOfContainerMethodName(),
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithFeatOfContainerMethodSummary(),

				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithAddedFeatMethodName(),
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithAddedFeatMethodSummary(),

				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithRemovedFeatMethodName(),
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithRemovedFeatMethodSummary(),

				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithExactFeatMethodName(),
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithExactFeatMethodSummary());
	}
}
