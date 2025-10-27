package cipm.consistency.fluentapi.gen;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EcoreFactory;

public class FluentAPIGenerationUtil {
	private static final String eoperationBodyKey = "body";

	public static String getEOperationBodyKey() {
		return eoperationBodyKey;
	}

	public static boolean isConcrete(EClass elemToInit) {
		return !elemToInit.isAbstract() && !elemToInit.isInterface();
	}

	public static boolean hasModifiableFeatures(EClass eObjEClass) {
		return eObjEClass.getEAllStructuralFeatures().stream()
				.anyMatch((f) -> FluentAPITargetMetamodelFeatureFilter.isFeatureEligible(f));
	}

	public static boolean hasModifiableFeatures(EClass eObjEClass, FluentAPITargetMetamodelFeatureFilter filter) {
		return eObjEClass.getEAllStructuralFeatures().stream().anyMatch((f) -> filter.isFeatureEligible(eObjEClass, f));
	}

	public static EClass getEObjectEClass() {
		return EcoreFactory.eINSTANCE.createEObject().eClass();
	}

	public static EClass getEClassEClass() {
		return EcoreFactory.eINSTANCE.createEClass().eClass();
	}

	public static String getFullyQualifiedEClassName(EClass eCls) {
		String result = eCls.getName();
		var pac = eCls.getEPackage();
		while (pac != null) {
			result = pac.getName() + "." + result;
			pac = pac.getESuperPackage();
		}
		return result;
	}

	public static EParameter generateSingleValuedEParameter(String name, EClassifier type) {
		var param = EcoreFactory.eINSTANCE.createEParameter();
		param.setName(name);
		param.setEType(type);
		param.setLowerBound(1);
		param.setUpperBound(1);
		return param;
	}

	public static EParameter generateManyValuedEParameter(String name, EClassifier type) {
		var param = EcoreFactory.eINSTANCE.createEParameter();
		param.setName(name);
		param.setEType(type);
		param.setLowerBound(1);
		param.setUpperBound(EParameter.UNBOUNDED_MULTIPLICITY);
		return param;
	}

	public static EOperation generateEOperationWithBody(String name, String genSource, EClassifier returnType,
			String methodBody) {
		var op = EcoreFactory.eINSTANCE.createEOperation();
		op.setEType(returnType);
		op.setName(name);

		// Add the method body
		if (methodBody != null) {
			var anno = EcoreFactory.eINSTANCE.createEAnnotation();
			anno.setSource(genSource);
			anno.getDetails().put(getEOperationBodyKey(), methodBody);
			op.getEAnnotations().add(anno);
		}
		return op;
	}

	public static EOperation generateEOperationWithBody(String name, String genSource, EClassifier returnType,
			String methodBody, EParameter... params) {
		var op = generateEOperationWithBody(name, genSource, returnType, methodBody);
		if (params != null) {
			for (var param : params)
				op.getEParameters().add(param);
		}
		return op;
	}

	public static List<EPackage> generatePackages(URI currentURI, String fullPacName) {
		var pacs = new ArrayList<EPackage>();
		var nss = List.of(fullPacName.split("\\."));
		for (int i = 0; i < nss.size(); i++) {
			var pacName = nss.get(i);
			var pacNss = nss.subList(0, i);

			var pac = EcoreFactory.eINSTANCE.createEPackage();
			pac.setName(pacName);
			pac.setNsPrefix(pacName);

			var nsUri = currentURI.appendSegments(pacNss.toArray(String[]::new)).appendSegment(pacName);
			pac.setNsURI(nsUri.toString());
			pacs.add(pac);
		}

		for (int i = 1; i < pacs.size(); i++) {
			pacs.get(i - 1).getESubpackages().add(pacs.get(i));
		}

		return pacs;
	}

	public static EPackage generateSubPackage(EPackage parentPac, String subPackageName) {
		var pac = EcoreFactory.eINSTANCE.createEPackage();
		pac.setName(subPackageName);
		pac.setNsPrefix(subPackageName);

		var nsUri = URI.createURI(parentPac.getNsURI()).appendSegment(subPackageName);
		pac.setNsURI(nsUri.toString());
		parentPac.getESubpackages().add(pac);
		return pac;
	}
}
