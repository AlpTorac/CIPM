package cipm.consistency.fluentapi.gen;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EcoreFactory;

import cipm.consistency.fluentapi.gen.init.FluentAPICreateNowMethodGenerator;
import cipm.consistency.fluentapi.gen.init.FluentAPIInitialisationDropOperationGenerator;
import cipm.consistency.fluentapi.gen.init.FluentAPIInitialisationMarkMethodGenerator;
import cipm.consistency.fluentapi.gen.init.FluentAPIInitialisationNewOperationGenerator;
import cipm.consistency.fluentapi.gen.init.FluentAPIInitialisationOnceExistsMethodGenerator;
import cipm.consistency.fluentapi.gen.init.FluentAPIInitialisationResetOperationGenerator;
import cipm.consistency.fluentapi.gen.init.FluentAPINextInitialisationMethodGenerator;
import cipm.consistency.fluentapi.gen.init.FluentAPIPreviousInitialisationMethodGenerator;
import cipm.consistency.fluentapi.gen.init.FluentAPIWithOperationGenerator;

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
				.setName(initialisedEClass.getName() + FluentAPIConstants.getFluentAPIInitialisationClassNameSuffix());
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
		xInitEClass.getEOperations().add(
				new FluentAPIInitialisationNewOperationGenerator().getNewOperationFor(xInitEClass, initialisedEClass));

		xInitEClass.getEOperations().addAll(new FluentAPIWithOperationGenerator()
				.generateAllWithOperationsFor(xInitEClass, initialisedEClass, targetMetamodelPackageProvider, filter));

		xInitEClass.getEOperations()
				.add(new FluentAPICreateNowMethodGenerator().generateCreateNowMethod(initialisedEClass));

		xInitEClass.getEOperations()
				.add(new FluentAPIInitialisationDropOperationGenerator().generateDropInitialisationMethod(xInitEClass));

		xInitEClass.getEOperations().add(
				new FluentAPIInitialisationResetOperationGenerator().generateResetInitialisationMethod(xInitEClass));

		xInitEClass.getEOperations().add(new FluentAPINextInitialisationMethodGenerator()
				.getNextInitialisationMethodFor(xInitEClass, initialisedEClass));

		xInitEClass.getEOperations().add(new FluentAPIPreviousInitialisationMethodGenerator()
				.getPreviousInitialisationMethodFor(xInitEClass, initialisedEClass));

		xInitEClass.getEOperations()
				.addAll(new FluentAPIInitialisationMarkMethodGenerator().generateAllMarkMethods(xInitEClass));

		xInitEClass.getEOperations().addAll(
				new FluentAPIInitialisationOnceExistsMethodGenerator().generateAllOnceExistsMethods(xInitEClass));
	}
}
