package cipm.consistency.fluentapi.gen;

import org.eclipse.emf.ecore.EClassifier;

public class FluentAPIMethodParameterOverload {
	private EClassifier parameterType;
	private String serialisedParameterPlugin;

	public FluentAPIMethodParameterOverload(EClassifier parameterType, String serialisedParameterPlugin) {
		this.parameterType = parameterType;
		this.serialisedParameterPlugin = serialisedParameterPlugin;
	}

	public EClassifier getParameterType() {
		return parameterType;
	}

	public String getSerialisedParameterPlugin() {
		return serialisedParameterPlugin;
	}

}
