package cipm.consistency.fluentapi.gen.rootapi;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EcorePackage;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.FluentAPIRootAPIConstants;
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
					+ FluentAPIRootAPIConstants.getFluentAPIRootAPIMarkKeyParameterName() + ")");

	private static final String getMarkedXMethodBodyTemplate = FluentAPIMethodsUtil
			.joinLOC("return (%s) " + FluentAPIMarkExtension.class.getName() + ".getMarked(this, "
					+ FluentAPIRootAPIConstants.getFluentAPIRootAPIMarkKeyParameterName() + ")");

	public List<EOperation> generateAllMarkMethods(EClass rootAPIECls, List<EClass> allElemsToInit, FluentAPITargetMetamodelPackageProvider provider) {
		var ops = new ArrayList<EOperation>();
		ops.add(generateUnmarkMethod(rootAPIECls));
		ops.add(generateGetMarkedMethod());
		allElemsToInit.forEach((eCls) -> ops.add(generateGetMarkedXMethod(eCls, provider)));
		return ops;
	}

	public EOperation generateUnmarkMethod(EClass rootAPIECls) {
		var param = getMarkKeyParam();
		return FluentAPIGenerationUtil.generateEOperationWithBody(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIUnmarkMethodName(), rootAPIECls, unmarkMethodBodyTemplate,
				param);
	}

	public EOperation generateGetMarkedMethod() {
		var param = getMarkKeyParam();
		return FluentAPIGenerationUtil.generateEOperationWithBody(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIGetMarkedMethodName(), EcorePackage.Literals.EOBJECT,
				getMarkedMethodBodyTemplate, param);
	}

	public EOperation generateGetMarkedXMethod(EClass elemToInit, FluentAPITargetMetamodelPackageProvider provider) {
		var param = getMarkKeyParam();
		return FluentAPIGenerationUtil.generateEOperationWithBody(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIGetMarkedXMethodNameForType(elemToInit), elemToInit,
				String.format(getMarkedXMethodBodyTemplate, provider.getFullyQualifiedClassNameFor(elemToInit)), param);
	}

	private EParameter getMarkKeyParam() {
		return FluentAPIGenerationUtil.generateSingleValuedEParameter(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIMarkKeyParameterName(),
				EcorePackage.Literals.EJAVA_OBJECT);
	}
}
