package cipm.consistency.fitests.similarity.jamopp.parser.tests;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.ecore.resource.Resource;

import cipm.consistency.fitests.similarity.jamopp.parser.AbstractJaMoPPParserSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.parser.AbstractJaMoPPParserSimilarityTestFactory;
import cipm.consistency.fitests.similarity.jamopp.parser.CombinationTestGenerationStrategy;
import cipm.consistency.fitests.similarity.jamopp.parser.EAllContentSimilarityTestFactory;
import cipm.consistency.fitests.similarity.jamopp.parser.IJaMoPPParserTestGenerationStrategy;
import cipm.consistency.fitests.similarity.jamopp.parser.ParserTestFileLayout;

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
	private static final Path splevoModelImplDirPath = Path.of("testmodels", "splevo-models");

	/**
	 * The first model to parse.
	 */
	private static final String model1Name = "a";
	/**
	 * The second model to parse.
	 */
	private static final String model2Name = "b";

	@Override
	protected ParserTestFileLayout initParserTestFileLayout() {
		var layout = super.initParserTestFileLayout();
		layout.setModelSourceFileRootDirPath(layout.getModelSourceFileRootDirPath().resolve(splevoModelImplDirPath));
		return layout;
	}

	@Override
	protected boolean isModelSourceFileDirectoryName(String s) {
		return s.equals(model1Name) || s.equals(model2Name);
	}

	@Override
	protected Collection<AbstractJaMoPPParserSimilarityTestFactory> getTestFactories() {
		var res = new ArrayList<AbstractJaMoPPParserSimilarityTestFactory>();
		res.add(new EAllContentSimilarityTestFactory(this.getSCC()));
		return res;
	}

	@Override
	protected Collection<IJaMoPPParserTestGenerationStrategy> getTestGenerationStrategies() {
		var strats = new ArrayList<IJaMoPPParserTestGenerationStrategy>();
		strats.add(new CombinationTestGenerationStrategy());
		return strats;
	}
}
