package cipm.consistency.fluentapi.gen;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
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
	private final List<FluentAPITargetMetamodelGenerationSettings> metamodelGenerationSettings = new ArrayList<>();

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

	public void setTargetMetamodelPackageProvider(
			FluentAPITargetMetamodelPackageProvider targetMetamodelPackageProvider) {
		this.targetMetamodelPackageProvider = targetMetamodelPackageProvider;
	}

	public void setTargetMetamodelFeatureFilter(FluentAPITargetMetamodelFeatureFilter targetMetamodelFeatureFilter) {
		this.targetMetamodelFeatureFilter = targetMetamodelFeatureFilter;
	}

	public List<FluentAPITargetMetamodelGenerationSettings> getAllMetamodelGenerationSettings() {
		return List.copyOf(metamodelGenerationSettings);
	}

	public void addMetamodelGenerationSettings(FluentAPITargetMetamodelGenerationSettings genSettings) {
		this.metamodelGenerationSettings.add(genSettings);
	}

	public void removeMetamodelGenerationSetttings(FluentAPITargetMetamodelGenerationSettings genSettings) {
		this.metamodelGenerationSettings.remove(genSettings);
	}

	public List<FluentAPIMethodParameterOverload> getAllMethodParameterOverloads(EClassifier eClassifier) {
		var overrides = getAllMetamodelGenerationSettings().stream()
				.map((gs) -> gs.getMetamodelSpecificParameterOverloads(eClassifier)).flatMap(List::stream)
				.collect(Collectors.toList());
		overrides.addAll(FluentAPITargetMetamodelGenerationSettings.getGlobalParameterOverloads(eClassifier));
		return overrides;
	}

	public <T extends EModelElement> T getModelElement(Class<T> modelElementType, String elemName) {
		return getModelElement(modelElementType, getRootPackage(), List.of(elemName));
	}

	public <T extends EModelElement> T getModelElement(Class<T> modelElementType, List<String> elemAndContainersName) {
		var outmostContainer = getModelElement(modelElementType, elemAndContainersName.get(0));
		if (elemAndContainersName.size() == 1) {
			return outmostContainer;
		}
		return getModelElement(modelElementType, outmostContainer,
				elemAndContainersName.subList(1, elemAndContainersName.size()));
	}

	public <T extends EModelElement> T getModelElement(Class<T> modelElementType, EModelElement container,
			String elemName) {
		return getModelElement(modelElementType, container, List.of(elemName));
	}

	public <T extends EModelElement> T getModelElement(Class<T> modelElementType, EModelElement container,
			List<String> elemAndContainersName) {
		var it = container.eAllContents();
		while (it.hasNext()) {
			var currentElem = it.next();
			if (currentElem instanceof ENamedElement) {
				var castedElem = (ENamedElement) currentElem;
				if (castedElem.getName().equals(elemAndContainersName.get(0))) {
					return getModelElement(modelElementType, castedElem,
							elemAndContainersName.subList(1, elemAndContainersName.size()));
				}
			}
		}
		return null;
	}
}
