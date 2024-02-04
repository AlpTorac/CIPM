package cipm.consistency.vsum.test;

import cipm.consistency.commitintegration.detection.ComponentDetectionStrategy;

public abstract class AbstractRepoSettings implements HasRepoSettings {
	@Override
	public AbstractRepoSettings getRepoSettings() {
		return this;
	}
	
	@Override
	public ComponentDetectionStrategy getComponentDetectionStrategy() {
		return null;
	}
}
