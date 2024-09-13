package cipm.consistency.fitests.similarity;

import java.util.Collection;

/**
 * An interface meant to be implemented by classes that store the
 * {@link ISimilarityChecker} under test, in order to spare other test classes
 * the need to add {@link ISimilarityChecker} as a dependency.
 * 
 * @author atora
 */
public interface ISimilarityCheckerContainer {
	/**
	 * Sets the {@link ISimilarityCheckerProvider} that will be used to create the
	 * stored {@link ISimilarityChecker}.
	 */
	public void setSimilarityCheckerProvider(ISimilarityCheckerProvider scp);

	/**
	 * @return The {@link ISimilarityCheckerProvider} that will be used to create
	 *         the stored {@link ISimilarityChecker}.
	 */
	public ISimilarityCheckerProvider getSimilarityCheckerProvider();

	/**
	 * Replaces the currently stored similarity checker with a new one created using
	 * the {@link #getSimilarityCheckerProvider()}. <br>
	 * <br>
	 * The similarity checker is not automatically re-created upon calling
	 * similarity checking methods, because it might be desirable to keep using the
	 * same similarity checker.
	 */
	public void resetSimilarityChecker();

	/**
	 * Delegates similarity checking
	 */
	public Boolean isSimilar(Object element1, Object element2);

	/**
	 * Delegates similarity checking
	 */
	public Boolean areSimilar(Collection<?> elements1, Collection<?> elements2);
}
