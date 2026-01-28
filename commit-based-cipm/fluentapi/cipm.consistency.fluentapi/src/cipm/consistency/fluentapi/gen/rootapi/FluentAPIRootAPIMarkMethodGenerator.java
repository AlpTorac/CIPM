package cipm.consistency.fluentapi.gen.rootapi;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EcorePackage;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.FluentAPITargetMetamodelPackageProvider;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;
import cipm.consistency.fluentapi.gen.methods.mark.FluentAPIMarkExtension;

public class FluentAPIRootAPIMarkMethodGenerator {
	// TODO Add documentation

	private static final String unmarkMethodBodyTemplate = FluentAPIMethodsUtil
			.joinLOC(FluentAPIMarkExtension.class.getName() + ".unmark(this, "
					+ FluentAPIRootAPIConstants.getFluentAPIRootAPIMarkKeyParameterName() + ")", "return this");

	private static final String getMarkedMethodBodyTemplate = FluentAPIMethodsUtil
			.joinLOC("return " + FluentAPIMarkExtension.class.getName() + ".getMarked(this, "
					+ FluentAPIRootAPIConstants.getFluentAPIRootAPIMarkKeyParameterName() + ", null)");

	private static final String getMarkedXMethodBodyTemplate = FluentAPIMethodsUtil
			.joinLOC("return (%s) " + FluentAPIMarkExtension.class.getName() + ".getMarked(this, "
					+ FluentAPIRootAPIConstants.getFluentAPIRootAPIMarkKeyParameterName() + ", %s.class)");

	public List<EOperation> generateAllMarkMethods(EClass rootAPIECls,
			FluentAPITargetMetamodelPackageProvider targetMetamodelPackageProvider) {
		var allEClss = targetMetamodelPackageProvider.getAllTargetMetamodelEClasses();
		var ops = new ArrayList<EOperation>();
		ops.add(generateUnmarkMethod(rootAPIECls));
		ops.add(generateGetMarkedMethod());
		allEClss.forEach((eCls) -> ops.add(generateGetMarkedXMethod(eCls)));
		return ops;
	}

	private EOperation generateUnmarkMethod(EClass rootAPIECls) {
		var param = getMarkKeyParam();
		var op = FluentAPIGenerationUtil
				.generateEOperation(FluentAPIRootAPIConstants.getFluentAPIRootAPIUnmarkMethodName(), rootAPIECls);
		FluentAPIGenerationUtil.addBody(op, unmarkMethodBodyTemplate);
		FluentAPIGenerationUtil.addEParameters(op, param);
		return op;
	}

	private EOperation generateGetMarkedMethod() {
		var param = getMarkKeyParam();
		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIGetMarkedMethodName(), EcorePackage.Literals.EOBJECT);
		FluentAPIGenerationUtil.addBody(op, getMarkedMethodBodyTemplate);
		FluentAPIGenerationUtil.addEParameters(op, param);
		return op;
	}

	private EOperation generateGetMarkedXMethod(EClass elemToInit) {
		var param = getMarkKeyParam();
		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIGetMarkedXMethodNameForType(elemToInit), elemToInit);
		FluentAPIGenerationUtil.addBody(op, String.format(getMarkedXMethodBodyTemplate,
				elemToInit.getInstanceClass().getName(), elemToInit.getInstanceClass().getName()));
		FluentAPIGenerationUtil.addEParameters(op, param);
		return op;
	}

	private EParameter getMarkKeyParam() {
		return FluentAPIGenerationUtil.generateSingleValuedEParameter(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIMarkKeyParameterName(),
				EcorePackage.Literals.EJAVA_OBJECT);
	}
}
