package cipm.consistency.fluentapi.gen;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

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

	public void setTargetMetamodelPackageProvider(FluentAPITargetMetamodelPackageProvider targetMetamodelPackageProvider) {
		this.targetMetamodelPackageProvider = targetMetamodelPackageProvider;
	}

	public void setTargetMetamodelFeatureFilter(FluentAPITargetMetamodelFeatureFilter targetMetamodelFeatureFilter) {
		this.targetMetamodelFeatureFilter = targetMetamodelFeatureFilter;
	}
	
}
