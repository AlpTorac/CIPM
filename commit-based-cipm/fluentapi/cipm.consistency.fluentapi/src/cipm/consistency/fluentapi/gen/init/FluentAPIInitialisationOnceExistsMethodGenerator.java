package cipm.consistency.fluentapi.gen.init;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EcorePackage;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;
import cipm.consistency.fluentapi.gen.methods.mark.FluentAPIOnceExistsExtension;

public class FluentAPIInitialisationOnceExistsMethodGenerator {
	// TODO Add documentation (for generated methods)
	
	private static final String markKeyParameterName = "markKey";
	private static final String markKeyListParameterName = "markKeyList";
	private static final String runnableParameterName = "toDoOnceExists";

	private static final String onceExistsMethodNameTemplate = "onceExists";
	private static final String onceExistsMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			// %s: Mark key parameter name
			// %s: Runnable parameter name (what to do)
			FluentAPIOnceExistsExtension.class.getName()
					+ ".addOnceExists(this.getRootAPI(), %s, (java.lang.Runnable) %s)",
			"return this");

	public List<EOperation> generateAllOnceExistsMethods(EClass initECls) {
		var ops = new ArrayList<EOperation>();
		ops.add(generateOnceExistsMethod(initECls));
		ops.add(generateOnceExistsListMethod(initECls));
		return ops;
	}

	private EOperation generateOnceExistsMethod(EClass initECls) {
		var keyParam = getMarkKeyParam();
		var consumerParam = getRunnableParam();
		return FluentAPIGenerationUtil.generateEOperationWithBody(onceExistsMethodNameTemplate,
				initECls, String.format(onceExistsMethodBodyTemplate, keyParam.getName(), consumerParam.getName()),
				keyParam, consumerParam);
	}

	private EOperation generateOnceExistsListMethod(EClass initECls) {
		var keyParam = getMarkKeyListParam();
		var consumerParam = getRunnableParam();
		return FluentAPIGenerationUtil.generateEOperationWithBody(onceExistsMethodNameTemplate,
				initECls, String.format(onceExistsMethodBodyTemplate, keyParam.getName(), consumerParam.getName()),
				keyParam, consumerParam);
	}

	private EParameter getMarkKeyParam() {
		return FluentAPIGenerationUtil.generateSingleValuedEParameter(markKeyParameterName,
				EcorePackage.Literals.EJAVA_OBJECT);
	}

	private EParameter getMarkKeyListParam() {
		return FluentAPIGenerationUtil.generateManyValuedEParameter(markKeyListParameterName,
				EcorePackage.Literals.EJAVA_OBJECT);
	}

	private EParameter getRunnableParam() {
		return FluentAPIGenerationUtil.generateSingleValuedEParameter(runnableParameterName,
				EcorePackage.Literals.EJAVA_OBJECT);
	}
}
