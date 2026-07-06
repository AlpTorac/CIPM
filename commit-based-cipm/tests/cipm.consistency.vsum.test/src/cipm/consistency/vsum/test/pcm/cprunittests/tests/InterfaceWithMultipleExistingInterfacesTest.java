package cipm.consistency.vsum.test.pcm.cprunittests.tests;

import java.nio.file.Path;
import java.util.List;

public class InterfaceWithMultipleExistingInterfacesTest extends PCMJavaCPRTest {
	@Override
	protected Path getTestRootDirName() {
		return Path.of("pcmjava-testmodels", "interface", "withMultipleExistingInterfaces");
	}

	@Override
	protected CRSConfig getCRSs() {
		var config = new CRSConfig();
		// Oracle CRS that looks up correspondences from target correspondence model, in
		// order to fully automate the test case
		config.setCRSFacs(List
				.of(() -> new CorrespondenceConflictResolutionStrategy(config.getWrapper().getTargetCorrespondences(),
						config.getWrapper().getPropagatedJavaModel())));
		return config;
	}

}
