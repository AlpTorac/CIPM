package cipm.consistency.fluentapi.gen;

import org.eclipse.emf.common.util.URI;

public final class FluentAPIRootAPIConstants {
	private static final String fluentAPIRootAPIClassName = "FluentEObjectAPI";
	private static final String fluentAPIRootAPIOngoingInitialisationsReferenceName = "ongoingInits";
	private static final String fluentAPIRootAPIInitialisationsReferenceName = "inits";
	private static final String fluentAPIRootPackageName = "cipm.consistency.fluentapi.api";
	private static final URI fluentAPIRootPackageURI = URI.createURI("http://www.cipmfluentapi.com/java");
	
	public static String getFluentAPIRootPackageName() {
		return fluentAPIRootPackageName;
	}

	public static URI getFluentAPIRootPackageURI() {
		return fluentAPIRootPackageURI;
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
}
