package cipm.consistency.fitests.similarity.java;

import org.splevo.jamopp.diffing.similarity.JavaSimilarityComparer;
import org.splevo.jamopp.diffing.similarity.base.ISimilarityToolbox;

public class DummySimilarityComparer extends JavaSimilarityComparer {
	private InnerSwitchFactory switchFac;
	
	public DummySimilarityComparer(ISimilarityToolbox st) {
		super(st);
	}
	
	public InnerSwitchFactory getSwitchFactory() {
		return this.switchFac;
	}
	
	public void setSwitchFactory(InnerSwitchFactory switchFac) {
		this.switchFac = switchFac;
	}
}
