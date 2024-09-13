package cipm.consistency.fitests.similarity.java.eobject.params;

import java.util.Collection;

import cipm.consistency.fitests.similarity.initialiser.IInitialiser;
import cipm.consistency.fitests.similarity.java.eobject.initialiser.EObjectInitialiserPackage;
import cipm.consistency.fitests.similarity.java.params.IInitialiserParameterAdaptationStrategy;
import cipm.consistency.fitests.similarity.java.params.IInitialiserParameters;

/**
 * A class that provides central access to concrete initialiser instances.
 * 
 * @author atora
 */
public class EObjectInitialiserParameters implements IInitialiserParameters {
	public Collection<IInitialiser> getAllNonAdaptedInitialisers() {
		return new EObjectInitialiserPackage().getAllInitialiserInstances();
	}

	@Override
	public IInitialiserParameterAdaptationStrategy getAdaptationStrategy() {
		return new EObjectInitialiserParameterAdaptationStrategy();
	}
}
