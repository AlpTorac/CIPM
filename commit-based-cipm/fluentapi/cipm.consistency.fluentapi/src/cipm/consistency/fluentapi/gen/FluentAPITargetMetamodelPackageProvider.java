package cipm.consistency.fluentapi.gen;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import cipm.consistency.fluentapi.gen.metamodels.MetamodelUtil;

public abstract class FluentAPITargetMetamodelPackageProvider {
	public abstract List<EPackage> getTargetMetamodelTopLevelPackages();

	public abstract String getTargetMetamodelName();

	public abstract List<GenModel> getTargetMetamodelGenModels();

	public List<EPackage> getAllTargetMetamodelPackages() {
		var list = new ArrayList<EPackage>();
		list.addAll(getTargetMetamodelTopLevelPackages());
		list.addAll(getAllTargetMetamodelSubPackages());
		return list;
	}

	public List<EClass> getAllTargetMetamodelConcreteEClasses() {
		return getTargetMetamodelTopLevelPackages().stream().map(MetamodelUtil::getAllConcreteEClasses)
				.flatMap(Collection::stream).collect(Collectors.toList());
	}

	public List<EPackage> getAllTargetMetamodelSubPackages() {
		return getTargetMetamodelTopLevelPackages().stream().map(MetamodelUtil::getAllSubPackages).flatMap(List::stream)
				.collect(Collectors.toList());
	}

	public abstract List<EPackage> getTargetMetamodelEcoreEPackages();
}
