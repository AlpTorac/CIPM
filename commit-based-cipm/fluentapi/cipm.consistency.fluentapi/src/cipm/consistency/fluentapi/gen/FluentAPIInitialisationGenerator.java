package cipm.consistency.fluentapi.gen;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EcoreFactory;

public class FluentAPIInitialisationGenerator {
	private static final String fluentAPIClassSuffix = "Initialisation";

	public List<EClass> generateFluentAPIInitialisationClasses(
			FluentAPITargetMetamodelPackageProvider targetMetamodelPackageProvider) {
		var allPackages = targetMetamodelPackageProvider.getTargetMetamodelPackages();
		var fluentAPISubClss = new ArrayList<EClass>();

		for (var pac : allPackages) {
			for (var eCls : pac.getEClassifiers().stream().filter((c) -> c instanceof EClass).map((c) -> (EClass) c)
					.filter(FluentAPIGenerationUtil::isConcrete).collect(Collectors.toCollection(ArrayList::new))) {
				fluentAPISubClss.add(generateFluentAPIInitialisationFor(eCls));
			}
		}

		return fluentAPISubClss;
	}

	public EClass generateFluentAPIInitialisationFor(EClass initialisedEClass) {
		var xInitEClass = EcoreFactory.eINSTANCE.createEClass();
		xInitEClass.setName(initialisedEClass.getName() + fluentAPIClassSuffix);

		// Add initialisation operations "newX()"
		var newOp = new FluentAPINewOperationGenerator().getNewOperationFor(initialisedEClass);
		xInitEClass.getEOperations().add(newOp);

		// Add modification methods "withX_Feat(obj, featValParam)"
		var withOps = new FluentAPIWithOperationGenerator().getAllWithOperationsFor(initialisedEClass);
		xInitEClass.getEOperations().addAll(withOps);

		return xInitEClass;
	}
}
