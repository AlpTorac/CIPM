package cipm.consistency.fluentapi.gen.rootapi;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;

import cipm.consistency.fluentapi.gen.FluentAPIGeneralParameterGenerator;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationContext;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.ModelConstants;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;

public class FluentAPIRootAPIModifyElementMethodGenerator {
	// TODO Add documentation

	private static final String modifyElementMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			// %s: Initialisation class name
			"return (%s) this" + ModelConstants.RootAPI.GetInitialisationFor.NAME
					.call(FluentAPIGeneralParameterGenerator.getFluentAPIEObjectParameterName()));

	private static final String modifyMarkedElementMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			// %s: Initialisation type class name
			// %s: GetMarked method name
			"return (%s) this" + ModelConstants.RootAPI.GetInitialisationFor.NAME
					.call("this.%s(" + FluentAPIGeneralParameterGenerator.getFluentAPIMarkKeyParameterName() + ")"));

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
		var markKeyParam = FluentAPIGeneralParameterGenerator.getMarkKeyParam();
		var op = FluentAPIGenerationUtil.generateEOperation(
				ModelConstants.RootAPI.ModifyMarked.NAME.getFor(StringUtils.capitalize(elemToInitECls.getName())),
				initECls);
		FluentAPIGenerationUtil.addBody(op, String.format(modifyMarkedElementMethodBodyTemplate,
				FluentAPIGenerationUtil.getFullyQualifiedEClassName(initECls),
				ModelConstants.RootAPI.GetMarked.NAME.getFor(StringUtils.capitalize(elemToInitECls.getName()))));
		FluentAPIGenerationUtil.addEParameters(op, markKeyParam);
		return op;
	}

	private EOperation getRootAPIModifyElementOperationForEClass(FluentAPIGenerationContext context,
			EClass elemToInitECls, EClass initECls) {
		var param = FluentAPIGeneralParameterGenerator.getEObjectParamOfType(elemToInitECls);
		var op = FluentAPIGenerationUtil.generateEOperation(
				ModelConstants.RootAPI.Modify.NAME.getFor(StringUtils.capitalize(elemToInitECls.getName())),
				initECls);
		FluentAPIGenerationUtil.addBody(op, String.format(modifyElementMethodBodyTemplate,
				FluentAPIGenerationUtil.getFullyQualifiedEClassName(initECls)));
		FluentAPIGenerationUtil.addEParameters(op, param);
		return op;
	}

	private EOperation getRootAPITopLevelModifyElementOperation(FluentAPIGenerationContext context) {
		var param = FluentAPIGeneralParameterGenerator.getEObjectParam();
		var op = FluentAPIGenerationUtil.generateEOperation(ModelConstants.RootAPI.Modify.TOP_NAME.get(),
				context.getInitSuperECls());
		FluentAPIGenerationUtil.addBody(op, String.format(modifyElementMethodBodyTemplate,
				FluentAPIGenerationUtil.getFullyQualifiedEClassName(context.getInitSuperECls())));
		FluentAPIGenerationUtil.addEParameters(op, param);
		return op;
	}

	private EOperation getRootAPITopLevelModifyMarkedElementOperation(FluentAPIGenerationContext context) {
		var param = FluentAPIGeneralParameterGenerator.getMarkKeyParam();
		var op = FluentAPIGenerationUtil.generateEOperation(ModelConstants.RootAPI.ModifyMarked.TOP_NAME.get(),
				context.getInitSuperECls());
		FluentAPIGenerationUtil.addBody(op,
				String.format(modifyMarkedElementMethodBodyTemplate,
						FluentAPIGenerationUtil.getFullyQualifiedEClassName(context.getInitSuperECls()),
						ModelConstants.RootAPI.GetMarked.TOP_NAME.get()));
		FluentAPIGenerationUtil.addEParameters(op, param);
		return op;
	}
}
