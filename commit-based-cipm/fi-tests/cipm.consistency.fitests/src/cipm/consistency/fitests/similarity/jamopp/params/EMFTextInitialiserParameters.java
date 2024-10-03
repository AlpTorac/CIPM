package cipm.consistency.fitests.similarity.jamopp.params;

import cipm.consistency.initialisers.IInitialiserPackage;
import cipm.consistency.initialisers.jamopp.EMFtextInitialiserPackage;
import cipm.consistency.fitests.similarity.params.IInitialiserParameterAdaptationStrategy;
import cipm.consistency.fitests.similarity.params.IInitialiserParameters;

/**
 * A class that provides central access to concrete initialiser instances.
 * 
 * @author atora
 */
public class EMFTextInitialiserParameters implements IInitialiserParameters {
	@Override
	public IInitialiserPackage getUsedInitialiserPackage() {
		return new EMFtextInitialiserPackage();
	}

	@Override
	public IInitialiserParameterAdaptationStrategy getAdaptationStrategy() {
		return new EMFTextInitialiserParameterAdaptationStrategy();
	}
}
