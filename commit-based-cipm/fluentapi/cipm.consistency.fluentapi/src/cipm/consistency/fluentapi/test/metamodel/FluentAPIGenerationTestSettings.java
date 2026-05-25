package cipm.consistency.fluentapi.test.metamodel;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EcorePackage;

import cipm.consistency.fluentapi.metamodel.FluentAPITargetMetamodelFilter;
import cipm.consistency.fluentapi.metamodel.FluentAPITargetMetamodelPackageProvider;

/**
 * A singleton class for encapsulating details on the fluent api model and those
 * of the metamodel the fluent api was generated for.
 * 
 * @author Alp Torac Genc
 */
public class FluentAPIGenerationTestSettings {
	private static FluentAPITargetMetamodelFilter metamodelFilter;
	private static FluentAPITargetMetamodelPackageProvider metamodelProvider;

	private static List<EOperation> allAPIOps;

	private static List<EClass> allSupportedConcreteEClss;

	private static List<EClass> allSupportedConcreteEClssWithModifiableFeats;
	private static List<EClass> allSupportedConcreteEClssWithOnlyOneModifiableFeat;
	private static List<EClass> allSupportedConcreteEClssWithNoModifiableFeat;

	private static Function<EClass, EClass> elemEClsToInitEClsFunc;

	private static Function<EClass, Boolean> multiValFunc;
	private static Function<EClass, Boolean> bigNumberVariantsFunc;

	public static void setElemEClsToInitEClsFunc(Function<EClass, EClass> func) {
		elemEClsToInitEClsFunc = func;
	}

	public static void setAPI(EObject api) {
		allAPIOps = List.copyOf(api.eClass().getEOperations());
	}

	public static void setFilter(FluentAPITargetMetamodelFilter filter) {
		metamodelFilter = filter;

		computeVariantFunctions();
		computeAllSupportedConcreteEClss();
	}

	public static void setPackageProvider(FluentAPITargetMetamodelPackageProvider provider) {
		metamodelProvider = provider;
		computeAllSupportedConcreteEClss();
	}

	private static void computeAllSupportedConcreteEClss() {
		if (metamodelProvider != null && metamodelFilter != null) {
			allSupportedConcreteEClss = metamodelProvider.getAllTargetMetamodelConcreteEClasses().stream()
					.filter((eCls) -> metamodelFilter.isEClassEligible(eCls)).collect(Collectors.toList());
		}
		if (allSupportedConcreteEClss != null && metamodelFilter != null) {
			allSupportedConcreteEClssWithModifiableFeats = allSupportedConcreteEClss.stream()
					.filter(metamodelFilter::hasModifiableFeatures).collect(Collectors.toList());
			allSupportedConcreteEClssWithOnlyOneModifiableFeat = allSupportedConcreteEClss.stream()
					.filter((eCls) -> metamodelFilter.getModifiableFeatureCount(eCls) == 1)
					.collect(Collectors.toList());
			allSupportedConcreteEClssWithNoModifiableFeat = allSupportedConcreteEClss.stream()
					.filter((eCls) -> !allSupportedConcreteEClssWithModifiableFeats.contains(eCls))
					.collect(Collectors.toList());
		}
	}

	private static void computeVariantFunctions() {
		if (metamodelFilter != null) {
			multiValFunc = (eCls) -> metamodelFilter.getModifiableFeatures(eCls).get(0).isMany();
			bigNumberVariantsFunc = (eCls) -> metamodelFilter.getModifiableFeatures(eCls).get(0).getEType()
					.equals(EcorePackage.Literals.EBIG_INTEGER)
					|| metamodelFilter.getModifiableFeatures(eCls).get(0).getEType()
							.equals(EcorePackage.Literals.EBIG_DECIMAL);
		}
	}

	public static FluentAPITargetMetamodelFilter getFilter() {
		return metamodelFilter;
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

	public static Function<EClass, EClass> getElemEClsToInitEClsFunc() {
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

	public static void clear() {
		allAPIOps = null;
		allSupportedConcreteEClss = null;
		allSupportedConcreteEClssWithModifiableFeats = null;
		allSupportedConcreteEClssWithNoModifiableFeat = null;
		allSupportedConcreteEClssWithOnlyOneModifiableFeat = null;
		bigNumberVariantsFunc = null;
		elemEClsToInitEClsFunc = null;
		metamodelFilter = null;
		metamodelProvider = null;
		multiValFunc = null;
	}
}
