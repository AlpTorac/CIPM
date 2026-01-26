package cipm.consistency.fluentapi.gen.rootapi;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EcorePackage;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;
import cipm.consistency.fluentapi.gen.methods.FluentEObjectAPIMethods;

public class FluentAPIRootAPIWithOperationGenerator {
	// TODO Add documentation

	private static final String xWithFeatMethodBody = FluentAPIMethodsUtil.joinLOC(
			FluentEObjectAPIMethods.class.getName() + ".xWithFeat(this, "
					+ FluentAPIRootAPIConstants.getFluentAPIRootAPIWithMethodEObjectParameterName() + ", "
					+ FluentAPIRootAPIConstants.getFluentAPIRootAPIWithMethodFeatureParameterName() + ", "
					+ FluentAPIRootAPIConstants.getFluentAPIRootAPIWithMethodFeatureValueParameterName() + ")",
			//
			"return this");

	private static final String xWithoutFeatMethodBody = FluentAPIMethodsUtil.joinLOC(
			FluentEObjectAPIMethods.class.getName() + ".xWithoutFeat(this, "
					+ FluentAPIRootAPIConstants.getFluentAPIRootAPIWithMethodEObjectParameterName() + ", "
					+ FluentAPIRootAPIConstants.getFluentAPIRootAPIWithMethodFeatureParameterName() + ")",
			//
			"return this");

	private static final String xWithAddedFeatMethodBody = FluentAPIMethodsUtil.joinLOC(
			FluentEObjectAPIMethods.class.getName() + ".xWithAddedFeat(this, "
					+ FluentAPIRootAPIConstants.getFluentAPIRootAPIWithMethodEObjectParameterName() + ", "
					+ FluentAPIRootAPIConstants.getFluentAPIRootAPIWithMethodFeatureParameterName() + ", "
					+ FluentAPIRootAPIConstants.getFluentAPIRootAPIWithMethodFeatureValueParameterName() + ")",
			//
			"return this");

	private static final String xWithRemovedFeatMethodBody = FluentAPIMethodsUtil.joinLOC(
			FluentEObjectAPIMethods.class.getName() + ".xWithRemovedFeat(this, "
					+ FluentAPIRootAPIConstants.getFluentAPIRootAPIWithMethodEObjectParameterName() + ", "
					+ FluentAPIRootAPIConstants.getFluentAPIRootAPIWithMethodFeatureParameterName() + ", "
					+ FluentAPIRootAPIConstants.getFluentAPIRootAPIWithMethodFeatureValueParameterName() + ")",
			//
			"return this");

	private static final String xWithExactFeatMethodBody = FluentAPIMethodsUtil.joinLOC(
			FluentEObjectAPIMethods.class.getName() + ".xWithExactFeat(this, "
					+ FluentAPIRootAPIConstants.getFluentAPIRootAPIWithMethodEObjectParameterName() + ", "
					+ FluentAPIRootAPIConstants.getFluentAPIRootAPIWithMethodFeatureParameterName() + ", "
					+ FluentAPIRootAPIConstants.getFluentAPIRootAPIWithMethodFeatureValueParameterName() + ")",
			//
			"return this");

	private static final String xWithFeatOfContainerMethodBody = FluentAPIMethodsUtil.joinLOC(
			FluentEObjectAPIMethods.class.getName() + ".xWithFeatOfContainer(this, "
					+ FluentAPIRootAPIConstants.getFluentAPIRootAPIWithMethodEObjectParameterName() + ", "
					+ FluentAPIRootAPIConstants.getFluentAPIRootAPIWithMethodFeatureParameterName() + ")",
			//
			"return this");

	public List<EOperation> getAllAPITopLevelWithOperations(EClass rootAPICls) {
		var ops = new ArrayList<EOperation>();

		ops.add(getWithFeatOp(rootAPICls));
		ops.add(getWithoutFeatOp(rootAPICls));
		ops.add(getWithAddedFeatOp(rootAPICls));
		ops.add(getWithRemovedFeatOp(rootAPICls));
		ops.add(getWithExactFeatOp(rootAPICls));
		ops.add(getWithAddedFeatArrayOp(rootAPICls));
		ops.add(getWithRemovedFeatArrayOp(rootAPICls));
		ops.add(getWithExactFeatArrayOp(rootAPICls));
		ops.add(getWithFeatOfContainerOp(rootAPICls));

		return ops;
	}

	private EOperation getWithFeatOp(EClass rootAPICls) {
		var eobjParam = getEObjectParam();
		var featParam = getFeatParam();
		var featValParam = getFeatValParam();

		var op = FluentAPIGenerationUtil
				.generateEOperation(FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithFeatMethodName(), rootAPICls);
		FluentAPIGenerationUtil.addBody(op, xWithFeatMethodBody);
		FluentAPIGenerationUtil.addEParameters(op, eobjParam, featParam, featValParam);
		return op;
	}

	private EOperation getWithoutFeatOp(EClass rootAPICls) {
		var eobjParam = getEObjectParam();
		var featParam = getFeatParam();

		var op = FluentAPIGenerationUtil
				.generateEOperation(FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithoutFeatMethodName(), rootAPICls);
		FluentAPIGenerationUtil.addBody(op, xWithoutFeatMethodBody);
		FluentAPIGenerationUtil.addEParameters(op, eobjParam, featParam);
		return op;
	}

	private EOperation getWithAddedFeatOp(EClass rootAPICls) {
		var eobjParam = getEObjectParam();
		var featParam = getFeatParam();
		var featValParam = getFeatValParam();

		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithAddedFeatMethodName(), rootAPICls);
		FluentAPIGenerationUtil.addBody(op, xWithAddedFeatMethodBody);
		FluentAPIGenerationUtil.addEParameters(op, eobjParam, featParam, featValParam);
		return op;
	}

	private EOperation getWithRemovedFeatOp(EClass rootAPICls) {
		var eobjParam = getEObjectParam();
		var featParam = getFeatParam();
		var featValParam = getFeatValParam();

		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithRemovedFeatMethodName(), rootAPICls);
		FluentAPIGenerationUtil.addBody(op, xWithRemovedFeatMethodBody);
		FluentAPIGenerationUtil.addEParameters(op, eobjParam, featParam, featValParam);
		return op;
	}

	private EOperation getWithExactFeatOp(EClass rootAPICls) {
		var eobjParam = getEObjectParam();
		var featParam = getFeatParam();
		var featValParam = getFeatValParam();

		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithExactFeatMethodName(), rootAPICls);
		FluentAPIGenerationUtil.addBody(op, xWithExactFeatMethodBody);
		FluentAPIGenerationUtil.addEParameters(op, eobjParam, featParam, featValParam);
		return op;
	}

	private EOperation getWithAddedFeatArrayOp(EClass rootAPICls) {
		var eobjParam = getEObjectParam();
		var featParam = getFeatParam();
		var featValParam = getFeatValArrayParam();

		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithAddedFeatMethodName(), rootAPICls);
		FluentAPIGenerationUtil.addBody(op, xWithAddedFeatMethodBody);
		FluentAPIGenerationUtil.addEParameters(op, eobjParam, featParam, featValParam);
		return op;
	}

	private EOperation getWithRemovedFeatArrayOp(EClass rootAPICls) {
		var eobjParam = getEObjectParam();
		var featParam = getFeatParam();
		var featValParam = getFeatValArrayParam();

		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithRemovedFeatMethodName(), rootAPICls);
		FluentAPIGenerationUtil.addBody(op, xWithRemovedFeatMethodBody);
		FluentAPIGenerationUtil.addEParameters(op, eobjParam, featParam, featValParam);
		return op;
	}

	private EOperation getWithExactFeatArrayOp(EClass rootAPICls) {
		var eobjParam = getEObjectParam();
		var featParam = getFeatParam();
		var featValParam = getFeatValArrayParam();

		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithExactFeatMethodName(), rootAPICls);
		FluentAPIGenerationUtil.addBody(op, xWithExactFeatMethodBody);
		FluentAPIGenerationUtil.addEParameters(op, eobjParam, featParam, featValParam);
		return op;
	}

	private EOperation getWithFeatOfContainerOp(EClass rootAPICls) {
		var eobjParam = getEObjectParam();
		var featParam = getFeatParam();

		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithFeatOfContainerMethodName(), rootAPICls);
		FluentAPIGenerationUtil.addBody(op, xWithFeatOfContainerMethodBody);
		FluentAPIGenerationUtil.addEParameters(op, eobjParam, featParam);
		return op;
	}

	private EParameter getEObjectParam() {
		// TODO Add documentation
		return FluentAPIGenerationUtil.generateSingleValuedEParameter(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIWithMethodEObjectParameterName(),
				EcorePackage.Literals.EOBJECT);
	}

	private EParameter getFeatParam() {
		// TODO Add documentation
		return FluentAPIGenerationUtil.generateSingleValuedEParameter(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIWithMethodFeatureParameterName(),
				EcorePackage.Literals.ESTRUCTURAL_FEATURE);
	}

	private EParameter getFeatValParam() {
		// TODO Add documentation
		return FluentAPIGenerationUtil.generateSingleValuedEParameter(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIWithMethodFeatureValueParameterName(),
				EcorePackage.Literals.EJAVA_OBJECT);
	}

	private EParameter getFeatValArrayParam() {
		// TODO Add documentation
		return FluentAPIGenerationUtil.generateArrayValuedEParameter(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIWithMethodFeatureValueParameterName(),
				EcorePackage.Literals.EJAVA_OBJECT);
	}
}
