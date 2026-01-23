package cipm.consistency.fluentapi.gen.superinit;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EcorePackage;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;
import cipm.consistency.fluentapi.gen.methods.mark.FluentAPIMarkExtension;
import cipm.consistency.fluentapi.gen.rootapi.FluentAPIRootAPIConstants;

public class FluentAPISuperInitialisationMarkMethodGenerator {
	private static final String unmarkMethodDocumentation = "Removes any associations between the given "
			+ FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationMarkKeyParameterName()
			+ " and its corresponding EObject obj. Doing so unmarks obj, meaning that "
			+ FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationMarkKeyParameterName()
			+ " can no longer be used to retrieve obj.";
	private static final String unmarkMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			// %s: Mark key parameter name
			FluentAPIMarkExtension.class.getName() + ".unmark(this.get"
					+ FluentAPISuperInitialisationConstants
							.getCapitalisedFluentAPISuperInitialisationRootAPIReferenceName()
					+ "(), %s)",
			"return this");

	private static final String markMethodDocumentation = "Associates this.get"
			+ FluentAPISuperInitialisationConstants
					.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName()
			+ "() with " + FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationMarkKeyParameterName()
			+ ". Doing so marks this.get"
			+ FluentAPISuperInitialisationConstants
					.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName()
			+ "(), meaning that using "
			+ FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationMarkKeyParameterName()
			+ " in mark-related operations will result in retrieving this.get" + FluentAPISuperInitialisationConstants
					.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName()
			+ "().";
	private static final String markMethodBodyTemplate = FluentAPIMethodsUtil
			// %s: Mark key parameter name
			.joinLOC(
					FluentAPIMarkExtension.class.getName() + ".mark(this.get"
							+ FluentAPISuperInitialisationConstants
									.getCapitalisedFluentAPISuperInitialisationRootAPIReferenceName()
							+ "(), %s, this.get"
							+ FluentAPISuperInitialisationConstants
									.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName()
							+ "())",
					"return this");

	private static final String getMarkedMethodDocumentation = "Returns the EObject obj associated with "
			+ FluentAPIRootAPIConstants.getFluentAPIRootAPIMarkKeyParameterName() + ". Note that "
			+ FluentAPIRootAPIConstants.getFluentAPIRootAPIMarkKeyParameterName()
			+ " has to be the exact Object instance that was used to mark obj, in order for this method to successfully retrieve obj. Using another Object instance that is content-wise equal to "
			+ FluentAPIRootAPIConstants.getFluentAPIRootAPIMarkKeyParameterName()
			+ " will not work, as only the memory address of "
			+ FluentAPIRootAPIConstants.getFluentAPIRootAPIMarkKeyParameterName() + " is relevant.";
	private static final String getMarkedMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			// %s: Mark key parameter name
			"return " + FluentAPIMarkExtension.class.getName() + ".getMarked(this.get"
					+ FluentAPISuperInitialisationConstants
							.getCapitalisedFluentAPISuperInitialisationRootAPIReferenceName()
					+ "(), %s)");

	public List<EOperation> generateAllMarkMethods(EClass initEClass) {
		return List.of(generateUnmarkMethod(initEClass), generateMarkMethod(initEClass), generateGetMarkedMethod());
	}

	public EOperation generateUnmarkMethod(EClass initEClass) {
		var param = getMarkKeyParam();
		return FluentAPIGenerationUtil.generateEOperationWithBodyAndDocumentation(
				FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationUnmarkMethodName(), initEClass,
				String.format(unmarkMethodBodyTemplate, param.getName()), unmarkMethodDocumentation, param);
	}

	public EOperation generateMarkMethod(EClass initEClass) {
		var param = getMarkKeyParam();
		return FluentAPIGenerationUtil.generateEOperationWithBodyAndDocumentation(
				FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationMarkMethodName(), initEClass,
				String.format(markMethodBodyTemplate, param.getName()), markMethodDocumentation, param);
	}

	public EOperation generateGetMarkedMethod() {
		var param = getMarkKeyParam();
		return FluentAPIGenerationUtil.generateEOperationWithBodyAndDocumentation(
				FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationGetMarkedMethodName(),
				EcorePackage.Literals.EOBJECT, String.format(getMarkedMethodBodyTemplate, param.getName()),
				getMarkedMethodDocumentation, param);
	}

	private EParameter getMarkKeyParam() {
		return FluentAPIGenerationUtil.generateSingleValuedEParameterWithDocumentation(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIMarkKeyParameterName(), EcorePackage.Literals.EJAVA_OBJECT,
				FluentAPIRootAPIConstants.getFluentAPIRootAPIMarkKeyDocumentation());
	}
}
