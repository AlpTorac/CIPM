package cipm.consistency.fluentapi.gen;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EcorePackage;

import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;
import cipm.consistency.fluentapi.gen.methods.mark.FluentAPIOnceExistsExtension;

public class FluentAPIRootAPIOnceExistsMethodGenerator {
	private static final String genModelURL = "http://www.eclipse.org/emf/2002/GenModel";

	private static final String markKeyParameterName = "markKey";
	private static final String runnableParameterName = "toDoOnceExists";

	private static final String onceExistsMethodNameTemplate = "onceExists";
	private static final String onceExistsMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			// %s: Mark key parameter name
			// %s: Runnable parameter name (what to do)
			FluentAPIOnceExistsExtension.class.getName() + ".addOnceExists(this, %s, (java.lang.Runnable) %s)",
			"return this");

	public EOperation generateOnceExistsMethod(EClass rootAPIECls) {
		var keyParam = getMarkKeyParam();
		var consumerParam = getRunnableParam();
		return FluentAPIGenerationUtil.generateEOperationWithBody(onceExistsMethodNameTemplate, genModelURL,
				rootAPIECls, String.format(onceExistsMethodBodyTemplate, keyParam.getName(), consumerParam.getName()),
				keyParam, consumerParam);
	}

	private EParameter getMarkKeyParam() {
		return FluentAPIGenerationUtil.generateSingleValuedEParameter(markKeyParameterName,
				EcorePackage.Literals.EJAVA_OBJECT);
	}

	private EParameter getRunnableParam() {
		return FluentAPIGenerationUtil.generateSingleValuedEParameter(runnableParameterName,
				EcorePackage.Literals.EJAVA_OBJECT);
	}
}
