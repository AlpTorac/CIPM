package cipm.consistency.fluentapi.gen;

import org.eclipse.emf.common.util.URI;

public class FluentAPIConstants {
	private static final String genModelURL = "http://www.eclipse.org/emf/2002/GenModel";
	
	private static final String fluentAPIInitialisationClassNameSuffix = "Initialisation";

	private static final String fluentAPIRootAPIClassName = "FluentEObjectAPI";
	private static final String fluentAPIRootAPIOngoingInitialisationsReferenceName = "ongoingInits";
	private static final String fluentAPIRootAPIInitialisationsReferenceName = "inits";

	private static final String fluentAPIRootPackageName = "cipm.consistency.fluentapi.api";
	private static final URI fluentAPIRootPackageURI = URI.createURI("http://www.cipmfluentapi.com/java");

	private static final String fluentAPIInitialisationsPackageName = "inits";
	private static final String fluentAPIInitialisationsPackageFullName = fluentAPIRootPackageName + "."
			+ fluentAPIInitialisationsPackageName;
	private static final URI fluentAPIInitialisationsPackageURI = fluentAPIRootPackageURI
			.appendSegment(FluentAPIConstants.getFluentAPIInitialisationsPackageName());

	private static final String fluentAPIArrayTypesPackageName = "arrayTypes";
	
	/**
	 * Named this way to make sure that the generated EClasses do not have the same
	 * name
	 */
	private static final String fluentAPISuperInitialisationClassName = "FluentAPISuperInitialisation";
	private static final String fluentAPISuperInitialisationRootAPIReferenceName = "rootAPI";
	private static final String fluentAPISuperInitialisationCurrentElementReferenceName = "currentElement";

	public static String getGenModelURL() {
		return genModelURL;
	}
	
	public static String getFluentAPIRootPackageName() {
		return fluentAPIRootPackageName;
	}

	public static URI getFluentAPIRootPackageURI() {
		return fluentAPIRootPackageURI;
	}

	public static String getFluentAPIInitialisationClassNameSuffix() {
		return fluentAPIInitialisationClassNameSuffix;
	}

	public static String getFluentAPIInitialisationsPackageName() {
		return fluentAPIInitialisationsPackageName;
	}
	
	public static String getFluentAPIArrayTypesPackageName() {
		return fluentAPIArrayTypesPackageName;
	}

	public static String getFluentAPIInitialisationsPackageFullName() {
		return fluentAPIInitialisationsPackageFullName;
	}

	public static URI getFluentAPIInitialisationsPackageURI() {
		return fluentAPIInitialisationsPackageURI;
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

	public static String getFluentAPISuperInitialisationRootAPIReferenceName() {
		return fluentAPISuperInitialisationRootAPIReferenceName;
	}

	public static String getFluentAPISuperInitialisationCurrentElementReferenceName() {
		return fluentAPISuperInitialisationCurrentElementReferenceName;
	}

	public static String getFluentAPISuperInitialisationClassName() {
		return fluentAPISuperInitialisationClassName;
	}
}
