package cipm.consistency.fitests.similarity.java.unittests;

import org.emftext.language.java.expressions.ExplicitlyTypedLambdaParameters;
import org.emftext.language.java.parameters.Parameter;

import cipm.consistency.fitests.similarity.java.initialiser.expressions.ExplicitlyTypedLambdaParametersInitialiser;

public interface UsesLambdaParameters extends UsesParameters {
	public default ExplicitlyTypedLambdaParameters createETLP(Parameter[] params) {
		var init = new ExplicitlyTypedLambdaParametersInitialiser();
		
		var result = init.instantiate();
		init.minimalInitialisation(result);
		init.addParameters(result, params);
		
		return result;
	}
	
	public default ExplicitlyTypedLambdaParameters createMinimalETLP(String paramName, String targetName) {
		return this.createETLP(new Parameter[] {
				this.createMinimalParamWithClsTarget(paramName, targetName)
		});
	}
}
