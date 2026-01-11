package cipm.consistency.fluentapi.gen.rootapi;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EcorePackage;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;
import cipm.consistency.fluentapi.gen.methods.mark.FluentAPIOnceExistsExtension;

public class FluentAPIRootAPIOnceExistsMethodGenerator {
	// TODO Add documentation

	private static final String markKeyParameterName = "markKey";
	private static final String markKeyListParameterName = "markKeyList";
	private static final String runnableParameterName = "toDoOnceExists";

	private static final String onceExistsMethodNameTemplate = "onceExists";
	private static final String onceExistsMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			// %s: Mark key parameter name
			// %s: Runnable parameter name (what to do)
			FluentAPIOnceExistsExtension.class.getName() + ".addOnceExists(this, %s, (java.lang.Runnable) %s)",
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
		return FluentAPIGenerationUtil.generateEOperationWithBody(onceExistsMethodNameTemplate, rootAPIECls,
				String.format(onceExistsMethodBodyTemplate, keyParam.getName(), consumerParam.getName()), keyParam,
				consumerParam);
	}

	private EOperation generateOnceExistsListMethod(EClass rootAPIECls) {
		var keyParam = getMarkKeyListParam();
		var consumerParam = getRunnableParam();
		return FluentAPIGenerationUtil.generateEOperationWithBody(onceExistsMethodNameTemplate, rootAPIECls,
				String.format(onceExistsMethodBodyTemplate, keyParam.getName(), consumerParam.getName()), keyParam,
				consumerParam);
	}

	private EOperation generateOnceExistsArrayMethod(EClass rootAPIECls) {
		var keyParam = getMarkKeyArrayParam();
		var consumerParam = getRunnableParam();
		return FluentAPIGenerationUtil.generateEOperationWithBody(onceExistsMethodNameTemplate, rootAPIECls,
				String.format(onceExistsMethodBodyTemplate, keyParam.getName(), consumerParam.getName()), keyParam,
				consumerParam);
	}

	private EParameter getMarkKeyParam() {
		return FluentAPIGenerationUtil.generateSingleValuedEParameter(markKeyParameterName,
				EcorePackage.Literals.EJAVA_OBJECT);
	}

	private EParameter getMarkKeyListParam() {
		return FluentAPIGenerationUtil.generateManyValuedEParameter(markKeyListParameterName,
				EcorePackage.Literals.EJAVA_OBJECT);
	}

	private EParameter getMarkKeyArrayParam() {
		return FluentAPIGenerationUtil.generateArrayValuedEParameterWithDocumentation(markKeyListParameterName,
				EcorePackage.Literals.EJAVA_OBJECT, "TODO");
	}

	private EParameter getRunnableParam() {
		return FluentAPIGenerationUtil.generateSingleValuedEParameter(runnableParameterName, Runnable.class);
	}
}
