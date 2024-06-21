package cipm.consistency.fitests.similarity.java.params;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public interface ISimilarityValues {
	public void addSimilarityEntry(EStructuralFeature attr, Boolean expectedSimResult);
	public void addSimilarityEntry(Class<? extends EObject> objCls, EStructuralFeature attr, Boolean breaksSimilarity);
	public void removeSimilarityEntry(Class<? extends EObject> objCls, EStructuralFeature attr);
	public void clear();
	
	/**
	 * @return True, if a difference in attr does not break similarity;
	 * False if a difference in attr does break similarity;
	 * Null if unspecified.
	 */
	public Boolean getExpectedSimilarityResult(Class<? extends EObject> objCls, EStructuralFeature attr);
	public Boolean getExpectedSimilarityResult(EStructuralFeature attr);
	
	public void setDefaultSimilarityResult(Boolean defSimRes);
	public Boolean getDefaultSimilarityResult();
}
