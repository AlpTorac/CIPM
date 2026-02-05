package cipm.consistency.fluentapi.gen.rootapi;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EcorePackage;

import cipm.consistency.fluentapi.gen.FluentAPIConstants;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationContext;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;

public class FluentAPIRootAPIModifyElementMethodGenerator {
	// TODO Add documentation

	private static final String modifyElementMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			// %s: Initialisation class name
			"return (%s) this." + FluentAPIRootAPIConstants.getFluentAPIRootAPIGetInitialisationForMethodName() + "("
					+ FluentAPIRootAPIConstants.getFluentAPIRootAPIModifyMethodEObjectParameterName() + ")");

	private static final String modifyMarkedElementMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			// %s: Initialisation type class name
			// %s: GetMarked method name
			"return (%s) this." + FluentAPIRootAPIConstants.getFluentAPIRootAPIGetInitialisationForMethodName()
					+ "(this.%s(" + FluentAPIRootAPIConstants.getFluentAPIRootAPIMarkKeyParameterName() + "))");

	public List<EOperation> getAllRootAPIModifyElementOperations(FluentAPIGenerationContext context) {
		var eObjEClss = context.getTargetMetamodelPackageProvider().getAllTargetMetamodelConcreteEClasses();
		var ops = new ArrayList<EOperation>();
		ops.add(getRootAPITopLevelModifyElementOperation(context));
		ops.add(getRootAPITopLevelModifyMarkedElementOperation(context));

		for (int i = 0; i < eObjEClss.size(); i++) {
			var elemToInitECls = eObjEClss.get(i);
			var initEClass = context.getAllInitEClss().get(i);
			ops.add(getRootAPIModifyElementOperationForEClass(context, elemToInitECls, initEClass));
			ops.add(getRootAPIModifyMarkedElementOperationForEClass(context, elemToInitECls, initEClass));
		}

		return ops;
	}

	private EOperation getRootAPIModifyMarkedElementOperationForEClass(FluentAPIGenerationContext context,
			EClass elemToInitECls, EClass initECls) {
		var markKeyParam = getMarkKeyParam();
		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIModifyMarkedMethodNameForType(elemToInitECls), initECls);
		FluentAPIGenerationUtil.addBody(op,
				String.format(modifyMarkedElementMethodBodyTemplate,
						FluentAPIGenerationUtil.getFullyQualifiedEClassName(initECls),
						FluentAPIRootAPIConstants.getFluentAPIRootAPIGetMarkedXMethodNameForType(elemToInitECls)));
		FluentAPIGenerationUtil.addEParameters(op, markKeyParam);
		return op;
	}

	private EOperation getRootAPIModifyElementOperationForEClass(FluentAPIGenerationContext context,
			EClass elemToInitECls, EClass initECls) {
		var param = getEObjectParam(elemToInitECls);
		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIModifyMethodNameForType(elemToInitECls), initECls);
		FluentAPIGenerationUtil.addBody(op, String.format(modifyElementMethodBodyTemplate,
				FluentAPIGenerationUtil.getFullyQualifiedEClassName(initECls)));
		FluentAPIGenerationUtil.addEParameters(op, param);
		return op;
	}

	private EOperation getRootAPITopLevelModifyElementOperation(FluentAPIGenerationContext context) {
		var param = getEObjectParam(EcorePackage.Literals.EOBJECT);
		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIModifyXMethodName(), context.getInitSuperECls());
		FluentAPIGenerationUtil.addBody(op, String.format(modifyElementMethodBodyTemplate,
				FluentAPIGenerationUtil.getFullyQualifiedEClassName(context.getInitSuperECls())));
		FluentAPIGenerationUtil.addEParameters(op, param);
		return op;
	}

	private EOperation getRootAPITopLevelModifyMarkedElementOperation(FluentAPIGenerationContext context) {
		var param = getMarkKeyParam();
		var op = FluentAPIGenerationUtil.generateEOperation(
				String.format(FluentAPIRootAPIConstants.getFluentAPIRootAPIModifyMarkedMethodNameTemplate(),
						FluentAPIConstants.getDocumentationPlaceholder()),
				context.getInitSuperECls());
		FluentAPIGenerationUtil.addBody(op,
				String.format(modifyMarkedElementMethodBodyTemplate,
						FluentAPIGenerationUtil.getFullyQualifiedEClassName(context.getInitSuperECls()),
						FluentAPIRootAPIConstants.getFluentAPIRootAPIGetMarkedMethodName()));
		FluentAPIGenerationUtil.addEParameters(op, param);
		return op;
	}

	private EParameter getEObjectParam(EClass elemToInitECls) {
		return FluentAPIGenerationUtil.generateSingleValuedEParameter(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIModifyMethodEObjectParameterName(), elemToInitECls);
	}

	private EParameter getMarkKeyParam() {
		return FluentAPIGenerationUtil.generateSingleValuedEParameter(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIMarkKeyParameterName(),
				EcorePackage.Literals.EJAVA_OBJECT);
	}
}
