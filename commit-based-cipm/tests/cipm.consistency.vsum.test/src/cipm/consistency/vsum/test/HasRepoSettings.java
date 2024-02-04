package cipm.consistency.vsum.test;

import cipm.consistency.commitintegration.detection.ComponentDetectionStrategy;
import tools.vitruv.framework.propagation.ChangePropagationSpecification;

/**
 * An interface for {@link AbstractRepoTest} and {@link AbstractRepoSettings} to
 * avoid implementing trivial getter methods below in both.
 * <br><br>
 * Getters below return things that are required by {@link AbstractCITest}, although
 * some of them are re-named to circumvent visibility issues.
 * <br><br>
 * <b>Make sure to override each getter. Otherwise it will result in an endless loop
 * of recursive calls.</b>
 * 
 * @author atora
 */
public interface HasRepoSettings {
	public default String getCommitHash(Object commitHashIdentifier) {
		return this.getRepoSettings().getCommitHash(commitHashIdentifier);
	}
	public default String getTestType() {
		return this.getRepoSettings().getTestType();
	}
	public default String getRepositoryAddress() {
		return this.getRepoSettings().getRepositoryAddress();
	}
	public default String getSettingsAddress() {
		return this.getRepoSettings().getSettingsAddress();
	}
	public default ChangePropagationSpecification getJavaPCMSpec() {
		return this.getRepoSettings().getJavaPCMSpec();
	}
	public default ComponentDetectionStrategy getComponentDetectionStrategy() {
		return this.getRepoSettings().getComponentDetectionStrategy();
	}
	
	public AbstractRepoSettings getRepoSettings();
}
