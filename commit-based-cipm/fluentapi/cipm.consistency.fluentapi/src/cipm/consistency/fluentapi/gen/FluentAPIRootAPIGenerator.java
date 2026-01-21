package cipm.consistency.fluentapi.gen;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

public class FluentAPIRootAPIGenerator {
	public List<EPackage> generateRootAPIPackages(
			FluentAPITargetMetamodelPackageProvider targetMetamodelPackageProvider,
			FluentAPITargetMetamodelFeatureFilter filter) {
		var rootPacs = generateFluentAPIRootPackage();
		var rootPac = rootPacs.get(rootPacs.size() - 1);
		var initPac = generateInitialisationsPackage(rootPac);

		var placeholderEDataTypesPac = generateArrayTypesPackage(rootPac);
		FluentAPIGenerationUtil.setPlaceholderEDataTypesPackage(placeholderEDataTypesPac);

		var fluentAPICls = generateRootAPIEClass();
		rootPac.getEClassifiers().add(fluentAPICls);

		var initSuperType = generateInitSuperTypeEClass();
		rootPac.getEClassifiers().add(initSuperType);

		var initEClss = generateConcreteInitEClasses(targetMetamodelPackageProvider, filter);
		initPac.getEClassifiers().addAll(initEClss);

		setupInitSuperTypeEClass(fluentAPICls, initSuperType);
		setupConcreteInitEClasses(initSuperType, initEClss);
		setupRootAPIEClass(fluentAPICls, initSuperType, initEClss, targetMetamodelPackageProvider, filter);

		return rootPacs;
	}

	public List<EPackage> generateFluentAPIRootPackage() {
		return FluentAPIGenerationUtil.generatePackages(FluentAPIRootAPIConstants.getFluentAPIRootPackageURI(),
				FluentAPIRootAPIConstants.getFluentAPIRootPackageName());
	}

	private EPackage generateInitialisationsPackage(EPackage rootPac) {
		return FluentAPIGenerationUtil.generateSubPackage(rootPac,
				FluentAPIInitialisationConstants.getFluentAPIInitialisationsPackageName());
	}

	private EPackage generateArrayTypesPackage(EPackage rootPac) {
		return FluentAPIGenerationUtil.generateSubPackage(rootPac,
				FluentAPIConstants.getFluentAPIPlaceholderEDataTypesPackageName());
	}

	private EClass generateInitSuperTypeEClass() {
		return new FluentAPISuperInitialisationEClassGenerator().generateSuperInitialisationEClass();
	}

	private void setupInitSuperTypeEClass(EClass fluentAPICls, EClass initSuperType) {
		new FluentAPISuperInitialisationEClassGenerator().setupSuperInitialisationEClass(fluentAPICls, initSuperType);
	}

	private List<EClass> generateConcreteInitEClasses(
			FluentAPITargetMetamodelPackageProvider targetMetamodelPackageProvider,
			FluentAPITargetMetamodelFeatureFilter filter) {
		var initEClss = new FluentAPIInitialisationEClassGenerator()
				.generateFluentAPIInitialisationClasses(targetMetamodelPackageProvider, filter);
		return initEClss;
	}

	private void setupConcreteInitEClasses(EClass initSuperType, List<EClass> initEClss) {
		initEClss.forEach((cls) -> cls.getESuperTypes().add(initSuperType));
	}

	private EClass generateRootAPIEClass() {
		return new FluentAPIRootClassGenerator().generateRootAPIEClass();
	}

	private void setupRootAPIEClass(EClass fluentAPICls, EClass initSuperType, List<EClass> initEClss,
			FluentAPITargetMetamodelPackageProvider targetMetamodelPackageProvider,
			FluentAPITargetMetamodelFeatureFilter filter) {
		new FluentAPIRootClassGenerator().setupRootAPIEClass(fluentAPICls, initSuperType, initEClss,
				targetMetamodelPackageProvider, filter);
	}
}
