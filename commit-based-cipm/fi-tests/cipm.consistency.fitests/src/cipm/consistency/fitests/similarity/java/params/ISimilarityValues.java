package cipm.consistency.fitests.similarity.java.params;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public interface ISimilarityValues {
	/**
	 * If no direct match is found, an entry for super classes/interfaces will be sought
	 * and returned.
	 */
	public void addSimilarityEntry(EStructuralFeature attr, Boolean expectedSimResult);
	/**
	 * If no direct match is found, an entry for super classes/interfaces will be sought
	 * and returned.
	 */
	public void addSimilarityEntry(Class<? extends EObject> objCls, EStructuralFeature attr, Boolean breaksSimilarity);
	public void removeSimilarityEntry(Class<? extends EObject> objCls, EStructuralFeature attr);
	public void clear();
	
	/**
	 * @return True, if a difference in attr does not break similarity;
	 * False if a difference in attr does break similarity;
	 * Null if unspecified.
	 */
	public Boolean getExpectedSimilarityResult(Class<? extends EObject> objCls, EStructuralFeature attr);
	/**
	 * @return True, if a difference in attr does not break similarity;
	 * False if a difference in attr does break similarity;
	 * Null if unspecified.
	 */
	public Boolean getExpectedSimilarityResult(EStructuralFeature attr);
	
	/**
	 * Sets the default return value for {@link #getExpectedSimilarityResult(Class, EStructuralFeature)}
	 * , in cases where no entries for a given (objCls, attr) pair is found. 
	 * 
	 * @param defSimRes The default return value for {@link #getExpectedSimilarityResult(Class, EStructuralFeature)}
	 */
	public void setDefaultSimilarityResult(Boolean defSimRes);
	/**
	 * @return The default return value for {@link #getExpectedSimilarityResult(Class, EStructuralFeature)}
	 */
	public Boolean getDefaultSimilarityResult();
}
