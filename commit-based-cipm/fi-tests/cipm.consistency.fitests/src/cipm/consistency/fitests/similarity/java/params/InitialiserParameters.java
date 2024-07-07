package cipm.consistency.fitests.similarity.java.params;

import java.util.Collection;

import cipm.consistency.fitests.similarity.java.initialiser.EObjectInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.InitialiserPackage;

/**
 * A class that provides central access to concrete initialiser instances. 
 * 
 * @author atora
 */
public class InitialiserParameters implements IInitialiserParameters {
	public Collection<EObjectInitialiser> getAllInitialisers() {
		return new InitialiserPackage().getAllInitialiserInstances();
	}
}
