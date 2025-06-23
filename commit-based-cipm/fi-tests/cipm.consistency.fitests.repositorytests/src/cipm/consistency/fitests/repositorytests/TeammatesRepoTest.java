package cipm.consistency.fitests.repositorytests;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.TestFactory;

import cipm.consistency.fitests.similarity.jamopp.parser.AbstractJaMoPPParserSimilarityTestFactory;
import cipm.consistency.fitests.similarity.jamopp.parser.EAllContentSimilarityTestFactory;

public class TeammatesRepoTest extends AbstractJaMoPPParserRepoTest {
	private static final List<String> commitIDs = List.of("648425746bb9434051647c8266dfab50a8f2d6a3",
			"48b67bae03babf5a5e578aefce47f0285e8de8b4");

	@Override
	protected List<String> getCommitIDs() {
		return commitIDs;
	}

	@Override
	protected URI getRepoURI() {
		return URI.createURI("https://github.com/TEAMMATES/teammates");
	}

	@Override
	protected Collection<AbstractJaMoPPParserSimilarityTestFactory> getTestFactories() {
		var res = new ArrayList<AbstractJaMoPPParserSimilarityTestFactory>();
		res.add(new EAllContentSimilarityTestFactory(this.getSCC()));
		res.forEach((tf) -> tf.setExpectedSimilarityResultProvider(getExpectedSimilarityResultProviderForCommits()));
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
		var resArr = this.cacheCommitResources().toArray(Resource[]::new);
		return super.createTests(resArr);
	}
}
