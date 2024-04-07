package cipm.consistency.fitests.similarity;

import org.splevo.jamopp.diffing.similarity.SimilarityChecker;

public class DummySimilarityChecker extends SimilarityChecker {
	
    @Override
    public DummySimilarityComparer createSimilarityComparer() {
    	// ToDo: Pass normalisation mechanisms
    	// ToDo: Replace LinkedHashMap with Map
    	DummySimilarityComparer sc = new DummySimilarityComparer();
    	return sc;
    }

	@Override
	public DummySimilarityComparer getSimilarityComparer() {
		return (DummySimilarityComparer) super.getSimilarityComparer();
	}
}
