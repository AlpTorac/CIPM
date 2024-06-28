package cipm.consistency.fitests.similarity.java;

import org.splevo.jamopp.diffing.similarity.base.ISimilarityChecker;

/**
 * An interface implemented by classes that can be used to create
 * {@link ISimilarityChecker} instances.
 * 
 * @author atora
 */
public interface ISimilarityCheckerProvider {
	public ISimilarityChecker createSC();
}
