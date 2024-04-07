package cipm.consistency.fitests.similarity;

import org.splevo.jamopp.diffing.similarity.SimilarityChecker;

public class DummySimilarityChecker extends SimilarityChecker {
	
    @Override
    public DummySimilarityComparer createSimilarityComparer() {
    	DummySimilarityComparer sc = new DummySimilarityComparer(
    				this.getClassifierNormalizations(),
    				this.getCompilationUnitNormalizations(),
    				this.getPackageNormalizations()
    			);
    	return sc;
    }

	@Override
	public DummySimilarityComparer getSimilarityComparer() {
		return (DummySimilarityComparer) super.getSimilarityComparer();
	}
}
