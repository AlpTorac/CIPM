package cipm.consistency.vsum.test;

import java.io.File;

import cipm.consistency.commitintegration.detection.ComponentDetectionStrategy;
import cipm.consistency.commitintegration.detection.TEAMMATESComponentDetectionStrategy;
import cipm.consistency.cpr.javapcm.teammates.TeammatesJavaPCMChangePropagationSpecification;
import tools.vitruv.framework.propagation.ChangePropagationSpecification;

public class TeammatesRepoSettings extends AbstractRepoSettings {
	private static final String COMMIT_TAG_V_8_0_0_RC_0 = "648425746bb9434051647c8266dfab50a8f2d6a3";
	private static final String[] COMMIT_HASHES = {
		COMMIT_TAG_V_8_0_0_RC_0,
		"48b67bae03babf5a5e578aefce47f0285e8de8b4", 
		"83f518e279807dc7eb7023d008a4d1ab290fefee",
		"f33d0bcd5843678b832efd8ee2963e72a95ecfc9",
		"ce4463a8741840fd25a41b14801eab9193c7ed18"
	};
	// This version is the next one after the last commit in COMMIT_HASHES.
	private static final String COMMIT_TAG_V_8_0_0_RC_2 = "8a97db611be37ae1975715723e1913de4fd675e8";

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
	public String getTestType() {
		return "TeammatesTest";
	}

	@Override
	public String getRepositoryAddress() {
		return "file:///C:/Users/atora/OneDrive/Belgeler/GitHub/teammates";
	}

	@Override
	public String getSettingsAddress() {
		return "teammates-exec-files" + File.separator + "settings.properties";
	}

	@Override
	public ChangePropagationSpecification getJavaPCMSpec() {
		return new TeammatesJavaPCMChangePropagationSpecification();
	}

	@Override
	public ComponentDetectionStrategy getComponentDetectionStrategy() {
		return new TEAMMATESComponentDetectionStrategy();
	}
}
