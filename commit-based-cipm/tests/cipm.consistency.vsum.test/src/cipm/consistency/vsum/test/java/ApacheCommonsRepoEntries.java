package cipm.consistency.vsum.test.java;

import java.util.HashMap;
import java.util.Map;

public class ApacheCommonsRepoEntries {
	public static class RepoEntry {
		public final String repoId;
		public final String remoteRepoURI;
		public final String commitId;

		private RepoEntry(String repoId, String remoteRepoURI, String commitId) {
			this.repoId = repoId;
			this.remoteRepoURI = remoteRepoURI;
			this.commitId = commitId;
		}
	}

	private static final String COMMONS_CSV_ID = "commons-csv";
	private static final String COMMONS_CSV_URL = "https://github.com/apache/" + COMMONS_CSV_ID;

	private static final String COMMONS_EXEC_ID = "commons-exec";
	private static final String COMMONS_EXEC_URL = "https://github.com/apache/" + COMMONS_EXEC_ID;

	private static final String COMMONS_CLI_ID = "commons-cli";
	private static final String COMMONS_CLI_URL = "https://github.com/apache/" + COMMONS_CLI_ID;

	private static final String COMMONS_STATISTICS_ID = "commons-statistics";
	private static final String COMMONS_STATISTICS_URL = "https://github.com/apache/" + COMMONS_STATISTICS_ID;

	private static final String COMMONS_BCEL_ID = "commons-bcel";
	private static final String COMMONS_BCEL_URL = "https://github.com/apache/" + COMMONS_BCEL_ID;

	private static Map<String, RepoEntry> getTestCaseMap(String csvCommit, String execCommit, String cliCommit,
			String statisticsCommit, String bcelCommit) {
		var map = new HashMap<String, RepoEntry>();

		if (csvCommit != null)
			map.put(COMMONS_CSV_ID, new RepoEntry(COMMONS_CSV_ID, COMMONS_CSV_URL, csvCommit));

		if (execCommit != null)
			map.put(COMMONS_EXEC_ID, new RepoEntry(COMMONS_EXEC_ID, COMMONS_EXEC_URL, execCommit));

		if (cliCommit != null)
			map.put(COMMONS_CLI_ID, new RepoEntry(COMMONS_CLI_ID, COMMONS_CLI_URL, cliCommit));

		if (statisticsCommit != null)
			map.put(COMMONS_STATISTICS_ID,
					new RepoEntry(COMMONS_STATISTICS_ID, COMMONS_STATISTICS_URL, statisticsCommit));

		if (bcelCommit != null)
			map.put(COMMONS_BCEL_ID, new RepoEntry(COMMONS_BCEL_ID, COMMONS_BCEL_URL, bcelCommit));
		return map;
	}

	public static Map<String, RepoEntry> getCaseVitruvTestCase() {
		// "rel/commons-csv-1.14.1"
		// "rel/commons-exec-1.6.0"
		// "commons-cli-1.11.0-RC1 rel/commons-cli-1.11.0"
		// "commons-statistics-1.3-RC1 rel/commons-statistics-1.3"
		return getTestCaseMap("e14ef8", "3ee697", "d74613", "2937eb", null);
	}

	/**
	 * @return A submodule configuration, where only one submodule changes w.r.t
	 *         {@link #getCaseVitruvTestCase()}
	 */
	public static Map<String, RepoEntry> getMinimalPropagationTestCase() {
		return getTestCaseMap("c3844a2", "3ee697", "d74613", "2937eb", null);
	}

	public static Map<String, RepoEntry> getPracticeTestCase6() {
		return getTestCaseMap("c3844a2", "92d9943", "c9e543d", "d390942", "1fcbc87");
	}

	/**
	 * Large test case, there are 1238 source files and 0 classpath entries
	 */
	public static Map<String, RepoEntry> getPracticeTestCase7() {
		return getTestCaseMap("d9b9f06", "92d9943", "0a68ae0", "d390942", "1fcbc87");
	}
}