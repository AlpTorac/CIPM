package cipm.consistency.fluentapi.gen.metamodels.java;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.emftext.language.java.JavaPackage;

import cipm.consistency.fluentapi.gen.FluentAPITargetMetamodelPackageProvider;

public class FluentAPIJavaMetamodelPackageProvider extends FluentAPITargetMetamodelPackageProvider {
	private static final String javaMetamodelName = "java";
	private static final URI javaMetamodelGenModelURI = URI
			.createURI("platform:/plugin/org.emftext.language.java/metamodel/java.genmodel");
	private static final URI javaMetamodelEcoreModelURI = URI
			.createURI("platform:/plugin/org.emftext.language.java/metamodel/java.ecore");
//			URI.createURI("http://www.emftext.org/java/metamodel/java.genmodel");

	private void resolveProxy(GenPackage nPac) {
		var ecorePac = nPac.getEcorePackage();
		if (ecorePac.eIsProxy()) {
			var resolvedEcorePac = (EPackage) ((InternalEObject) ecorePac).eResolveProxy(((InternalEObject) ecorePac));
			nPac.setEcorePackage(resolvedEcorePac);
		}
	}

	private void g(GenPackage pac) {
		for (var nPac : pac.getNestedGenPackages()) {
			resolveProxy(nPac);
			g(nPac);
		}
	}

	@Override
	public List<EPackage> getTargetMetamodelEcoreEPackages() {
		var genModelResSet = new ResourceSetImpl();
		var ecoreModelRes = genModelResSet.getResource(javaMetamodelEcoreModelURI, true);

		return List.of((EPackage) ecoreModelRes.getContents().get(0));
	}

	@Override
	public List<GenModel> getTargetMetamodelGenModels() {
		var genModelResSet = new ResourceSetImpl();
		var ecoreModelRes = genModelResSet.getResource(javaMetamodelEcoreModelURI, true);
		var genModelRes = genModelResSet.getResource(javaMetamodelGenModelURI, true);
		var javaGenModel = (GenModel) genModelRes.getContents().get(0);
		javaGenModel.setCanGenerate(false);

		for (var genPac : javaGenModel.getGenPackages()) {
			resolveProxy(genPac);
			g(genPac);
		}

		return List.of(javaGenModel);
	}

	@Override
	public List<EPackage> getTargetMetamodelTopLevelPackages() {
		return List.of(JavaPackage.eINSTANCE);
	}

	@Override
	public String getTargetMetamodelName() {
		return javaMetamodelName;
	}
}
