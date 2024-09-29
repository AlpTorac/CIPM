package cipm.consistency.fitests.similarity.emftext;

import java.io.File;
import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import cipm.consistency.fitests.similarity.ISimilarityCheckerContainer;
import cipm.consistency.fitests.similarity.base.JavaSimilarityCheckerProvider;
import cipm.consistency.fitests.similarity.base.SimilarityCheckerContainerWithProvider;
import cipm.consistency.fitests.similarity.emftext.params.EMFTextInitialiserParameters;
import cipm.consistency.fitests.similarity.emftext.params.EMFTextSimilarityValues;
import cipm.consistency.fitests.similarity.eobject.AbstractEObjectSimilarityTest;
import cipm.consistency.fitests.similarity.params.IInitialiserParameters;
import cipm.consistency.fitests.similarity.params.InitialiserTestSettingsProvider;
import cipm.consistency.initialisers.IInitialiserBase;
import cipm.consistency.initialisers.emftext.IEMFTextEObjectInitialiser;

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

	/**
	 * @return The {@link InitialiserTestSettingsProvider} that will be used in
	 *         tests. Initialises the instance to be returned, if not properly
	 *         initialised.
	 */
	public static InitialiserTestSettingsProvider getClassesInitialiserTestSettingsProvider() {
		var instance = InitialiserTestSettingsProvider.getInstance();

		if (instance == null) {
			InitialiserTestSettingsProvider.initialise();
			instance = InitialiserTestSettingsProvider.getInstance();
		}

		if (instance.getParameters() == null) {
			instance.setParameters(new EMFTextInitialiserParameters());
		}

		if (instance.getSimilarityValues() == null) {
			instance.setSimilarityValues(new EMFTextSimilarityValues());
		}

		return instance;
	}

	@Override
	public InitialiserTestSettingsProvider getInitialiserTestSettingsProvider() {
		return getClassesInitialiserTestSettingsProvider();
	}

	/**
	 * @see {@link IInitialiserParameters#getEachInitialiserOnceBySuper(Class)}
	 */
	public static Stream<Arguments> getEachInitialiserArgumentsOnceFor(
			Class<? extends IEMFTextEObjectInitialiser> superType) {
		return getClassesInitialiserTestSettingsProvider().getParameters().getEachInitialiserOnceBySuper(superType)
				.stream().map((i) -> Arguments.of(i));
	}

	/**
	 * @see {@link IInitialiserParameters#getAllInitialisersBySuper(Class)}
	 */
	public static Stream<Arguments> getAllInitialiserArgumentsFor(
			Class<? extends IEMFTextEObjectInitialiser> superType) {
		return getClassesInitialiserTestSettingsProvider().getParameters().getAllInitialisersBySuper(superType).stream()
				.map((i) -> Arguments.of(i));
	}

	/**
	 * @see {@link IInitialiserParameters#getAdaptedInitialisersBySuper(Class)}
	 */
	public static <T extends IEMFTextEObjectInitialiser & IInitialiserBase> Stream<Arguments> getAdaptedInitialiserArgumentsFor(
			Class<T> superType) {
		return getClassesInitialiserTestSettingsProvider().getParameters().getAdaptedInitialisersBySuper(superType)
				.stream().map((i) -> Arguments.of(i));
	}

	/**
	 * @see {@link IInitialiserParameters#getNonAdaptedInitialisersBySuper(Class)}
	 */
	public static Stream<Arguments> getNonAdaptedInitialiserArgumentsFor(
			Class<? extends IEMFTextEObjectInitialiser> superType) {
		return getClassesInitialiserTestSettingsProvider().getParameters().getNonAdaptedInitialisersBySuper(superType)
				.stream().map((i) -> Arguments.of(i));
	}
}
