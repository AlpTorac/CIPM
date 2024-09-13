package cipm.consistency.fitests.similarity.java.params;

import java.util.Collection;

import cipm.consistency.fitests.similarity.initialiser.IInitialiser;
import cipm.consistency.fitests.similarity.java.eobject.initialiser.EObjectInitialiserPackage;

/**
 * A class that provides central access to concrete initialiser instances.
 * 
 * @author atora
 */
public class InitialiserParameters implements IInitialiserParameters {
	public Collection<IInitialiser> getAllNonAdaptedInitialisers() {
		return new EObjectInitialiserPackage().getAllInitialiserInstances();
	}

	@Override
	public IInitialiserParameterAdaptationStrategy getAdaptationStrategy() {
		return new InitialiserParameterAdaptationStrategy();
	}
}
