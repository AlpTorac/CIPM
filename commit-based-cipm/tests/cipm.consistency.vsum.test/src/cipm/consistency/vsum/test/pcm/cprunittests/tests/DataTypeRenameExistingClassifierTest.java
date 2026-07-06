package cipm.consistency.vsum.test.pcm.cprunittests.tests;

import java.nio.file.Path;

public class DataTypeRenameExistingClassifierTest extends PCMJavaCPRTest {
	@Override
	public Path getTestRootDirName() {
		return Path.of("pcmjava-testmodels", "datatype", "renameExistingClassifier");
	}

	@Override
	public CRSConfig getCRSs() {
		return new CRSConfig();
	}
}
