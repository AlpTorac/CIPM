package cipm.consistency.fitests.similarity.java.unittests.concepttests.dummy;

import org.splevo.jamopp.diffing.similarity.base.ISimilarityToolbox;
import org.splevo.jamopp.diffing.similarity.base.ISimilarityToolboxFactory;

public class DummySimilarityToolboxFactory implements ISimilarityToolboxFactory {
	@Override
	public ISimilarityToolbox createSimilarityToolbox() {
		return new DummySimilarityToolbox();
	}
}
