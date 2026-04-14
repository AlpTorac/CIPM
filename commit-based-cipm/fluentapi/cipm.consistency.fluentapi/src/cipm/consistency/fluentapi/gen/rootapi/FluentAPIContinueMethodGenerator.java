package cipm.consistency.fluentapi.gen.rootapi;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EcorePackage;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.FluentAPIRootAPIConstants;
import cipm.consistency.fluentapi.gen.FluentAPITargetMetamodelFeatureFilter;
import cipm.consistency.fluentapi.gen.FluentAPITargetMetamodelPackageProvider;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;
import cipm.consistency.fluentapi.gen.methods.FluentEObjectAPIMethods;

public class FluentAPIContinueMethodGenerator {
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

	public List<EOperation> generateAllContinueMethods(List<EClass> initEClss, List<EClass> eObjEClss,
			FluentAPITargetMetamodelFeatureFilter filter, FluentAPITargetMetamodelPackageProvider provider) {

		var ops = new ArrayList<EOperation>();

		for (int i = 0; i < eObjEClss.size(); i++) {
			var eObjEClass = eObjEClss.get(i);
			var initEClass = initEClss.get(i);
			if (filter.hasModifiableFeatures(eObjEClass)) {
				ops.add(generateContinueMethod(eObjEClass, initEClass, provider));
				ops.add(generateContinueNewestMethod(eObjEClass, initEClass, provider));
				ops.add(generateContinueOldestMethod(eObjEClass, initEClass, provider));
				ops.add(generateContinueFromStartMethod(eObjEClass, initEClass, provider));
				ops.add(generateContinueFromEndMethod(eObjEClass, initEClass, provider));
			}
		}

		return ops;
	}

	private EOperation generateContinueMethod(EClass elemToInit, EClass initECls, FluentAPITargetMetamodelPackageProvider provider) {
		return FluentAPIGenerationUtil.generateEOperationWithBody(
				String.format(FluentAPIRootAPIConstants.getFluentAPIRootAPIContinueMethodNameTemplate(),
						StringUtils.capitalize(elemToInit.getName())),
				initECls,
				String.format(continueMethodBodyTemplate, FluentAPIGenerationUtil.getFullyQualifiedEClassName(initECls),
						provider.getFullyQualifiedClassNameFor(elemToInit)));
	}

	private EOperation generateContinueNewestMethod(EClass elemToInit, EClass initECls, FluentAPITargetMetamodelPackageProvider provider) {
		return FluentAPIGenerationUtil.generateEOperationWithBody(
				String.format(FluentAPIRootAPIConstants.getFluentAPIRootAPIContinueWithNewestMethodNameTemplate(),
						StringUtils.capitalize(elemToInit.getName())),
				initECls,
				String.format(continueWithNewestMethodBodyTemplate,
						FluentAPIGenerationUtil.getFullyQualifiedEClassName(initECls),
						provider.getFullyQualifiedClassNameFor(elemToInit)));
	}

	private EOperation generateContinueOldestMethod(EClass elemToInit, EClass initECls, FluentAPITargetMetamodelPackageProvider provider) {
		return FluentAPIGenerationUtil.generateEOperationWithBody(
				String.format(FluentAPIRootAPIConstants.getFluentAPIRootAPIContinueWithOldestMethodNameTemplate(),
						StringUtils.capitalize(elemToInit.getName())),
				initECls,
				String.format(continueWithOldestMethodBodyTemplate,
						FluentAPIGenerationUtil.getFullyQualifiedEClassName(initECls),
						provider.getFullyQualifiedClassNameFor(elemToInit)));
	}

	private EOperation generateContinueFromStartMethod(EClass elemToInit, EClass initECls, FluentAPITargetMetamodelPackageProvider provider) {
		var param = this.getIndexParam(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIContinueFromStartMethodIndexFromStartParameterName());
		return FluentAPIGenerationUtil.generateEOperationWithBody(
				String.format(FluentAPIRootAPIConstants.getFluentAPIRootAPIContinueFromStartMethodNameTemplate(),
						StringUtils.capitalize(elemToInit.getName())),
				initECls,
				String.format(continueFromStartMethodBodyTemplate,
						FluentAPIGenerationUtil.getFullyQualifiedEClassName(initECls),
						provider.getFullyQualifiedClassNameFor(elemToInit)),
				param);
	}

	private EOperation generateContinueFromEndMethod(EClass elemToInit, EClass initECls, FluentAPITargetMetamodelPackageProvider provider) {
		var param = this.getIndexParam(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIContinueFromStartMethodIndexFromEndParameterName());
		return FluentAPIGenerationUtil.generateEOperationWithBody(
				String.format(FluentAPIRootAPIConstants.getFluentAPIRootAPIContinueFromEndMethodNameTemplate(),
						StringUtils.capitalize(elemToInit.getName())),
				initECls,
				String.format(continueFromEndMethodBodyTemplate,
						FluentAPIGenerationUtil.getFullyQualifiedEClassName(initECls),
						provider.getFullyQualifiedClassNameFor(elemToInit)),
				param);
	}

	private EParameter getIndexParam(String name) {
		return FluentAPIGenerationUtil.generateSingleValuedEParameter(name, EcorePackage.Literals.EINT);
	}
}
