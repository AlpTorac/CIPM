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

	private static final String eobjParamName = "eobjToModify";
	private static final String featParamName = "featToModify";
	private static final String featValParamName = "featVal";

	private static final String xWithFeatMethodName = "xWithFeat";
	private static final String xWithFeatMethodBody = FluentAPIMethodsUtil.joinLOC(
			// %s: EObject param name
			// %s: Feat param name
			// %s: Feat val param name
			FluentEObjectAPIMethods.class.getName() + ".xWithFeat(this, %s, %s, %s)",
			//
			"return this");

	private static final String xWithoutFeatMethodName = "xWithoutFeat";
	private static final String xWithoutFeatMethodBody = FluentAPIMethodsUtil.joinLOC(
			// %s: EObject param name
			// %s: Feat param name
			FluentEObjectAPIMethods.class.getName() + ".xWithoutFeat(this, %s, %s)",
			//
			"return this");

	private static final String xWithAddedFeatMethodName = "xWithAddedFeat";
	private static final String xWithAddedFeatMethodBody = FluentAPIMethodsUtil.joinLOC(
			// %s: EObject param name
			// %s: Feat param name
			// %s: Feat val param name
			FluentEObjectAPIMethods.class.getName() + ".xWithAddedFeat(this, %s, %s, %s)",
			//
			"return this");

	private static final String xWithRemovedFeatMethodName = "xWithRemovedFeat";
	private static final String xWithRemovedFeatMethodBody = FluentAPIMethodsUtil.joinLOC(
			// %s: EObject param name
			// %s: Feat param name
			// %s: Feat val param name
			FluentEObjectAPIMethods.class.getName() + ".xWithRemovedFeat(this, %s, %s, %s)",
			//
			"return this");
	private static final String xWithExactFeatMethodName = "xWithExactFeat";
	private static final String xWithExactFeatMethodBody = FluentAPIMethodsUtil.joinLOC(
			// %s: EObject param name
			// %s: Feat param name
			// %s: Feat val param name
			FluentEObjectAPIMethods.class.getName() + ".xWithExactFeat(this, %s, %s, %s)",
			//
			"return this");

	private static final String xWithFeatOfContainerMethodName = "xWithFeatOfContainer";
	private static final String xWithFeatOfContainerMethodBody = FluentAPIMethodsUtil.joinLOC(
			// %s: EObject param name
			// %s: Feat param name
			FluentEObjectAPIMethods.class.getName() + ".xWithFeatOfContainer(this, %s, %s)",
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

		return FluentAPIGenerationUtil.generateEOperationWithBody(xWithFeatMethodName, rootAPICls,
				String.format(xWithFeatMethodBody, eobjParam.getName(), featParam.getName(), featValParam.getName()),
				eobjParam, featParam, featValParam);
	}

	private EOperation getWithoutFeatOp(EClass rootAPICls) {
		var eobjParam = getEObjectParam();
		var featParam = getFeatParam();

		return FluentAPIGenerationUtil.generateEOperationWithBody(xWithoutFeatMethodName, rootAPICls,
				String.format(xWithoutFeatMethodBody, eobjParam.getName(), featParam.getName()), eobjParam, featParam);
	}

	private EOperation getWithAddedFeatOp(EClass rootAPICls) {
		var eobjParam = getEObjectParam();
		var featParam = getFeatParam();
		var featValParam = getFeatValParam();

		return FluentAPIGenerationUtil
				.generateEOperationWithBody(
						xWithAddedFeatMethodName, rootAPICls, String.format(xWithAddedFeatMethodBody,
								eobjParam.getName(), featParam.getName(), featValParam.getName()),
						eobjParam, featParam, featValParam);
	}

	private EOperation getWithRemovedFeatOp(EClass rootAPICls) {
		var eobjParam = getEObjectParam();
		var featParam = getFeatParam();
		var featValParam = getFeatValParam();

		return FluentAPIGenerationUtil
				.generateEOperationWithBody(
						xWithRemovedFeatMethodName, rootAPICls, String.format(xWithRemovedFeatMethodBody,
								eobjParam.getName(), featParam.getName(), featValParam.getName()),
						eobjParam, featParam, featValParam);
	}

	private EOperation getWithExactFeatOp(EClass rootAPICls) {
		var eobjParam = getEObjectParam();
		var featParam = getFeatParam();
		var featValParam = getFeatValParam();

		return FluentAPIGenerationUtil
				.generateEOperationWithBody(
						xWithExactFeatMethodName, rootAPICls, String.format(xWithExactFeatMethodBody,
								eobjParam.getName(), featParam.getName(), featValParam.getName()),
						eobjParam, featParam, featValParam);
	}

	private EOperation getWithAddedFeatArrayOp(EClass rootAPICls) {
		var eobjParam = getEObjectParam();
		var featParam = getFeatParam();
		var featValParam = getFeatValArrayParam();

		return FluentAPIGenerationUtil
				.generateEOperationWithBody(
						xWithAddedFeatMethodName, rootAPICls, String.format(xWithAddedFeatMethodBody,
								eobjParam.getName(), featParam.getName(), featValParam.getName()),
						eobjParam, featParam, featValParam);
	}

	private EOperation getWithRemovedFeatArrayOp(EClass rootAPICls) {
		var eobjParam = getEObjectParam();
		var featParam = getFeatParam();
		var featValParam = getFeatValArrayParam();

		return FluentAPIGenerationUtil
				.generateEOperationWithBody(
						xWithRemovedFeatMethodName, rootAPICls, String.format(xWithRemovedFeatMethodBody,
								eobjParam.getName(), featParam.getName(), featValParam.getName()),
						eobjParam, featParam, featValParam);
	}

	private EOperation getWithExactFeatArrayOp(EClass rootAPICls) {
		var eobjParam = getEObjectParam();
		var featParam = getFeatParam();
		var featValParam = getFeatValArrayParam();

		return FluentAPIGenerationUtil
				.generateEOperationWithBody(
						xWithExactFeatMethodName, rootAPICls, String.format(xWithExactFeatMethodBody,
								eobjParam.getName(), featParam.getName(), featValParam.getName()),
						eobjParam, featParam, featValParam);
	}

	private EOperation getWithFeatOfContainerOp(EClass rootAPICls) {
		var eobjParam = getEObjectParam();
		var featParam = getFeatParam();

		return FluentAPIGenerationUtil.generateEOperationWithBody(xWithFeatOfContainerMethodName, rootAPICls,
				String.format(xWithFeatOfContainerMethodBody, eobjParam.getName(), featParam.getName()), eobjParam,
				featParam);
	}

	private EParameter getEObjectParam() {
		return FluentAPIGenerationUtil.generateSingleValuedEParameter(eobjParamName, EcorePackage.Literals.EOBJECT);
	}

	private EParameter getFeatParam() {
		return FluentAPIGenerationUtil.generateSingleValuedEParameter(featParamName,
				EcorePackage.Literals.ESTRUCTURAL_FEATURE);
	}

	private EParameter getFeatValParam() {
		return FluentAPIGenerationUtil.generateSingleValuedEParameter(featValParamName,
				EcorePackage.Literals.EJAVA_OBJECT);
	}

	private EParameter getFeatValArrayParam() {
		return FluentAPIGenerationUtil.generateArrayValuedEParameterWithDocumentation(featValParamName,
				EcorePackage.Literals.EJAVA_OBJECT, "TODO");
	}
}
