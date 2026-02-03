package cipm.consistency.fluentapi.gen.rootapi;

import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EcorePackage;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationContext;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;
import cipm.consistency.fluentapi.gen.methods.FluentEObjectAPIMethods;

public class FluentAPIRootAPIGetInitialisationForMethodGenerator {
	// TODO Add documentation

	private static final String getInitialisationMethodBody = FluentAPIMethodsUtil
			.joinLOC("return (%s)" + FluentEObjectAPIMethods.class.getName() + ".getInitialisationForX(this, "
					+ FluentAPIRootAPIConstants.getFluentAPIRootAPIGetInitialisationForEClassParameterName() + ")");

	private static final String getInitialisationForClassMethodBody = FluentAPIMethodsUtil
			.joinLOC("return (%s)" + FluentEObjectAPIMethods.class.getName() + ".getInitialisationForX(this, "
					+ FluentAPIRootAPIConstants.getFluentAPIRootAPIGetInitialisationForClassParameterName() + ")");

	private static final String getInitialisationForEObjectMethodBody = FluentAPIMethodsUtil
			.joinLOC("return (%s)" + FluentEObjectAPIMethods.class.getName() + ".getInitialisationForX(this, "
					+ FluentAPIRootAPIConstants.getFluentAPIRootAPIGetInitialisationForEObjectParameterName() + ")");

	public EOperation getInitialisationForEClassMethod(FluentAPIGenerationContext context) {
		var param = getInitialisationForEClassParam();

		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIGetInitialisationForMethodName(),
				context.getInitSuperECls());

		FluentAPIGenerationUtil.addBody(op, String.format(getInitialisationMethodBody,
				FluentAPIGenerationUtil.getFullyQualifiedEClassName(context.getInitSuperECls())));

		FluentAPIGenerationUtil.addEParameters(op, param);

		return op;
	}

	public EParameter getInitialisationForEClassParam() {
		return FluentAPIGenerationUtil.generateSingleValuedEParameter(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIGetInitialisationForEClassParameterName(),
				EcorePackage.Literals.ECLASS);
	}

	public EOperation getInitialisationForClassMethod(FluentAPIGenerationContext context) {
		var param = getInitialisationForClassParam();

		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIGetInitialisationForMethodName(),
				context.getInitSuperECls());

		FluentAPIGenerationUtil.addBody(op, String.format(getInitialisationForClassMethodBody,
				FluentAPIGenerationUtil.getFullyQualifiedEClassName(context.getInitSuperECls())));

		FluentAPIGenerationUtil.addEParameters(op, param);

		return op;
	}

	public EParameter getInitialisationForClassParam() {
		var paramType = FluentAPIGenerationUtil.generateEGenericTypeWithClassifier(EcorePackage.Literals.EJAVA_CLASS);
		FluentAPIGenerationUtil.addTypeArgument(paramType, FluentAPIGenerationUtil.generateWildcardTypeArgument());
		return FluentAPIGenerationUtil.generateSingleValuedEParameter(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIGetInitialisationForClassParameterName(), paramType);
	}

	public EOperation getInitialisationForEObjectMethod(FluentAPIGenerationContext context) {
		var param = getInitialisationForEObjectParam();

		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIGetInitialisationForMethodName(),
				context.getInitSuperECls());

		FluentAPIGenerationUtil.addBody(op, String.format(getInitialisationForEObjectMethodBody,
				FluentAPIGenerationUtil.getFullyQualifiedEClassName(context.getInitSuperECls())));

		FluentAPIGenerationUtil.addEParameters(op, param);

		return op;
	}

	public EParameter getInitialisationForEObjectParam() {
		return FluentAPIGenerationUtil.generateSingleValuedEParameter(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIGetInitialisationForEObjectParameterName(),
				EcorePackage.Literals.EOBJECT);
	}
}
