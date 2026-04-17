package cipm.consistency.fluentapi.gen.metamodels.java;

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
	private static final String javaMetamodelName = "java";
	private static final URI javaMetamodelGenModelURI = URI
			.createURI("platform:/plugin/org.emftext.language.java/metamodel/java.genmodel");
	private static final URI javaMetamodelEcoreModelURI = URI
			.createURI("platform:/plugin/org.emftext.language.java/metamodel/java.ecore");

	private final ResourceSet metamodelResSet = new ResourceSetImpl();
	private Resource ecoreRes;
	private Resource genModelRes;

	private void removeLayoutsPackage(EPackage parsedJavaPac) {
		parsedJavaPac.getESubpackages().remove(parsedJavaPac.getESubpackages().size() - 1);
	}

	private void fixInstanceClasses(EPackage parsedJavaPac) {
		var parsedEClss = MetamodelUtil.getAllEClasses(parsedJavaPac);
		var actualEClss = MetamodelUtil.getAllEClasses(JavaPackage.eINSTANCE);
		actualEClss.addAll(MetamodelUtil.getAllEClasses(LayoutPackage.eINSTANCE));

		if (parsedEClss.size() != actualEClss.size())
			throw new IllegalStateException(
					"Parsed Java package and the actual Java package contain different amounts of EClasses");

		for (var parsedECls : parsedEClss) {
			var matchingActualECls = actualEClss.stream()
					.filter((cls) -> cls.getEPackage().getName().equals(parsedECls.getEPackage().getName()))
					.filter((cls) -> cls.getName().equals(parsedECls.getName())).toArray(EClass[]::new);
			if (matchingActualECls.length != 1)
				throw new IllegalStateException("Unknown EClass has been parsed");

			var actualECls = matchingActualECls[0];
			parsedECls.setInstanceClassName(actualECls.getInstanceClassName());
			parsedECls.setInstanceTypeName(actualECls.getInstanceTypeName());
			parsedECls.setInstanceClass(actualECls.getInstanceClass());
		}

//		var parsedIt = parsedEClss.iterator();
//		var actualIt = actualEClss.iterator();
//
//		while (parsedIt.hasNext()) {
//			var parsedECls = parsedIt.next();
//			var actualECls = actualIt.next();
//
//			parsedECls.setInstanceClassName(actualECls.getInstanceClassName());
//			parsedECls.setInstanceTypeName(actualECls.getInstanceTypeName());
//			parsedECls.setInstanceClass(actualECls.getInstanceClass());
//		}
	}

	@Override
	public List<EPackage> getTargetMetamodelEcoreEPackages() {
		if (ecoreRes == null) {
			ecoreRes = metamodelResSet.getResource(javaMetamodelEcoreModelURI, true);
			var parsedJavaPac = (EPackage) ecoreRes.getContents().get(0);
//			removeLayoutsPackage(parsedJavaPac);
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
//		FIXME Decide if the LayoutPackage can be left out from here

//		return List.of(JavaPackage.eINSTANCE, LayoutPackage.eINSTANCE);
		return this.getTargetMetamodelEcoreEPackages();
	}

	@Override
	public String getTargetMetamodelName() {
		return javaMetamodelName;
	}

	@Override
	public ResourceSet getTargetMetamodelResourceSet() {
		return metamodelResSet;
	}
}
