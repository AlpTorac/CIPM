package cipm.consistency.fitests.similarity.eobject.java.params;

import cipm.consistency.initialisers.eobject.java.EObjectJavaInitialiserPackage;
import cipm.consistency.initialisers.IInitialiserPackage;
import cipm.consistency.fitests.similarity.params.IInitialiserParameterAdaptationStrategy;
import cipm.consistency.fitests.similarity.params.IInitialiserParameters;

/**
 * A class that provides central access to concrete initialiser instances.
 * 
 * @author atora
 */
public class EObjectJavaInitialiserParameters implements IInitialiserParameters {
	@Override
	public IInitialiserPackage getUsedInitialiserPackage() {
		return new EObjectJavaInitialiserPackage();
	}

	@Override
	public IInitialiserParameterAdaptationStrategy getAdaptationStrategy() {
		return new EObjectJavaInitialiserParameterAdaptationStrategy();
	}
}
