package cipm.consistency.vsum.test;

import cipm.consistency.commitintegration.JavaParserAndPropagatorUtils;
import cipm.consistency.commitintegration.detection.ComponentDetectionStrategy;
import tools.vitruv.framework.propagation.ChangePropagationSpecification;

/**
 * An interface for {@link AbstractRepoTest} to
 * avoid implementing trivial getter methods below in both.
 * <br><br>
 * Getters below return things that are required by {@link AbstractCITest}, although
 * some of them are slightly re-named to circumvent visibility issues in implementors,
 * which would have the same method signatures otherwise.
 * <br><br>
 * <b>Make sure to override each getter. Otherwise it will result in an endless loop
 * of recursive calls.</b>
 * 
 * @author atora
 */
public interface HasRepoSettings {
	/**
	 * A method that allows retrieving a commit hash from the implementor of
	 * this interface. The parameter format and requirements may change depending
	 * on the concrete implementor.
	 * 
	 * @param commitHashIdentifier A given parameter, with which the concrete
	 * implementor can identify the desired commit hash and return it back
	 * @return The desired commit hash.
	 */
	public default String getCommitHash(Object commitHashIdentifier) {
		return this.getRepoSettings().getCommitHash(commitHashIdentifier);
	}
	/**
	 * @return The name of the test group (or test type) needed by
	 * {@link AbstractCITest#getTestType()}
	 */
	public default String getTestGroup() {
		return this.getRepoSettings().getTestGroup();
	}
	/**
	 * @return The path to the repository needed by
	 * {@link AbstractCITest#getRepositoryPath()}
	 */
	public default String getRepositoryAddress() {
		return this.getRepoSettings().getRepositoryAddress();
	}
	/**
	 * @return The path to the settings needed by
	 * {@link AbstractCITest#getSettingsPath()}
	 */
	public default String getSettingsAddress() {
		return this.getRepoSettings().getSettingsAddress();
	}
	/**
	 * The underlying implementation of
	 * {@link AbstractCITest#getJavaPCMSpecification()}
	 */
	public default ChangePropagationSpecification getJavaPCMSpec() {
		return this.getRepoSettings().getJavaPCMSpec();
	}
	/**
	 * @return The component detection strategies, which may be required
	 * by {@link JavaParserAndPropagatorUtils.Configuration} in some cases
	 */
	public default ComponentDetectionStrategy[] getComponentDetectionStrategy() {
		return this.getRepoSettings().getComponentDetectionStrategy();
	}
	
	/**
	 * Implemented to access the underlying {@link HasRepoSettings} members to avoid
	 * having to unnecessarily duplicate all getter methods above in
	 * implementors.
	 */
	public HasRepoSettings getRepoSettings();
}
