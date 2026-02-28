package cipm.consistency.fluentapi.gen.init;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EStructuralFeature;

import cipm.consistency.fluentapi.gen.ModelConstants;
import cipm.consistency.fluentapi.gen.rootapi.FluentAPIRootAPIConstants;
import cipm.consistency.fluentapi.gen.superinit.FluentAPISuperInitialisationConstants;

public final class FluentAPIInitialisationConstants {
	/*
	 * EPackage
	 */
	private static final String fluentAPIInitialisationsPackageName = "inits";
	private static final String fluentAPIInitialisationsPackageFullName = FluentAPIRootAPIConstants
			.getFluentAPIRootPackageName() + "." + fluentAPIInitialisationsPackageName;
	private static final URI fluentAPIInitialisationsPackageURI = URI.createURI(ModelConstants.ROOT_PACKAGE_URI.get())
			.appendSegment(fluentAPIInitialisationsPackageName);

	/*
	 * EClass
	 */
	private static final String fluentAPIInitialisationClassNameSuffix = "Initialisation";

	/*
	 * EOperation
	 */

//	/*
//	 * createNow
//	 */
//	private static final String fluentAPIInitialisationCreateNowMethodTypeParamName = "T";
//	private static final String fluentAPIInitialisationCreateNowMethodParamName = "returnTypeCls";
//	private static final String fluentAPIInitialisationCreateNowMethodName = "createNow";

	/*
	 * newElement
	 */
	private static final String fluentAPIInitialisationNewElementOperationName = FluentAPISuperInitialisationConstants
			.getFluentAPISuperInitialisationNewElementMethodName();

	/*
	 * onceExists
	 */
	private static final String fluentAPIInitialisationOnceExistsMethodName = FluentAPISuperInitialisationConstants
			.getFluentAPISuperInitialisationOnceExistsMethodName();

	/*
	 * with
	 */
	private static final String fluentAPIInitialisationWithXFeatNameTemplate = "with%s";
	private static final String fluentAPIInitialisationWithoutXFeatNameTemplate = "without%s";
	private static final String fluentAPIInitialisationWithAddedXFeatNameTemplate = "withAdded%s";
	private static final String fluentAPIInitialisationWithRemovedXFeatNameTemplate = "withRemoved%s";
	private static final String fluentAPIInitialisationCleanXFeatNameTemplate = "clean%s";

	/*
	 * EParameter
	 */
	private static final String fluentAPIInitialisationWithMethodNewFeatValParamName = "newFeatVal";
	private static final String fluentAPIInitialisationWithMethodAddedFeatValParamName = "featValToAdd";
	private static final String fluentAPIInitialisationWithMethodRemovedFeatValParamName = "featValToRemove";

	public static String getElementToInitialiseName(EStructuralFeature feat) {
		return StringUtils.capitalize(feat.getName());
	}

	public static String getFluentAPIInitialisationWithMethodRemovedFeatValParamName() {
		return fluentAPIInitialisationWithMethodRemovedFeatValParamName;
	}

	public static String getFluentAPIInitialisationWithMethodAddedFeatValParamName() {
		return fluentAPIInitialisationWithMethodAddedFeatValParamName;
	}

	public static String getFluentAPIInitialisationWithMethodNewFeatValParamName() {
		return fluentAPIInitialisationWithMethodNewFeatValParamName;
	}

	public static String getFluentAPIInitialisationWithXFeatNameTemplate() {
		return fluentAPIInitialisationWithXFeatNameTemplate;
	}

	public static String getFluentAPIInitialisationWithXFeatNameForType(EStructuralFeature feat) {
		return String.format(getFluentAPIInitialisationWithXFeatNameTemplate(), getElementToInitialiseName(feat));
	}

	public static String getFluentAPIInitialisationWithoutXFeatNameTemplate() {
		return fluentAPIInitialisationWithoutXFeatNameTemplate;
	}

	public static String getFluentAPIInitialisationWithoutXFeatNameForType(EStructuralFeature feat) {
		return String.format(getFluentAPIInitialisationWithoutXFeatNameTemplate(), getElementToInitialiseName(feat));
	}

	public static String getFluentAPIInitialisationWithAddedXFeatNameTemplate() {
		return fluentAPIInitialisationWithAddedXFeatNameTemplate;
	}

	public static String getFluentAPIInitialisationWithAddedXFeatNameForType(EStructuralFeature feat) {
		return String.format(getFluentAPIInitialisationWithAddedXFeatNameTemplate(), getElementToInitialiseName(feat));
	}

	public static String getFluentAPIInitialisationWithRemovedXFeatNameTemplate() {
		return fluentAPIInitialisationWithRemovedXFeatNameTemplate;
	}

	public static String getFluentAPIInitialisationWithRemovedXFeatNameForType(EStructuralFeature feat) {
		return String.format(getFluentAPIInitialisationWithRemovedXFeatNameTemplate(),
				getElementToInitialiseName(feat));
	}

	public static String getFluentAPIInitialisationCleanXFeatNameTemplate() {
		return fluentAPIInitialisationCleanXFeatNameTemplate;
	}

	public static String getFluentAPIInitialisationCleanXFeatNameForType(EStructuralFeature feat) {
		return String.format(getFluentAPIInitialisationCleanXFeatNameTemplate(), getElementToInitialiseName(feat));
	}

	public static String getFluentAPIInitialisationOnceExistsMethodNamee() {
		return fluentAPIInitialisationOnceExistsMethodName;
	}

	public static String getFluentAPIInitialisationNewElementOperationName() {
		return fluentAPIInitialisationNewElementOperationName;
	}

	public static String getFluentAPIInitialisationsPackageFullName() {
		return fluentAPIInitialisationsPackageFullName;
	}

	public static URI getFluentAPIInitialisationsPackageURI() {
		return fluentAPIInitialisationsPackageURI;
	}

	public static String getFluentAPIInitialisationClassNameSuffix() {
		return fluentAPIInitialisationClassNameSuffix;
	}

	public static String getFluentAPIInitialisationsPackageName() {
		return fluentAPIInitialisationsPackageName;
	}

}
