package cipm.consistency.vsum.test.pcm.cprunittests.tests;

import java.io.File;
import java.io.FileFilter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.stream.Stream;

import org.apache.log4j.Level;
import org.junit.jupiter.api.DynamicContainer;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import cipm.consistency.vsum.test.appspace.LoggingSetup;

/**
 * The abstract test class that the concrete CPR tests should extend.
 * 
 * @author Alp Torac Genc
 */
public abstract class PCMJavaCPRTest {
	/**
	 * Recursively discovers test cases.
	 * 
	 * @param testDir The root, from which onward the test case discovery will be
	 *                recursively carried out
	 * @return The test cases
	 */
	protected Stream<DynamicNode> discoverTests(File testDir) {
		var testList = new ArrayList<DynamicNode>();

		if (testDir.isDirectory()) {
			var dirs = testDir.listFiles((FileFilter) (f) -> f.isDirectory());
			if (dirs.length > 0) {
				// Directory with nested directories => Recursively discover for nested tests
				for (var dir : dirs)
					testList.add(DynamicContainer.dynamicContainer(testDir.getPath(), discoverTests(dir)));
			} else {
				// Directory without nested directories => Add the test case
				testList.add(DynamicTest.dynamicTest(testDir.getName(),
						() -> new PCMJavaTestBody(getCRSs(), testDir).testBody()));
			}
		}

		return testList.stream();
	}

	/**
	 * The main method of this test class.
	 * 
	 * @return The test cases
	 */
	@TestFactory
	public Stream<DynamicNode> test() {
		LoggingSetup.setMinLogLevel(Level.DEBUG);
		return discoverTests(getTestRootDirName().toFile());
	}

	/**
	 * @return The path to the root directory, where the tests are located. Can be
	 *         absolute or relative.
	 */
	protected abstract Path getTestRootDirName();

	/**
	 * @return The means to construct the necessary
	 *         {@link ConflictResolutionStrategy} (CRS) instances.
	 */
	protected abstract CRSConfig getCRSs();
}
