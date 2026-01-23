package cipm.consistency.fluentapi.gen.init;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EcoreFactory;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.FluentAPITargetMetamodelFeatureFilter;
import cipm.consistency.fluentapi.gen.FluentAPITargetMetamodelPackageProvider;
import cipm.consistency.fluentapi.gen.superinit.FluentAPISuperInitialisationCreateNowMethodGenerator;
import cipm.consistency.fluentapi.gen.superinit.FluentAPISuperInitialisationDropOperationGenerator;
import cipm.consistency.fluentapi.gen.superinit.FluentAPISuperInitialisationMarkMethodGenerator;
import cipm.consistency.fluentapi.gen.superinit.FluentAPISuperInitialisationNextInitialisationMethodGenerator;
import cipm.consistency.fluentapi.gen.superinit.FluentAPISuperInitialisationPreviousInitialisationMethodGenerator;
import cipm.consistency.fluentapi.gen.superinit.FluentAPISuperInitialisationResetOperationGenerator;

public class FluentAPIInitialisationEClassGenerator {
	public List<EClass> generateFluentAPIInitialisationClasses(
			FluentAPITargetMetamodelPackageProvider targetMetamodelPackageProvider,
			FluentAPITargetMetamodelFeatureFilter filter) {
		var allPackages = targetMetamodelPackageProvider.getTargetMetamodelPackages();
		var initSubClss = new ArrayList<EClass>();

		for (var pac : allPackages) {
			for (var initialisedEClass : pac.getEClassifiers().stream().filter((c) -> c instanceof EClass)
					.map((c) -> (EClass) c).filter(FluentAPIGenerationUtil::isConcrete)
					.collect(Collectors.toCollection(ArrayList::new))) {
				var initSubCls = generateInitialisationEClass(initialisedEClass);
				setupFluentAPIInitialisationFor(initSubCls, initialisedEClass, targetMetamodelPackageProvider, filter);
				initSubClss.add(initSubCls);
			}
		}

		return initSubClss;
	}

	private EClass generateInitialisationEClass(EClass initialisedEClass) {
		var xInitEClass = EcoreFactory.eINSTANCE.createEClass();
		xInitEClass
				.setName(initialisedEClass.getName() + FluentAPIInitialisationConstants.getFluentAPIInitialisationClassNameSuffix());
		return xInitEClass;
	}

	private void setupFluentAPIInitialisationFor(EClass xInitEClass, EClass initialisedEClass,
			FluentAPITargetMetamodelPackageProvider targetMetamodelPackageProvider,
			FluentAPITargetMetamodelFeatureFilter filter) {
		addOperations(xInitEClass, initialisedEClass, targetMetamodelPackageProvider, filter);
	}

	private void addOperations(EClass xInitEClass, EClass initialisedEClass,
			FluentAPITargetMetamodelPackageProvider targetMetamodelPackageProvider,
			FluentAPITargetMetamodelFeatureFilter filter) {
		xInitEClass.getEOperations().add(new FluentAPIInitialisationNewElementOperationGenerator()
				.getNewElementOperationFor(xInitEClass, initialisedEClass));

		xInitEClass.getEOperations().addAll(new FluentAPIInitialisationWithOperationGenerator()
				.generateAllWithOperationsFor(xInitEClass, initialisedEClass, targetMetamodelPackageProvider, filter));

		xInitEClass.getEOperations()
				.addAll(new FluentAPISuperInitialisationCreateNowMethodGenerator().generateAllCreateNowMethods(initialisedEClass));

		xInitEClass.getEOperations().add(
				new FluentAPISuperInitialisationDropOperationGenerator().generateDropInitialisationMethod(xInitEClass));

		xInitEClass.getEOperations().add(
				new FluentAPISuperInitialisationResetOperationGenerator().generateResetInitialisationMethod(xInitEClass));

		xInitEClass.getEOperations().add(new FluentAPISuperInitialisationNextInitialisationMethodGenerator()
				.getNextInitialisationMethodFor(xInitEClass, initialisedEClass));

		xInitEClass.getEOperations().add(new FluentAPISuperInitialisationPreviousInitialisationMethodGenerator()
				.getPreviousInitialisationMethodFor(xInitEClass, initialisedEClass));

		xInitEClass.getEOperations()
				.addAll(new FluentAPISuperInitialisationMarkMethodGenerator().generateAllMarkMethods(xInitEClass));

		xInitEClass.getEOperations().addAll(
				new FluentAPIInitialisationOnceExistsMethodGenerator().generateAllOnceExistsMethods(xInitEClass));
	}
}
