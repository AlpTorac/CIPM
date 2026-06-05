package cipm.consistency.cpr.pcmjava.preprocessing;

import org.eclipse.emf.ecore.EStructuralFeature;

public abstract class IDAdjustingStrategy {
	private static final String cachedEObjectURIPrefix = "cache:/";
	private static final String cachedEObjectURI = cachedEObjectURIPrefix + 0;

	public abstract String setAsValueOfFeature(String currentID, EStructuralFeature toBeContainingSingleValuedFeature);

	/**
	 * 
	 * @param currentID
	 * @param toBeContainingManyValuedFeature
	 * @param indexValue                      The value of the index
	 * @return
	 */
	public abstract String insertIntoFeature(String currentID, EStructuralFeature toBeContainingManyValuedFeature,
			int indexValue);

	/**
	 * @param currentID  The ID to be adjusted
	 * @param indexValue The value of the index
	 * @return
	 */
	public abstract String addIndex(String currentID, int indexValue);

	/**
	 * @param currentID  The ID to be adjusted
	 * @param newIndices The new indices within the currentID in order
	 * @return The adjusted ID
	 */
	public abstract String adjustIndicesOf(String currentID, int[] newIndices);

	/**
	 * @param currentID   The ID to be adjusted
	 * @param indexNumber The order of the index (1 is the first index from the
	 *                    root, i.e. the root index), which will be set to newIndex
	 * @param newIndex    The new value of the index
	 * @return The adjusted ID
	 */
	public abstract String adjustIndexOf(String currentID, int indexNumber, int newIndex);

	/**
	 * Added as a convenience method to {@link #adjustIndexOf(String, int, int)}
	 * 
	 * @param currentID    The ID to be adjusted (the ID of the containing Resource
	 *                     instance in this case)
	 * @param newRootIndex The new value of the root index
	 * @return The adjusted ID
	 */
	public String adjustRootIndex(String currentID, int newRootIndex) {
		return adjustIndexOf(currentID, 1, newRootIndex);
	}

	public String moveToStagedArea(String currentID) {
		return cachedEObjectURI;
	}

	public String deleteFromStagedArea(String currentID) {
		return "";
	}

	public String addToResource(String resourceURI, int rootIndex) {
		return resourceURI + "#/" + rootIndex;
	}
}
