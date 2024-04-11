package cipm.consistency.fitests.similarity.java;

import org.splevo.jamopp.diffing.similarity.JavaSimilarityChecker;
import org.splevo.jamopp.diffing.similarity.base.ISimilarityToolbox;

public class DummySimilarityChecker extends JavaSimilarityChecker {

	public DummySimilarityChecker(ISimilarityToolbox st) {
		super(st);
	}

	public InnerSwitchFactory getSwitchFactory() {
		return this.getSimilarityComparer().getSwitchFactory();
	}

	public void setSwitchFactory(InnerSwitchFactory switchFac) {
		this.getSimilarityComparer().setSwitchFactory(switchFac);
	}

	@Override
	protected DummySimilarityComparer getSimilarityComparer() {
		return (DummySimilarityComparer) super.getSimilarityComparer();
	}
	
	@Override
	protected DummySimilarityComparer createSimilarityComparer(ISimilarityToolbox st) {
		return new DummySimilarityComparer(st);
	}
}
