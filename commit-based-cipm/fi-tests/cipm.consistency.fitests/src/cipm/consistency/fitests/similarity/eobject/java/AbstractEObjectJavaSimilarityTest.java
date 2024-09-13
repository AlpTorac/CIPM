package cipm.consistency.fitests.similarity.eobject.java;

import java.io.File;

import cipm.consistency.fitests.similarity.ISimilarityCheckerContainer;
import cipm.consistency.fitests.similarity.SimilarityCheckerContainerWithProvider;
import cipm.consistency.fitests.similarity.eobject.AbstractEObjectSimilarityTest;
import cipm.consistency.fitests.similarity.eobject.java.params.EObjectJavaInitialiserParameters;
import cipm.consistency.fitests.similarity.eobject.java.params.EObjectJavaSimilarityValues;
import cipm.consistency.fitests.similarity.params.InitialiserTestSettingsProvider;

/**
 * The abstract test class that contains test elements needed in similarity
 * checking tests.
 * 
 * @author atora
 * @see {@link AbstractEObjectSimilarityTest}
 */
public abstract class AbstractEObjectJavaSimilarityTest extends AbstractEObjectSimilarityTest {
	/**
	 * {@inheritDoc} <br>
	 * <br>
	 * Uses {@link JavaSimilarityCheckerProvider} to create Java similarity
	 * checkers.
	 */
	@Override
	protected ISimilarityCheckerContainer initSCC() {
		var scc = new SimilarityCheckerContainerWithProvider();
		scc.setSimilarityCheckerProvider(new JavaSimilarityCheckerProvider());
		return scc;
	}

	@Override
	public String getAbsoluteResourceRootPath() {
		return new File("").getAbsoluteFile().getAbsolutePath() + File.separator + "testModels";
	}

	@Override
	public String getResourceFileExtension() {
		return "javaxmi";
	}

	@Override
	protected void setupInitialiserTestSettingsProvider() {
		InitialiserTestSettingsProvider.getInstance().setParameters(new EObjectJavaInitialiserParameters());
		InitialiserTestSettingsProvider.getInstance().setSimilarityValues(new EObjectJavaSimilarityValues());
	}
}
