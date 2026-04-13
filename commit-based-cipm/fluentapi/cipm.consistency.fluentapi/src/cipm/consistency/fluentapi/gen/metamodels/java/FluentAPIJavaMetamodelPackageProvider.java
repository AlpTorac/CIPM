package cipm.consistency.fluentapi.gen.metamodels.java;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import cipm.consistency.fluentapi.gen.FluentAPITargetMetamodelPackageProvider;

public class FluentAPIJavaMetamodelPackageProvider extends FluentAPITargetMetamodelPackageProvider {
	private static final String javaMetamodelName = "java";
	private static final URI javaMetamodelGenModelURI = URI
			.createURI("platform:/plugin/org.emftext.language.java/metamodel/java.genmodel");
	private static final URI javaMetamodelEcoreModelURI = URI
			.createURI("platform:/plugin/org.emftext.language.java/metamodel/java.ecore");

	private final ResourceSet metamodelResSet = new ResourceSetImpl();
	private Resource ecoreRes;
	private Resource genModelRes;

//	private void resolveProxy(GenPackage nPac) {
//		var ecorePac = nPac.getEcorePackage();
//		if (ecorePac.eIsProxy()) {
//			var resolvedEcorePac = (EPackage) ((InternalEObject) ecorePac).eResolveProxy(((InternalEObject) ecorePac));
//			nPac.setEcorePackage(resolvedEcorePac);
//		}
//	}
//
//	private void g(GenPackage pac) {
//		for (var nPac : pac.getNestedGenPackages()) {
//			resolveProxy(nPac);
//			g(nPac);
//		}
//	}

	@Override
	public List<EPackage> getTargetMetamodelEcoreEPackages() {
		if (ecoreRes == null) {
			ecoreRes = metamodelResSet.getResource(javaMetamodelEcoreModelURI, true);
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

//		for (var genPac : javaGenModel.getGenPackages()) {
//			resolveProxy(genPac);
//			g(genPac);
//		}

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
}
