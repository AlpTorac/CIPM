package cipm.consistency.vsum.test.pcm.cprunittests.tests;

import java.nio.file.Path;

public class DataTypeWithExistingClassifierTest extends PCMJavaCPRTest {
	@Override
	public Path getTestRootDirName() {
		return Path.of("pcmjava-testmodels", "datatype", "withExistingClassifier");
	}

	@Override
	public CRSConfig getCRSs() {
		return new CRSConfig();
	}
}
