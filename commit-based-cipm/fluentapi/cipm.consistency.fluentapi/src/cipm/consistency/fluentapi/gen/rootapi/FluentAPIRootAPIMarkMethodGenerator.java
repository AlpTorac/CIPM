package cipm.consistency.fluentapi.gen.rootapi;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EcorePackage;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
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

	private static final String getMarkedXMethodNameTemplate = "getMarked%s";
	private static final String getMarkedXMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			// %s: Mark key parameter name
			"return (%s) " + FluentAPIMarkExtension.class.getName() + ".getMarked(this, %s)");

	public List<EOperation> generateAllMarkMethods(EClass rootAPIECls, List<EClass> allElemsToInit) {
		var ops = new ArrayList<EOperation>();
		ops.add(generateUnmarkMethod(rootAPIECls));
		ops.add(generateGetMarkedMethod());
		allElemsToInit.forEach((eCls) -> ops.add(generateGetMarkedXMethod(eCls)));
		return ops;
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

	public EOperation generateGetMarkedXMethod(EClass elemToInit) {
		var param = getMarkKeyParam();
		return FluentAPIGenerationUtil.generateEOperationWithBody(
				String.format(getMarkedXMethodNameTemplate, StringUtils.capitalize(elemToInit.getName())), genModelURL,
				elemToInit, String.format(getMarkedXMethodBodyTemplate,
						elemToInit.getInstanceClass().getName(), param.getName()),
				param);
	}

	private EParameter getMarkKeyParam() {
		return FluentAPIGenerationUtil.generateSingleValuedEParameter(markKeyParameterName,
				EcorePackage.Literals.EJAVA_OBJECT);
	}
}
