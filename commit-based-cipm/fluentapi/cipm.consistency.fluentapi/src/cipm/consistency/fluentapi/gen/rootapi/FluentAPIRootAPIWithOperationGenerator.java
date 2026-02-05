package cipm.consistency.fluentapi.gen.rootapi;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EcorePackage;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationContext;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;
import cipm.consistency.fluentapi.gen.methods.FluentEObjectAPIMethods;

public class FluentAPIRootAPIWithOperationGenerator {
	// TODO Add documentation

	private static final String xWithFeatValParameterMethodBody = FluentAPIMethodsUtil.joinLOC(
			// %s: Corresponding method's name in FluentEObjectAPIMethods
			FluentEObjectAPIMethods.class.getName() + ".%s(this, "
					+ FluentAPIRootAPIConstants.getFluentAPIRootAPIWithMethodEObjectParameterName() + ", "
					+ FluentAPIRootAPIConstants.getFluentAPIRootAPIWithMethodFeatureParameterName() + ", "
					+ FluentAPIRootAPIConstants.getFluentAPIRootAPIWithMethodFeatureValueParameterName() + ")",
			//
			"return this");

	private static final String xWithoutFeatValParameterMethodBody = FluentAPIMethodsUtil.joinLOC(
			// %s: Corresponding method's name in FluentEObjectAPIMethods
			FluentEObjectAPIMethods.class.getName() + ".%s(this, "
					+ FluentAPIRootAPIConstants.getFluentAPIRootAPIWithMethodEObjectParameterName() + ", "
					+ FluentAPIRootAPIConstants.getFluentAPIRootAPIWithMethodFeatureParameterName() + ")",
			//
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
		var eobjParam = getEObjectParam();
		var featParam = getFeatParam();
		var featValParam = getFeatValParam();

		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithFeatMethodName(), context.getFluentAPIECls());
		FluentAPIGenerationUtil.addBody(op, String.format(xWithFeatValParameterMethodBody, "xWithFeat"));
		FluentAPIGenerationUtil.addEParameters(op, eobjParam, featParam, featValParam);
		FluentAPIGenerationUtil.addDocumentation(op,
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithFeatMethodSummary());
		return op;
	}

	private EOperation getWithoutFeatOp(FluentAPIGenerationContext context) {
		var eobjParam = getEObjectParam();
		var featParam = getFeatParam();

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
			var eobjParam = getEObjectParam();
			var featParam = getFeatParam();
			var op = FluentAPIGenerationUtil.generateEOperation(
					FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithAddedFeatMethodName(),
					context.getFluentAPIECls());
			FluentAPIGenerationUtil.addBody(op, String.format(xWithFeatValParameterMethodBody, "xWithAddedFeat"));
			FluentAPIGenerationUtil.addEParameters(op, eobjParam, featParam, featValParam);
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
			var eobjParam = getEObjectParam();
			var featParam = getFeatParam();
			var op = FluentAPIGenerationUtil.generateEOperation(
					FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithRemovedFeatMethodName(),
					context.getFluentAPIECls());
			FluentAPIGenerationUtil.addBody(op, String.format(xWithFeatValParameterMethodBody, "xWithRemovedFeat"));
			FluentAPIGenerationUtil.addEParameters(op, eobjParam, featParam, featValParam);
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
			var eobjParam = getEObjectParam();
			var featParam = getFeatParam();
			var op = FluentAPIGenerationUtil.generateEOperation(
					FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithExactFeatMethodName(),
					context.getFluentAPIECls());
			FluentAPIGenerationUtil.addBody(op, String.format(xWithFeatValParameterMethodBody, "xWithExactFeat"));
			FluentAPIGenerationUtil.addEParameters(op, eobjParam, featParam, featValParam);
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
		var eobjParam = getEObjectParam();
		var featParam = getFeatParam();

		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithFeatOfContainerMethodName(),
				context.getFluentAPIECls());
		FluentAPIGenerationUtil.addBody(op, String.format(xWithoutFeatValParameterMethodBody, "xWithFeatOfContainer"));
		FluentAPIGenerationUtil.addEParameters(op, eobjParam, featParam);
		FluentAPIGenerationUtil.addDocumentation(op,
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithFeatOfContainerMethodSummary());
		return op;
	}

	private EParameter getEObjectParam() {
		var param = FluentAPIGenerationUtil.generateSingleValuedEParameter(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIWithMethodEObjectParameterName(),
				EcorePackage.Literals.EOBJECT);
		FluentAPIGenerationUtil.addDocumentation(param,
				FluentAPIRootAPIConstants.getFluentAPIRootAPIWithMethodEObjectParameterDocumentation());
		return param;
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
}
