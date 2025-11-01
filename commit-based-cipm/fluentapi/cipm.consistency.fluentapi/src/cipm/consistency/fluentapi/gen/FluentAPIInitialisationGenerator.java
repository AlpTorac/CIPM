package cipm.consistency.fluentapi.gen;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EcoreFactory;

public class FluentAPIInitialisationGenerator {
	private static final String fluentAPIClassSuffix = "Initialisation";

	public List<EClass> generateFluentAPIInitialisationClasses(
			FluentAPITargetMetamodelPackageProvider targetMetamodelPackageProvider,
			FluentAPITargetMetamodelFeatureFilter filter) {
		var allPackages = targetMetamodelPackageProvider.getTargetMetamodelPackages();
		var fluentAPISubClss = new ArrayList<EClass>();

		for (var pac : allPackages) {
			for (var eCls : pac.getEClassifiers().stream().filter((c) -> c instanceof EClass).map((c) -> (EClass) c)
					.filter(FluentAPIGenerationUtil::isConcrete).collect(Collectors.toCollection(ArrayList::new))) {
				fluentAPISubClss.add(generateFluentAPIInitialisationFor(eCls, targetMetamodelPackageProvider, filter));
			}
		}

		return fluentAPISubClss;
	}

	public EClass generateFluentAPIInitialisationFor(EClass initialisedEClass,
			FluentAPITargetMetamodelPackageProvider targetMetamodelPackageProvider,
			FluentAPITargetMetamodelFeatureFilter filter) {
		var xInitEClass = EcoreFactory.eINSTANCE.createEClass();
		xInitEClass.setName(initialisedEClass.getName() + fluentAPIClassSuffix);

		// Extract the part with operations into a builder

		var newOp = new FluentAPIInitialisationNewOperationGenerator().getNewOperationFor(xInitEClass,
				initialisedEClass);
		xInitEClass.getEOperations().add(newOp);

		var withOps = new FluentAPIWithOperationGenerator().generateAllWithOperationsFor(xInitEClass, initialisedEClass,
				targetMetamodelPackageProvider, filter);
		xInitEClass.getEOperations().addAll(withOps);

		var gen = new FluentAPICreateNowMethodGenerator();
		xInitEClass.getEOperations().add(gen.generateCreateNowMethod(initialisedEClass));

		xInitEClass.getEOperations()
				.add(new FluentAPIInitialisationDropOperationGenerator().generateDropInitialisationMethod(xInitEClass));

		xInitEClass.getEOperations().add(
				new FluentAPIInitialisationResetOperationGenerator().generateResetInitialisationMethod(xInitEClass));

		xInitEClass.getEOperations().add(new FluentAPINextInitialisationMethodGenerator()
				.getNextInitialisationMethodFor(xInitEClass, initialisedEClass));

		xInitEClass.getEOperations().add(new FluentAPIPreviousInitialisationMethodGenerator()
				.getPreviousInitialisationMethodFor(xInitEClass, initialisedEClass));

		return xInitEClass;
	}
}
