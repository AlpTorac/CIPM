package cipm.consistency.commitintegration.diff.util.pcm;

import org.splevo.jamopp.diffing.similarity.AbstractSimilaritySwitch;
import org.splevo.jamopp.diffing.similarity.ISimilarityChecker;

import cipm.consistency.commitintegration.diff.util.pcm.switches.SimilarityRepositorySwitch;
import cipm.consistency.commitintegration.diff.util.pcm.switches.SimilaritySeffSwitch;

public class PCMRepositorySimilaritySwitch extends AbstractSimilaritySwitch {
	public PCMRepositorySimilaritySwitch(ISimilarityChecker sc) {
		super(sc);
	}

	@Override
	protected void initInnerSwitches() {
		this.addSwitch(new SimilarityRepositorySwitch(this));
		this.addSwitch(new SimilaritySeffSwitch(this));
	}
}