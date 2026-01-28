package cipm.consistency.fluentapi.gen.superinit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EcorePackage;

import cipm.consistency.fluentapi.gen.FluentAPIDocumentationUtil;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.IFluentAPIMethodGenerator;
import cipm.consistency.fluentapi.gen.init.FluentAPIInitialisationConstants;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;
import cipm.consistency.fluentapi.gen.methods.FluentEObjectAPIMethods;

public class FluentAPISuperInitialisationPreviousInitialisationMethodGenerator implements IFluentAPIMethodGenerator {
	private static final String previousInitMethodSummary = "Returns the Initialisation instance of the same type that this API instance created before this one.";
	private static final String previousInitMethodDocumentation = FluentAPIDocumentationUtil
			.appendSummaryToStart(previousInitMethodSummary) + "Returns the "
			+ FluentAPIInitialisationConstants.getFluentAPIInitialisationClassNameSuffix()
			+ " instance of the same type, which was created by this."
			+ FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationToAPIMethodName()
			+ "() before this one. Can be used to quickly swap between "
			+ FluentAPIInitialisationConstants.getFluentAPIInitialisationClassNameSuffix()
			+ " instances that were created consecutively.";
	private static final String previousInitMethodBodyTemplate = FluentAPIMethodsUtil
			// %s: Initialisation class
			// %s: Initialised EObject class
			.joinLOC(
					"return (%s) " + FluentEObjectAPIMethods.class.getName() + "."
							+ FluentAPISuperInitialisationConstants
									.getFluentAPISuperInitialisationPreviousInitMethodName()
							+ "(this, this."
							+ FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationToAPIMethodName()
							+ "(), %s.class)");

	private static final String previousInitWithIndexMethodBodyTemplate = FluentAPIMethodsUtil
			// %s: Initialisation class
			// %s: Initialised EObject class
			.joinLOC("return (%s) " + FluentEObjectAPIMethods.class.getName() + "."
					+ FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationPreviousInitMethodName()
					+ "(this, this."
					+ FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationToAPIMethodName()
					+ "(), %s.class, "
					+ FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationGetInitStepParameterName()
					+ ")");

	public List<EOperation> getAllPreviousInitialisationMethods(EClass initEClass, EClass eobjEClass) {
		var ops = new ArrayList<EOperation>();
		ops.add(getPreviousInitialisationMethodFor(initEClass, eobjEClass));
		ops.add(getPreviousInitialisationMethodWithIndexFor(initEClass, eobjEClass));
		return ops;
	}
	
	private EOperation getPreviousInitialisationMethodFor(EClass initEClass, EClass eobjEClass) {
		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationPreviousInitMethodName(),
				initEClass);
		FluentAPIGenerationUtil.addBody(op,
				String.format(previousInitMethodBodyTemplate,
						FluentAPIGenerationUtil.getFullyQualifiedEClassName(initEClass),
						eobjEClass.getInstanceClass().getName()));
		FluentAPIGenerationUtil.addDocumentation(op, previousInitMethodDocumentation);
		return op;
	}

	private EOperation getPreviousInitialisationMethodWithIndexFor(EClass initEClass, EClass eobjEClass) {
		var param = FluentAPIGenerationUtil.generateSingleValuedEParameter(
				FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationGetInitStepParameterName(),
				EcorePackage.Literals.EINT);
		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationPreviousInitMethodName(),
				initEClass);
		FluentAPIGenerationUtil.addBody(op,
				String.format(previousInitWithIndexMethodBodyTemplate,
						FluentAPIGenerationUtil.getFullyQualifiedEClassName(initEClass),
						eobjEClass.getInstanceClass().getName()));
		FluentAPIGenerationUtil.addEParameters(op, param);
		FluentAPIGenerationUtil.addDocumentation(op, previousInitMethodDocumentation);
		return op;
	}

	@Override
	public Map<String, String> getMethodNamesToDescriptions() {
		return Map.of(FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationPreviousInitMethodName(),
				previousInitMethodSummary);
	}
}
