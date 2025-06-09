package cipm.consistency.fitests.repositorytests;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.TestFactory;

import cipm.consistency.fitests.similarity.jamopp.parser.AbstractJaMoPPParserSimilarityTestFactory;
import cipm.consistency.fitests.similarity.jamopp.parser.EAllContentSimilarityTestFactory;

public class MinimalRepoTest extends AbstractJaMoPPParserRepoTest {
	private static final List<String> commitIDs = List.of("364155e24b21122e980cad73da2d0b09c1c994aa",
			"9eda56eaa52c2d5e47aade7a688e9ebda2645bde", "21ae1643905898a29470157de076942a7be05698",
			"9ab81979f64539442b9dc5857b65d696918e4732");

	@Override
	protected List<String> getCommitIDs() {
		return commitIDs;
	}

	@Override
	protected URI getRepoURI() {
		return URI.createFileURI("C:\\Users\\sdq-l\\OneDrive\\Desktop\\parserTestRepo");
	}

	@Override
	protected Collection<AbstractJaMoPPParserSimilarityTestFactory> getTestFactories() {
		var res = new ArrayList<AbstractJaMoPPParserSimilarityTestFactory>();
		res.add(new EAllContentSimilarityTestFactory(this.getSCC()) {
			@Override
			public boolean getExpectedResultFor(Resource res1, Path path1, Resource res2, Path path2) {
				return res1 == res2;
			}
		});
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
