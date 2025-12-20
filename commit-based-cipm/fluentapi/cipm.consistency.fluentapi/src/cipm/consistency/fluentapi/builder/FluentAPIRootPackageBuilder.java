package cipm.consistency.fluentapi.builder;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import cipm.consistency.fluentapi.gen.FluentAPISuperInitialisationGenerator;
import cipm.consistency.fluentapi.gen.FluentAPIRootClassGenerator;
import cipm.consistency.fluentapi.gen.FluentAPIContinueMethodGenerator;
import cipm.consistency.fluentapi.gen.FluentAPICurrentElementReferenceGenerator;
import cipm.consistency.fluentapi.gen.FluentAPIDropInitialisationMethodGenerator;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.FluentAPIGetInitialisationForMethodGenerator;
import cipm.consistency.fluentapi.gen.FluentAPIInitialisationEClassesReferenceGenerator;
import cipm.consistency.fluentapi.gen.FluentAPIInitialisationGenerator;
import cipm.consistency.fluentapi.gen.FluentAPIInitialisationsPackageGenerator;
import cipm.consistency.fluentapi.gen.FluentAPIModifyElementMethodGenerator;
import cipm.consistency.fluentapi.gen.FluentAPIPreviousInitialisationMethodGenerator;
import cipm.consistency.fluentapi.gen.FluentAPIRootAPIMarkMethodGenerator;
import cipm.consistency.fluentapi.gen.FluentAPIOngoingInitialisationsReferenceGenerator;
import cipm.consistency.fluentapi.gen.FluentAPINextInitialisationMethodGenerator;
import cipm.consistency.fluentapi.gen.FluentAPIRootAPINewMethodGenerator;
import cipm.consistency.fluentapi.gen.FluentAPIRootAPIOnceExistsMethodGenerator;
import cipm.consistency.fluentapi.gen.FluentAPIRootAPIReferenceGenerator;
import cipm.consistency.fluentapi.gen.FluentAPIRootAPIWithOperationGenerator;
import cipm.consistency.fluentapi.gen.FluentAPIRootPackageGenerator;
import cipm.consistency.fluentapi.gen.FluentAPITargetMetamodelFeatureFilter;
import cipm.consistency.fluentapi.gen.FluentAPITargetMetamodelPackageProvider;
import cipm.consistency.fluentapi.gen.FluentAPIToAPIMethodGenerator;

public class FluentAPIRootPackageBuilder {
	public List<EPackage> buildRootPackage(FluentAPITargetMetamodelPackageProvider targetMetamodelPackageProvider,
			FluentAPITargetMetamodelFeatureFilter filter) {
		var rootPacs = new FluentAPIRootPackageGenerator().generateRootPackage();
		var rootPac = rootPacs.get(rootPacs.size() - 1);

		var initPac = new FluentAPIInitialisationsPackageGenerator().generateInitialisationsPackage(rootPac);

		var fluentAPICls = addRootAPICls(rootPac);

		var initSuperType = addInitialisationSuperType(rootPac);

		var initEClss = addConcreteInitialisations(initPac, initSuperType, targetMetamodelPackageProvider, filter);
		addSuperTypeToConcreteInitialisations(initEClss, initSuperType);

		addRootAPIClsRefs(fluentAPICls, initSuperType);
		addInitSuperTypeRefs(initSuperType, fluentAPICls);

		// TODO Add methods once that part is extracted

		var allEClassesToInit = targetMetamodelPackageProvider.getAllTargetMetamodelConcreteEClasses();

		/*
		 * fluentAPICls
		 */

		fluentAPICls.getEOperations().addAll(new FluentAPIRootAPINewMethodGenerator()
				.getAllRootAPINewOperations(fluentAPICls, initSuperType, initEClss, allEClassesToInit, filter));

		fluentAPICls.getEOperations().addAll(new FluentAPIModifyElementMethodGenerator()
				.getAllRootAPIModifyElementOperations(fluentAPICls, initSuperType, initEClss, allEClassesToInit));

		fluentAPICls.getEOperations().addAll(new FluentAPIContinueMethodGenerator()
				.generateAllContinueMethods(initEClss, allEClassesToInit, filter));

		fluentAPICls.getEOperations().add(new FluentAPIDropInitialisationMethodGenerator()
				.generateDropInitialisationMethod(fluentAPICls, initSuperType));

		fluentAPICls.getEOperations().addAll(
				new FluentAPIRootAPIMarkMethodGenerator().generateAllMarkMethods(fluentAPICls, allEClassesToInit));

		fluentAPICls.getEOperations()
				.addAll(new FluentAPIRootAPIOnceExistsMethodGenerator().generateAllOnceExistsMethods(fluentAPICls));

		fluentAPICls.getEOperations().add(
				new FluentAPIGetInitialisationForMethodGenerator().getInitialisationForEClassMethod(initSuperType));

		fluentAPICls.getEOperations()
				.add(new FluentAPIGetInitialisationForMethodGenerator().getInitialisationForClassMethod(initSuperType));

		fluentAPICls.getEOperations().add(
				new FluentAPIGetInitialisationForMethodGenerator().getInitialisationForEObjectMethod(initSuperType));

		fluentAPICls.getEOperations()
				.addAll(new FluentAPIRootAPIWithOperationGenerator().getAllAPITopLevelWithOperations(fluentAPICls));

		/*
		 * initSuperType
		 */
		initSuperType.getEOperations().add(new FluentAPIToAPIMethodGenerator().generateToAPIMethod(fluentAPICls));

		initSuperType.getEOperations().add(new FluentAPINextInitialisationMethodGenerator()
				.getNextInitialisationMethodFor(initSuperType, FluentAPIGenerationUtil.getEObjectEClass()));

		initSuperType.getEOperations().add(new FluentAPIPreviousInitialisationMethodGenerator()
				.getPreviousInitialisationMethodFor(initSuperType, FluentAPIGenerationUtil.getEObjectEClass()));

		return rootPacs;
	}

	public void addRootAPIClsRefs(EClass rootAPIEClass, EClass initSuperType) {
		addInitialisationsReferenceToRootAPI(rootAPIEClass);
		addOngoingInitialisationsReferenceToRootAPI(rootAPIEClass, initSuperType);
	}

	public void addInitSuperTypeRefs(EClass initSuperType, EClass rootAPIEClass) {
		addRootAPIReferenceToInitSuperType(rootAPIEClass, initSuperType);
		addCurrentElementRefToInitSuperTypeRefs(initSuperType);
	}

	public void addCurrentElementRefToInitSuperTypeRefs(EClass initSuperType) {
		var currentElemRef = new FluentAPICurrentElementReferenceGenerator()
				.getCurrentElementReference(FluentAPIGenerationUtil.getEObjectEClass());
		initSuperType.getEStructuralFeatures().add(currentElemRef);
	}

	public EClass addRootAPICls(EPackage rootPac) {
		var rootAPICls = new FluentAPIRootClassGenerator().getFluentAPIRootClass();
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
		var fluentAPIInitialisationSuperType = new FluentAPISuperInitialisationGenerator()
				.generateFluentAPISuperInitialisationEClass();
		rootPac.getEClassifiers().add(fluentAPIInitialisationSuperType);
		return fluentAPIInitialisationSuperType;
	}

	public List<EClass> addConcreteInitialisations(EPackage initsPac, EClass initialisationSuperType,
			FluentAPITargetMetamodelPackageProvider targetMetamodelPackageProvider,
			FluentAPITargetMetamodelFeatureFilter filter) {
		var fluentAPIInitialisationClasses = new FluentAPIInitialisationGenerator()
				.generateFluentAPIInitialisationClasses(targetMetamodelPackageProvider, filter);
		initsPac.getEClassifiers().addAll(fluentAPIInitialisationClasses);
		return fluentAPIInitialisationClasses;
	}

	public void addSuperTypeToConcreteInitialisations(List<EClass> inits, EClass initialisationSuperType) {
		inits.forEach((cls) -> cls.getESuperTypes().add(initialisationSuperType));
	}
}
