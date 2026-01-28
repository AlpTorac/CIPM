package cipm.consistency.fluentapi.gen.rootapi;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EcorePackage;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.FluentAPITargetMetamodelFeatureFilter;
import cipm.consistency.fluentapi.gen.FluentAPITargetMetamodelPackageProvider;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;
import cipm.consistency.fluentapi.gen.methods.FluentEObjectAPIMethods;

public class FluentAPIRootAPIContinueMethodGenerator {
	// TODO Add documentation

	private static final String continueMethodBodyTemplate = FluentAPIMethodsUtil
			.joinLOC("return (%s) " + FluentEObjectAPIMethods.class.getName() + ".continueElement(this, %s.class)");

	private static final String continueFromStartMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC("return (%s) "
			+ FluentEObjectAPIMethods.class.getName() + ".continueElementFromStart(this, %s.class, "
			+ FluentAPIRootAPIConstants.getFluentAPIRootAPIContinueFromStartMethodIndexFromStartParameterName() + ")");

	private static final String continueFromEndMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC("return (%s) "
			+ FluentEObjectAPIMethods.class.getName() + ".continueElementFromEnd(this, %s.class, "
			+ FluentAPIRootAPIConstants.getFluentAPIRootAPIContinueFromStartMethodIndexFromEndParameterName() + ")");

	private static final String continueWithNewestMethodBodyTemplate = FluentAPIMethodsUtil
			.joinLOC("return (%s) " + FluentEObjectAPIMethods.class.getName() + ".continueElement(this, %s.class)");

	private static final String continueWithOldestMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			"return (%s) " + FluentEObjectAPIMethods.class.getName() + ".continueOldestElement(this, %s.class)");

	public List<EOperation> generateAllContinueMethods(List<EClass> initEClss,
			FluentAPITargetMetamodelPackageProvider targetMetamodelPackageProvider,
			FluentAPITargetMetamodelFeatureFilter filter) {
		var eObjEClss = targetMetamodelPackageProvider.getAllTargetMetamodelConcreteEClasses();
		var ops = new ArrayList<EOperation>();

		for (int i = 0; i < eObjEClss.size(); i++) {
			var eObjEClass = eObjEClss.get(i);
			var initEClass = initEClss.get(i);
			if (filter.hasModifiableFeatures(eObjEClass)) {
				ops.add(generateContinueMethod(eObjEClass, initEClass));
				ops.add(generateContinueNewestMethod(eObjEClass, initEClass));
				ops.add(generateContinueOldestMethod(eObjEClass, initEClass));
				ops.add(generateContinueFromStartMethod(eObjEClass, initEClass));
				ops.add(generateContinueFromEndMethod(eObjEClass, initEClass));
			}
		}

		return ops;
	}

	private EOperation generateContinueMethod(EClass elemToInit, EClass initECls) {
		var op = FluentAPIGenerationUtil.generateEOperation(
				String.format(FluentAPIRootAPIConstants.getFluentAPIRootAPIContinueMethodNameTemplate(),
						StringUtils.capitalize(elemToInit.getName())),
				initECls);

		FluentAPIGenerationUtil.addBody(op,
				String.format(continueMethodBodyTemplate, FluentAPIGenerationUtil.getFullyQualifiedEClassName(initECls),
						elemToInit.getInstanceClass().getName()));
		return op;
	}

	private EOperation generateContinueNewestMethod(EClass elemToInit, EClass initECls) {
		var op = FluentAPIGenerationUtil.generateEOperation(
				String.format(FluentAPIRootAPIConstants.getFluentAPIRootAPIContinueWithNewestMethodNameTemplate(),
						StringUtils.capitalize(elemToInit.getName())),
				initECls);

		FluentAPIGenerationUtil.addBody(op,
				String.format(continueWithNewestMethodBodyTemplate,
						FluentAPIGenerationUtil.getFullyQualifiedEClassName(initECls),
						elemToInit.getInstanceClass().getName()));
		return op;
	}

	private EOperation generateContinueOldestMethod(EClass elemToInit, EClass initECls) {
		var op = FluentAPIGenerationUtil.generateEOperation(
				String.format(FluentAPIRootAPIConstants.getFluentAPIRootAPIContinueWithOldestMethodNameTemplate(),
						StringUtils.capitalize(elemToInit.getName())),
				initECls);

		FluentAPIGenerationUtil.addBody(op,
				String.format(continueWithOldestMethodBodyTemplate,
						FluentAPIGenerationUtil.getFullyQualifiedEClassName(initECls),
						elemToInit.getInstanceClass().getName()));
		return op;
	}

	private EOperation generateContinueFromStartMethod(EClass elemToInit, EClass initECls) {
		var param = this.getIndexParam(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIContinueFromStartMethodIndexFromStartParameterName());
		var op = FluentAPIGenerationUtil.generateEOperation(
				String.format(FluentAPIRootAPIConstants.getFluentAPIRootAPIContinueFromStartMethodNameTemplate(),
						StringUtils.capitalize(elemToInit.getName())),
				initECls);

		FluentAPIGenerationUtil.addBody(op,
				String.format(continueFromStartMethodBodyTemplate,
						FluentAPIGenerationUtil.getFullyQualifiedEClassName(initECls),
						elemToInit.getInstanceClass().getName()));
		FluentAPIGenerationUtil.addEParameters(op, param);
		return op;
	}

	private EOperation generateContinueFromEndMethod(EClass elemToInit, EClass initECls) {
		var param = this.getIndexParam(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIContinueFromStartMethodIndexFromEndParameterName());
		var op = FluentAPIGenerationUtil.generateEOperation(
				String.format(FluentAPIRootAPIConstants.getFluentAPIRootAPIContinueFromEndMethodNameTemplate(),
						StringUtils.capitalize(elemToInit.getName())),
				initECls);

		FluentAPIGenerationUtil.addBody(op,
				String.format(continueFromEndMethodBodyTemplate,
						FluentAPIGenerationUtil.getFullyQualifiedEClassName(initECls),
						elemToInit.getInstanceClass().getName()));
		FluentAPIGenerationUtil.addEParameters(op, param);
		return op;
	}

	private EParameter getIndexParam(String name) {
		return FluentAPIGenerationUtil.generateSingleValuedEParameter(name, EcorePackage.Literals.EINT);
	}
}
