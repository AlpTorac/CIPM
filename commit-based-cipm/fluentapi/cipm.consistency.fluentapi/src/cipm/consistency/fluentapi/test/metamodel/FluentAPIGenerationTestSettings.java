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

	/**
	 * @see {@link #getElemEClsToInitEClsFunc()}
	 */
	public static void setElemEClsToInitEClsFunc(Function<EClass, EClass> func) {
		elemEClsToInitEClsFunc = func;
	}

	/**
	 * Takes a fluent api instance and derives its relevant attributes. Currently
	 * derives all EOperations in api and saves them in this class.
	 * 
	 * @param api The fluent api instance to be considered
	 */
	public static void setAPI(EObject api) {
		allAPIOps = List.copyOf(api.eClass().getEOperations());
	}

	/**
	 * @see {@link #getMetamodelFilter()}
	 */
	public static void setMetamodelFilter(FluentAPITargetMetamodelFilter filter) {
		metamodelFilter = filter;

		computeVariantFunctions();
		computeAllSupportedConcreteEClss();
	}

	/**
	 * @see {@link #getMetamodelProvider()}
	 */
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

	/**
	 * @return The object that filters the metamodel the fluent api was generated
	 *         for.
	 */
	public static FluentAPITargetMetamodelFilter getMetamodelFilter() {
		return metamodelFilter;
	}

	/**
	 * @return The object that provides access to the metamodel the fluent api was
	 *         generated for.
	 */
	public static FluentAPITargetMetamodelPackageProvider getMetamodelProvider() {
		return metamodelProvider;
	}

	/**
	 * @return A list of all EOperations that the fluent api instance has.
	 * @see {@link #setAPI(EObject)}
	 */
	public static List<EOperation> getAllAPIOps() {
		return allAPIOps;
	}

	/**
	 * @return A list of all concrete EClasses within the metamodel that the fluent
	 *         api instance was generated for.
	 * @see {@link #getMetamodelFilter()} for what EClasses and features are
	 *      supported
	 * @see {@link #getMetamodelProvider()} for the metamodel
	 */
	public static List<EClass> getAllSupportedConcreteEClss() {
		return allSupportedConcreteEClss;
	}

	/**
	 * @return A list of all concrete EClasses within the metamodel that the fluent
	 *         api was generated for, which have modifiable features.
	 * @see {@link #getMetamodelFilter()} for what EClasses and features are
	 *      supported
	 * @see {@link #getMetamodelProvider()} for the metamodel
	 */
	public static List<EClass> getAllSupportedConcreteEClssWithModifiableFeats() {
		return allSupportedConcreteEClssWithModifiableFeats;
	}

	/**
	 * @return A list of all concrete EClasses within the metamodel that the fluent
	 *         api was generated for, which have exactly one modifiable feature.
	 * @see {@link #getMetamodelFilter()} for what EClasses and features are
	 *      supported
	 * @see {@link #getMetamodelProvider()} for the metamodel
	 */
	public static List<EClass> getAllSupportedConcreteEClssWithOnlyOneModifiableFeat() {
		return allSupportedConcreteEClssWithOnlyOneModifiableFeat;
	}

	/**
	 * The returned map can be used to map EClasses to their corresponding
	 * initialisation EClass.
	 * 
	 * @return The mapping between the EClasses within the metamodel that the fluent
	 *         api was generated for and the initialisation EClasses within the
	 *         fluent api model.
	 */
	public static Function<EClass, EClass> getElemEClsToInitEClsFunc() {
		return elemEClsToInitEClsFunc;
	}

	/**
	 * The returned map can be used to determine, whether to expect overloading
	 * modification methods (with array or collection types) in corresponding
	 * initialisation classes for individual EClasses of the metamodel, which the
	 * fluent api was generated for.
	 * 
	 * @return A map that denotes for EClasses of the metamodel, which the fluent
	 *         api was generated for, whether any of their features should have
	 *         overloading modification methods (with array and collection types) in
	 *         their corresponding initialisation class.
	 */
	public static Function<EClass, Boolean> getMultiValFunc() {
		return multiValFunc;
	}

	/**
	 * The returned map can be used to determine, whether to expect overloading
	 * modification methods (with primitive types, such as int or long) in
	 * corresponding initialisation classes for individual EClasses of the
	 * metamodel, which the fluent api was generated for.
	 * 
	 * @return A map that denotes for EClasses of the metamodel, which the fluent
	 *         api was generated for, whether any of their features should have
	 *         overloading modification methods (with primitive types, such as int
	 *         or long) in their corresponding initialisation class.
	 */
	public static Function<EClass, Boolean> getBigNumberVariantsFunc() {
		return bigNumberVariantsFunc;
	}

	/**
	 * @return A list of all concrete EClasses within the metamodel that the fluent
	 *         api was generated for, which have no modifiable features.
	 * @see {@link #getMetamodelFilter()} for what EClasses and features are
	 *      supported
	 * @see {@link #getMetamodelProvider()} for the metamodel
	 */
	public static List<EClass> getAllSupportedConcreteEClssWithNoModifiableFeat() {
		return allSupportedConcreteEClssWithNoModifiableFeat;
	}

	/**
	 * Resets all attributes of this class.
	 */
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
