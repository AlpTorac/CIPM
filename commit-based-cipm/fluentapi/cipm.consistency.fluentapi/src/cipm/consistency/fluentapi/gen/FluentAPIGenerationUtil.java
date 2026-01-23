package cipm.consistency.fluentapi.gen;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EcoreFactory;

public class FluentAPIGenerationUtil {
	private static EPackage placeholderEDataTypesPac;

	private static final String doNotUseFromOutsideDocumentationNote = "This method is not intended for outside use, but is generated as public because of code generation limitations.";
	private static final String documentationParagraphSeparator = "<p><p>";

	private static final String classMethodOverviewIntroTemplate = "It is recommended to only use the methods presented below. In the following, replace 'X's with the concrete feature name:"
			+ getDocParagraphSeparator() + "<ul>%s</ul>";

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

	public static EParameter generateArrayValuedEParameterWithDocumentation(String name, EClassifier type,
			String documentation) {

		var arrayType = createOrGetArrayEDataType(type);

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

	public static String getDocParagraphSeparator() {
		return documentationParagraphSeparator;
	}

	public static String getDoNotUseFromOutsideDocNote() {
		return doNotUseFromOutsideDocumentationNote;
	}

	public static String getClassMethodOverviewIntroTemplate() {
		return classMethodOverviewIntroTemplate;
	}

	public static String getClassMethodOverviewIntro(Map<String, String> methodNameToSummaryMap) {
		return String.format(getClassMethodOverviewIntroTemplate(), serialiseSummaries(methodNameToSummaryMap));
	}

	public static String appendDoNotUseFromOutsideDocNoteAtEnd() {
		return FluentAPIGenerationUtil.getDocParagraphSeparator() + doNotUseFromOutsideDocumentationNote
				+ FluentAPIGenerationUtil.getDocParagraphSeparator();
	}

	public static String appendSummaryToStart(String summary) {
		return summary + FluentAPIGenerationUtil.getDocParagraphSeparator();
	}

	public static String serialiseSummaries(Map<String, String> methodNameToSummaryMap) {
		var sb = new StringBuilder();
		methodNameToSummaryMap
				.forEach((metName, summary) -> sb.append("<li><b>").append(metName).append("</b>: ").append(summary));
		return sb.toString();
	}
}
