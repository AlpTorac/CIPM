package cipm.consistency.fluentapi.gen;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;

public final class FluentAPIRootAPIConstants {
	/*
	 * EPackage
	 */
	private static final String fluentAPIRootAPIMetamodelSuffix = "API";
	// %s: Target metamodel name
	private static final String fluentAPIRootAPIPackageURITemplate = "http://www.cipmfluentapi.com/%s" + fluentAPIRootAPIMetamodelSuffix;
	// %s: Target metamodel name
	private static final String fluentAPIRootAPIPackageNameTemplate = "cipm.consistency.fluentapi.%s" + fluentAPIRootAPIMetamodelSuffix;

	/*
	 * EClass
	 */
	private static final String fluentAPIRootAPIClassName = "FluentEObjectAPI";

	/*
	 * EReferences
	 */
	private static final String fluentAPIRootAPIOngoingInitialisationsReferenceName = "ongoingInits";
	private static final String fluentAPIRootAPIInitialisationsReferenceName = "inits";

	/*
	 * EOperations
	 */
	/*
	 * continue
	 */
	private static final String fluentAPIRootAPIContinueMethodNameTemplate = "continue%s";
	private static final String fluentAPIRootAPIContinueFromStartMethodNameTemplate = "continue%sFromStart";
	private static final String fluentAPIRootAPIContinueFromEndMethodNameTemplate = "continue%sFromEnd";
	private static final String fluentAPIRootAPIContinueWithNewestMethodNameTemplate = "continueNewest%s";
	private static final String fluentAPIRootAPIContinueWithOldestMethodNameTemplate = "continueOldest%s";

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

	/*
	 * with
	 */
	private static final String fluentAPIRootAPIXWithFeatMethodName = "xWithFeat";
	private static final String fluentAPIRootAPIXWithoutFeatMethodName = "xWithoutFeat";
	private static final String fluentAPIRootAPIXWithAddedFeatMethodName = "xWithAddedFeat";
	private static final String fluentAPIRootAPIXWithRemovedFeatMethodName = "xWithRemovedFeat";
	private static final String fluentAPIRootAPIXWithExactFeatMethodName = "xWithExactFeat";
	private static final String fluentAPIRootAPIXWithFeatOfContainerMethodName = "xWithFeatOfContainer";

	/*
	 * EParameter
	 */
	private static final String fluentAPIRootAPIIndexFromStartParameterName = "idxFromStart";
	private static final String fluentAPIRootAPIIndexFromEndParameterName = "idxFromEnd";

	private static final String fluentAPIRootAPICreateNewXWithClassParameterTypeParameterName = "T";
	private static final String fluentAPIRootAPICreateNewXWithClassParameterMethodParameterName = "eObjCls";

	private static final String fluentAPIRootAPIDropInitialisationParameterName = "initToDrop";

	private static final String fluentAPIRootAPIGetInitialisationForEClassParameterName = "eClsToInit";
	private static final String fluentAPIRootAPIGetInitialisationForClassParameterName = "clsToInit";
	private static final String fluentAPIRootAPIGetInitialisationForEObjectParameterName = "eobjToInit";

	private static final String fluentAPIRootAPIMarkKeyParameterName = "markKey";
	private static final String fluentAPIRootAPIMarkKeyDocumentation = "The Object instance, whose memory address will serve as a key in mark-related operations. Note that the content of the given Object instance are fully irrelevant here, only its memory address matters.";

	private static final String fluentAPIRootAPIModifyMethodEObjectParameterName = "eobjToModify";

	private static final String fluentAPIRootAPINewMethodEClassParameterName = "eObjEClass";
	private static final String fluentAPIRootAPINewMethodClassParameterName = "eObjCls";
	private static final String fluentAPIRootAPINewMethodFeatureValueParameterName = "featVal";

	private static final String fluentAPIRootAPIOnceExistsMarkKeyListParameterName = "markKeyList";
	private static final String fluentAPIRootAPIOnceExistsRunnableParameterName = "toDoOnceExists";

	private static final String fluentAPIRootAPIWithMethodEObjectParameterName = "eobjToModify";
	private static final String fluentAPIRootAPIWithMethodFeatureParameterName = "featToModify";
	private static final String fluentAPIRootAPIWithMethodFeatureValueParameterName = "featVal";

	private static String getElementToInitialiseName(EClass elemToInitECls) {
		return StringUtils.capitalize(elemToInitECls.getName());
	}

	public static String getFluentAPIRootAPIMarkKeyDocumentation() {
		return fluentAPIRootAPIMarkKeyDocumentation;
	}

	public static String getFluentAPIRootAPIWithMethodEObjectParameterName() {
		return fluentAPIRootAPIWithMethodEObjectParameterName;
	}

	public static String getFluentAPIRootAPIWithMethodFeatureParameterName() {
		return fluentAPIRootAPIWithMethodFeatureParameterName;
	}

	public static String getFluentAPIRootAPIWithMethodFeatureValueParameterName() {
		return fluentAPIRootAPIWithMethodFeatureValueParameterName;
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

	public static String getFluentAPIRootAPIOnceExistsMarkKeyListParameterName() {
		return fluentAPIRootAPIOnceExistsMarkKeyListParameterName;
	}

	public static String getFluentAPIRootAPIOnceExistsRunnableParameterName() {
		return fluentAPIRootAPIOnceExistsRunnableParameterName;
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

	public static String getFluentAPIRootAPIMarkKeyParameterName() {
		return fluentAPIRootAPIMarkKeyParameterName;
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

	public static String getFluentAPIRootPackageNameTemplate() {
		return fluentAPIRootAPIPackageNameTemplate;
	}

	/**
	 * @param targetMetamodelName Name of the target metamodel without
	 *                            capitalisation
	 * 
	 * @return The name of the root package of the fluent api to be generated
	 */
	public static String getFluentAPIRootPackageName(String targetMetamodelName) {
		return String.format(getFluentAPIRootPackageNameTemplate(), targetMetamodelName);
	}

	public static String getFluentAPIRootPackageURITemplate() {
		return fluentAPIRootAPIPackageURITemplate;
	}

	/**
	 * @param targetMetamodelName Name of the target metamodel without
	 *                            capitalisation
	 * 
	 * @return The URI of the root package of the fluent api to be generated
	 */
	public static URI getFluentAPIRootPackageURI(String targetMetamodelName) {
		return URI.createURI(String.format(getFluentAPIRootPackageURITemplate(), targetMetamodelName));
	}

	public static String getFluentAPIRootAPIClassName() {
		return fluentAPIRootAPIClassName;
	}

	public static String getRootAPIInitialisationsReferenceName() {
		return fluentAPIRootAPIInitialisationsReferenceName;
	}

	public static String getRootAPIOngoingInitialisationsReferenceName() {
		return fluentAPIRootAPIOngoingInitialisationsReferenceName;
	}

	public static String getFluentAPIRootAPIContinueFromStartMethodIndexFromStartParameterName() {
		return fluentAPIRootAPIIndexFromStartParameterName;
	}

	public static String getFluentAPIRootAPIContinueFromStartMethodIndexFromEndParameterName() {
		return fluentAPIRootAPIIndexFromEndParameterName;
	}

	public static String getFluentAPIRootAPIContinueMethodNameTemplate() {
		return fluentAPIRootAPIContinueMethodNameTemplate;
	}

	public static String getFluentAPIRootAPIContinueMethodNameForType(EClass elemToInitECls) {
		return String.format(getFluentAPIRootAPIContinueMethodNameTemplate(),
				getElementToInitialiseName(elemToInitECls));
	}

	public static String getFluentAPIRootAPIContinueFromStartMethodNameTemplate() {
		return fluentAPIRootAPIContinueFromStartMethodNameTemplate;
	}

	public static String getFluentAPIRootAPIContinueFromStartMethodNameForType(EClass elemToInitECls) {
		return String.format(getFluentAPIRootAPIContinueFromStartMethodNameTemplate(),
				getElementToInitialiseName(elemToInitECls));
	}

	public static String getFluentAPIRootAPIContinueFromEndMethodNameTemplate() {
		return fluentAPIRootAPIContinueFromEndMethodNameTemplate;
	}

	public static String getFluentAPIRootAPIContinueFromEndMethodNameForType(EClass elemToInitECls) {
		return String.format(getFluentAPIRootAPIContinueFromEndMethodNameTemplate(),
				getElementToInitialiseName(elemToInitECls));
	}

	public static String getFluentAPIRootAPIContinueWithNewestMethodNameTemplate() {
		return fluentAPIRootAPIContinueWithNewestMethodNameTemplate;
	}

	public static String getFluentAPIRootAPIContinueWithNewestMethodNameForType(EClass elemToInitECls) {
		return String.format(getFluentAPIRootAPIContinueWithNewestMethodNameTemplate(),
				getElementToInitialiseName(elemToInitECls));
	}

	public static String getFluentAPIRootAPIContinueWithOldestMethodNameTemplate() {
		return fluentAPIRootAPIContinueWithOldestMethodNameTemplate;
	}

	public static String getFluentAPIRootAPIContinueWithOldestMethodNameForType(EClass elemToInitECls) {
		return String.format(getFluentAPIRootAPIContinueWithOldestMethodNameTemplate(),
				getElementToInitialiseName(elemToInitECls));
	}
}
