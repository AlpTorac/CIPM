package cipm.consistency.fluentapi.pcm.metamodel;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.palladiosimulator.pcm.PcmPackage;

import cipm.consistency.fluentapi.metamodel.FluentAPITargetMetamodelPackageProvider;
import cipm.consistency.fluentapi.metamodel.MetamodelUtil;

public class FluentAPIPcmMetamodelPackageProvider extends FluentAPITargetMetamodelPackageProvider {
	private static final URI pcmMetamodelGenModelURI = URI
			.createURI("platform:/plugin/org.palladiosimulator.pcm/model/pcm.genmodel");
	private static final URI pcmMetamodelEcoreModelURI = URI
			.createURI("platform:/plugin/org.palladiosimulator.pcm/model/pcm.ecore");

	private final ResourceSet metamodelResSet = new ResourceSetImpl();
	private Resource ecoreRes;
	private Resource genModelRes;
	private List<EClass> originalEClss;

	private void cacheOriginalEClasses() {
		if (originalEClss == null) {
			originalEClss = new ArrayList<EClass>(MetamodelUtil.getAllEClasses(PcmPackage.eINSTANCE));
		}
	}

	@Override
	public String getTargetMetamodelName() {
		var topPac = getTargetMetamodelEcoreEPackages().get(0);
		return topPac.getName();
	}

	@Override
	public List<EClass> getAllTargetMetamodelEClasses() {
		var topPac = getTargetMetamodelEcoreEPackages().get(0);
		return List.copyOf(MetamodelUtil.getAllEClasses(topPac));
	}

	@Override
	public List<EClass> getAllEClassesInOriginalMetamodel() {
		cacheOriginalEClasses();
		return originalEClss;
	}

	@Override
	public List<GenModel> getTargetMetamodelGenModels() {
		if (genModelRes == null) {
			genModelRes = metamodelResSet.getResource(pcmMetamodelGenModelURI, true);
		}

		var pcmGenModel = (GenModel) genModelRes.getContents().get(0);
		pcmGenModel.setCanGenerate(false);

		return List.of(pcmGenModel);
	}

	@Override
	public List<EPackage> getTargetMetamodelEcoreEPackages() {
		if (ecoreRes == null) {
			ecoreRes = metamodelResSet.getResource(pcmMetamodelEcoreModelURI, true);
			var parsedPcmPac = (EPackage) ecoreRes.getContents().get(0);
			fixInstanceClasses(parsedPcmPac);
		}

		return List.of((EPackage) ecoreRes.getContents().get(0));
	}

	private void fixInstanceClasses(EPackage parsedPcmPac) {
		var parsedEClss = MetamodelUtil.getAllEClasses(parsedPcmPac);
		cacheOriginalEClasses();

		if (parsedEClss.size() != originalEClss.size())
			throw new IllegalStateException(
					"Parsed PCM package and the actual PCM package contain different amounts of EClasses");

		for (var parsedECls : parsedEClss) {
			var matchingActualECls = originalEClss.stream()
					.filter((cls) -> cls.getEPackage().getName().equals(parsedECls.getEPackage().getName()))
					.filter((cls) -> cls.getName().equals(parsedECls.getName())).toArray(EClass[]::new);
			if (matchingActualECls.length != 1)
				throw new IllegalStateException("Unknown EClass has been parsed");

			var actualECls = matchingActualECls[0];
			parsedECls.setInstanceClassName(actualECls.getInstanceClassName());
			parsedECls.setInstanceTypeName(actualECls.getInstanceTypeName());
			parsedECls.setInstanceClass(actualECls.getInstanceClass());
		}
	}
}
