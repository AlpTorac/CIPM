package cipm.consistency.fluentapi.gen;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.EcoreFactory;

import cipm.consistency.fluentapi.metamodel.FluentAPITargetMetamodelFeatureFilter;
import cipm.consistency.fluentapi.metamodel.FluentAPITargetMetamodelPackageProvider;

public class FluentAPIGenerationContext {
	private FluentAPITargetMetamodelPackageProvider targetMetamodelPackageProvider;
	private FluentAPITargetMetamodelFeatureFilter targetMetamodelFeatureFilter;

	private EPackage placeholderEDataTypesPac;

	private EPackage rootPackage;
	private EPackage apiPackage;
	private EClass fluentAPIECls;

	private EClass initSuperECls;
	private EReference initSuperEClsApiReference;
	private EReference initSuperEClsCurrentElement;

	private EPackage initsPackage;
	private final Map<EClass, EClass> initEClss = new LinkedHashMap<>();

	public EPackage getRootPackage() {
		return rootPackage;
	}

	public void setRootPackage(EPackage rootPackage) {
		this.rootPackage = rootPackage;
	}

	public EPackage getApiPackage() {
		return apiPackage;
	}

	public void setApiPackage(EPackage rootPackage) {
		this.apiPackage = rootPackage;
	}

	public EClass getFluentAPIECls() {
		return fluentAPIECls;
	}

	public void setFluentAPIECls(EClass fluentAPIECls) {
		this.fluentAPIECls = fluentAPIECls;
	}

	public EClass getInitSuperECls() {
		return initSuperECls;
	}

	public void setInitSuperECls(EClass initSuperECls) {
		this.initSuperECls = initSuperECls;
	}

	public EPackage getInitsPackage() {
		return initsPackage;
	}

	public void setInitsPackage(EPackage initsPackage) {
		this.initsPackage = initsPackage;
	}

	public void addInitECls(EClass elemToInitECls, EClass initECls) {
		initEClss.put(elemToInitECls, initECls);
	}

	public EClass getInitEClsFor(EClass elemToInitECls) {
		return initEClss.get(elemToInitECls);
	}

	public EClass getElemToInitFor(EClass initECls) {
		return initEClss.entrySet().stream().filter((e) -> e.getValue().equals(initECls)).map((e) -> e.getKey())
				.findFirst().orElse(null);
	}

	public List<EClass> getAllInitEClss() {
		return List.copyOf(initEClss.values());
	}

	public FluentAPITargetMetamodelPackageProvider getTargetMetamodelPackageProvider() {
		return targetMetamodelPackageProvider;
	}

	public FluentAPITargetMetamodelFeatureFilter getTargetMetamodelFeatureFilter() {
		return targetMetamodelFeatureFilter;
	}

	public EPackage getPlaceholderEDataTypesPac() {
		return placeholderEDataTypesPac;
	}

	public void setPlaceholderEDataTypesPac(EPackage placeholderEDataTypesPac) {
		this.placeholderEDataTypesPac = placeholderEDataTypesPac;
	}

	public EReference getInitSuperEClsApiReference() {
		return initSuperEClsApiReference;
	}

	public void setInitSuperEClsApiReference(EReference initSuperEClsApiReference) {
		this.initSuperEClsApiReference = initSuperEClsApiReference;
	}

	public EReference getInitSuperEClsCurrentElement() {
		return initSuperEClsCurrentElement;
	}

	public void setInitSuperEClsCurrentElement(EReference initSuperEClsCurrentElement) {
		this.initSuperEClsCurrentElement = initSuperEClsCurrentElement;
	}

	public void setTargetMetamodelPackageProvider(
			FluentAPITargetMetamodelPackageProvider targetMetamodelPackageProvider) {
		this.targetMetamodelPackageProvider = targetMetamodelPackageProvider;
	}

	public void setTargetMetamodelFeatureFilter(FluentAPITargetMetamodelFeatureFilter targetMetamodelFeatureFilter) {
		this.targetMetamodelFeatureFilter = targetMetamodelFeatureFilter;
	}

	/**
	 * Adds the given amount of type parameters. Only works, if the given type does
	 * not already have a placeholder.
	 */
	public EDataType createOrGetEDataType(Class<?> type, int typeParamCount) {
		var list = new ArrayList<ETypeParameter>();
		for (int i = 0; i < typeParamCount; i++)
			list.add(FluentAPIGenerationUtil.generateETypeParameter("T" + i));
		return createOrGetEDataType(type, list.toArray(ETypeParameter[]::new));
	}

	public EDataType createOrGetEDataType(Class<?> type, ETypeParameter... typeParameters) {
		var eDataTypeName = type.getSimpleName() + ModelConstants.EDATATYPE_WRAPPER_NAME_SUFFIX.get();
		EDataType eDataType = (EDataType) getPlaceholderEDataTypesPac().getEClassifier(eDataTypeName);

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

			getPlaceholderEDataTypesPac().getEClassifiers().add(eDataType);
		}

		return eDataType;
	}

	public EDataType createOrGetEDataType(Class<?> type) {
		// Use an empty array to avoid StackOverflowErrors, since otherwise this method
		// will be called repeatedly
		return createOrGetEDataType(type, new ETypeParameter[] {});
	}

	public EDataType createOrGetArrayEDataType(EClassifier type) {
		var arrayEDataTypeName = type.getName() + ModelConstants.EDATATYPE_ARRAY_WRAPPER_NAME_SUFFIX.get();
		var arrayTypeInstanceTypeName = type.getName() + ModelConstants.EDATATYPE_ARRAY_WRAPPER_TYPE_NAME_SUFFIX.get();
		EDataType arrayType = (EDataType) getPlaceholderEDataTypesPac().getEClassifier(arrayEDataTypeName);

		if (arrayType == null) {
			arrayType = EcoreFactory.eINSTANCE.createEDataType();
			arrayType.setSerializable(false);
			arrayType.setName(arrayEDataTypeName);
			arrayType.setInstanceTypeName(arrayTypeInstanceTypeName);
			arrayType.setInstanceClassName(arrayTypeInstanceTypeName);
			// Get array type this way, since cls.arrayType() is introduced in Java 12
			arrayType.setInstanceClass(Array.newInstance(type.getInstanceClass(), 0).getClass());
			getPlaceholderEDataTypesPac().getEClassifiers().add(arrayType);
		}

		return arrayType;
	}
}
