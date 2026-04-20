package cipm.consistency.fluentapi.gen;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;

import cipm.consistency.fluentapi.gen.metamodels.MetamodelUtil;

public abstract class FluentAPITargetMetamodelPackageProvider {
	public abstract List<EPackage> getTargetMetamodelPackages();

	public abstract String getTargetMetamodelName();

	public abstract List<EClass> getAllTargetMetamodelEClasses();

	public abstract List<EClass> getAllTargetMetamodelConcreteEClasses();

	public EClass getEClass(String eClsName, String... namespaces) {
		var matchingClss = this.getAllTargetMetamodelEClasses().stream()
				.filter((eCls) -> eCls.getName().equals(eClsName)).toArray(EClass[]::new);
		if (matchingClss.length == 1) {
			return matchingClss[0];
		} else if (matchingClss.length == 0) {
			return null;
		} else if (namespaces == null) {
			return null;
		}

		var nss = String.join(".", namespaces);

		for (var cls : matchingClss) {
			var instanceCls = cls.getInstanceClass();
			var instanceClsName = cls.getInstanceClassName();
			var instanceClsType = cls.getInstanceTypeName();

			if (instanceCls != null && instanceCls.getPackageName().equals(nss))
				return cls;
			if (nss.equals(instanceClsName))
				return cls;
			if (nss.equals(instanceClsType))
				return cls;
		}

		return null;
	}

	public abstract EClass getEClassInOriginalMetamodel(String eClsName, String... namespaces);

	public abstract List<EClass> getAllEClassedInOriginalMetamodel();
	
	public abstract List<EClass> getAllConcreteEClassedInOriginalMetamodel();
	
	public abstract List<EPackage> getTargetMetamodelTopLevelPackages();

	public abstract List<GenModel> getTargetMetamodelGenModels();

	public List<EPackage> getAllTargetMetamodelPackages() {
		var list = new ArrayList<EPackage>();
		list.addAll(getTargetMetamodelTopLevelPackages());
		list.addAll(getAllTargetMetamodelSubPackages());
		return list;
	}

	public List<EPackage> getAllTargetMetamodelSubPackages() {
		return getTargetMetamodelTopLevelPackages().stream().map(MetamodelUtil::getAllSubPackages).flatMap(List::stream)
				.collect(Collectors.toList());
	}

	public abstract List<EPackage> getTargetMetamodelEcoreEPackages();

	public abstract ResourceSet getTargetMetamodelResourceSet();
}
