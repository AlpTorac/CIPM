package cipm.consistency.fluentapi.gen;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EcorePackage;

import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;
import cipm.consistency.fluentapi.gen.methods.mark.FluentAPIMarkExtension;

public class FluentAPIRootAPIMarkMethodGenerator {
	private static final String genModelURL = "http://www.eclipse.org/emf/2002/GenModel";

	private static final String markKeyParameterName = "markKey";

	private static final String unmarkMethodNameTemplate = "unmark";
	private static final String unmarkMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			// %s: Mark key parameter name
			FluentAPIMarkExtension.class.getName() + ".unmark(this, %s)", "return this");

	private static final String getMarkedMethodNameTemplate = "getMarked";
	private static final String getMarkedMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			// %s: Mark key parameter name
			"return " + FluentAPIMarkExtension.class.getName() + ".getMarked(this, %s)");

	public List<EOperation> generateAllMarkMethods(EClass rootAPIECls) {
		return List.of(generateUnmarkMethod(rootAPIECls), generateGetMarkedMethod());
	}

	public EOperation generateUnmarkMethod(EClass rootAPIECls) {
		var param = getMarkKeyParam();
		return FluentAPIGenerationUtil.generateEOperationWithBody(unmarkMethodNameTemplate, genModelURL, rootAPIECls,
				String.format(unmarkMethodBodyTemplate, param.getName()), param);
	}

	public EOperation generateGetMarkedMethod() {
		var param = getMarkKeyParam();
		return FluentAPIGenerationUtil.generateEOperationWithBody(getMarkedMethodNameTemplate, genModelURL,
				FluentAPIGenerationUtil.getEObjectEClass(), String.format(getMarkedMethodBodyTemplate, param.getName()),
				param);
	}

	private EParameter getMarkKeyParam() {
		return FluentAPIGenerationUtil.generateSingleValuedEParameter(markKeyParameterName,
				EcorePackage.Literals.EJAVA_OBJECT);
	}
}
