package cipm.consistency.fluentapi.gen.rootapi;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;

import cipm.consistency.fluentapi.gen.FluentAPIDocumentationUtil;

public final class FluentAPIRootAPIConstants {
	/*
	 * EPackage
	 */
	private static final URI fluentAPIRootAPIPackageURI = URI.createURI("http://www.cipmfluentapi.com/java");
	private static final String fluentAPIRootAPIPackageName = "cipm.consistency.fluentapi.api";

	/*
	 * EClass
	 */
	private static final String fluentAPIRootAPIClassName = "FluentEObjectAPI";

	/*
	 * EOperations
	 */
	/*
	 * continue
	 */
	private static final String fluentAPIRootAPIContinueMethodNameTemplate = "continue%s";
	private static final String fluentAPIRootAPIContinueMarkedMethodNameTemplate = "continueMarked%s";

	/*
	 * createNew
	 */
	private static final String fluentAPIRootAPICreateNewXMethodNameTemplate = "createNew%s";
	private static final String fluentAPIRootAPICreateNewXWithClassParameterMethodName = "createNewX";

	/*
	 * drop
	 */
	private static final String fluentAPIRootAPIDropInitialisationMethodName = "dropInitialisation";

	/*
	 * getAllSupportedClasses
	 */
	private static final String fluentAPIRootAPIGetAllSupportedClassesMethodName = "getAllSupportedClasses";

	/*
	 * getInitialisationFor
	 */
	private static final String fluentAPIRootAPIGetInitialisationForMethodName = "getInitialisationForX";

	/*
	 * mark
	 */
	private static final String fluentAPIRootAPIGetMarkedMethodName = "getMarked";
	private static final String fluentAPIRootAPIGetMarkedXMethodNameTemplate = "getMarked%s";

	/*
	 * unmark
	 */
	private static final String fluentAPIRootAPIUnmarkMethodName = "unmark";

	/*
	 * modify
	 */
	private static final String fluentAPIRootAPIModifyXMethodName = "modifyX";
	private static final String fluentAPIRootAPIModifyMethodNameTemplate = "modify%s";
	private static final String fluentAPIRootAPIModifyMarkedMethodNameTemplate = "modifyMarked%s";

	/*
	 * new
	 */
	private static final String fluentAPIRootAPINewXMethodName = "newX";
	private static final String fluentAPIRootAPINewMethodNameTemplate = "new%s";

	/*
	 * onceExists
	 */
	private static final String fluentAPIRootAPIOnceExistsMethodName = "onceExists";
	private static final String fluentAPIRootAPIOnceExistsMethodSummary = "Suspends certain model construction steps till certain markKey(s) exist.";
	private static final String fluentAPIRootAPIOnceExistsMethodDocumentation = FluentAPIDocumentationUtil
			.appendSummaryToStart(fluentAPIRootAPIOnceExistsMethodSummary)
			+ "Allows specifying model construction steps as a Runnable instance R, which this API will execute after using the given markKey(s) to mark objects. This method enables preserving the flow of model construction by enabling the specification of construction steps on objects that may not yet exist. The main purpose of this method is to facilitate model constructions, where dependencies between model elements either forcefully require bottom-up approaches or require mixing the construction of several model elements."
			+ FluentAPIDocumentationUtil.getDocParagraphSeparator()
			+ "Note: markKey(s) are NOT shared across all API instances. Therefore, different API instances have access to different markKey(s).";

	/*
	 * with
	 */
	private static final String fluentAPIRootAPIXWithFeatMethodName = "xWithFeat";
	private static final String fluentAPIRootAPIXWithoutFeatMethodName = "xWithoutFeat";
	private static final String fluentAPIRootAPIXWithAddedFeatMethodName = "xWithAddedFeat";
	private static final String fluentAPIRootAPIXWithRemovedFeatMethodName = "xWithRemovedFeat";
	private static final String fluentAPIRootAPIXWithExactFeatMethodName = "xWithExactFeat";
	private static final String fluentAPIRootAPIXWithFeatOfContainerMethodName = "xWithFeatOfContainer";
	private static final String fluentAPIRootAPIXWithFeatMethodSummary = "Sets the given value of a certain single-valued EStructuralFeature for a certain EObject.";
	private static final String fluentAPIRootAPIXWithoutFeatMethodSummary = "Unsets the value of a certain single-valued EStructuralFeature for a certain EObject.";
	private static final String fluentAPIRootAPIXWithAddedFeatMethodSummary = "Adds the given value(s) to a certain many-valued EStructuralFeature for a certain EObject.";
	private static final String fluentAPIRootAPIXWithRemovedFeatMethodSummary = "Removes the given value(s) from a certain many-valued EStructuralFeature for a certain EObject.";
	private static final String fluentAPIRootAPIXWithExactFeatMethodSummary = "Sets the given value(s) as the value of a certain many-valued EStructuralFeature for a certain EObject. Replaces all existing values of that EStructuralFeature.";
	private static final String fluentAPIRootAPIXWithFeatOfContainerMethodSummary = "Sets the value of a certain single-valued EStructuralFeature F for a certain EObject EO to the value of F in EO.eContainer().";

	/*
	 * EParameter
	 */
	private static final String fluentAPIRootAPICreateNewXWithClassParameterTypeParameterName = "T";
	private static final String fluentAPIRootAPICreateNewXWithClassParameterMethodParameterName = "eObjCls";

	private static final String fluentAPIRootAPIDropInitialisationParameterName = "initToDrop";

	private static final String fluentAPIRootAPIGetInitialisationForEClassParameterName = "eClsToInit";
	private static final String fluentAPIRootAPIGetInitialisationForClassParameterName = "clsToInit";
	private static final String fluentAPIRootAPIGetInitialisationForEObjectParameterName = "eobjToInit";

	private static final String fluentAPIRootAPIModifyMethodEObjectParameterName = "eobjToModify";

	private static final String fluentAPIRootAPINewMethodEClassParameterName = "eObjEClass";
	private static final String fluentAPIRootAPINewMethodClassParameterName = "eObjCls";
	private static final String fluentAPIRootAPINewMethodFeatureValueParameterName = "featVal";


	private static String getElementToInitialiseName(EClass elemToInitECls) {
		return StringUtils.capitalize(elemToInitECls.getName());
	}

	public static String getFluentAPIRootAPIXWithFeatMethodSummary() {
		return fluentAPIRootAPIXWithFeatMethodSummary;
	}

	public static String getFluentAPIRootAPIXWithoutFeatMethodSummary() {
		return fluentAPIRootAPIXWithoutFeatMethodSummary;
	}

	public static String getFluentAPIRootAPIXWithAddedFeatMethodSummary() {
		return fluentAPIRootAPIXWithAddedFeatMethodSummary;
	}

	public static String getFluentAPIRootAPIXWithRemovedFeatMethodSummary() {
		return fluentAPIRootAPIXWithRemovedFeatMethodSummary;
	}

	public static String getFluentAPIRootAPIXWithExactFeatMethodSummary() {
		return fluentAPIRootAPIXWithExactFeatMethodSummary;
	}

	public static String getFluentAPIRootAPIXWithFeatOfContainerMethodSummary() {
		return fluentAPIRootAPIXWithFeatOfContainerMethodSummary;
	}

	public static final String getFluentAPIRootAPIOnceExistsMethodSummary() {
		return fluentAPIRootAPIOnceExistsMethodSummary;
	}

	public static final String getFluentAPIRootAPIOnceExistsMethodDocumentation() {
		return fluentAPIRootAPIOnceExistsMethodDocumentation;
	}

	public static String getFluentAPIRootAPIXWithFeatMethodName() {
		return fluentAPIRootAPIXWithFeatMethodName;
	}

	public static String getFluentAPIRootAPIXWithoutFeatMethodName() {
		return fluentAPIRootAPIXWithoutFeatMethodName;
	}

	public static String getFluentAPIRootAPIXWithAddedFeatMethodName() {
		return fluentAPIRootAPIXWithAddedFeatMethodName;
	}

	public static String getFluentAPIRootAPIXWithRemovedFeatMethodName() {
		return fluentAPIRootAPIXWithRemovedFeatMethodName;
	}

	public static String getFluentAPIRootAPIXWithExactFeatMethodName() {
		return fluentAPIRootAPIXWithExactFeatMethodName;
	}

	public static String getFluentAPIRootAPIXWithFeatOfContainerMethodName() {
		return fluentAPIRootAPIXWithFeatOfContainerMethodName;
	}

	public static String getFluentAPIRootAPIOnceExistsMethodName() {
		return fluentAPIRootAPIOnceExistsMethodName;
	}

	public static String getFluentAPIRootAPINewXMethodName() {
		return fluentAPIRootAPINewXMethodName;
	}

	public static String getFluentAPIRootAPINewMethodNameTemplate() {
		return fluentAPIRootAPINewMethodNameTemplate;
	}

	public static String getFluentAPIRootAPINewMethodNameForType(EClass elemToInitECls) {
		return String.format(getFluentAPIRootAPINewMethodNameTemplate(), getElementToInitialiseName(elemToInitECls));
	}

	public static String getFluentAPIRootAPINewMethodFeatureValueParameterName() {
		return fluentAPIRootAPINewMethodFeatureValueParameterName;
	}

	public static String getFluentAPIRootAPINewMethodClassParameterName() {
		return fluentAPIRootAPINewMethodClassParameterName;
	}

	public static String getFluentAPIRootAPINewMethodEClassParameterName() {
		return fluentAPIRootAPINewMethodEClassParameterName;
	}

	public static String getFluentAPIRootAPIModifyXMethodName() {
		return fluentAPIRootAPIModifyXMethodName;
	}

	public static String getFluentAPIRootAPIModifyMethodNameTemplate() {
		return fluentAPIRootAPIModifyMethodNameTemplate;
	}

	public static String getFluentAPIRootAPIModifyMethodNameForType(EClass elemToInitECls) {
		return String.format(getFluentAPIRootAPIModifyMethodNameTemplate(), getElementToInitialiseName(elemToInitECls));
	}

	public static String getFluentAPIRootAPIModifyMarkedMethodNameTemplate() {
		return fluentAPIRootAPIModifyMarkedMethodNameTemplate;
	}

	public static String getFluentAPIRootAPIModifyMarkedMethodNameForType(EClass elemToInitECls) {
		return String.format(getFluentAPIRootAPIModifyMarkedMethodNameTemplate(),
				getElementToInitialiseName(elemToInitECls));
	}

	public static String getFluentAPIRootAPIModifyMethodEObjectParameterName() {
		return fluentAPIRootAPIModifyMethodEObjectParameterName;
	}

	public static String getFluentAPIRootAPIUnmarkMethodName() {
		return fluentAPIRootAPIUnmarkMethodName;
	}

	public static String getFluentAPIRootAPIGetMarkedMethodName() {
		return fluentAPIRootAPIGetMarkedMethodName;
	}

	public static String getFluentAPIRootAPIGetMarkedXMethodNameTemplate() {
		return fluentAPIRootAPIGetMarkedXMethodNameTemplate;
	}

	public static String getFluentAPIRootAPIGetMarkedXMethodNameForType(EClass elemToInitECls) {
		return String.format(getFluentAPIRootAPIGetMarkedXMethodNameTemplate(),
				getElementToInitialiseName(elemToInitECls));
	}

	public static String getFluentAPIRootAPIGetInitialisationForMethodName() {
		return fluentAPIRootAPIGetInitialisationForMethodName;
	}

	public static String getFluentAPIRootAPIGetInitialisationForEClassParameterName() {
		return fluentAPIRootAPIGetInitialisationForEClassParameterName;
	}

	public static String getFluentAPIRootAPIGetInitialisationForClassParameterName() {
		return fluentAPIRootAPIGetInitialisationForClassParameterName;
	}

	public static String getFluentAPIRootAPIGetInitialisationForEObjectParameterName() {
		return fluentAPIRootAPIGetInitialisationForEObjectParameterName;
	}

	public static String getFluentAPIRootAPIGetAllSupportedClassesMethodName() {
		return fluentAPIRootAPIGetAllSupportedClassesMethodName;
	}

	public static String getFluentAPIRootAPIDropInitialisationMethodName() {
		return fluentAPIRootAPIDropInitialisationMethodName;
	}

	public static String getFluentAPIRootAPIDropInitialisationParameterName() {
		return fluentAPIRootAPIDropInitialisationParameterName;
	}

	public static String getFluentAPIRootAPICreateNewXWithClassParameterMethodName() {
		return fluentAPIRootAPICreateNewXWithClassParameterMethodName;
	}

	public static String getFluentAPIRootAPICreateNewXWithClassParameterMethodTypeParameterName() {
		return fluentAPIRootAPICreateNewXWithClassParameterTypeParameterName;
	}

	public static String getFluentAPIRootAPICreateNewXWithClassParameterMethodParameterName() {
		return fluentAPIRootAPICreateNewXWithClassParameterMethodParameterName;
	}

	public static String getFluentAPIRootAPICreateNewXMethodNameTemplate() {
		return fluentAPIRootAPICreateNewXMethodNameTemplate;
	}

	public static String getFluentAPIRootAPICreateNewXMethodNameTemplateForType(EClass elemToInitECls) {
		return String.format(getFluentAPIRootAPICreateNewXMethodNameTemplate(),
				getElementToInitialiseName(elemToInitECls));
	}

	public static String getFluentAPIRootPackageName() {
		return fluentAPIRootAPIPackageName;
	}

	public static URI getFluentAPIRootPackageURI() {
		return fluentAPIRootAPIPackageURI;
	}

	public static String getFluentAPIRootAPIClassName() {
		return fluentAPIRootAPIClassName;
	}

	public static String getFluentAPIRootAPIContinueMethodNameTemplate() {
		return fluentAPIRootAPIContinueMethodNameTemplate;
	}

	public static String getFluentAPIRootAPIContinueMarkedMethodNameTemplate() {
		return fluentAPIRootAPIContinueMarkedMethodNameTemplate;
	}

	public static String getFluentAPIRootAPIContinueMethodNameForType(EClass elemToInitECls) {
		return String.format(getFluentAPIRootAPIContinueMethodNameTemplate(),
				getElementToInitialiseName(elemToInitECls));
	}

	public static String getFluentAPIRootAPIContinueMarkedMethodNameForType(EClass elemToInitECls) {
		return String.format(getFluentAPIRootAPIContinueMarkedMethodNameTemplate(),
				getElementToInitialiseName(elemToInitECls));
	}
}
