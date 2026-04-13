package cipm.consistency.fluentapi.gen.metamodels.pcm;

import java.util.List;

import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.palladiosimulator.pcm.PcmPackage;
import org.palladiosimulator.pcm.allocation.AllocationPackage;
import org.palladiosimulator.pcm.repository.RepositoryPackage;
import org.palladiosimulator.pcm.resourceenvironment.ResourceenvironmentPackage;
import org.palladiosimulator.pcm.system.SystemPackage;
import org.palladiosimulator.pcm.usagemodel.UsagemodelPackage;

import cipm.consistency.fluentapi.gen.FluentAPITargetMetamodelPackageProvider;
import cipm.consistency.fluentapi.gen.metamodels.MetamodelUtil;

public class FluentAPIPcmRepositoryMetamodelPackageProvider extends FluentAPITargetMetamodelPackageProvider {
	private static final String pcmMetamodelPackageName = "pcm";

	@Override
	public List<EClass> getAllTargetMetamodelConcreteEClasses() {
		return List.copyOf(MetamodelUtil.getAllConcreteEClasses(PcmPackage.eINSTANCE));
	}

	@Override
	public List<EPackage> getTargetMetamodelTopLevelPackages() {
		return List.of(RepositoryPackage.eINSTANCE, SystemPackage.eINSTANCE, AllocationPackage.eINSTANCE,
				ResourceenvironmentPackage.eINSTANCE, UsagemodelPackage.eINSTANCE);
	}

	@Override
	public String getTargetMetamodelName() {
		return pcmMetamodelPackageName;
	}

	@Override
	public List<GenModel> getTargetMetamodelGenModels() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EPackage> getTargetMetamodelEcoreEPackages() {
		// TODO Auto-generated method stub
		return null;
	}
}
