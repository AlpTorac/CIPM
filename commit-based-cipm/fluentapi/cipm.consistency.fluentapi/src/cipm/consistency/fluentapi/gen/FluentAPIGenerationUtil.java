package cipm.consistency.fluentapi.gen;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;

public class FluentAPIGenerationUtil {
	private static final String placeholderEDataTypeSuffix = "EDataTypePlaceholder";

	private static final String arrayEDataTypeNameSuffix = "Array";
	private static final String arrayTypeNameSuffix = "[]";

	private static final String eoperationBodyKey = "body";
	private static final String eoperationDocumentationKey = "documentation";

	private static final String packageNameSeparator = ".";
	private static final String packageNameSeparatorRegex = "\\.";

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

	/**
	 * Use {@code collectionElementExtends == null} in order to generate a wildcard
	 * type argument.
	 */
	public static EGenericType generateEGenericTypeWithTypeArgument(FluentAPIGenerationContext context,
			Class<?> genericType, EGenericType colGenTypeArgument) {
		var pureGenType = FluentAPIGenerationUtil.createOrGetEDataType(context, genericType, 1);
		var genType = FluentAPIGenerationUtil.generateEGenericTypeWithClassifier(pureGenType);
		FluentAPIGenerationUtil.addTypeArgument(genType, colGenTypeArgument);
		return genType;
	}

	/**
	 * Use {@code collectionElementExtends == null} in order to generate a wildcard
	 * type argument.
	 */
	public static EGenericType generateCollectionTypeWithTypeArgument(FluentAPIGenerationContext context,
			EGenericType colGenTypeArgument) {
		return generateEGenericTypeWithTypeArgument(context, Collection.class, colGenTypeArgument);
	}

	/**
	 * Use {@code collectionElementExtends == null} in order to generate a wildcard
	 * type argument.
	 */
	public static EGenericType generateCollectionTypeParameter(FluentAPIGenerationContext context,
			EClassifier collectionElementExtends) {
		var colExtendsType = collectionElementExtends;
		if (colExtendsType.equals(EcorePackage.Literals.EINT))
			colExtendsType = EcorePackage.Literals.EINTEGER_OBJECT;
		if (colExtendsType.equals(EcorePackage.Literals.ELONG))
			colExtendsType = EcorePackage.Literals.ELONG_OBJECT;
		if (colExtendsType.equals(EcorePackage.Literals.EFLOAT))
			colExtendsType = EcorePackage.Literals.EFLOAT_OBJECT;
		if (colExtendsType.equals(EcorePackage.Literals.EDOUBLE))
			colExtendsType = EcorePackage.Literals.EDOUBLE_OBJECT;
		if (colExtendsType.equals(EcorePackage.Literals.EBOOLEAN))
			colExtendsType = EcorePackage.Literals.EBOOLEAN_OBJECT;
		if (colExtendsType.equals(EcorePackage.Literals.EBYTE))
			colExtendsType = EcorePackage.Literals.EBYTE_OBJECT;
		if (colExtendsType.equals(EcorePackage.Literals.ESHORT))
			colExtendsType = EcorePackage.Literals.ESHORT_OBJECT;
		if (colExtendsType.equals(EcorePackage.Literals.ECHAR))
			colExtendsType = EcorePackage.Literals.ECHARACTER_OBJECT;

		var colGenTypeArgument = colExtendsType != null
				? FluentAPIGenerationUtil.generateEGenericTypeWithBounds(null,
						FluentAPIGenerationUtil.generateEGenericTypeWithClassifier(colExtendsType))
				: FluentAPIGenerationUtil.generateWildcardTypeArgument();
		return generateCollectionTypeWithTypeArgument(context, colGenTypeArgument);
	}

	public static EParameter generateSingleValuedEParameter(String name) {
		var param = EcoreFactory.eINSTANCE.createEParameter();
		param.setName(name);
		param.setLowerBound(1);
		param.setUpperBound(1);
		return param;
	}

	public static EParameter generateSingleValuedEParameter(FluentAPIGenerationContext context, String name,
			Class<?> type) {
		return generateSingleValuedEParameter(name, createOrGetEDataType(context, type));
	}

	public static EParameter generateSingleValuedEParameter(String name, EClassifier type) {
		var param = generateSingleValuedEParameter(name);
		param.setEType(type);
		return param;
	}

	public static EParameter generateSingleValuedEParameter(String name, EGenericType type) {
		var param = generateSingleValuedEParameter(name);
		param.setEGenericType(type);
		return param;
	}

	public static <T extends EModelElement> T addDocumentation(T elem, String documentation) {
		var anno = createOrGetEAnnotation(elem);
		// Add the documentation
		anno.getDetails().put(getEOperationDocumentationKey(), documentation);
		if (!elem.getEAnnotations().contains(anno))
			elem.getEAnnotations().add(anno);
		return elem;
	}

	public static <T extends EModelElement> T useDocumentationOf(T elem, EModelElement docSource) {
		if (!docSource.getEAnnotations().isEmpty()) {
			var anno = docSource.getEAnnotations().get(0);
			if (anno.getDetails().containsKey(getEOperationDocumentationKey())) {
				var doc = anno.getDetails().get(getEOperationDocumentationKey());
				addDocumentation(elem, doc);
			}
		}
		return elem;
	}

	public static <T extends EOperation> T addTypeParameters(T elem, ETypeParameter... typeParams) {
		if (typeParams != null)
			for (var tp : typeParams)
				elem.getETypeParameters().add(tp);
		return elem;
	}

	/**
	 * Adds the given amount of type parameters. Only works, if the given type does
	 * not already have a placeholder.
	 */
	public static EDataType createOrGetEDataType(FluentAPIGenerationContext context, Class<?> type,
			int typeParamCount) {
		var list = new ArrayList<ETypeParameter>();
		for (int i = 0; i < typeParamCount; i++)
			list.add(generateETypeParameter("T" + i));
		return createOrGetEDataType(context, type, list.toArray(ETypeParameter[]::new));
	}

	public static EDataType createOrGetEDataType(FluentAPIGenerationContext context, Class<?> type,
			ETypeParameter... typeParameters) {
		var eDataTypeName = type.getSimpleName() + placeholderEDataTypeSuffix;
		EDataType eDataType = (EDataType) context.getPlaceholderEDataTypesPac().getEClassifier(eDataTypeName);

		if (eDataType == null) {
			eDataType = EcoreFactory.eINSTANCE.createEDataType();
			eDataType.setSerializable(false);
			eDataType.setName(eDataTypeName);
			eDataType.setInstanceTypeName(eDataTypeName);
			eDataType.setInstanceClassName(eDataTypeName);
			eDataType.setInstanceClass(type);

			if (typeParameters != null)
				for (var t : typeParameters)
					eDataType.getETypeParameters().add(t);

			context.getPlaceholderEDataTypesPac().getEClassifiers().add(eDataType);
		}

		return eDataType;
	}

	public static EDataType createOrGetEDataType(FluentAPIGenerationContext context, Class<?> type) {
		// Use an empty array to avoid StackOverflowErrors, since otherwise this method
		// will be called repeatedly
		return createOrGetEDataType(context, type, new ETypeParameter[] {});
	}

	public static EDataType createOrGetArrayEDataType(FluentAPIGenerationContext context, EClassifier type) {
		var arrayEDataTypeName = type.getName() + arrayEDataTypeNameSuffix;
		var arrayTypeInstanceTypeName = type.getName() + arrayTypeNameSuffix;
		EDataType arrayType = (EDataType) context.getPlaceholderEDataTypesPac().getEClassifier(arrayEDataTypeName);

		if (arrayType == null) {
			arrayType = EcoreFactory.eINSTANCE.createEDataType();
			arrayType.setSerializable(false);
			arrayType.setName(arrayEDataTypeName);
			arrayType.setInstanceTypeName(arrayTypeInstanceTypeName);
			arrayType.setInstanceClassName(arrayTypeInstanceTypeName);
			// Get array type this way, since cls.arrayType() is introduced in Java 12
			arrayType.setInstanceClass(Array.newInstance(type.getInstanceClass(), 0).getClass());
			context.getPlaceholderEDataTypesPac().getEClassifiers().add(arrayType);
		}

		return arrayType;
	}

	public static EParameter generateArrayValuedEParameter(FluentAPIGenerationContext context, String name,
			EClassifier type) {
		var arrayType = createOrGetArrayEDataType(context, type);
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

	public static EOperation generateEOperation(String name, EGenericType returnType) {
		var op = EcoreFactory.eINSTANCE.createEOperation();
		op.setEGenericType(returnType);
		op.setName(name);
		return op;
	}

	public static ETypeParameter generateETypeParameter(String typeParameterName) {
		var typeParam = EcoreFactory.eINSTANCE.createETypeParameter();
		typeParam.setName(typeParameterName);
		return typeParam;
	}

	public static EGenericType generateWildcardTypeArgument() {
		return generateEGenericTypeWithBounds(null, null);
	}

	/**
	 * Do not use with {@code T = eStructuralFeature.getEGenericType()}, as it will
	 * move the type of the feature into the generated EGenericType instance. Use a
	 * fresh EGenericType that uses T as its EClassifier instead.
	 * 
	 * <p>
	 * {@code lowerBound = upperBound = null} will result in wildcard "?"
	 * 
	 * @param lowerBound "X" in "? super X"
	 * @param upperBound "X" in "? extends X"
	 */
	public static EGenericType generateEGenericTypeWithBounds(EGenericType lowerBound, EGenericType upperBound) {
		var genericParamTypeForJavaClass = EcoreFactory.eINSTANCE.createEGenericType();
		genericParamTypeForJavaClass.setELowerBound(lowerBound);
		genericParamTypeForJavaClass.setEUpperBound(upperBound);
		return genericParamTypeForJavaClass;
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

	private static EAnnotation createOrGetEAnnotation(EModelElement elem) {
		EAnnotation anno = null;
		if (elem.getEAnnotation(FluentAPIConstants.getGenModelURL()) == null) {
			anno = EcoreFactory.eINSTANCE.createEAnnotation();
			anno.setSource(FluentAPIConstants.getGenModelURL());
		} else {
			anno = elem.getEAnnotation(FluentAPIConstants.getGenModelURL());
		}
		return anno;
	}

	public static <T extends EModelElement> T addBody(T elem, String body) {
		var anno = createOrGetEAnnotation(elem);
		// Add the body
		anno.getDetails().put(getEOperationBodyKey(), body);
		if (!elem.getEAnnotations().contains(anno))
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
