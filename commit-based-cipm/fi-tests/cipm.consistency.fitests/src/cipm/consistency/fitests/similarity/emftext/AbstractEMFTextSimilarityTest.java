package cipm.consistency.fitests.similarity.emftext;

import java.io.File;

import cipm.consistency.fitests.similarity.ISimilarityCheckerContainer;
import cipm.consistency.fitests.similarity.base.JavaSimilarityCheckerProvider;
import cipm.consistency.fitests.similarity.base.SimilarityCheckerContainerWithProvider;
import cipm.consistency.fitests.similarity.emftext.params.EMFTextInitialiserParameters;
import cipm.consistency.fitests.similarity.emftext.params.EMFTextSimilarityValues;
import cipm.consistency.fitests.similarity.eobject.AbstractEObjectSimilarityTest;
import cipm.consistency.fitests.similarity.params.InitialiserTestSettingsProvider;

/**
 * The abstract test class that contains test elements needed in similarity
 * checking tests.
 * 
 * @author atora
 * @see {@link AbstractEObjectSimilarityTest}
 */
public abstract class AbstractEMFTextSimilarityTest extends AbstractEObjectSimilarityTest {
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
		InitialiserTestSettingsProvider.getInstance().setParameters(new EMFTextInitialiserParameters());
		InitialiserTestSettingsProvider.getInstance().setSimilarityValues(new EMFTextSimilarityValues());
	}
}
