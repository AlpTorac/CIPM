package cipm.consistency.commitintegration.diff.util.pcm;

import org.splevo.jamopp.diffing.similarity.ISimilaritySwitch;
import org.splevo.jamopp.diffing.similarity.SimilarityChecker;

/**
 * A SimilarityChecker for PCM repository models.
 * 
 * @author Martin Armbruster
 */
public class PCMRepositorySimilarityChecker extends SimilarityChecker {
	@Override
	protected ISimilaritySwitch makeSwitch(boolean defaultCheckStatementPositionFlag) {
		return new PCMRepositorySimilaritySwitch(this, defaultCheckStatementPositionFlag);
	}
}
