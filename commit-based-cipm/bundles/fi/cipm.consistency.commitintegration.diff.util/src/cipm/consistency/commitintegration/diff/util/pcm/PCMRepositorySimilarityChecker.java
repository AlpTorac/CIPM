package cipm.consistency.commitintegration.diff.util.pcm;

import org.splevo.jamopp.diffing.similarity.ISimilarityChecker;

/**
 * A SimilarityChecker for PCM repository models.
 * 
 * @author Martin Armbruster
 */
public class PCMRepositorySimilarityChecker implements ISimilarityChecker {
	private PCMRepositorySimilarityComparer sc;
	
	public PCMRepositorySimilarityChecker() {
		this.sc = this.createSimilarityComparer();
	}

	@Override
	public PCMRepositorySimilarityComparer getSimilarityComparer() {
		return this.sc;
	}

	@Override
	public PCMRepositorySimilarityComparer createSimilarityComparer() {
		return new PCMRepositorySimilarityComparer();
	}
}
