package cipm.consistency.fitests.similarity.base.dummy;

import coveragepac.ISimilarityToolbox;
import coveragepac.ISimilarityToolboxFactory;

/**
 * A minimal toolbox factory.
 * 
 * @author Alp Torac Genc
 */
public class DummySimilarityToolboxFactory implements ISimilarityToolboxFactory {
	@Override
	public ISimilarityToolbox createSimilarityToolbox() {
		return new DummySimilarityToolbox();
	}
}
