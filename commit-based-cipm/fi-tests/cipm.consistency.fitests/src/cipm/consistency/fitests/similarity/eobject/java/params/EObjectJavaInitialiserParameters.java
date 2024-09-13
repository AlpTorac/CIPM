package cipm.consistency.fitests.similarity.eobject.java.params;

import java.util.Collection;

import cipm.consistency.fitests.similarity.eobject.initialiser.java.EObjectJavaInitialiserPackage;
import cipm.consistency.fitests.similarity.initialiser.IInitialiser;
import cipm.consistency.fitests.similarity.params.IInitialiserParameterAdaptationStrategy;
import cipm.consistency.fitests.similarity.params.IInitialiserParameters;

/**
 * A class that provides central access to concrete initialiser instances.
 * 
 * @author atora
 */
public class EObjectJavaInitialiserParameters implements IInitialiserParameters {
	@Override
	public Collection<IInitialiser> getAllNonAdaptedInitialisers() {
		return new EObjectJavaInitialiserPackage().getAllInitialiserInstances();
	}

	@Override
	public IInitialiserParameterAdaptationStrategy getAdaptationStrategy() {
		return new EObjectJavaInitialiserParameterAdaptationStrategy();
	}
}
