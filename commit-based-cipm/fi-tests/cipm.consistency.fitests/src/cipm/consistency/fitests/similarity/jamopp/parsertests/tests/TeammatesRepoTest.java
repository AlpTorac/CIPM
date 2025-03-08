package cipm.consistency.fitests.similarity.jamopp.parsertests.tests;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.TestFactory;

import cipm.consistency.fitests.similarity.jamopp.parsertests.AbstractJaMoPPParserRepoTest;

public class TeammatesRepoTest extends AbstractJaMoPPParserRepoTest {
	/**
	 * The name of the root directory of the models
	 */
	private static final String repoName = "Teammates";

	private static final List<String> commitIDs = List.of("648425746bb9434051647c8266dfab50a8f2d6a3",
			"48b67bae03babf5a5e578aefce47f0285e8de8b4");

	@Override
	protected List<String> getCommitIDs() {
		return commitIDs;
	}

	@Override
	protected String getRepoURI() {
		return "https://github.com/TEAMMATES/teammates";
	}

	@Override
	protected String getRepoName() {
		return repoName;
	}
	
	@Override
	public Collection<DynamicNode> testAllContentsSimilarity() {
		this.prepareLocalRepoClones();
		return super.testAllContentsSimilarity();
	}

	@TestFactory
	@Override
	public Collection<DynamicNode> testSimilarityWithModelComparison() {
		this.prepareLocalRepoClones();
		return super.testSimilarityWithModelComparison();
	}
}
