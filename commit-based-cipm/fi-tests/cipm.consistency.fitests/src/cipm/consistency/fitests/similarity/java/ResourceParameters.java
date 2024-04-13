package cipm.consistency.fitests.similarity.java;

public enum ResourceParameters {
	NAME("name"),
	ORIGIN("origin"),
	NAMESPACE("namespace"),
	
	// Package-specific
	SET_MODULE_AS_PACKAGE_CONTAINER("setModuleAsPackageContainer"),
	MODULE("module"),
	
	// Module-specific
	PACKAGES("packages"),
	OPEN("open")
	;

	private final String paramName;
	
	private ResourceParameters(String paramName) {
		this.paramName = paramName;
	}
	
	public String getParamName() {
		return this.paramName;
	}
}
