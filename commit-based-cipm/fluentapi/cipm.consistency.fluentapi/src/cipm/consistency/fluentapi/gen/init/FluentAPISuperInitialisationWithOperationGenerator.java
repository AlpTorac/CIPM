package cipm.consistency.fluentapi.gen.init;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EcorePackage;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;
import cipm.consistency.fluentapi.gen.methods.FluentEObjectAPIMethods;

public class FluentAPISuperInitialisationWithOperationGenerator {
	// TODO Add documentation

	private static final String featParamName = "featToModify";
	private static final String featValParamName = "featVal";

	private static final String xWithFeatMethodName = "xWithFeat";
	private static final String xWithFeatMethodBody = FluentAPIMethodsUtil.joinLOC(
			// %s: Feat param name
			// %s: Feat val param name
			FluentEObjectAPIMethods.class.getName() + ".xWithFeat(this.toAPI(), this.getCurrentElement(), %s, %s)",
			//
			"return this");

	private static final String xWithoutFeatMethodName = "xWithoutFeat";
	private static final String xWithoutFeatMethodBody = FluentAPIMethodsUtil.joinLOC(
			// %s: Feat param name
			FluentEObjectAPIMethods.class.getName() + ".xWithoutFeat(this.toAPI(), this.getCurrentElement(), %s)",
			//
			"return this");

	private static final String xWithAddedFeatMethodName = "xWithAddedFeat";
	private static final String xWithAddedFeatMethodBody = FluentAPIMethodsUtil.joinLOC(
			// %s: Feat param name
			// %s: Feat val param name
			FluentEObjectAPIMethods.class.getName() + ".xWithAddedFeat(this.toAPI(), this.getCurrentElement(), %s, %s)",
			//
			"return this");

	private static final String xWithRemovedFeatMethodName = "xWithRemovedFeat";
	private static final String xWithRemovedFeatMethodBody = FluentAPIMethodsUtil.joinLOC(
			// %s: Feat param name
			// %s: Feat val param name
			FluentEObjectAPIMethods.class.getName()
					+ ".xWithRemovedFeat(this.toAPI(), this.getCurrentElement(), %s, %s)",
			//
			"return this");
	private static final String xWithExactFeatMethodName = "xWithExactFeat";
	private static final String xWithExactFeatMethodBody = FluentAPIMethodsUtil.joinLOC(
			// %s: Feat param name
			// %s: Feat val param name
			FluentEObjectAPIMethods.class.getName() + ".xWithExactFeat(this.toAPI(), this.getCurrentElement(), %s, %s)",
			//
			"return this");

	private static final String xWithFeatOfContainerMethodName = "xWithFeatOfContainer";
	private static final String xWithFeatOfContainerMethodBody = FluentAPIMethodsUtil.joinLOC(
			// %s: Feat param name
			FluentEObjectAPIMethods.class.getName()
					+ ".xWithFeatOfContainer(this.toAPI(), this.getCurrentElement(), %s)",
			//
			"return this");

	public List<EOperation> getAllAPITopLevelWithOperations(EClass superInitCls) {
		var ops = new ArrayList<EOperation>();

		ops.add(getWithFeatOp(superInitCls));
		ops.add(getWithoutFeatOp(superInitCls));
		ops.add(getWithAddedFeatOp(superInitCls));
		ops.add(getWithRemovedFeatOp(superInitCls));
		ops.add(getWithExactFeatOp(superInitCls));
		ops.add(getWithAddedFeatArrayOp(superInitCls));
		ops.add(getWithRemovedFeatArrayOp(superInitCls));
		ops.add(getWithExactFeatArrayOp(superInitCls));
		ops.add(getWithFeatOfContainerOp(superInitCls));

		return ops;
	}

	private EOperation getWithFeatOp(EClass superInitCls) {
		var featParam = getFeatParam();
		var featValParam = getFeatValParam();

		return FluentAPIGenerationUtil.generateEOperationWithBody(xWithFeatMethodName, superInitCls,
				String.format(xWithFeatMethodBody, featParam.getName(), featValParam.getName()), featParam,
				featValParam);
	}

	private EOperation getWithoutFeatOp(EClass superInitCls) {
		var featParam = getFeatParam();

		return FluentAPIGenerationUtil.generateEOperationWithBody(xWithoutFeatMethodName, superInitCls,
				String.format(xWithoutFeatMethodBody, featParam.getName()), featParam);
	}

	private EOperation getWithAddedFeatOp(EClass superInitCls) {
		var featParam = getFeatParam();
		var featValParam = getFeatValParam();

		return FluentAPIGenerationUtil.generateEOperationWithBody(xWithAddedFeatMethodName, superInitCls,
				String.format(xWithAddedFeatMethodBody, featParam.getName(), featValParam.getName()), featParam,
				featValParam);
	}

	private EOperation getWithRemovedFeatOp(EClass superInitCls) {
		var featParam = getFeatParam();
		var featValParam = getFeatValParam();

		return FluentAPIGenerationUtil.generateEOperationWithBody(xWithRemovedFeatMethodName, superInitCls,
				String.format(xWithRemovedFeatMethodBody, featParam.getName(), featValParam.getName()), featParam,
				featValParam);
	}

	private EOperation getWithExactFeatOp(EClass superInitCls) {
		var featParam = getFeatParam();
		var featValParam = getFeatValParam();

		return FluentAPIGenerationUtil.generateEOperationWithBody(xWithExactFeatMethodName, superInitCls,
				String.format(xWithExactFeatMethodBody, featParam.getName(), featValParam.getName()), featParam,
				featValParam);
	}

	private EOperation getWithAddedFeatArrayOp(EClass superInitCls) {
		var featParam = getFeatParam();
		var featValParam = getFeatValArrayParam();

		return FluentAPIGenerationUtil.generateEOperationWithBody(xWithAddedFeatMethodName, superInitCls,
				String.format(xWithAddedFeatMethodBody, featParam.getName(), featValParam.getName()), featParam,
				featValParam);
	}

	private EOperation getWithRemovedFeatArrayOp(EClass superInitCls) {
		var featParam = getFeatParam();
		var featValParam = getFeatValArrayParam();

		return FluentAPIGenerationUtil.generateEOperationWithBody(xWithRemovedFeatMethodName, superInitCls,
				String.format(xWithRemovedFeatMethodBody, featParam.getName(), featValParam.getName()), featParam,
				featValParam);
	}

	private EOperation getWithExactFeatArrayOp(EClass superInitCls) {
		var featParam = getFeatParam();
		var featValParam = getFeatValArrayParam();

		return FluentAPIGenerationUtil.generateEOperationWithBody(xWithExactFeatMethodName, superInitCls,
				String.format(xWithExactFeatMethodBody, featParam.getName(), featValParam.getName()), featParam,
				featValParam);
	}

	private EOperation getWithFeatOfContainerOp(EClass superInitCls) {
		var featParam = getFeatParam();

		return FluentAPIGenerationUtil.generateEOperationWithBody(xWithFeatOfContainerMethodName, superInitCls,
				String.format(xWithFeatOfContainerMethodBody, featParam.getName()), featParam);
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
