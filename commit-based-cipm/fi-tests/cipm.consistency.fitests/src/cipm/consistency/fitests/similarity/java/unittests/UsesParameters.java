package cipm.consistency.fitests.similarity.java.unittests;

import org.emftext.language.java.parameters.OrdinaryParameter;
import org.emftext.language.java.parameters.Parameter;
import org.emftext.language.java.types.TypeReference;

import cipm.consistency.fitests.similarity.java.initialiser.IParameterInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.parameters.OrdinaryParameterInitialiser;

public interface UsesParameters extends UsesTypeReferences {
	public default Parameter createMinimalParamWithClsTarget(String paramName, String targetName) {
		return this.createMinimalParameter(new OrdinaryParameterInitialiser(),
				paramName, this.createMinimalClsRef(targetName));
	}
	
	public default OrdinaryParameter createMinimalOrdParam(String paramName, String targetName) {
		return (OrdinaryParameter) this.createMinimalParameter(new OrdinaryParameterInitialiser(),
				paramName, this.createMinimalClsRef(targetName));
	}
	
	public default Parameter createMinimalParamWithClsTarget(IParameterInitialiser init,
			String paramName, String targetName) {
		return this.createMinimalParameter(init, paramName, this.createMinimalClsRef(targetName));
	}
	
	public default Parameter createMinimalParameter(IParameterInitialiser init, String paramName, TypeReference tref) {
		Parameter result = init.instantiate();
		init.initialiseName(result, paramName);
		init.setTypeReference(result, tref);
		return result;
	}
}
