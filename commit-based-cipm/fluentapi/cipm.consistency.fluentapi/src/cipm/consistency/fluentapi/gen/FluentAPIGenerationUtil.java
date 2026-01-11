package cipm.consistency.fluentapi.gen;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.Resource;

public class FluentAPIGenerationUtil {
	private static Resource ecoreRes;
	private static EPackage syntheticArrayTypePac;

	private static final String eoperationBodyKey = "body";
	private static final String eoperationDocumentationKey = "documentation";

	public static void setSyntheticArrayTypePackage(EPackage pac) {
		syntheticArrayTypePac = pac;
	}

	public static void setEcoreRes(Resource res) {
		ecoreRes = res;
	}

	public static String getEOperationBodyKey() {
		return eoperationBodyKey;
	}

	public static String getEOperationDocumentationKey() {
		return eoperationDocumentationKey;
	}

	public static boolean isConcrete(EClass elemToInit) {
		return !elemToInit.isAbstract() && !elemToInit.isInterface();
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

	public static EParameter generateSingleValuedEParameterWithDocumentation(String name, EClassifier type,
			String documentation) {
		var param = generateSingleValuedEParameter(name, type);
		var anno = EcoreFactory.eINSTANCE.createEAnnotation();
		anno.setSource(FluentAPIConstants.getGenModelURL());
		// Add the documentation
		anno.getDetails().put(getEOperationDocumentationKey(), documentation);
		param.getEAnnotations().add(anno);
		return param;
	}

	public static EParameter generateManyValuedEParameterWithDocumentation(String name, EClassifier type,
			String documentation) {
		var param = generateManyValuedEParameter(name, type);
		var anno = EcoreFactory.eINSTANCE.createEAnnotation();
		anno.setSource(FluentAPIConstants.getGenModelURL());
		// Add the documentation
		anno.getDetails().put(getEOperationDocumentationKey(), documentation);
		param.getEAnnotations().add(anno);
		return param;
	}

	public static EParameter generateArrayValuedEParameterWithDocumentation(String name, EClassifier type,
			String documentation) {

		var arrayEDataTypeName = type.getName() + "Array";
		var arrayTypeInstanceTypeName = type.getName() + "[]";
		EDataType arrayType = (EDataType) syntheticArrayTypePac.getEClassifier(arrayEDataTypeName);

		if (arrayType == null) {
			arrayType = EcoreFactory.eINSTANCE.createEDataType();
			arrayType.setSerializable(false);
			arrayType.setName(arrayEDataTypeName);
			arrayType.setInstanceTypeName(arrayTypeInstanceTypeName);
			arrayType.setInstanceClassName(arrayTypeInstanceTypeName);
			// Get array type this way, since cls.arrayType() is introduced in Java 12
			arrayType.setInstanceClass(Array.newInstance(type.getInstanceClass(), 0).getClass());
			syntheticArrayTypePac.getEClassifiers().add(arrayType);
		}

		var param = EcoreFactory.eINSTANCE.createEParameter();
		param.setName(name);
		param.setEType(arrayType);
		param.setLowerBound(1);
		param.setUpperBound(1);

		var anno = EcoreFactory.eINSTANCE.createEAnnotation();
		anno.setSource(FluentAPIConstants.getGenModelURL());
		// Add the documentation
		anno.getDetails().put(getEOperationDocumentationKey(), documentation);
		param.getEAnnotations().add(anno);
		return param;
	}

	public static EOperation generateEOperation(String name, EClassifier returnType, EAnnotation anno) {
		var op = EcoreFactory.eINSTANCE.createEOperation();
		op.setEType(returnType);
		op.setName(name);
		op.getEAnnotations().add(anno);
		return op;
	}

	public static EOperation generateEOperationWithBody(String name, EClassifier returnType, String methodBody) {
		var anno = EcoreFactory.eINSTANCE.createEAnnotation();
		anno.setSource(FluentAPIConstants.getGenModelURL());

		// Add the method body
		anno.getDetails().put(getEOperationBodyKey(), methodBody);

		return generateEOperation(name, returnType, anno);
	}

	public static EOperation generateEOperationWithBody(String name, EClassifier returnType, String methodBody,
			EParameter... params) {
		var op = generateEOperationWithBody(name, returnType, methodBody);
		if (params != null) {
			for (var param : params)
				op.getEParameters().add(param);
		}
		return op;
	}

	public static EOperation generateEOperationWithBodyAndDocumentation(String name, EClassifier returnType,
			String methodBody, String documentation) {
		var anno = EcoreFactory.eINSTANCE.createEAnnotation();
		anno.setSource(FluentAPIConstants.getGenModelURL());

		// Add the method body
		anno.getDetails().put(getEOperationBodyKey(), methodBody);
		// Add the documentation
		anno.getDetails().put(getEOperationDocumentationKey(), documentation);

		return generateEOperation(name, returnType, anno);
	}

	public static EOperation generateEOperationWithBodyAndDocumentation(String name, EClassifier returnType,
			String methodBody, String documentation, EParameter... params) {
		var op = generateEOperationWithBodyAndDocumentation(name, returnType, methodBody, documentation);
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
