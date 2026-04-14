package cipm.consistency.fluentapi.gen.metamodels.java;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.emftext.language.java.JavaPackage;

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

	private static final List<EClass> allJavaEClasses = new ArrayList<EClass>();

	static {
		var javaList = EcoreUtil.getAllContents(JavaPackage.eINSTANCE.eResource(), true);
		javaList.forEachRemaining((c) -> {
			if (c instanceof EClass)
				allJavaEClasses.add((EClass) c);
		});
	}

//	private void fixParsedEcoreResource(List<EObject> ecoreObjs, List<EObject> javaObjs) {
//		if (ecoreObjs.size() == 0)
//			return;
//		var ecoreIt = ecoreObjs.iterator();
//		var javaIt = javaObjs.iterator();
//
//		while (ecoreIt.hasNext()) {
//			var javaObj = javaIt.next();
//			var ecoreObj = ecoreIt.next();
//
//			if (ecoreObj instanceof EClass) {
//				var ecoreCls = (EClass) ecoreObj;
//				var javaCls = (EClass) javaObj;
//
//				ecoreCls.setInstanceClass(javaCls.getInstanceClass());
//				ecoreCls.setInstanceClassName(javaCls.getInstanceClassName());
//				ecoreCls.setInstanceTypeName(javaCls.getInstanceTypeName());
//			}
//
//			fixParsedEcoreResource(ecoreObj.eContents(), javaObj.eContents());
//		}
//	}

	@Override
	public List<EPackage> getTargetMetamodelEcoreEPackages() {
		if (ecoreRes == null) {
			ecoreRes = metamodelResSet.getResource(javaMetamodelEcoreModelURI, true);

//			var javaRes = new ArrayList<EObject>();
//			javaRes.addAll(JavaPackage.eINSTANCE.eResource().getContents());
////			javaRes.addAll(LayoutPackage.eINSTANCE.eResource().getContents());
//
//			var ecoreList = EcoreUtil.getAllContents(ecoreRes, true);
//			var javaList = EcoreUtil.getAllContents(JavaPackage.eINSTANCE.eResource(), true);
//
//			var ecoreClss = new ArrayList<EClass>();
//			var javaClss = new ArrayList<EClass>();
//
//			ecoreList.forEachRemaining((c) -> {
//				if (c instanceof EClass)
//					ecoreClss.add((EClass) c);
//			});
//			javaList.forEachRemaining((c) -> {
//				if (c instanceof EClass)
//					javaClss.add((EClass) c);
//			});
//
////			var layoutClss = new ArrayList<EClass>();
//			for (var ecoreCls : ecoreClss) {
//				var javaCor = javaClss.stream()
//						.filter((c) -> c.getEPackage().getName().equals(ecoreCls.getEPackage().getName()))
//						.filter((c) -> c.getName().equals(ecoreCls.getName())).findFirst().orElse(null);
//				if (javaCor != null) {
//					ecoreCls.setInstanceClass(javaCor.getInstanceClass());
//					ecoreCls.setInstanceClassName(javaCor.getInstanceClassName());
//					ecoreCls.setInstanceTypeName(javaCor.getInstanceTypeName());
//				} else {
////					layoutClss.add(ecoreCls);
//				}
//			}
//
////			fixParsedEcoreResource(ecoreRes.getContents(), javaRes);
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
	public String getFullyQualifiedClassNameFor(EClass eCls) {
		var matchingEClss = List.of(
				allJavaEClasses.stream().filter((jc) -> jc.getName().equals(eCls.getName())).toArray(EClass[]::new));

		if (matchingEClss.size() == 1) {
			return matchingEClss.get(0).getInstanceClass().getName();
		} else {
			return null;
		}
	}

	@Override
	public String getSimpleClassNameFor(EClass eCls) {
		return StringUtils.capitalize(eCls.getName());
	}

	@Override
	public String getFullyQualifiedPackageNameFor(EClass eCls) {
		var matchingEClss = List.of(
				allJavaEClasses.stream().filter((jc) -> jc.getName().equals(eCls.getName())).toArray(EClass[]::new));

		if (matchingEClss.size() == 1) {
			var matchingCls = matchingEClss.get(0).getInstanceClass();
			return matchingCls.getPackageName() + "." + StringUtils.capitalize(eCls.getEPackage().getName())
					+ "Package";
		} else {
			return null;
		}
	}
}
