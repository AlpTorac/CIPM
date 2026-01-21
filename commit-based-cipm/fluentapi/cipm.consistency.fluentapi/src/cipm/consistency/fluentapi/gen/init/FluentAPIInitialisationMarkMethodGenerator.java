package cipm.consistency.fluentapi.gen.init;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EcorePackage;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;
import cipm.consistency.fluentapi.gen.methods.mark.FluentAPIMarkExtension;

public class FluentAPIInitialisationMarkMethodGenerator {
	private static final String markKeyParameterName = "markKey";
	private static final String markKeyDocumentation = "The Object instance, whose memory address will serve as a key in mark-related operations. Note that the content of the given Object instance are fully irrelevant here, only its memory address matters.";

	private static final String unmarkMethodNameTemplate = "unmark";
	private static final String unmarkMethodDocumentation = "Removes any associations between the given markKey and its corresponding EObject obj. Doing so unmarks obj, meaning that markKey can no longer be used to retrieve obj.";
	private static final String unmarkMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			// %s: Mark key parameter name
			FluentAPIMarkExtension.class.getName() + ".unmark(this.getRootAPI(), %s)", "return this");

	private static final String markMethodNameTemplate = "markCurrent";
	private static final String markMethodDocumentation = "Associates this.getCurrentElement() with markKey. Doing so marks this.getCurrentElement(), meaning that using markKey in mark-related operations will result in retrieving this.getCurrentElement().";
	private static final String markMethodBodyTemplate = FluentAPIMethodsUtil
			// %s: Mark key parameter name
			.joinLOC(FluentAPIMarkExtension.class.getName() + ".mark(this.getRootAPI(), %s, this.getCurrentElement())",
					"return this");

	private static final String getMarkedMethodNameTemplate = "getMarked";
	private static final String getMarkedMethodDocumentation = "Returns the EObject obj associated with markKey. Note that markKey has to be the exact Object instance that was used to mark obj, in order for this method to successfully retrieve obj. Using another Object instance that is content-wise equal to markKey will not work, as only the memory address of markKey is relevant.";
	private static final String getMarkedMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			// %s: Mark key parameter name
			"return " + FluentAPIMarkExtension.class.getName() + ".getMarked(this.getRootAPI(), %s)");

	public List<EOperation> generateAllMarkMethods(EClass initEClass) {
		return List.of(generateUnmarkMethod(initEClass), generateMarkMethod(initEClass), generateGetMarkedMethod());
	}

	public EOperation generateUnmarkMethod(EClass initEClass) {
		var param = getMarkKeyParam();
		return FluentAPIGenerationUtil.generateEOperationWithBodyAndDocumentation(unmarkMethodNameTemplate, initEClass,
				String.format(unmarkMethodBodyTemplate, param.getName()), unmarkMethodDocumentation, param);
	}

	public EOperation generateMarkMethod(EClass initEClass) {
		var param = getMarkKeyParam();
		return FluentAPIGenerationUtil.generateEOperationWithBodyAndDocumentation(markMethodNameTemplate, initEClass,
				String.format(markMethodBodyTemplate, param.getName()), markMethodDocumentation, param);
	}

	public EOperation generateGetMarkedMethod() {
		var param = getMarkKeyParam();
		return FluentAPIGenerationUtil.generateEOperationWithBodyAndDocumentation(getMarkedMethodNameTemplate,
				EcorePackage.Literals.EOBJECT, String.format(getMarkedMethodBodyTemplate, param.getName()),
				getMarkedMethodDocumentation, param);
	}

	private EParameter getMarkKeyParam() {
		return FluentAPIGenerationUtil.generateSingleValuedEParameterWithDocumentation(markKeyParameterName,
				EcorePackage.Literals.EJAVA_OBJECT, markKeyDocumentation);
	}
}
