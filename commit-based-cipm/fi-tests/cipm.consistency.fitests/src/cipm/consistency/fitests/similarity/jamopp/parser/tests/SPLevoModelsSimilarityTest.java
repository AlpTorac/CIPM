package cipm.consistency.fitests.similarity.jamopp.parser.tests;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.ecore.resource.Resource;

import cipm.consistency.fitests.similarity.jamopp.parser.AbstractJaMoPPParserSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.parser.AbstractJaMoPPParserSimilarityTestFactory;
import cipm.consistency.fitests.similarity.jamopp.parser.EAllContentSimilarityTestFactory;
import cipm.consistency.fitests.similarity.jamopp.parser.RecursiveEAllContentSimilarityTestFactory;

/**
 * A test class that attempts to parse and check similarity of {@link Resource}
 * files.
 * 
 * @author Alp Torac Genc
 */
public class SPLevoModelsSimilarityTest extends AbstractJaMoPPParserSimilarityTest {
	/**
	 * The name of the root directory of the models from SPLevo
	 */
	private static final String splevoModelImplDirName = "splevo-testmodels";

	/**
	 * The first model to parse.
	 */
	private static final String model1Name = "a";
	/**
	 * The second model to parse.
	 */
	private static final String model2Name = "b";

	@Override
	protected Path getRootDirPath() {
		return super.getRootDirPath().resolve(splevoModelImplDirName);
	}

	@Override
	protected boolean isModelDirectoryName(String s) {
		return s.equals(model1Name) || s.equals(model2Name);
	}

	/**
	 * {@inheritDoc} <br>
	 * <br>
	 * Additionally checks whether the given path contains SPLevo test model folder.
	 */
	@Override
	protected boolean isResourceRelevant(Path path, Resource r) {
		return super.isResourceRelevant(path, r) && path.toString().contains(splevoModelImplDirName);
	}

	@Override
	protected Collection<AbstractJaMoPPParserSimilarityTestFactory> getTestFactories() {
		var res = new ArrayList<AbstractJaMoPPParserSimilarityTestFactory>();
		res.add(new EAllContentSimilarityTestFactory(this.getSCC()));
		res.add(new RecursiveEAllContentSimilarityTestFactory(this.getSCC()));
		return res;
	}
}
