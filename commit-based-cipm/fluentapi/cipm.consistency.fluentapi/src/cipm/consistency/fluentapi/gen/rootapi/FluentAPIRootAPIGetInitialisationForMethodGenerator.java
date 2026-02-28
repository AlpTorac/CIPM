package cipm.consistency.fluentapi.gen.rootapi;

import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EcorePackage;

import cipm.consistency.fluentapi.gen.FluentAPIGeneralParameterGenerator;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationContext;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.ModelConstants;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;
import cipm.consistency.fluentapi.gen.methods.FluentEObjectAPIMethods;

public class FluentAPIRootAPIGetInitialisationForMethodGenerator {
	// TODO Add documentation

	private static final String getInitialisationMethodBodyTemplate = FluentAPIMethodsUtil
			// %s: Initialisation class
			// %s: EClass / class / EObject parameter name
			.joinLOC("return (%s)" + FluentEObjectAPIMethods.class.getName()
					+ ModelConstants.RootAPI.GetInitialisationFor.NAME.call("this", "%s"));

	public EOperation getInitialisationForEClassMethod(FluentAPIGenerationContext context) {
		var param = getInitialisationForEClassParam();

		var op = FluentAPIGenerationUtil.generateEOperation(ModelConstants.RootAPI.GetInitialisationFor.NAME.get(),
				context.getInitSuperECls());

		FluentAPIGenerationUtil.addBody(op, String.format(getInitialisationMethodBodyTemplate,
				FluentAPIGenerationUtil.getFullyQualifiedEClassName(context.getInitSuperECls()), param.getName()));

		FluentAPIGenerationUtil.addEParameters(op, param);

		return op;
	}

	public EParameter getInitialisationForEClassParam() {
		return FluentAPIGenerationUtil.generateSingleValuedEParameter(
				ModelConstants.RootAPI.GetInitialisationFor.ECLASS_PARAMETER_NAME.get(), EcorePackage.Literals.ECLASS);
	}

	public EOperation getInitialisationForClassMethod(FluentAPIGenerationContext context) {
		var param = getInitialisationForClassParam();

		var op = FluentAPIGenerationUtil.generateEOperation(ModelConstants.RootAPI.GetInitialisationFor.NAME.get(),
				context.getInitSuperECls());

		FluentAPIGenerationUtil.addBody(op, String.format(getInitialisationMethodBodyTemplate,
				FluentAPIGenerationUtil.getFullyQualifiedEClassName(context.getInitSuperECls()), param.getName()));

		FluentAPIGenerationUtil.addEParameters(op, param);

		return op;
	}

	public EParameter getInitialisationForClassParam() {
		var paramType = FluentAPIGenerationUtil.generateEGenericTypeWithClassifier(EcorePackage.Literals.EJAVA_CLASS);
		FluentAPIGenerationUtil.addTypeArgument(paramType, FluentAPIGenerationUtil.generateWildcardTypeArgument());
		return FluentAPIGenerationUtil.generateSingleValuedEParameter(
				ModelConstants.RootAPI.GetInitialisationFor.CLASS_PARAMETER_NAME.get(), paramType);
	}

	public EOperation getInitialisationForEObjectMethod(FluentAPIGenerationContext context) {
		var param = FluentAPIGeneralParameterGenerator.getEObjectParam();

		var op = FluentAPIGenerationUtil.generateEOperation(ModelConstants.RootAPI.GetInitialisationFor.NAME.get(),
				context.getInitSuperECls());

		FluentAPIGenerationUtil.addBody(op, String.format(getInitialisationMethodBodyTemplate,
				FluentAPIGenerationUtil.getFullyQualifiedEClassName(context.getInitSuperECls()), param.getName()));

		FluentAPIGenerationUtil.addEParameters(op, param);

		return op;
	}
}
