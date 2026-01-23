package cipm.consistency.fluentapi.gen.metamodels.pcm;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.palladiosimulator.pcm.PcmPackage;
import cipm.consistency.fluentapi.gen.FluentAPITargetMetamodelPackageProvider;
import cipm.consistency.fluentapi.gen.metamodels.MetamodelUtil;

public class FluentAPIPcmRepositoryMetamodelPackageProvider extends FluentAPITargetMetamodelPackageProvider {
	@Override
	public List<EPackage> getTargetMetamodelPackages() {
		return MetamodelUtil.getAllSubPackages(PcmPackage.eINSTANCE);
	}

	@Override
	public List<EClass> getAllTargetMetamodelConcreteEClasses() {
		return List.copyOf(MetamodelUtil.getAllConcreteEClasses(PcmPackage.eINSTANCE));
	}

	@Override
	public String getTargetMetamodelName() {
		return PcmPackage.eINSTANCE.getName();
	}
}
