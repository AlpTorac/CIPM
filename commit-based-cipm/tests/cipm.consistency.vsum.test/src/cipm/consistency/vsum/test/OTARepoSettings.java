package cipm.consistency.vsum.test;

import java.io.File;

import cipm.consistency.commitintegration.detection.ComponentDetectionStrategy;
import cipm.consistency.commitintegration.detection.ModuleState;
import cipm.consistency.commitintegration.detection.PackageBasedComponentDetectionStrategy;
import cipm.consistency.cpr.javapcm.teammates.TeammatesJavaPCMChangePropagationSpecification;
import tools.vitruv.framework.propagation.ChangePropagationSpecification;

public class OTARepoSettings implements HasRepoSettings {
	private static final String[] COMMIT_HASHES = {
			"1c04e3c8a7674cc67dd18d91eeae443a0424c2ea",
			"b5e4adbdcbaeb5f988ef5ec11ecd098eac926d6e",
			"3e92f23bf3dbf7f4591f8ff0d417249aeb4f9b50",
			"17757a38a9aa3b28a1021af5d2d7f81f77091d7d",
			"e5bff503727f05b9505d631cb91c4b11af363132",
			"efe456673d51f96880291ba73a47c2d904c5afd7"
		};
	
	/**
	 * @param commitHashIdentifier Index of the commit hash in {@link #COMMIT_HASHES} as int
	 * (indexing starts with 0)
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public String getCommitHash(Object commitHashIdentifier) {
		return COMMIT_HASHES[(int) commitHashIdentifier];
	}

	@Override
	public String getTestGroup() {
		return "OTATest";
	}

	@Override
	public String getRepositoryAddress() {
		return "file:///C:/Users/atora/OneDrive/Belgeler/GitHub/OTA";
	}

	@Override
	public String getSettingsAddress() {
		return this.getExecFilesAddress() + File.separator + "settings.properties";
	}
	
	@Override
	public String getExecFilesAddress() {
		return "ota-exec-files";
	}

	@Override
	public ChangePropagationSpecification getJavaPCMSpec() {
		return new TeammatesJavaPCMChangePropagationSpecification();
	}

	@Override
	public ComponentDetectionStrategy[] getComponentDetectionStrategy() {
		return new ComponentDetectionStrategy[] {new PackageBasedComponentDetectionStrategy() {
			@Override
			protected void initializeMappings() {
				this.addPackageModuleMapping("gatling-gradle.*?", "gatling-gradle", ModuleState.MICROSERVICE_COMPONENT);
				this.addPackageModuleMapping("ota-tester-with-broker.*?", "ota-tester-with-broker", ModuleState.MICROSERVICE_COMPONENT);
			}
		}};
	}
	
	@Override
	public HasRepoSettings getRepoSettings() {
		return this;
	}
}
