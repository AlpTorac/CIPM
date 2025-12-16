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
	private static final String consumerParameterName = "toDoOnceExists";

	private static final String onceExistsMethodNameTemplate = "onceExists";
	private static final String onceExistsMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			// %s: Mark key parameter name
			// %s: Consumer parameter name (what to do)
			FluentAPIOnceExistsExtension.class.getName() + ".addOnceExists(this, %s, (java.util.function.Consumer) %s)",
			"return this");

	public EOperation generateOnceExistsMethod(EClass rootAPIECls) {
		var keyParam = getMarkKeyParam();
		var consumerParam = getConsumerParam();
		return FluentAPIGenerationUtil.generateEOperationWithBody(onceExistsMethodNameTemplate, genModelURL,
				rootAPIECls, String.format(onceExistsMethodBodyTemplate, keyParam.getName(), consumerParam.getName()),
				keyParam, consumerParam);
	}

	private EParameter getMarkKeyParam() {
		return FluentAPIGenerationUtil.generateSingleValuedEParameter(markKeyParameterName,
				EcorePackage.Literals.EJAVA_OBJECT);
	}

	private EParameter getConsumerParam() {
		return FluentAPIGenerationUtil.generateSingleValuedEParameter(consumerParameterName,
				EcorePackage.Literals.EJAVA_OBJECT);
	}
}
