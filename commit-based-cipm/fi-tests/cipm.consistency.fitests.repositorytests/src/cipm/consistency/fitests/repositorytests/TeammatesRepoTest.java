package cipm.consistency.fitests.repositorytests;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.TestFactory;

import cipm.consistency.fitests.similarity.jamopp.parser.AbstractJaMoPPParserSimilarityTestFactory;
import cipm.consistency.fitests.similarity.jamopp.parser.EAllContentSimilarityTestFactory;

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
	protected Collection<AbstractJaMoPPParserSimilarityTestFactory> getTestFactories() {
		var res = new ArrayList<AbstractJaMoPPParserSimilarityTestFactory>();
		res.add(new EAllContentSimilarityTestFactory(this.getSCC()));
		return res;
	}

	/**
	 * Extends the super method by preparing repository clones before generating
	 * dynamic tests. <br>
	 * <br>
	 * {@inheritDoc}
	 */
	@TestFactory
	@Override
	public Collection<DynamicNode> createTests() {
		this.prepareLocalRepoClones();
		return super.createTests();
	}
}
