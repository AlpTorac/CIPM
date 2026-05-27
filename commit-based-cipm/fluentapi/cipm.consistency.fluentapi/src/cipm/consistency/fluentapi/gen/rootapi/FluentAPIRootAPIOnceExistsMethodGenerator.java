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
import cipm.consistency.fluentapi.gen.methods.mark.FluentAPIOnceExistsExtension;

public class FluentAPIRootAPIOnceExistsMethodGenerator {
	// TODO Add documentation

	private static final String onceExistsMethodSingleMarkKeyBody = FluentAPIMethodsUtil.joinLOC(
			FluentAPIOnceExistsExtension.class.getName() + ".addOnceExists(this, "
					+ FluentAPIRootAPIConstants.getFluentAPIRootAPIMarkKeyParameterName() + ", (java.lang.Runnable) "
					+ FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsRunnableParameterName() + ")",
			"return this");

	private static final String onceExistsMethodMultipleKeyBody = FluentAPIMethodsUtil.joinLOC(
			FluentAPIOnceExistsExtension.class.getName() + ".addOnceExists(this, "
					+ FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsMarkKeyListParameterName()
					+ ", (java.lang.Runnable) "
					+ FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsRunnableParameterName() + ")",
			"return this");

	public List<EOperation> generateAllOnceExistsMethods(EClass rootAPIECls) {
		var ops = new ArrayList<EOperation>();
		ops.add(generateOnceExistsMethod(rootAPIECls));
		ops.add(generateOnceExistsListMethod(rootAPIECls));
		ops.add(generateOnceExistsArrayMethod(rootAPIECls));
		return ops;
	}

	private EOperation generateOnceExistsMethod(EClass rootAPIECls) {
		var keyParam = getMarkKeyParam();
		var consumerParam = getRunnableParam();
		return FluentAPIGenerationUtil.generateEOperationWithBody(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsMethodName(), rootAPIECls,
				onceExistsMethodSingleMarkKeyBody, keyParam, consumerParam);
	}

	private EOperation generateOnceExistsListMethod(EClass rootAPIECls) {
		var keyParam = getMarkKeyListParam();
		var consumerParam = getRunnableParam();
		return FluentAPIGenerationUtil.generateEOperationWithBody(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsMethodName(), rootAPIECls,
				onceExistsMethodMultipleKeyBody, keyParam, consumerParam);
	}

	private EOperation generateOnceExistsArrayMethod(EClass rootAPIECls) {
		var keyParam = getMarkKeyArrayParam();
		var consumerParam = getRunnableParam();
		return FluentAPIGenerationUtil.generateEOperationWithBody(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsMethodName(), rootAPIECls,
				onceExistsMethodMultipleKeyBody, keyParam, consumerParam);
	}

	private EParameter getMarkKeyParam() {
		return FluentAPIGenerationUtil.generateSingleValuedEParameter(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIMarkKeyParameterName(),
				EcorePackage.Literals.EJAVA_OBJECT);
	}

	private EParameter getMarkKeyListParam() {
		return FluentAPIGenerationUtil.generateManyValuedEParameter(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsMarkKeyListParameterName(),
				EcorePackage.Literals.EJAVA_OBJECT);
	}

	private EParameter getMarkKeyArrayParam() {
		return FluentAPIGenerationUtil.generateArrayValuedEParameterWithDocumentation(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsMarkKeyListParameterName(),
				EcorePackage.Literals.EJAVA_OBJECT, "TODO");
	}

	private EParameter getRunnableParam() {
		return FluentAPIGenerationUtil.generateSingleValuedEParameter(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsRunnableParameterName(), Runnable.class);
	}
}
