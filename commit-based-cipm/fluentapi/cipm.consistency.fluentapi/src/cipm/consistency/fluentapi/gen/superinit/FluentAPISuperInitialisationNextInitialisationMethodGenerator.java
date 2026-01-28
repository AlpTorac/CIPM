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

public class FluentAPISuperInitialisationNextInitialisationMethodGenerator implements IFluentAPIMethodGenerator {
	private static final String nextInitMethodSummary = "Returns the Initialisation instance of the same type that this API instance created after this one.";
	private static final String nextInitMethodDocumentation = FluentAPIDocumentationUtil
			.appendSummaryToStart(nextInitMethodSummary) + "Returns the "
			+ FluentAPIInitialisationConstants.getFluentAPIInitialisationClassNameSuffix()
			+ " instance of the same type, which was created by this."
			+ FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationToAPIMethodName()
			+ "() after this one. Can be used to quickly swap between "
			+ FluentAPIInitialisationConstants.getFluentAPIInitialisationClassNameSuffix()
			+ " instances that were created consecutively.";

	private static final String nextInitMethodBodyTemplate = FluentAPIMethodsUtil
			// %s: Initialisation class
			// %s: Initialised EObject class
			.joinLOC("return (%s) " + FluentEObjectAPIMethods.class.getName() + "."
					+ FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationNextInitMethodName()
					+ "(this, this."
					+ FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationToAPIMethodName()
					+ "(), %s.class)");

	private static final String nextInitWithIndexMethodBodyTemplate = FluentAPIMethodsUtil
			// %s: Initialisation class
			// %s: Initialised EObject class
			.joinLOC("return (%s) " + FluentEObjectAPIMethods.class.getName() + "."
					+ FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationNextInitMethodName()
					+ "(this, this."
					+ FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationToAPIMethodName()
					+ "(), %s.class, "
					+ FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationGetInitStepParameterName()
					+ ")");

	public List<EOperation> getAllNextInitialisationMethods(EClass initEClass, EClass eobjEClass) {
		var ops = new ArrayList<EOperation>();
		ops.add(getNextInitialisationMethodFor(initEClass, eobjEClass));
		ops.add(getNextInitialisationMethodWithIndexFor(initEClass, eobjEClass));
		return ops;
	}

	private EOperation getNextInitialisationMethodFor(EClass initEClass, EClass eobjEClass) {
		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationNextInitMethodName(), initEClass);
		FluentAPIGenerationUtil.addBody(op,
				String.format(nextInitMethodBodyTemplate,
						FluentAPIGenerationUtil.getFullyQualifiedEClassName(initEClass),
						eobjEClass.getInstanceClass().getName()));
		FluentAPIGenerationUtil.addDocumentation(op, nextInitMethodDocumentation);
		return op;
	}

	private EOperation getNextInitialisationMethodWithIndexFor(EClass initEClass, EClass eobjEClass) {
		var param = FluentAPIGenerationUtil.generateSingleValuedEParameter(
				FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationGetInitStepParameterName(),
				EcorePackage.Literals.EINT);
		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationNextInitMethodName(), initEClass);
		FluentAPIGenerationUtil.addBody(op,
				String.format(nextInitWithIndexMethodBodyTemplate,
						FluentAPIGenerationUtil.getFullyQualifiedEClassName(initEClass),
						eobjEClass.getInstanceClass().getName()));
		FluentAPIGenerationUtil.addEParameters(op, param);
		FluentAPIGenerationUtil.addDocumentation(op, nextInitMethodDocumentation);
		return op;
	}

	@Override
	public Map<String, String> getMethodNamesToDescriptions() {
		return Map.of(FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationNextInitMethodName(),
				nextInitMethodSummary);
	}
}
