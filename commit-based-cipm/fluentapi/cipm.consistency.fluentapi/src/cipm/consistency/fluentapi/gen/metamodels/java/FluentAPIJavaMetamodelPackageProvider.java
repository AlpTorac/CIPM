package cipm.consistency.fluentapi.gen.metamodels.java;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.emftext.commons.layout.LayoutPackage;
import org.emftext.language.java.JavaPackage;

import cipm.consistency.fluentapi.gen.FluentAPITargetMetamodelPackageProvider;
import cipm.consistency.fluentapi.gen.metamodels.MetamodelUtil;

public class FluentAPIJavaMetamodelPackageProvider extends FluentAPITargetMetamodelPackageProvider {
	private static final URI javaMetamodelGenModelURI = URI
			.createURI("platform:/plugin/org.emftext.language.java/metamodel/java.genmodel");
	private static final URI javaMetamodelEcoreModelURI = URI
			.createURI("platform:/plugin/org.emftext.language.java/metamodel/java.ecore");

	private final ResourceSet metamodelResSet = new ResourceSetImpl();
	private Resource ecoreRes;
	private Resource genModelRes;
	private List<EClass> originalEClss;

	@Override
	public List<EPackage> getTargetMetamodelPackages() {
		var topPac = getTargetMetamodelEcoreEPackages().get(0);
		return MetamodelUtil.getAllSubPackages(topPac);
	}

	@Override
	public List<EClass> getAllTargetMetamodelConcreteEClasses() {
		var topPac = getTargetMetamodelEcoreEPackages().get(0);
		return List.copyOf(MetamodelUtil.getAllConcreteEClasses(topPac));
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

	private void cacheOriginalEClasses() {
		if (originalEClss == null) {
			originalEClss = new ArrayList<EClass>(MetamodelUtil.getAllEClasses(JavaPackage.eINSTANCE));
			originalEClss.addAll(MetamodelUtil.getAllEClasses(LayoutPackage.eINSTANCE));
		}
	}

	private void fixInstanceClasses(EPackage parsedJavaPac) {
		var parsedEClss = MetamodelUtil.getAllEClasses(parsedJavaPac);
		cacheOriginalEClasses();

		if (parsedEClss.size() != originalEClss.size())
			throw new IllegalStateException(
					"Parsed Java package and the actual Java package contain different amounts of EClasses");

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

	@Override
	public List<EPackage> getTargetMetamodelEcoreEPackages() {
		if (ecoreRes == null) {
			ecoreRes = metamodelResSet.getResource(javaMetamodelEcoreModelURI, true);
			var parsedJavaPac = (EPackage) ecoreRes.getContents().get(0);
			fixInstanceClasses(parsedJavaPac);
		}

		return List.of((EPackage) ecoreRes.getContents().get(0));
	}

	@Override
	public List<GenModel> getTargetMetamodelGenModels() {
		if (genModelRes == null) {
			genModelRes = metamodelResSet.getResource(javaMetamodelGenModelURI, true);
		}

		var javaGenModel = (GenModel) genModelRes.getContents().get(0);
		javaGenModel.setCanGenerate(false);

		return List.of(javaGenModel);
	}

	@Override
	public List<EPackage> getTargetMetamodelTopLevelPackages() {
		return this.getTargetMetamodelEcoreEPackages();
	}

	@Override
	public ResourceSet getTargetMetamodelResourceSet() {
		return metamodelResSet;
	}

	@Override
	public EClass getEClassInOriginalMetamodel(String eClsName, String... namespaces) {
		cacheOriginalEClasses();
		var matchingClss = originalEClss.stream().filter((eCls) -> eCls.getName().equals(eClsName))
				.toArray(EClass[]::new);
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

	@Override
	public List<EClass> getAllEClassedInOriginalMetamodel() {
		cacheOriginalEClasses();
		return originalEClss;
	}

	@Override
	public List<EClass> getAllConcreteEClassedInOriginalMetamodel() {
		cacheOriginalEClasses();
		return List.of(
				originalEClss.stream().filter((cls) -> !cls.isInterface() && !cls.isAbstract()).toArray(EClass[]::new));
	}
}
