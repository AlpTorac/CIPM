package cipm.consistency.fluentapi.test.metamodel;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EcorePackage;

import cipm.consistency.fluentapi.gen.FluentAPITargetMetamodelFeatureFilter;
import cipm.consistency.fluentapi.gen.FluentAPITargetMetamodelPackageProvider;

public class FluentAPIGenerationTestUtil {
	private static FluentAPITargetMetamodelFeatureFilter featureFilter;
	private static FluentAPITargetMetamodelPackageProvider metamodelProvider;

	private static List<EOperation> allAPIOps;

	private static List<EClass> allSupportedConcreteEClss;

	private static List<EClass> allSupportedConcreteEClssWithModifiableFeats;
	private static List<EClass> allSupportedConcreteEClssWithOnlyOneModifiableFeat;
	private static List<EClass> allSupportedConcreteEClssWithNoModifiableFeat;

	private static Function<EClass, EClassifier> elemEClsToInitEClsFunc;

	private static Function<EClass, Boolean> multiValFunc;
	private static Function<EClass, Boolean> bigNumberVariantsFunc;

	public static void setElemEClsToInitEClsFunc(Function<EClass, EClassifier> func) {
		elemEClsToInitEClsFunc = func;
	}

	public static void setAPI(EObject api) {
		allAPIOps = List.copyOf(api.eClass().getEOperations());
	}

	public static void setFeatureFilter(FluentAPITargetMetamodelFeatureFilter filter) {
		featureFilter = filter;

		computeVariantFunctions();
		computeAllSupportedConcreteEClss();
	}

	public static void setPackageProvider(FluentAPITargetMetamodelPackageProvider provider) {
		metamodelProvider = provider;
		computeAllSupportedConcreteEClss();
	}

	private static void computeAllSupportedConcreteEClss() {
		if (metamodelProvider != null) {
			allSupportedConcreteEClss = metamodelProvider.getAllTargetMetamodelConcreteEClasses();
		}
		if (allSupportedConcreteEClss != null && featureFilter != null) {
			allSupportedConcreteEClssWithModifiableFeats = allSupportedConcreteEClss.stream()
					.filter(featureFilter::hasModifiableFeatures).collect(Collectors.toList());
			allSupportedConcreteEClssWithOnlyOneModifiableFeat = allSupportedConcreteEClss.stream()
					.filter((eCls) -> featureFilter.getModifiableFeatureCount(eCls) == 1).collect(Collectors.toList());
			allSupportedConcreteEClssWithNoModifiableFeat = allSupportedConcreteEClss.stream()
					.filter((eCls) -> !allSupportedConcreteEClssWithModifiableFeats.contains(eCls))
					.collect(Collectors.toList());
		}
	}

	private static void computeVariantFunctions() {
		if (featureFilter != null) {
			multiValFunc = (eCls) -> featureFilter.getModifiableFeatures(eCls).get(0).isMany();
			bigNumberVariantsFunc = (eCls) -> featureFilter.getModifiableFeatures(eCls).get(0).getEType()
					.equals(EcorePackage.Literals.EBIG_INTEGER)
					|| featureFilter.getModifiableFeatures(eCls).get(0).getEType()
							.equals(EcorePackage.Literals.EBIG_DECIMAL);
		}
	}

	public static FluentAPITargetMetamodelFeatureFilter getFeatureFilter() {
		return featureFilter;
	}

	public static FluentAPITargetMetamodelPackageProvider getMetamodelProvider() {
		return metamodelProvider;
	}

	public static List<EOperation> getAllAPIOps() {
		return allAPIOps;
	}

	public static List<EClass> getAllSupportedConcreteEClss() {
		return allSupportedConcreteEClss;
	}

	public static List<EClass> getAllSupportedConcreteEClssWithModifiableFeats() {
		return allSupportedConcreteEClssWithModifiableFeats;
	}

	public static List<EClass> getAllSupportedConcreteEClssWithOnlyOneModifiableFeat() {
		return allSupportedConcreteEClssWithOnlyOneModifiableFeat;
	}

	public static Function<EClass, EClassifier> getElemEClsToInitEClsFunc() {
		return elemEClsToInitEClsFunc;
	}

	public static Function<EClass, Boolean> getMultiValFunc() {
		return multiValFunc;
	}

	public static Function<EClass, Boolean> getBigNumberVariantsFunc() {
		return bigNumberVariantsFunc;
	}

	public static List<EClass> getAllSupportedConcreteEClssWithNoModifiableFeat() {
		return allSupportedConcreteEClssWithNoModifiableFeat;
	}

}
