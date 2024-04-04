package cipm.consistency.commitintegration.diff.util.pcm;

/**
 * A SimiliarityChecker for PCM Repository models which includes the IDs of elements in the comparison.
 * 
 * @author Martin Armbruster
 */
public class PCMRepositoryIDBasedSimilarityChecker extends PCMRepositorySimilarityChecker {
	@Override
	public PCMRepositoryIDBasedSimilarityComparer getSimilarityComparer() {
		return (PCMRepositoryIDBasedSimilarityComparer) super.getSimilarityComparer();
	}
	
	@Override
	public PCMRepositoryIDBasedSimilarityComparer createSimilarityComparer() {
		return new PCMRepositoryIDBasedSimilarityComparer();
	}
}
