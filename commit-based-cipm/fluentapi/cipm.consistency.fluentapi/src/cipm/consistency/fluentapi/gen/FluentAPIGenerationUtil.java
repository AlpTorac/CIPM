package cipm.consistency.fluentapi.gen;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.EcoreFactory;

public class FluentAPIGenerationUtil {
	private static EPackage placeholderEDataTypesPac;

	private static final String placeholderEDataTypeSuffix = "EDataTypePlaceholder";

	private static final String arrayEDataTypeNameSuffix = "Array";
	private static final String arrayTypeNameSuffix = "[]";

	private static final String eoperationBodyKey = "body";
	private static final String eoperationDocumentationKey = "documentation";

	private static final String packageNameSeparator = ".";
	private static final String packageNameSeparatorRegex = "\\.";

	public static void setPlaceholderEDataTypesPackage(EPackage pac) {
		placeholderEDataTypesPac = pac;
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

	public static String getFullyQualifiedEClassName(EClass eCls) {
		String result = eCls.getName();
		var pac = eCls.getEPackage();
		while (pac != null) {
			result = pac.getName() + packageNameSeparator + result;
			pac = pac.getESuperPackage();
		}
		return result;
	}

	public static EParameter generateSingleValuedEParameter(String name) {
		var param = EcoreFactory.eINSTANCE.createEParameter();
		param.setName(name);
		param.setLowerBound(1);
		param.setUpperBound(1);
		return param;
	}

	public static EParameter generateSingleValuedEParameter(String name, Class<?> type) {
		var param = EcoreFactory.eINSTANCE.createEParameter();
		param.setName(name);
		param.setEType(createOrGetEDataType(type));
		param.setLowerBound(1);
		param.setUpperBound(1);
		return param;
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

	public static <T extends EModelElement> T addDocumentation(T elem, String documentation) {
		var anno = EcoreFactory.eINSTANCE.createEAnnotation();
		anno.setSource(FluentAPIConstants.getGenModelURL());
		// Add the documentation
		anno.getDetails().put(getEOperationDocumentationKey(), documentation);
		elem.getEAnnotations().add(anno);
		return elem;
	}

	private static EDataType createOrGetEDataType(Class<?> type) {
		var eDataTypeName = type.getSimpleName() + placeholderEDataTypeSuffix;
		EDataType eDataType = (EDataType) placeholderEDataTypesPac.getEClassifier(eDataTypeName);

		if (eDataType == null) {
			eDataType = EcoreFactory.eINSTANCE.createEDataType();
			eDataType.setSerializable(false);
			eDataType.setName(eDataTypeName);
			eDataType.setInstanceTypeName(eDataTypeName);
			eDataType.setInstanceClassName(eDataTypeName);
			eDataType.setInstanceClass(type);
			placeholderEDataTypesPac.getEClassifiers().add(eDataType);
		}

		return eDataType;
	}

	private static EDataType createOrGetArrayEDataType(EClassifier type) {
		var arrayEDataTypeName = type.getName() + arrayEDataTypeNameSuffix;
		var arrayTypeInstanceTypeName = type.getName() + arrayTypeNameSuffix;
		EDataType arrayType = (EDataType) placeholderEDataTypesPac.getEClassifier(arrayEDataTypeName);

		if (arrayType == null) {
			arrayType = EcoreFactory.eINSTANCE.createEDataType();
			arrayType.setSerializable(false);
			arrayType.setName(arrayEDataTypeName);
			arrayType.setInstanceTypeName(arrayTypeInstanceTypeName);
			arrayType.setInstanceClassName(arrayTypeInstanceTypeName);
			// Get array type this way, since cls.arrayType() is introduced in Java 12
			arrayType.setInstanceClass(Array.newInstance(type.getInstanceClass(), 0).getClass());
			placeholderEDataTypesPac.getEClassifiers().add(arrayType);
		}

		return arrayType;
	}

	public static EParameter generateArrayValuedEParameter(String name, EClassifier type) {
		var arrayType = createOrGetArrayEDataType(type);
		var param = EcoreFactory.eINSTANCE.createEParameter();
		param.setName(name);
		param.setEType(arrayType);
		param.setLowerBound(1);
		param.setUpperBound(1);
		return param;
	}

	public static EOperation generateEOperation(String name) {
		var op = EcoreFactory.eINSTANCE.createEOperation();
		op.setName(name);
		return op;
	}

	public static EOperation generateEOperation(String name, EClassifier returnType) {
		var op = EcoreFactory.eINSTANCE.createEOperation();
		op.setEType(returnType);
		op.setName(name);
		return op;
	}

	public static ETypeParameter generateETypeParameter(String typeParameterName) {
		var typeParam = EcoreFactory.eINSTANCE.createETypeParameter();
		typeParam.setName(typeParameterName);
		return typeParam;
	}

	/**
	 * @param genericType "Type" in "Type<...>"
	 */
	public static EGenericType generateEGenericTypeWithClassifier(EClassifier genericType) {
		var genericParamTypeForJavaClass = EcoreFactory.eINSTANCE.createEGenericType();
		genericParamTypeForJavaClass.setEClassifier(genericType);
		return genericParamTypeForJavaClass;
	}

	/**
	 * @param genericType "Type" in "Type<...>"
	 */
	public static EGenericType generateEGenericTypeWithTypeParameter(ETypeParameter typeParameter) {
		var genericParamTypeForJavaClass = EcoreFactory.eINSTANCE.createEGenericType();
		genericParamTypeForJavaClass.setETypeParameter(typeParameter);
		return genericParamTypeForJavaClass;
	}

	public static EGenericType addTypeArgument(EGenericType genericType, EGenericType... typeArguments) {
		if (typeArguments != null) {
			for (var ta : typeArguments)
				genericType.getETypeArguments().add(ta);
		}
		return genericType;
	}

	public static <T extends EModelElement> T addBody(T elem, String body) {
		var anno = EcoreFactory.eINSTANCE.createEAnnotation();
		anno.setSource(FluentAPIConstants.getGenModelURL());
		// Add the body
		anno.getDetails().put(getEOperationBodyKey(), body);
		elem.getEAnnotations().add(anno);
		return elem;
	}

	public static EOperation addEParameters(EOperation op, EParameter... params) {
		if (params != null) {
			for (var param : params)
				op.getEParameters().add(param);
		}
		return op;
	}

	public static List<EPackage> generatePackages(URI currentURI, String fullPacName) {
		var pacs = new ArrayList<EPackage>();
		var nss = List.of(fullPacName.split(packageNameSeparatorRegex));
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
