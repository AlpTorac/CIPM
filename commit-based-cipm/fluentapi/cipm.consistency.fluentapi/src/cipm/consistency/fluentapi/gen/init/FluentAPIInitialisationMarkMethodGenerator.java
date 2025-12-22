package cipm.consistency.fluentapi.gen.init;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EcorePackage;

import cipm.consistency.fluentapi.gen.FluentAPIConstants;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;
import cipm.consistency.fluentapi.gen.methods.mark.FluentAPIMarkExtension;

public class FluentAPIInitialisationMarkMethodGenerator {
	private static final String markKeyParameterName = "markKey";

	private static final String unmarkMethodNameTemplate = "unmark";
	private static final String unmarkMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			// %s: Mark key parameter name
			FluentAPIMarkExtension.class.getName() + ".unmark(this.getRootAPI(), %s)", "return this");

	private static final String markMethodNameTemplate = "markCurrent";
	private static final String markMethodBodyTemplate = FluentAPIMethodsUtil
			// %s: Mark key parameter name
			.joinLOC(FluentAPIMarkExtension.class.getName() + ".mark(this.getRootAPI(), %s, this.getCurrentElement())",
					"return this");

	private static final String getMarkedMethodNameTemplate = "getMarked";
	private static final String getMarkedMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			// %s: Mark key parameter name
			"return " + FluentAPIMarkExtension.class.getName() + ".getMarked(this.getRootAPI(), %s)");

	public List<EOperation> generateAllMarkMethods(EClass initEClass) {
		return List.of(generateUnmarkMethod(initEClass), generateMarkMethod(initEClass), generateGetMarkedMethod());
	}

	public EOperation generateUnmarkMethod(EClass initEClass) {
		var param = getMarkKeyParam();
		return FluentAPIGenerationUtil.generateEOperationWithBody(unmarkMethodNameTemplate,
				FluentAPIConstants.getGenModelURL(), initEClass,
				String.format(unmarkMethodBodyTemplate, param.getName()), param);
	}

	public EOperation generateMarkMethod(EClass initEClass) {
		var param = getMarkKeyParam();
		return FluentAPIGenerationUtil.generateEOperationWithBody(markMethodNameTemplate,
				FluentAPIConstants.getGenModelURL(), initEClass, String.format(markMethodBodyTemplate, param.getName()),
				param);
	}

	public EOperation generateGetMarkedMethod() {
		var param = getMarkKeyParam();
		return FluentAPIGenerationUtil.generateEOperationWithBody(getMarkedMethodNameTemplate,
				FluentAPIConstants.getGenModelURL(), FluentAPIGenerationUtil.getEObjectEClass(),
				String.format(getMarkedMethodBodyTemplate, param.getName()), param);
	}

	private EParameter getMarkKeyParam() {
		return FluentAPIGenerationUtil.generateSingleValuedEParameter(markKeyParameterName,
				EcorePackage.Literals.EJAVA_OBJECT);
	}
}
