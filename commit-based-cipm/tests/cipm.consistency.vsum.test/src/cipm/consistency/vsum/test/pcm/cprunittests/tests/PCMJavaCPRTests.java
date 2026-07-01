package cipm.consistency.vsum.test.pcm.cprunittests.tests;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.stream.Stream;

import org.junit.jupiter.api.DynamicContainer;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

public class PCMJavaCPRTests {
	private static final String testRootDirName = "pcmjava-testmodels";

	private Stream<DynamicNode> discoverTests(File testDir) {
		var testList = new ArrayList<DynamicNode>();
		for (var file : testDir.listFiles()) {
			if (file.isDirectory()) {
				if (file.listFiles((FileFilter) (f) -> f.isDirectory()).length > 0) {
					// Directory with nested directories => Discover for nested tests
					testList.add(DynamicContainer.dynamicContainer(file.getPath(), discoverTests(file)));
				} else {
					// Directory without nested directories => Add the test case
					testList.add(DynamicTest.dynamicTest(file.getName(), () -> new PCMJavaTestBody(file).testBody()));
				}
			}
		}
		return testList.stream();
	}

	@TestFactory
	public Stream<DynamicNode> test() {
		return discoverTests(new File(testRootDirName));
	}
}
