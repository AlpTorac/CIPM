package cipm.consistency.fitests.similarity.eobject.java;

import java.io.File;

import org.eclipse.emf.ecore.resource.Resource;

import cipm.consistency.fitests.similarity.ISimilarityCheckerContainer;
import cipm.consistency.fitests.similarity.SimilarityCheckerContainer;
import cipm.consistency.fitests.similarity.eobject.AbstractEObjectSimilarityTest;
import cipm.consistency.fitests.similarity.eobject.java.params.EObjectInitialiserParameters;
import cipm.consistency.fitests.similarity.eobject.java.params.EObjectSimilarityValues;
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
	 * Creates the concrete {@link ISimilarityCheckerContainer} that will be used to
	 * store the {@link ISimilarityChecker} under test. <br>
	 * <br>
	 * If necessary, it can be overridden in tests to change the said similarity
	 * checker during set up.
	 */
	protected ISimilarityCheckerContainer initSCC() {
		var scc = new SimilarityCheckerContainer();
		scc.setSimilarityCheckerProvider(new JavaSimilarityCheckerProvider());
		return scc;
	}

	/**
	 * @return The absolute path, under which the {@link Resource} files will be
	 *         saved.
	 */
	public String getAbsoluteResourceRootPath() {
		return new File("").getAbsoluteFile().getAbsolutePath() + File.separator + "testModels";
	}

	/**
	 * @return The extension of the {@link Resource} files, if they are saved.
	 */
	public String getResourceFileExtension() {
		return "javaxmi";
	}

	protected void setupInitialiserTestSettingsProvider() {
		InitialiserTestSettingsProvider.getInstance().setParameters(new EObjectInitialiserParameters());
		InitialiserTestSettingsProvider.getInstance().setSimilarityValues(new EObjectSimilarityValues());
	}
}
