package cipm.consistency.fluentapi.gen;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;

public class FluentAPIGenerationUtil {
	/**
	 * A map of EClass representations of primitive types to EClass representations
	 * of their wrapper type.
	 */
	private static final Map<EClassifier, EClassifier> primitiveEClsToWrapperEClsMap;

	static {
		primitiveEClsToWrapperEClsMap = new HashMap<>();
		primitiveEClsToWrapperEClsMap.put(EcorePackage.Literals.EINT, EcorePackage.Literals.EINTEGER_OBJECT);
		primitiveEClsToWrapperEClsMap.put(EcorePackage.Literals.ELONG, EcorePackage.Literals.ELONG_OBJECT);
		primitiveEClsToWrapperEClsMap.put(EcorePackage.Literals.EFLOAT, EcorePackage.Literals.EFLOAT_OBJECT);
		primitiveEClsToWrapperEClsMap.put(EcorePackage.Literals.EDOUBLE, EcorePackage.Literals.EDOUBLE_OBJECT);
		primitiveEClsToWrapperEClsMap.put(EcorePackage.Literals.EBOOLEAN, EcorePackage.Literals.EBOOLEAN_OBJECT);
		primitiveEClsToWrapperEClsMap.put(EcorePackage.Literals.EBYTE, EcorePackage.Literals.EBYTE_OBJECT);
		primitiveEClsToWrapperEClsMap.put(EcorePackage.Literals.ESHORT, EcorePackage.Literals.ESHORT_OBJECT);
		primitiveEClsToWrapperEClsMap.put(EcorePackage.Literals.ECHAR, EcorePackage.Literals.ECHARACTER_OBJECT);
	}

	// TODO Refactor these methods, extract potential constants

	/**
	 * Note: This method yields a best-effort result by looking at the EPackage
	 * structure of the given EClass. To this end, the assumption is that all
	 * (parent) packages of the class represented by the given EClass also have a
	 * corresponding EPackage and that the Java code structure of the generated
	 * class is reflected in its EMF model:
	 * <p>
	 * <p>
	 * Given EClass eCls representing the Java class ns1.ns2.ns3.Cls, if the EMF
	 * containment tree of eCls is not ns1 -> ns2 -> ns3 -> eCls (with nsI being
	 * EPackages) and {@code eCls.getInstanceClass() == null}, the returned fully
	 * qualified name will be incorrect.
	 * 
	 * @return The fully qualified name for the given EClass. If
	 *         {@code eCls.getInstanceClass() != null}, returns the name of the
	 *         contained instance class. Otherwise, returns the fully qualified name
	 *         based on the EPackage of the given EClass and super EPackages
	 *         thereof.
	 */
	public static String getFullyQualifiedEClassName(FluentAPIGenerationContext context, EClass eCls) {
		// Attempt to get the namespaces from the potentially underlying instance class
		if (eCls.getInstanceClass() != null)
			return eCls.getInstanceClass().getName();
		if (eCls.getInstanceClassName() != null)
			return eCls.getInstanceClassName();
		if (eCls.getInstanceTypeName() != null)
			return eCls.getInstanceTypeName();

		// Attempt to get the namespaces from super packages
		String result = eCls.getName();
		var pac = eCls.getEPackage();
		while (pac != null) {
			result = pac.getName() + "." + result;
			pac = pac.getESuperPackage();
		}

		// Append the base package name, if set
		if (context != null) {
			result = context.getBasePackageName() + "." + result;
		}
		return result;
	}

	/**
	 * A variant of
	 * {@link #getFullyQualifiedEClassName(FluentAPIGenerationContext, EClass)}
	 * without a context object. This variant is meant for EClasses that do not
	 * belong to the fluent API.
	 */
	public static String getFullyQualifiedEClassName(EClass eCls) {
		return getFullyQualifiedEClassName(null, eCls);
	}

	/**
	 * Use {@code collectionElementExtends == null} in order to generate a wildcard
	 * type argument.
	 */
	public static EGenericType generateEGenericTypeWithTypeArgument(FluentAPIGenerationContext context,
			Class<?> genericType, EGenericType colGenTypeArgument) {
		var pureGenType = context.createOrGetEDataType(genericType, 1);
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
		// Wrap the given EClassifier, if it is a primitive type
		var colExtendsType = primitiveEClsToWrapperEClsMap.getOrDefault(collectionElementExtends,
				collectionElementExtends);
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
		return generateSingleValuedEParameter(name, context.createOrGetEDataType(type));
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
		anno.getDetails().put(ModelConstants.GEN_MODEL_DOC_KEY.get(), documentation);
		if (!elem.getEAnnotations().contains(anno))
			elem.getEAnnotations().add(anno);
		return elem;
	}

	public static <T extends EModelElement> T useDocumentationOf(T elem, EModelElement docSource) {
		if (!docSource.getEAnnotations().isEmpty()) {
			var anno = docSource.getEAnnotations().get(0);
			if (anno.getDetails().containsKey(ModelConstants.GEN_MODEL_DOC_KEY.get())) {
				var doc = anno.getDetails().get(ModelConstants.GEN_MODEL_DOC_KEY.get());
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

	public static EParameter generateArrayValuedEParameter(FluentAPIGenerationContext context, String name,
			EClassifier type) {
		var arrayType = context.createOrGetArrayEDataType(type);
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
		var genModelSourceURL = ModelConstants.GEN_MODEL_SOURCE_URL.get();
		if (elem.getEAnnotation(genModelSourceURL) == null) {
			anno = EcoreFactory.eINSTANCE.createEAnnotation();
			anno.setSource(genModelSourceURL);
		} else {
			anno = elem.getEAnnotation(genModelSourceURL);
		}
		return anno;
	}

	public static <T extends EModelElement> T addBody(T elem, String body) {
		var anno = createOrGetEAnnotation(elem);
		// Add the body
		anno.getDetails().put(ModelConstants.GEN_MODEL_BODY_KEY.get(), body);
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
