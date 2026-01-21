package cipm.consistency.fluentapi.gen.rootapi;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EcorePackage;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.FluentAPIRootAPIConstants;
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

		return FluentAPIGenerationUtil.generateEOperationWithBody(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithFeatMethodName(), rootAPICls,
				String.format(xWithFeatMethodBody, eobjParam.getName(), featParam.getName(), featValParam.getName()),
				eobjParam, featParam, featValParam);
	}

	private EOperation getWithoutFeatOp(EClass rootAPICls) {
		var eobjParam = getEObjectParam();
		var featParam = getFeatParam();

		return FluentAPIGenerationUtil.generateEOperationWithBody(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithoutFeatMethodName(), rootAPICls,
				String.format(xWithoutFeatMethodBody, eobjParam.getName(), featParam.getName()), eobjParam, featParam);
	}

	private EOperation getWithAddedFeatOp(EClass rootAPICls) {
		var eobjParam = getEObjectParam();
		var featParam = getFeatParam();
		var featValParam = getFeatValParam();

		return FluentAPIGenerationUtil.generateEOperationWithBody(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithAddedFeatMethodName(), rootAPICls,
				String.format(xWithAddedFeatMethodBody, eobjParam.getName(), featParam.getName(),
						featValParam.getName()),
				eobjParam, featParam, featValParam);
	}

	private EOperation getWithRemovedFeatOp(EClass rootAPICls) {
		var eobjParam = getEObjectParam();
		var featParam = getFeatParam();
		var featValParam = getFeatValParam();

		return FluentAPIGenerationUtil.generateEOperationWithBody(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithRemovedFeatMethodName(), rootAPICls,
				String.format(xWithRemovedFeatMethodBody, eobjParam.getName(), featParam.getName(),
						featValParam.getName()),
				eobjParam, featParam, featValParam);
	}

	private EOperation getWithExactFeatOp(EClass rootAPICls) {
		var eobjParam = getEObjectParam();
		var featParam = getFeatParam();
		var featValParam = getFeatValParam();

		return FluentAPIGenerationUtil.generateEOperationWithBody(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithExactFeatMethodName(), rootAPICls,
				String.format(xWithExactFeatMethodBody, eobjParam.getName(), featParam.getName(),
						featValParam.getName()),
				eobjParam, featParam, featValParam);
	}

	private EOperation getWithAddedFeatArrayOp(EClass rootAPICls) {
		var eobjParam = getEObjectParam();
		var featParam = getFeatParam();
		var featValParam = getFeatValArrayParam();

		return FluentAPIGenerationUtil.generateEOperationWithBody(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithAddedFeatMethodName(), rootAPICls,
				String.format(xWithAddedFeatMethodBody, eobjParam.getName(), featParam.getName(),
						featValParam.getName()),
				eobjParam, featParam, featValParam);
	}

	private EOperation getWithRemovedFeatArrayOp(EClass rootAPICls) {
		var eobjParam = getEObjectParam();
		var featParam = getFeatParam();
		var featValParam = getFeatValArrayParam();

		return FluentAPIGenerationUtil.generateEOperationWithBody(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithRemovedFeatMethodName(), rootAPICls,
				String.format(xWithRemovedFeatMethodBody, eobjParam.getName(), featParam.getName(),
						featValParam.getName()),
				eobjParam, featParam, featValParam);
	}

	private EOperation getWithExactFeatArrayOp(EClass rootAPICls) {
		var eobjParam = getEObjectParam();
		var featParam = getFeatParam();
		var featValParam = getFeatValArrayParam();

		return FluentAPIGenerationUtil.generateEOperationWithBody(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithExactFeatMethodName(), rootAPICls,
				String.format(xWithExactFeatMethodBody, eobjParam.getName(), featParam.getName(),
						featValParam.getName()),
				eobjParam, featParam, featValParam);
	}

	private EOperation getWithFeatOfContainerOp(EClass rootAPICls) {
		var eobjParam = getEObjectParam();
		var featParam = getFeatParam();

		return FluentAPIGenerationUtil.generateEOperationWithBody(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIXWithFeatOfContainerMethodName(), rootAPICls,
				String.format(xWithFeatOfContainerMethodBody, eobjParam.getName(), featParam.getName()), eobjParam,
				featParam);
	}

	private EParameter getEObjectParam() {
		return FluentAPIGenerationUtil.generateSingleValuedEParameter(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIWithMethodEObjectParameterName(),
				EcorePackage.Literals.EOBJECT);
	}

	private EParameter getFeatParam() {
		return FluentAPIGenerationUtil.generateSingleValuedEParameter(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIWithMethodFeatureParameterName(),
				EcorePackage.Literals.ESTRUCTURAL_FEATURE);
	}

	private EParameter getFeatValParam() {
		return FluentAPIGenerationUtil.generateSingleValuedEParameter(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIWithMethodFeatureValueParameterName(),
				EcorePackage.Literals.EJAVA_OBJECT);
	}

	private EParameter getFeatValArrayParam() {
		return FluentAPIGenerationUtil.generateArrayValuedEParameterWithDocumentation(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIWithMethodFeatureValueParameterName(),
				EcorePackage.Literals.EJAVA_OBJECT, "TODO");
	}
}
