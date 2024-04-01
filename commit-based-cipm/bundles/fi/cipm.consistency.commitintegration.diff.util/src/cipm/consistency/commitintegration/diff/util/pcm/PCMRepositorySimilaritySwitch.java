package cipm.consistency.commitintegration.diff.util.pcm;

import org.splevo.jamopp.diffing.similarity.AbstractSimilaritySwitch;
import org.splevo.jamopp.diffing.similarity.ISimilarityChecker;
import org.splevo.jamopp.diffing.similarity.ISimilaritySwitch;

import cipm.consistency.commitintegration.diff.util.pcm.switches.SimilarityRepositorySwitch;
import cipm.consistency.commitintegration.diff.util.pcm.switches.SimilaritySeffSwitch;

public class PCMRepositorySimilaritySwitch extends AbstractSimilaritySwitch {
	public PCMRepositorySimilaritySwitch(ISimilarityChecker sc, boolean defaultCheckStatementPositionFlag) {
		super(sc, defaultCheckStatementPositionFlag);
		
		this.addSwitch(new SimilarityRepositorySwitch(this));
		this.addSwitch(new SimilaritySeffSwitch(this));
	}
    
	@Override
	protected ISimilaritySwitch makeSwitch(boolean checkStatementPosition) {
		return new PCMRepositorySimilaritySwitch(this.getSimilarityChecker(), checkStatementPosition);
	}
}