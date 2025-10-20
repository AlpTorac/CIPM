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
				fluentAPISubClss.add(generateFluentAPIInitialisationFor(eCls, filter));
			}
		}

		return fluentAPISubClss;
	}

	public EClass generateFluentAPIInitialisationFor(EClass initialisedEClass,
			FluentAPITargetMetamodelFeatureFilter filter) {
		var xInitEClass = EcoreFactory.eINSTANCE.createEClass();
		xInitEClass.setName(initialisedEClass.getName() + fluentAPIClassSuffix);

		// Extract the part with operations into a builder

		var newOp = new FluentAPINewOperationGenerator().getNewOperationFor(initialisedEClass);
		xInitEClass.getEOperations().add(newOp);

		var withOps = new FluentAPIWithOperationGenerator().generateAllWithOperationsFor(initialisedEClass, filter);
		xInitEClass.getEOperations().addAll(withOps);

		return xInitEClass;
	}
}
