package cipm.consistency.fluentapi.gen;

import org.eclipse.emf.common.util.URI;

public final class FluentAPIInitialisationConstants {
	/*
	 * EPackage
	 */
	private static final String fluentAPIInitialisationsPackageName = "inits";
	private static final String fluentAPIInitialisationsPackageFullName = FluentAPIRootAPIConstants
			.getFluentAPIRootPackageName() + "." + fluentAPIInitialisationsPackageName;
	private static final URI fluentAPIInitialisationsPackageURI = FluentAPIRootAPIConstants.getFluentAPIRootPackageURI()
			.appendSegment(fluentAPIInitialisationsPackageName);

	/*
	 * EClass
	 */
	private static final String fluentAPIInitialisationClassNameSuffix = "Initialisation";

	/*
	 * EOperation
	 */

	/*
	 * createNow
	 */
	private static final String fluentAPIInitialisationCreateNowMethodTypeParamName = "T";
	private static final String fluentAPIInitialisationCreateNowMethodParamName = "returnTypeCls";
	private static final String fluentAPIInitialisationCreateNowMethodName = "createNow";

	/*
	 * newElement
	 */
	private static final String fluentAPIInitialisationNewElementOperationName = FluentAPISuperInitialisationConstants
			.getFluentAPISuperInitialisationNewElementMethodName();

	/*
	 * onceExists
	 */
	private static final String fluentAPIInitialisationOnceExistsMethodNameTemplate = FluentAPIRootAPIConstants
			.getFluentAPIRootAPIOnceExistsMethodName();

	/*
	 * EParameter
	 */

	public static String getFluentAPIInitialisationNewElementOperationName() {
		return fluentAPIInitialisationNewElementOperationName;
	}

	public static String getFluentapiinitialisationcreatenowmethodtypeparamname() {
		return fluentAPIInitialisationCreateNowMethodTypeParamName;
	}

	public static String getFluentapiinitialisationcreatenowmethodparamname() {
		return fluentAPIInitialisationCreateNowMethodParamName;
	}

	public static String getFluentapiinitialisationcreatenowmethodname() {
		return fluentAPIInitialisationCreateNowMethodName;
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
