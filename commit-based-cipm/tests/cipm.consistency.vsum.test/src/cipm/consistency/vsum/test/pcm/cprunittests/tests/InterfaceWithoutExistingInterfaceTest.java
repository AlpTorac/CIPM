package cipm.consistency.vsum.test.pcm.cprunittests.tests;

import java.nio.file.Path;
import java.util.List;

import cipm.consistency.cpr.pcmjava.userinteraction.NamespaceConflictResolutionStrategy;

public class InterfaceWithoutExistingInterfaceTest extends PCMJavaCPRTest {
	@Override
	protected Path getTestRootDirName() {
		return Path.of("pcmjava-testmodels", "interface", "withoutExistingInterface");
	}

	@Override
	protected CRSConfig getCRSs() {
		var config = new CRSConfig();
		// Oracle CRS that looks up namespaces from target Java code model, in order to
		// fully automate the test case
		config.setCRSFacs(
				List.of(() -> new NamespaceConflictResolutionStrategy(config.getWrapper().getTargetJavaModel())));
		return config;
	}
}
