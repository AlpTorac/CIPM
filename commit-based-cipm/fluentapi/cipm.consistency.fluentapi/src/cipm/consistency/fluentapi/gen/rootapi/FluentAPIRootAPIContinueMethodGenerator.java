package cipm.consistency.fluentapi.gen.rootapi;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EcorePackage;

import cipm.consistency.fluentapi.gen.FluentAPIConstants;
import cipm.consistency.fluentapi.gen.FluentAPIGeneralParameterGenerator;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationContext;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.methods.FluentAPIInitialisationStorage;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;
import cipm.consistency.fluentapi.gen.methods.FluentEObjectAPIMethods;
import cipm.consistency.fluentapi.gen.superinit.FluentAPISuperInitialisationConstants;

public class FluentAPIRootAPIContinueMethodGenerator {
	// TODO Add documentation

	private static final String continueMethodBodyTemplate =
			// %s: Full Initialisation class name
			// %s: Initialised element class (statically, i.e. either via method parameter
			// or via .class)
			FluentAPIMethodsUtil
					.joinLOC("return (%s) " + FluentEObjectAPIMethods.class.getName() + ".continueElement(%s)");

	private static final String continueMarkedMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			"var markedElem = this." + FluentAPIRootAPIConstants.getFluentAPIRootAPIGetMarkedMethodName() + "("
					+ FluentAPIGeneralParameterGenerator.getFluentAPIMarkKeyParameterName() + ")",
			// %s: Init class name
			"return markedElem == null ? null : (%s) " + FluentAPIInitialisationStorage.class.getName()
					+ ".getOngoingInits().stream().filter((i) -> (("
					+ FluentAPIRootAPIConstants.getFluentAPIRootPackageName() + "."
					+ FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationClassName() + ") i).get"
					+ FluentAPISuperInitialisationConstants
							.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName()
					+ "() == markedElem).findFirst().get()");

	public List<EOperation> generateAllContinueMethods(FluentAPIGenerationContext context) {
		var eObjEClss = context.getTargetMetamodelPackageProvider().getAllTargetMetamodelConcreteEClasses();
		var ops = new ArrayList<EOperation>();

		ops.add(generateTopLevelContinueMethod(context));
		ops.add(generateTopLevelContinueMarkedMethod(context));

		for (int i = 0; i < eObjEClss.size(); i++) {
			var eObjEClass = eObjEClss.get(i);
			var initEClass = context.getAllInitEClss().get(i);
			if (context.getTargetMetamodelFeatureFilter().hasModifiableFeatures(eObjEClass)) {
				ops.add(generateContinueMethod(eObjEClass, initEClass));
				ops.add(generateContinueMarkedMethod(eObjEClass, initEClass));
			}
		}

		return ops;
	}

	private EOperation generateTopLevelContinueMethod(FluentAPIGenerationContext context) {
		var paramType = FluentAPIGenerationUtil.generateEGenericTypeWithClassifier(EcorePackage.Literals.EJAVA_CLASS);
		FluentAPIGenerationUtil.addTypeArgument(paramType, FluentAPIGenerationUtil.generateWildcardTypeArgument());

		var param = FluentAPIGenerationUtil.generateSingleValuedEParameter(
				FluentAPIRootAPIConstants.getFluentAPIRootAPINewMethodClassParameterName(), paramType);

		var op = FluentAPIGenerationUtil.generateEOperation(
				String.format(FluentAPIRootAPIConstants.getFluentAPIRootAPIContinueMethodNameTemplate(),
						FluentAPIConstants.getDocumentationPlaceholder()),
				context.getInitSuperECls());

		FluentAPIGenerationUtil.addEParameters(op, param);
		FluentAPIGenerationUtil.addBody(op, String.format(continueMethodBodyTemplate,
				FluentAPIGenerationUtil.getFullyQualifiedEClassName(context.getInitSuperECls()), param.getName()));
		return op;
	}

	private EOperation generateContinueMethod(EClass elemToInit, EClass initECls) {
		var op = FluentAPIGenerationUtil.generateEOperation(
				String.format(FluentAPIRootAPIConstants.getFluentAPIRootAPIContinueMethodNameTemplate(),
						StringUtils.capitalize(elemToInit.getName())),
				initECls);

		FluentAPIGenerationUtil.addBody(op,
				String.format(continueMethodBodyTemplate, FluentAPIGenerationUtil.getFullyQualifiedEClassName(initECls),
						elemToInit.getInstanceClass().getName() + ".class"));
		return op;
	}

	private EOperation generateContinueMarkedMethod(EClass elemToInit, EClass initECls) {
		var param = FluentAPIGeneralParameterGenerator.getMarkKeyParam();
		var op = FluentAPIGenerationUtil.generateEOperation(
				String.format(FluentAPIRootAPIConstants.getFluentAPIRootAPIContinueMarkedMethodNameTemplate(),
						StringUtils.capitalize(elemToInit.getName())),
				initECls);

		FluentAPIGenerationUtil.addBody(op, String.format(continueMarkedMethodBodyTemplate,
				FluentAPIGenerationUtil.getFullyQualifiedEClassName(initECls)));
		FluentAPIGenerationUtil.addEParameters(op, param);
		return op;
	}

	private EOperation generateTopLevelContinueMarkedMethod(FluentAPIGenerationContext context) {
		var param = FluentAPIGeneralParameterGenerator.getMarkKeyParam();
		var op = FluentAPIGenerationUtil.generateEOperation(
				String.format(FluentAPIRootAPIConstants.getFluentAPIRootAPIContinueMarkedMethodNameTemplate(),
						FluentAPIConstants.getDocumentationPlaceholder()),
				context.getInitSuperECls());

		FluentAPIGenerationUtil.addBody(op, String.format(continueMarkedMethodBodyTemplate,
				FluentAPIGenerationUtil.getFullyQualifiedEClassName(context.getInitSuperECls())));
		FluentAPIGenerationUtil.addEParameters(op, param);
		return op;
	}
}
