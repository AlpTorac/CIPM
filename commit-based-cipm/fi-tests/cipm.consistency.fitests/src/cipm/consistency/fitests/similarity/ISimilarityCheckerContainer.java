package cipm.consistency.fitests.similarity;

import java.util.Collection;

/**
 * An interface meant to be implemented by classes that store the similarity
 * checker under test, in order to spare other test classes the need to add that
 * similarity checker as a dependency. <br>
 * <br>
 * This interface contains dependencies to neither similarity checker interfaces
 * nor to concrete implementations, because doing so would reduce the
 * re-usability. <br>
 * <br>
 * Keep in mind that reseting the underlying similarity checker has to be done
 * manually. Otherwise the same similarity checker will be used in similarity
 * checking.
 * 
 * @author Alp Torac Genc
 */
public interface ISimilarityCheckerContainer {
	/**
	 * Replaces the currently stored similarity checker with a new one. <br>
	 * <br>
	 * The similarity checker is not automatically re-created upon calling
	 * similarity checking methods, because it might be desirable to keep using the
	 * same similarity checker.
	 */
	public void resetSimilarityChecker();

	/**
	 * Delegates similarity checking to the stored similarity checker
	 */
	public Boolean isSimilar(Object element1, Object element2);

	/**
	 * Delegates similarity checking to the stored similarity checker
	 */
	public Boolean areSimilar(Collection<?> elements1, Collection<?> elements2);
}
