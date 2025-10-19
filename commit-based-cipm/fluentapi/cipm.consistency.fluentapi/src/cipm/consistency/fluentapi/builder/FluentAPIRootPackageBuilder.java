package cipm.consistency.fluentapi.builder;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import cipm.consistency.fluentapi.gen.FluentAPIAbstractInitialisationGenerator;
import cipm.consistency.fluentapi.gen.FluentAPIClassGenerator;
import cipm.consistency.fluentapi.gen.FluentAPICurrentElementReferenceGenerator;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.FluentAPIInitialisationEClassesReferenceGenerator;
import cipm.consistency.fluentapi.gen.FluentAPIInitialisationGenerator;
import cipm.consistency.fluentapi.gen.FluentAPIInitialisedEClassReference;
import cipm.consistency.fluentapi.gen.FluentAPIOngoingInitialisationsReferenceGenerator;
import cipm.consistency.fluentapi.gen.FluentAPIRootAPIReferenceGenerator;
import cipm.consistency.fluentapi.gen.FluentAPIRootPackageGenerator;
import cipm.consistency.fluentapi.gen.FluentAPITargetMetamodelPackageProvider;

public class FluentAPIRootPackageBuilder {
	public EPackage buildRootPackage(FluentAPITargetMetamodelPackageProvider targetMetamodelPackageProvider) {
		var rootPac = new FluentAPIRootPackageGenerator().generateRootPackage(targetMetamodelPackageProvider);

		var fluentAPICls = addRootAPICls(rootPac);

		var initSuperType = addInitialisationSuperType(rootPac);

		var initEClss = addConcreteInitialisations(rootPac, initSuperType, targetMetamodelPackageProvider);
		addSuperTypeToConcreteInitialisations(initEClss, initSuperType);

		addRootAPIClsRefs(fluentAPICls, initSuperType);
		addInitSuperTypeRefs(initSuperType, fluentAPICls);

		// TODO Add methods

		return rootPac;
	}

	public void addRootAPIClsRefs(EClass rootAPIEClass, EClass initSuperType) {
		addInitialisationsReferenceToRootAPI(rootAPIEClass);
		addOngoingInitialisationsReferenceToRootAPI(rootAPIEClass, initSuperType);
	}

	public void addInitSuperTypeRefs(EClass initSuperType, EClass rootAPIEClass) {
		addRootAPIReferenceToInitSuperType(rootAPIEClass, initSuperType);
		addCurrentElementRefToInitSuperTypeRefs(initSuperType);
		addInitialisedEClassRefToInitSuperTypeRefs(initSuperType);
	}

	public void addCurrentElementRefToInitSuperTypeRefs(EClass initSuperType) {
		var currentElemRef = new FluentAPICurrentElementReferenceGenerator()
				.getCurrentElementReference(FluentAPIGenerationUtil.getEObjectEClass());
		initSuperType.getEStructuralFeatures().add(currentElemRef);
	}

	public void addInitialisedEClassRefToInitSuperTypeRefs(EClass initSuperType) {
		var initialisedEClassRef = new FluentAPIInitialisedEClassReference().getInitialisedEClassReference();
		initSuperType.getEStructuralFeatures().add(initialisedEClassRef);
	}

	public EClass addRootAPICls(EPackage rootPac) {
		var rootAPICls = new FluentAPIClassGenerator().getFluentAPIClass();
		rootPac.getEClassifiers().add(rootAPICls);
		return rootAPICls;
	}

	public void addInitialisationsReferenceToRootAPI(EClass rootAPIEClass) {
		var initialisationsRef = new FluentAPIInitialisationEClassesReferenceGenerator()
				.getInitialisationEClassesReference();
		rootAPIEClass.getEStructuralFeatures().add(initialisationsRef);
	}

	public void addOngoingInitialisationsReferenceToRootAPI(EClass rootAPIEClass, EClass initsSuperType) {
		var ongoingInitialisationsRef = new FluentAPIOngoingInitialisationsReferenceGenerator()
				.getOngoingInitialisationsReference(initsSuperType);
		rootAPIEClass.getEStructuralFeatures().add(ongoingInitialisationsRef);
	}

	public void addRootAPIReferenceToInitSuperType(EClass rootAPIEClass, EClass initsSuperType) {
		var rootAPIRefGen = new FluentAPIRootAPIReferenceGenerator();
		initsSuperType.getEStructuralFeatures().add(rootAPIRefGen.getRootAPIReference(rootAPIEClass));
	}

	public EClass addInitialisationSuperType(EPackage rootPac) {
		var fluentAPIInitialisationSuperType = new FluentAPIAbstractInitialisationGenerator()
				.generateFluentAPISuperType();
		rootPac.getEClassifiers().add(fluentAPIInitialisationSuperType);
		return fluentAPIInitialisationSuperType;
	}

	public List<EClass> addConcreteInitialisations(EPackage rootPac, EClass initialisationSuperType,
			FluentAPITargetMetamodelPackageProvider targetMetamodelPackageProvider) {
		var fluentAPIInitialisationClasses = new FluentAPIInitialisationGenerator()
				.generateFluentAPIInitialisationClasses(targetMetamodelPackageProvider);
		rootPac.getEClassifiers().addAll(fluentAPIInitialisationClasses);
		return fluentAPIInitialisationClasses;
	}

	public void addSuperTypeToConcreteInitialisations(List<EClass> inits, EClass initialisationSuperType) {
		inits.forEach((cls) -> cls.getESuperTypes().add(initialisationSuperType));
	}
}
