package cipm.consistency.fluentapi.gen.rootapi;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EcorePackage;

import cipm.consistency.fluentapi.gen.FluentAPIDocumentationUtil;
import cipm.consistency.fluentapi.gen.FluentAPIGeneralParameterGenerator;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationContext;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;
import cipm.consistency.fluentapi.gen.methods.mark.FluentAPIMarkExtension;

public class FluentAPIRootAPIMarkMethodGenerator {
	// TODO Add documentation

	private static final String unmarkMethodBody = FluentAPIMethodsUtil.joinLOC(FluentAPIMarkExtension.class.getName()
			+ ".unmark(" + FluentAPIGeneralParameterGenerator.getFluentAPIMarkKeyParameterName() + ")", "return this");

	private static final String unmarkFullMethodBody = FluentAPIMethodsUtil
			.joinLOC(
					FluentAPIMarkExtension.class.getName() + ".unmark("
							+ FluentAPIGeneralParameterGenerator.getFluentAPIMarkKeyParameterName() + ", "
							+ FluentAPIGeneralParameterGenerator.getFluentAPIMarkValParameterName() + ")",
					"return this");

	private static final String markMethodSummary = "Marks the object with "
			+ FluentAPIGeneralParameterGenerator.getFluentAPIMarkKeyParameterName() + ", does not modify the object.";
	private static final String markMethodDocumentation = FluentAPIDocumentationUtil
			.appendSummaryToStart(markMethodSummary) + "Associates the given object with "
			+ FluentAPIGeneralParameterGenerator.getFluentAPIMarkKeyParameterName()
			+ ". Doing so marks the given object, meaning that using "
			+ FluentAPIGeneralParameterGenerator.getFluentAPIMarkKeyParameterName()
			+ " in mark-related operations will result in retrieving the given object.";
	private static final String markMethodBodyTemplate = FluentAPIMethodsUtil
			.joinLOC(
					FluentAPIMarkExtension.class.getName() + ".mark("
							+ FluentAPIGeneralParameterGenerator.getFluentAPIMarkKeyParameterName() + ", "
							+ FluentAPIGeneralParameterGenerator.getFluentAPIMarkValParameterName() + ")",
					"return this");

	private static final String getMarkedMethodBody = FluentAPIMethodsUtil
			.joinLOC("return " + FluentAPIMarkExtension.class.getName() + ".getMarked("
					+ FluentAPIGeneralParameterGenerator.getFluentAPIMarkKeyParameterName() + ")");

	private static final String getMarkedXMethodBodyTemplate =
			// %s: Element's class
			// %s: Element's class
			FluentAPIMethodsUtil.joinLOC("return (%s) " + FluentAPIMarkExtension.class.getName() + ".getMarked("
					+ FluentAPIGeneralParameterGenerator.getFluentAPIMarkKeyParameterName() + ", %s.class)");

	public List<EOperation> generateAllMarkMethods(FluentAPIGenerationContext context) {
		var allEClss = context.getTargetMetamodelPackageProvider().getAllTargetMetamodelEClasses();
		var ops = new ArrayList<EOperation>();
		ops.add(generateUnmarkMethod(context));
		ops.add(generateUnmarkFullMethod(context));
		ops.add(generateMarkMethod(context));
		ops.add(generateGetMarkedMethod());
		allEClss.forEach((eCls) -> ops.add(generateGetMarkedXMethod(eCls)));
		return ops;
	}

	private EOperation generateMarkMethod(FluentAPIGenerationContext context) {
		var markKeyParam = FluentAPIGeneralParameterGenerator.getMarkKeyParam();
		var markValParam = FluentAPIGeneralParameterGenerator.getMarkValParam();
		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIMarkMethodName(), context.getFluentAPIECls());
		FluentAPIGenerationUtil.addBody(op, markMethodBodyTemplate);
		FluentAPIGenerationUtil.addDocumentation(op, markMethodDocumentation);
		FluentAPIGenerationUtil.addEParameters(op, markKeyParam, markValParam);
		return op;
	}

	private EOperation generateUnmarkMethod(FluentAPIGenerationContext context) {
		var param = FluentAPIGeneralParameterGenerator.getMarkKeyParam();
		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIUnmarkMethodName(), context.getFluentAPIECls());
		FluentAPIGenerationUtil.addBody(op, unmarkMethodBody);
		FluentAPIGenerationUtil.addEParameters(op, param);
		return op;
	}

	private EOperation generateUnmarkFullMethod(FluentAPIGenerationContext context) {
		var markKeyParam = FluentAPIGeneralParameterGenerator.getMarkKeyParam();
		var markValParam = FluentAPIGeneralParameterGenerator.getMarkValParam();
		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIUnmarkMethodName(), context.getFluentAPIECls());
		FluentAPIGenerationUtil.addBody(op, unmarkFullMethodBody);
		FluentAPIGenerationUtil.addEParameters(op, markKeyParam, markValParam);
		return op;
	}

	private EOperation generateGetMarkedMethod() {
		var param = FluentAPIGeneralParameterGenerator.getMarkKeyParam();
		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIGetMarkedMethodName(), EcorePackage.Literals.EOBJECT);
		FluentAPIGenerationUtil.addBody(op, getMarkedMethodBody);
		FluentAPIGenerationUtil.addEParameters(op, param);
		return op;
	}

	private EOperation generateGetMarkedXMethod(EClass elemToInit) {
		var param = FluentAPIGeneralParameterGenerator.getMarkKeyParam();
		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIGetMarkedXMethodNameForType(elemToInit), elemToInit);
		FluentAPIGenerationUtil.addBody(op, String.format(getMarkedXMethodBodyTemplate,
				elemToInit.getInstanceClass().getName(), elemToInit.getInstanceClass().getName()));
		FluentAPIGenerationUtil.addEParameters(op, param);
		return op;
	}
}
