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
import cipm.consistency.fluentapi.gen.rootapi.templates.FluentAPIRootAPIContinueMethodTemplate;
import cipm.consistency.fluentapi.gen.superinit.FluentAPISuperInitialisationConstants;

public class FluentAPIRootAPIContinueMethodGenerator {
	// TODO Add documentation

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
				FluentAPIRootAPIContinueMethodTemplate.renderTopLevelContinueMethodName(), context.getInitSuperECls());

		FluentAPIGenerationUtil.addEParameters(op, param);
		FluentAPIGenerationUtil.addBody(op, FluentAPIRootAPIContinueMethodTemplate.renderContinueMethodBody(
				FluentAPIGenerationUtil.getFullyQualifiedEClassName(context.getInitSuperECls()), param.getName()));
		return op;
	}

	private EOperation generateContinueMethod(EClass elemToInit, EClass initECls) {
		var op = FluentAPIGenerationUtil.generateEOperation(String.format(FluentAPIRootAPIContinueMethodTemplate
				.renderContinueMethodNameForECls(FluentAPIGenerationUtil.getFullyQualifiedEClassName(elemToInit))),
				initECls);

		FluentAPIGenerationUtil.addBody(op,
				FluentAPIRootAPIContinueMethodTemplate.renderContinueMethodBody(
						FluentAPIGenerationUtil.getFullyQualifiedEClassName(initECls),
						FluentAPIGenerationUtil.getFullyQualifiedEClassName(elemToInit)));
		return op;
	}

	private EOperation generateContinueMarkedMethod(EClass elemToInit, EClass initECls) {
		var param = FluentAPIGeneralParameterGenerator.getMarkKeyParam();
		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIRootAPIContinueMethodTemplate.renderContinueMarkedMethodNameForECls(elemToInit.getName()), initECls);

		FluentAPIGenerationUtil.addBody(op, FluentAPIRootAPIContinueMethodTemplate
				.renderContinueMarkedMethodBody(FluentAPIGenerationUtil.getFullyQualifiedEClassName(initECls)));
		FluentAPIGenerationUtil.addEParameters(op, param);
		return op;
	}

	private EOperation generateTopLevelContinueMarkedMethod(FluentAPIGenerationContext context) {
		var param = FluentAPIGeneralParameterGenerator.getMarkKeyParam();
		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIRootAPIContinueMethodTemplate.renderTopLevelContinueMarkedMethodName(), context.getInitSuperECls());

		FluentAPIGenerationUtil.addBody(op, FluentAPIRootAPIContinueMethodTemplate.renderContinueMarkedMethodBody(
				FluentAPIGenerationUtil.getFullyQualifiedEClassName(context.getInitSuperECls())));
		FluentAPIGenerationUtil.addEParameters(op, param);
		return op;
	}
}
