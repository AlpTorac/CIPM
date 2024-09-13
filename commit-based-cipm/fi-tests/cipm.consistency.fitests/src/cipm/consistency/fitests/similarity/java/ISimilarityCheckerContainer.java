package cipm.consistency.fitests.similarity.java;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;

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
	public Boolean isSimilar(EObject element1, EObject element2);

	/**
	 * Delegates similarity checking
	 */
	public Boolean areSimilar(Collection<? extends EObject> elements1, Collection<? extends EObject> elements2);
}
