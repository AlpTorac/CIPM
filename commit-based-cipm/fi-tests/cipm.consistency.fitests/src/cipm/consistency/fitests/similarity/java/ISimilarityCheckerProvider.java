package cipm.consistency.fitests.similarity.java;

import org.splevo.jamopp.diffing.similarity.base.ISimilarityChecker;

public interface ISimilarityCheckerProvider {
	public ISimilarityChecker createSC();
}
