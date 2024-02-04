package cipm.consistency.vsum.test;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;

import cipm.consistency.commitintegration.JavaParserAndPropagatorUtils;
import tools.vitruv.framework.propagation.ChangePropagationSpecification;

public abstract class AbstractRepoTest extends AbstractCITest implements HasRepoSettings {
	protected AbstractRepoSettings repoSettings;
	
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
	public AbstractRepoSettings getRepoSettings() {
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
	
	protected void setJavaParserAndPropagatorUtilsConfig() {
		var compDetectionStrategy = this.getComponentDetectionStrategy();
		
		if (compDetectionStrategy != null) {
			JavaParserAndPropagatorUtils.setConfiguration(new JavaParserAndPropagatorUtils.Configuration(
					this.getJavaParserAndPropagatorUtilsResolveAll(),
					compDetectionStrategy));
		}
	}
	
	protected boolean getJavaParserAndPropagatorUtilsResolveAll() {
		return false;
	}
	
	protected abstract AbstractRepoSettings initRepoSettings();
	
}
