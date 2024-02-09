package cipm.consistency.vsum.test;

import java.io.File;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;

import cipm.consistency.commitintegration.JavaParserAndPropagatorUtils;
import cipm.consistency.commitintegration.detection.TEAMMATESComponentDetectionStrategy;
import cipm.consistency.cpr.javapcm.teammates.TeammatesJavaPCMChangePropagationSpecification;
import tools.vitruv.framework.propagation.ChangePropagationSpecification;

public abstract class AbstractTEAMMATESCITest extends AbstractCITest {
	private static final Logger LOGGER = Logger.getLogger("cipm." + AbstractTEAMMATESCITest.class.getSimpleName());
	
	private final String commitTag_v800RC0 = "648425746bb9434051647c8266dfab50a8f2d6a3";
	private final String[] commitHashes = {
		commitTag_v800RC0,
		"48b67bae03babf5a5e578aefce47f0285e8de8b4", 
		"83f518e279807dc7eb7023d008a4d1ab290fefee",
		"f33d0bcd5843678b832efd8ee2963e72a95ecfc9",
		"ce4463a8741840fd25a41b14801eab9193c7ed18"
	};
	// This version is the next one after the last commit in commitHashes.
	private final String commitTag_v800RC2 = "8a97db611be37ae1975715723e1913de4fd675e8";
	
	@BeforeAll
	public static void setUpForAll() {
		JavaParserAndPropagatorUtils.setConfiguration(new JavaParserAndPropagatorUtils.Configuration(false,
				new TEAMMATESComponentDetectionStrategy()));
	}
	
	protected String getCommitHash(int index) {
		return this.commitHashes[index];
	}
	
	protected String getVersionAfterCommitHashes() {
		return this.commitTag_v800RC2;
	}
	
	protected String getTestName() {
		return "TeammatesTest";
	}

	@Override
	protected String getRepositoryPath() {
		return "file:///C:/Users/atora/OneDrive/Belgeler/GitHub/teammates";
	}

	@Override
	protected String getSettingsPath() {
		return "teammates-exec-files" + File.separator + "settings.properties";
	}
	
	@Override
	protected ChangePropagationSpecification getJavaPCMSpecification() {
		return new TeammatesJavaPCMChangePropagationSpecification();
	}
}
