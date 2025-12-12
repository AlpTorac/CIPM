package cipm.consistency.fluentapi.gen.metamodels.pcm;

import java.util.List;

import org.eclipse.emf.ecore.EPackage;
import org.palladiosimulator.pcm.PcmPackage;
import cipm.consistency.fluentapi.gen.FluentAPITargetMetamodelPackageProvider;

public class FluentAPIPcmRepositoryMetamodelPackageProvider extends FluentAPITargetMetamodelPackageProvider {
	@Override
	public List<EPackage> getTargetMetamodelTopLevelPackages() {
		return List.of(PcmPackage.eINSTANCE);
	}
}
