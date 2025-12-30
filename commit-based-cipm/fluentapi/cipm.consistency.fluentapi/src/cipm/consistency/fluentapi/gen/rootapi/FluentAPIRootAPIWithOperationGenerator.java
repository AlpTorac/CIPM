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

	private static final String genModelURL = "http://www.eclipse.org/emf/2002/GenModel";

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
		ops.add(getWithFeatOfContainerOp(rootAPICls));

		return ops;
	}

	public EOperation getWithFeatOp(EClass rootAPICls) {
		var eobjParam = getEObjectParam();
		var featParam = getFeatParam();
		var featValParam = getFeatValParam();

		return FluentAPIGenerationUtil.generateEOperationWithBody(xWithFeatMethodName, rootAPICls,
				String.format(xWithFeatMethodBody, eobjParam.getName(), featParam.getName(), featValParam.getName()),
				eobjParam, featParam, featValParam);
	}

	public EOperation getWithoutFeatOp(EClass rootAPICls) {
		var eobjParam = getEObjectParam();
		var featParam = getFeatParam();

		return FluentAPIGenerationUtil.generateEOperationWithBody(xWithoutFeatMethodName, rootAPICls,
				String.format(xWithoutFeatMethodBody, eobjParam.getName(), featParam.getName()), eobjParam, featParam);
	}

	public EOperation getWithAddedFeatOp(EClass rootAPICls) {
		var eobjParam = getEObjectParam();
		var featParam = getFeatParam();
		var featValParam = getFeatValParam();

		return FluentAPIGenerationUtil
				.generateEOperationWithBody(
						xWithAddedFeatMethodName, rootAPICls, String.format(xWithAddedFeatMethodBody,
								eobjParam.getName(), featParam.getName(), featValParam.getName()),
						eobjParam, featParam, featValParam);
	}

	public EOperation getWithRemovedFeatOp(EClass rootAPICls) {
		var eobjParam = getEObjectParam();
		var featParam = getFeatParam();
		var featValParam = getFeatValParam();

		return FluentAPIGenerationUtil
				.generateEOperationWithBody(
						xWithRemovedFeatMethodName, rootAPICls, String.format(xWithRemovedFeatMethodBody,
								eobjParam.getName(), featParam.getName(), featValParam.getName()),
						eobjParam, featParam, featValParam);
	}

	public EOperation getWithExactFeatOp(EClass rootAPICls) {
		var eobjParam = getEObjectParam();
		var featParam = getFeatParam();
		var featValParam = getFeatValParam();

		return FluentAPIGenerationUtil
				.generateEOperationWithBody(
						xWithExactFeatMethodName, rootAPICls, String.format(xWithExactFeatMethodBody,
								eobjParam.getName(), featParam.getName(), featValParam.getName()),
						eobjParam, featParam, featValParam);
	}

	public EOperation getWithFeatOfContainerOp(EClass rootAPICls) {
		var eobjParam = getEObjectParam();
		var featParam = getFeatParam();

		return FluentAPIGenerationUtil.generateEOperationWithBody(xWithFeatOfContainerMethodName, rootAPICls,
				String.format(xWithFeatOfContainerMethodBody, eobjParam.getName(), featParam.getName()), eobjParam,
				featParam);
	}

	public EParameter getEObjectParam() {
		return FluentAPIGenerationUtil.generateSingleValuedEParameter(eobjParamName,
				FluentAPIGenerationUtil.getEObjectEClass());
	}

	public EParameter getFeatParam() {
		return FluentAPIGenerationUtil.generateSingleValuedEParameter(featParamName,
				EcorePackage.Literals.ESTRUCTURAL_FEATURE);
	}

	public EParameter getFeatValParam() {
		return FluentAPIGenerationUtil.generateSingleValuedEParameter(featValParamName,
				EcorePackage.Literals.EJAVA_OBJECT);
	}
}
