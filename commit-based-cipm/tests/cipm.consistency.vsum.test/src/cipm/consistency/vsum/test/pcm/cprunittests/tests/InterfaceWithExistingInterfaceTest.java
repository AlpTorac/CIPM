package cipm.consistency.vsum.test.pcm.cprunittests.tests;

import java.nio.file.Path;

public class InterfaceWithExistingInterfaceTest extends PCMJavaCPRTest {
	@Override
	public Path getTestRootDirName() {
		return Path.of("pcmjava-testmodels", "interface", "withExistingInterface");
	}

	@Override
	public CRSConfig getCRSs() {
		return new CRSConfig();
	}
}
