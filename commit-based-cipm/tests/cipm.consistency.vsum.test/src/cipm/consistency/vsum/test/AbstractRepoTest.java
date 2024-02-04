package cipm.consistency.vsum.test;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;

import cipm.consistency.commitintegration.JavaParserAndPropagatorUtils;
import tools.vitruv.framework.propagation.ChangePropagationSpecification;

public abstract class AbstractRepoTest extends AbstractCITest implements HasRepoSettings {
	protected HasRepoSettings repoSettings;

	@BeforeEach
	@Override
	public void setUp() throws Exception {
		this.repoSettings = this.initRepoSettings();
		this.setJavaParserAndPropagatorUtilsConfig();
		super.setUp();
	}

	@Override
	protected ChangePropagationSpecification getJavaPCMSpecification() {
		return this.getJavaPCMSpec();
	}

	@Override
	public HasRepoSettings getRepoSettings() {
		return this.repoSettings;
	}

	@Override
	protected String getTestPath() {
		return "target" + File.separator + this.getTestType();
	}

	@Override
	protected String getRepositoryPath() {
		return this.getRepositoryAddress();
	}

	@Override
	protected String getSettingsPath() {
		return this.getSettingsAddress();
	}

	@Override
	protected String getTestType() {
		return this.getTestGroup();
	}

	/**
	 * Sets {@link JavaParserAndPropagatorUtils} configuration, if necessary.
	 */
	protected void setJavaParserAndPropagatorUtilsConfig() {
		var compDetectionStrategy = this.getComponentDetectionStrategy();

		if (compDetectionStrategy != null) {
			JavaParserAndPropagatorUtils.setConfiguration(new JavaParserAndPropagatorUtils.Configuration(
					this.getJavaParserAndPropagatorUtilsResolveAll(), compDetectionStrategy));
		}
	}

	/**
	 * @return resolveAll parameter of
	 *         {@link JavaParserAndPropagatorUtils.Configuration#Configuration(boolean, cipm.consistency.commitintegration.detection.ComponentDetectionStrategy...)}
	 */
	protected boolean getJavaParserAndPropagatorUtilsResolveAll() {
		return false;
	}

	/**
	 * @return The {@link HasRepoSettings} instance to be used during unit tests
	 */
	protected abstract HasRepoSettings initRepoSettings();
}
